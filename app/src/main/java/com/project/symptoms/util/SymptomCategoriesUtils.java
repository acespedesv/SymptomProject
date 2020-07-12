package com.project.symptoms.util;

import com.project.symptoms.R;
import com.project.symptoms.dto.CategoryDTO;
import com.project.symptoms.dto.SymptomOptionDTO;

public class SymptomCategoriesUtils {

    public static CategoryDTO[] getCategoryNames(){
        return new CategoryDTO[]{
				new CategoryDTO(R.string.pain_type),
                new CategoryDTO(R.string.digestive_pain),
                new CategoryDTO(R.string.respiratory_pain),
                new CategoryDTO(R.string.sensory_pain),
                new CategoryDTO(R.string.emotional_pain),
                new CategoryDTO(R.string.dermatological_pain),
                new CategoryDTO(R.string.triggering_activity),
                new CategoryDTO(R.string.triggering_feeling),
                new CategoryDTO(R.string.triggering_weather_condition)};
    }

    public static SymptomOptionDTO[] getPainTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.sharp_pain, R.drawable.ic_agudo),
                new SymptomOptionDTO(R.string.throbbing_pain, R.drawable.ic_punzante),
                new SymptomOptionDTO(R.string.palpitating_pain, R.drawable.ic_palpitante),
                new SymptomOptionDTO(R.string.oppressive_pain, R.drawable.ic_opresivo),
                new SymptomOptionDTO(R.string.electric_pain, R.drawable.ic_electrico)
        };
    }

    public static SymptomOptionDTO[] getDigestiveTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.sickness, R.drawable.ic_nauseas),
                new SymptomOptionDTO(R.string.bad_breath, R.drawable.ic_mal_aliento),
                new SymptomOptionDTO(R.string.heartburn, R.drawable.ic_agruras),
                new SymptomOptionDTO(R.string.sourness, R.drawable.ic_acidez),
                new SymptomOptionDTO(R.string.reflux, R.drawable.ic_reflujo),
                new SymptomOptionDTO(R.string.inflammation, R.drawable.ic_inflamacion),
                new SymptomOptionDTO(R.string.constipation, R.drawable.ic_estrenimiento),
                new SymptomOptionDTO(R.string.diarrhea, R.drawable.ic_diarrea),
                new SymptomOptionDTO(R.string.vomit, R.drawable.ic_vomito),
                new SymptomOptionDTO(R.string.difficulty_to_swallow, R.drawable.ic_dificultad_para_tragar),
                new SymptomOptionDTO(R.string.bleeding, R.drawable.ic_sangrado),
                new SymptomOptionDTO(R.string.incontinence, R.drawable.ic_incontinencia),
                new SymptomOptionDTO(R.string.mouth_ulcers, R.drawable.ic_ulceras_bucales)
        };
    }

    public static SymptomOptionDTO[] getRespiratoryTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.shortness_of_breath, R.drawable.ic_falta_de_aire),
                new SymptomOptionDTO(R.string.flu, R.drawable.ic_catarro),
                new SymptomOptionDTO(R.string.cough, R.drawable.ic_tos),
                new SymptomOptionDTO(R.string.phlegm, R.drawable.ic_flemas),
                new SymptomOptionDTO(R.string.congestion, R.drawable.ic_congestion),
                new SymptomOptionDTO(R.string.irritation, R.drawable.ic_irritacion),
                new SymptomOptionDTO(R.string.hissing, R.drawable.ic_silbido)
        };
    }

    public static SymptomOptionDTO[] getSensoryTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.dizziness, R.drawable.ic_mareos),
                new SymptomOptionDTO(R.string.visual_disturbances, R.drawable.ic_alteraciones_visuales),
                new SymptomOptionDTO(R.string.hearing_disturbances, R.drawable.ic_alteraciones_auditivas),
                new SymptomOptionDTO(R.string.tingling, R.drawable.ic_hormigueo),
                new SymptomOptionDTO(R.string.numbness, R.drawable.ic_adormecimiento),
                new SymptomOptionDTO(R.string.rigidity, R.drawable.ic_rigidez),
                new SymptomOptionDTO(R.string.heaviness, R.drawable.ic_pesadez),
                new SymptomOptionDTO(R.string.weakness, R.drawable.ic_debilidad)};
    }

    public static SymptomOptionDTO[] getEmotionalTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.anxiety, R.drawable.ic_ansiedad),
                new SymptomOptionDTO(R.string.depression, R.drawable.ic_depresion),
                new SymptomOptionDTO(R.string.insomnia, R.drawable.ic_insomnio),
                new SymptomOptionDTO(R.string.drowsiness, R.drawable.ic_somnolencia),
                new SymptomOptionDTO(R.string.humor_changes, R.drawable.ic_cambios_de_humor),
                new SymptomOptionDTO(R.string.exhaustion, R.drawable.ic_agotamiento)
        };
    }

    public static SymptomOptionDTO[] getDermatologicalTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.bruising, R.drawable.ic_hematoma),
                new SymptomOptionDTO(R.string.rash, R.drawable.ic_sarpullido),
                new SymptomOptionDTO(R.string.spots, R.drawable.ic_manchas),
                new SymptomOptionDTO(R.string.redness, R.drawable.ic_enrojecimiento),
                new SymptomOptionDTO(R.string.acne, R.drawable.ic_acne),
                new SymptomOptionDTO(R.string.hair_loss, R.drawable.ic_caida_de_cabello),
                new SymptomOptionDTO(R.string.ulcers, R.drawable.ic_ulceras),
                new SymptomOptionDTO(R.string.skin_lumps, R.drawable.ic_bultos_en_la_piel),
                new SymptomOptionDTO(R.string.urticaria, R.drawable.ic_urticaria),
                new SymptomOptionDTO(R.string.itch, R.drawable.ic_comeson)
        };
    }

    public static SymptomOptionDTO[] getTriggeringActivityTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.waking_up, R.drawable.ic_despertar),
                new SymptomOptionDTO(R.string.sleeping, R.drawable.ic_dormir),
                new SymptomOptionDTO(R.string.physical_activity, R.drawable.ic_durante_actividad_fisica),
                new SymptomOptionDTO(R.string.after_physical_activity, R.drawable.ic_despues_de_actividad)
        };
    }

    public static SymptomOptionDTO[] getTriggeringEmotionTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.scare, R.drawable.ic_susto),
                new SymptomOptionDTO(R.string.anger, R.drawable.ic_enojo),
                new SymptomOptionDTO(R.string.sadness, R.drawable.ic_tristeza),
                new SymptomOptionDTO(R.string.stress, R.drawable.ic_estres),
                new SymptomOptionDTO(R.string.anguish, R.drawable.ic_angustia)
        };
    }

    public static SymptomOptionDTO[] getTriggeringWeatherStateTypeNames(){
        return new SymptomOptionDTO[]{
				new SymptomOptionDTO(R.string.cold, R.drawable.ic_frio),
                new SymptomOptionDTO(R.string.hot, R.drawable.ic_calor),
                new SymptomOptionDTO(R.string.humidity, R.drawable.ic_humedad),
                new SymptomOptionDTO(R.string.dryness, R.drawable.ic_sequedad)
        };
    }

}
