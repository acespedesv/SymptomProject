package com.project.symptoms.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.project.symptoms.R;
import com.project.symptoms.activity.MainActivity;

import java.util.concurrent.TimeUnit;


public class NotificationWrapper {
    private Context context;
    private final String CHANNEL_ID = "symptoms_notification_channel"; // Used in Android 8+
    private final String CHANNEL_NAME = "symptoms_notification";
    private final String CHANNEL_DESCRIPTION = "used_to_remind_unfinished_symptoms";
    private final int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_DEFAULT;

    private int ICON_RESOURCE_ID = R.drawable.ic_launcher_foreground;

    // Should be increased for each notification
    private static int NOTIFICATION_ID = 1;

    private static NotificationWrapper instance;

    public static NotificationWrapper getInstance(Context context){
        if(instance == null)
            instance = new NotificationWrapper(context);
        return instance;
    }

    private NotificationWrapper(Context context){
        this.context = context;
        createNotificationChannel();
    }

    /**
     * This is required to be called once for enabling the app to push notifications
     * in Android 8+
     */
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_IMPORTANCE);
            channel.setDescription(CHANNEL_DESCRIPTION);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Set what to do when the notification is touched
     */
    private PendingIntent createPendingIntent(){
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 0, intent, 0);
        return pendingIntent;
    }


    public void notify(String title, String content){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(ICON_RESOURCE_ID)
                .setContentIntent(createPendingIntent())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this.context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }


    /**
     * Program a worker to check if the symptom has finished yet and notify if it has not
     */
    public void startReminderFor(long symptomId){
        int frequency = getCurrentFrequency();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SymptomCheckerWorker.class)
                .setInputData(buildWorkerParameters(symptomId))
                .setInitialDelay(frequency, getCurrentTimeUnit())
                .build();
        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);

        showReminderSetToast(frequency);
    }

    private void showReminderSetToast(int frequency) {
        String format = context.getString(R.string.reminder_set_format);
        String text = String.format(format, frequency);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    private TimeUnit getCurrentTimeUnit() {
        return TimeUnit.HOURS;
    }

    // TODO: user here the frequency set in preferences
    private int getCurrentFrequency() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String key = context.getString(R.string.preference_reminder_frequency_key);
        String defaultValueString = context.getString(R.string.preference_reminder_frequency_default);
        String savedFrequencyString = prefs.getString(key, defaultValueString);
        int savedValue = Integer.parseInt(savedFrequencyString);
        return savedValue;

    }

    private Data buildWorkerParameters(long symptomId) {
        return new Data.Builder()
                .putString(SymptomCheckerWorker.MESSAGE_KEY,"The notification")
                .putLong(SymptomCheckerWorker.SYMPTOM_ID_KEY,symptomId)
                .build();
    }



}