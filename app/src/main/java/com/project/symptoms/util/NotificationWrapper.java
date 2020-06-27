package com.project.symptoms.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.project.symptoms.R;
import com.project.symptoms.activity.MainActivity;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
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
    public void startReminderFor(int symptomId){
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(SymptomCheckerWorker.class)
                .setInputData(buildInputData(symptomId))
                .setInitialDelay(getCurrentFrequency(), getCurrentTimeUnit())
                .build();
        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);
    }

    // TODO set this in hours
    private TimeUnit getCurrentTimeUnit() {
        return TimeUnit.SECONDS;
    }

    // TODO: user here the frequency set in preferences
    private int getCurrentFrequency() {
        return 2;
    }

    private Data buildInputData(int symptomId) {
        return new Data.Builder()
                .putString("message","The notification")
                .putInt("symptom_id",symptomId)
                .build();
    }



}
