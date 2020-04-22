package com.project.symptoms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class SymptomForm extends AppCompatActivity {

    HashMap<String, SymptomOption[]> optionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_form);


        optionsList = new HashMap<>();

        SymptomOption dolores[] = {
                new SymptomOption(R.drawable.ic_agudo, "Agudo"),
                new SymptomOption(R.drawable.ic_punzante, "Punzante"),
                new SymptomOption(R.drawable.ic_palpitante, "Palpitante"),
                new SymptomOption(R.drawable.ic_opresivo, "Opresivo"),
                new SymptomOption(R.drawable.ic_electrico, "Eléctrico")
        };
        optionsList.put("Tipo de dolor", dolores);

        SymptomOption digestivos[] = {
                new SymptomOption(R.drawable.ic_nauseas, "Nauseas"),
                new SymptomOption(R.drawable.ic_mal_aliento, "Mal aliento"),
                new SymptomOption(R.drawable.ic_agruras, "Agruras"),
                new SymptomOption(R.drawable.ic_acidez, "Acidez"),
                new SymptomOption(R.drawable.ic_reflujo, "Reflujo"),
                new SymptomOption(R.drawable.ic_inflamacion, "Inflamación"),
                new SymptomOption(R.drawable.ic_estrenimiento, "Estreñimiento"),
                new SymptomOption(R.drawable.ic_diarrea, "Diarrea"),
                new SymptomOption(R.drawable.ic_vomito, "Vómito"),
                new SymptomOption(R.drawable.ic_dificultad_para_tragar, "Dificultad para tragar"),
                new SymptomOption(R.drawable.ic_sangrad, "Sangrado"),
                new SymptomOption(R.drawable.ic_incontinencia, "Incotinencia"),
                new SymptomOption(R.drawable.ic_ulceras_bucales, "Úlceras bucales")
        };
        optionsList.put("Digestivo", digestivos);

        SymptomOption respiratorio[] = {
                new SymptomOption(R.drawable.ic_falta_de_aire, "Falta de aire"),
                new SymptomOption(R.drawable.ic_catarro, "Catarro"),
                new SymptomOption(R.drawable.ic_tos, "Tos"),
                new SymptomOption(R.drawable.ic_flemas, "Flema"),
                new SymptomOption(R.drawable.ic_congestion, "Congestión"),
                new SymptomOption(R.drawable.ic_irritacion, "Irritación"),
                new SymptomOption(R.drawable.ic_silbido, "Silbido")
        };
        optionsList.put("Respiratorio", respiratorio);


        SymptomOption sensorial[] = {
                new SymptomOption(R.drawable.ic_mareos, "Mareo"),
                new SymptomOption(R.drawable.ic_alteraciones_visuales, "Alteraciones Visuales"),
                new SymptomOption(R.drawable.ic_alteraciones_auditivas, "Alteraciones Auditivas"),
                new SymptomOption(R.drawable.ic_hormigueo, "Hormigueo"),
                new SymptomOption(R.drawable.ic_adormecimiento, "Adormecimiento"),
                new SymptomOption(R.drawable.ic_rigidez, "Rigidez"),
                new SymptomOption(R.drawable.ic_pesadez, "Pesadez"),
                new SymptomOption(R.drawable.ic_debilidad, "Debilidad")
        };
        optionsList.put("Sensorial", sensorial);


        SymptomOption nervioso[] = {
                new SymptomOption(R.drawable.ic_ansiedad, "Ansiedad"),
                new SymptomOption(R.drawable.ic_depresion, "Depresión"),
                new SymptomOption(R.drawable.ic_insomnio, "Insomnio"),
                new SymptomOption(R.drawable.ic_somnolencia, "Somnolencia"),
                new SymptomOption(R.drawable.ic_cambios_de_humor, "Cambios de humor"),
                new SymptomOption(R.drawable.ic_agotamiento, "Agotamiento")
        };
        optionsList.put("Nervioso", nervioso);

        SymptomOption dermatologico[] = {
                new SymptomOption(R.drawable.ic_hematoma, "Hematomas"),
                new SymptomOption(R.drawable.ic_sarpullido, "Sarpullido/Erupción"),
                new SymptomOption(R.drawable.ic_manchas, "Manchas"),
                new SymptomOption(R.drawable.ic_enrojecimiento, "Enrojecimiento"),
                new SymptomOption(R.drawable.ic_acne, "Acné"),
                new SymptomOption(R.drawable.ic_caida_de_cabello, "Caida del cabello"),
                new SymptomOption(R.drawable.ic_ulceras, "Úlceras"),
                new SymptomOption(R.drawable.ic_bultos_en_la_piel, "Bultos en la piel"),
                new SymptomOption(R.drawable.ic_urticaria, "Urticaria"),
                new SymptomOption(R.drawable.ic_comeson, "Comezón"),
        };
        optionsList.put("Dermatológico", dermatologico);

        SymptomOption actividadesDetonantes[] = {
                new SymptomOption(R.drawable.ic_despertar, "Al despertar"),
                new SymptomOption(R.drawable.ic_dormir, "Al dormir"),
                new SymptomOption(R.drawable.ic_durante_actividad_fisica, "Durante actividad física"),
                new SymptomOption(R.drawable.ic_despues_de_actividad, "Post actividad física")
        };
        optionsList.put("Actividades Detonantes", actividadesDetonantes);

        SymptomOption emocionesDetonantes[] = {
                new SymptomOption(R.drawable.ic_susto, "Susto"),
                new SymptomOption(R.drawable.ic_enojo, "Enojo"),
                new SymptomOption(R.drawable.ic_tristeza, "Tristeza"),
                new SymptomOption(R.drawable.ic_estres, "Estrés"),
                new SymptomOption(R.drawable.ic_angustia, "Angustia")
        };
        optionsList.put("Emociones detonantes", emocionesDetonantes);

        SymptomOption sensacionClimatica[] = {
                new SymptomOption(R.drawable.ic_frio, "Frio"),
                new SymptomOption(R.drawable.ic_calor, "Calor"),
                new SymptomOption(R.drawable.ic_humedad, "Humedad"),
                new SymptomOption(R.drawable.ic_sequedad, "Sequedad")
        };
        optionsList.put("Clima", sensacionClimatica);

        LinearLayout categoriesScrollView = findViewById(R.id.symtom_form);

        inflateSymptomsCategories(categoriesScrollView, optionsList);
    }

    /**
     * Should inflate each SymptomOption in the hashmap to the UI
     * @param categories should be a hashmap from string (category name) to SymptomOption[]
     */
    private void inflateSymptomsCategories(LinearLayout parentLayout, HashMap<String,SymptomOption[]> categories){
        String categoryName = "";
        for( Object key : categories.keySet()){

            categoryName = (String) key;

            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.VERTICAL);

            TextView categoryLabel = new TextView(this);
            categoryLabel.setText(categoryName);
            categoryLabel.setTextSize(20);
            categoryLabel.setPadding(20,50,20,20);

            row.addView(categoryLabel);

            HorizontalScrollView scrollView = new HorizontalScrollView(this);
            LinearLayout optionsRow = new LinearLayout(this);
            optionsRow.setOrientation(LinearLayout.HORIZONTAL);
            scrollView.addView(optionsRow);


            for(SymptomOption option : categories.get(categoryName)){
                View newView = createViewFromOption(option);
                optionsRow.addView(newView);
            }

            row.addView(scrollView);
            parentLayout.addView(row);

        }

    }

    // Create a view for representing the parameter
//    private View createViewFromOption(SymptomOption option){
//        LinearLayout box = new LinearLayout(this);
//        box.setGravity(Gravity.CENTER);
//        box.setOrientation(LinearLayout.VERTICAL);
//        box.setPadding(20,0,20,0);
//
//
//        ImageView image = new ImageView(this);
//        image.setImageResource(option.imageResId);
//
//        CheckBox checkbox = new CheckBox(this);
//
//        checkbox.setText(option.label);
//
//        box.addView(image);
//        box.addView(checkbox);
//
//        return box;
//    }

    private View createViewFromOption(SymptomOption option){
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.symptom_option,null);
        RelativeLayout relativeLayout = (RelativeLayout)view;

        ImageView icon = relativeLayout.findViewWithTag("icon");
        icon.setImageResource(option.imageResId);

        TextView label = relativeLayout.findViewWithTag("label");
        label.setText(option.label);
        label.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        return view;
    }

    private class SymptomOption {
        int imageResId;
        String label;

        public SymptomOption(int resId, String label){
            this.imageResId = resId;
            this.label = label;

        }
    }
}
