package com.project.symptoms.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.project.symptoms.R;
import com.project.symptoms.db.controller.SymptomController;
import com.project.symptoms.db.model.SymptomModel;

public class SymptomCheckerWorker extends Worker {
    public static final String MESSAGE_KEY =  "message";
    public static final String SYMPTOM_ID_KEY =  "symptom_id";

    String notificationMessage;
    long symptomId;

    public SymptomCheckerWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.notificationMessage = workerParams.getInputData().getString(MESSAGE_KEY);
        this.symptomId = workerParams.getInputData().getLong(SYMPTOM_ID_KEY,-1);
    }

    @NonNull
    @Override
    public Result doWork() {
        if(symptomStillOpen()){
            Log.i("#","Still open");
            NotificationWrapper.getInstance(getApplicationContext()).notify(buildTitle(), buildMessageFor(symptomId));
            NotificationWrapper.getInstance(getApplicationContext()).startReminderFor(symptomId);
        }
        else{
            Log.i("#","Symptom finished");
        }

        return Result.success();

    }

    private boolean symptomStillOpen(){
        SymptomModel symptom = SymptomController.getInstance(getApplicationContext()).findById(symptomId);
        return symptom.getDuration() < 0;
    }

    private String buildMessageFor(long symptomId){
        String format = getApplicationContext().getResources().getString(R.string.symptom_reminder_format);
        return String.format(format,"X","DATE");
    }

    private String buildTitle(){
        return getApplicationContext().getResources().getString(R.string.app_name);
    }
}