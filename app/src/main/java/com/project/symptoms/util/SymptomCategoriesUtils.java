package com.project.symptoms.util;

import android.content.res.Resources;

import com.project.symptoms.R;

public class SymptomCategoriesUtils {

    public static String[] getCategoryNames(Resources res){
        return new String[]{res.getString(R.string.pain_type),
                res.getString(R.string.digestive_pain),
                res.getString(R.string.respiratory_pain),
                res.getString(R.string.sensory_pain),
                res.getString(R.string.emotional_pain),
                res.getString(R.string.dermatological_pain),
                res.getString(R.string.triggering_activity),
                res.getString(R.string.triggering_feeling),
                res.getString(R.string.triggering_weather_condition)};
    }

    public static String[] getPainTypeNames(Resources res){
        return new String[]{res.getString(R.string.sharp_pain),
                res.getString(R.string.throbbing_pain),
                res.getString(R.string.palpitating_pain),
                res.getString(R.string.oppressive_pain),
                res.getString(R.string.electric_pain)};
    }

    public static String[] getDigestiveTypeNames(Resources res){
        return new String[]{res.getString(R.string.sickness),
                res.getString(R.string.bad_breath),
                res.getString(R.string.heartburn),
                res.getString(R.string.sourness),
                res.getString(R.string.reflux),
                res.getString(R.string.inflammation),
                res.getString(R.string.constipation),
                res.getString(R.string.diarrhea),
                res.getString(R.string.vomit),
                res.getString(R.string.difficulty_to_swallow),
                res.getString(R.string.bleeding),
                res.getString(R.string.incontinence),
                res.getString(R.string.mouth_ulcers)};
    }

    public static String[] getRespiratoryTypeNames(Resources res){
        return new String[]{res.getString(R.string.shortness_of_breath),
                res.getString(R.string.flu),
                res.getString(R.string.cough),
                res.getString(R.string.phlegm),
                res.getString(R.string.congestion),
                res.getString(R.string.irritation),
                res.getString(R.string.hissing)};
    }

    public static String[] getSensoryTypeNames(Resources res){
        return new String[]{res.getString(R.string.dizziness),
                res.getString(R.string.visual_disturbances),
                res.getString(R.string.hearing_disturbances),
                res.getString(R.string.tingling),
                res.getString(R.string.numbness),
                res.getString(R.string.rigidity),
                res.getString(R.string.heaviness),
                res.getString(R.string.weakness)};
    }

    public static String[] getEmotionalTypeNames(Resources res){
        return new String[]{res.getString(R.string.anxiety),
                res.getString(R.string.depression),
                res.getString(R.string.insomnia),
                res.getString(R.string.drowsiness),
                res.getString(R.string.humor_changes),
                res.getString(R.string.exhaustion)};
    }

    public static String[] getDermatologicalTypeNames(Resources res){
        return new String[]{res.getString(R.string.bruising),
                res.getString(R.string.rash),
                res.getString(R.string.spots),
                res.getString(R.string.redness),
                res.getString(R.string.acne),
                res.getString(R.string.hair_loss),
                res.getString(R.string.ulcers),
                res.getString(R.string.skin_lumps),
                res.getString(R.string.urticaria),
                res.getString(R.string.itch)};
    }

    public static String[] getTriggeringActivityTypeNames(Resources res){
        return new String[]{res.getString(R.string.waking_up),
                res.getString(R.string.sleeping),
                res.getString(R.string.physical_activity),
                res.getString(R.string.after_physical_activity)};
    }

    public static String[] getTriggeringEmotionTypeNames(Resources res){
        return new String[]{res.getString(R.string.scare),
                res.getString(R.string.anger),
                res.getString(R.string.sadness),
                res.getString(R.string.stress),
                res.getString(R.string.anguish)};
    }

    public static String[] getTriggeringWeatherStateTypeNames(Resources res){
        return new String[]{res.getString(R.string.cold),
                res.getString(R.string.hot),
                res.getString(R.string.humidity),
                res.getString(R.string.dryness)};
    }

}
