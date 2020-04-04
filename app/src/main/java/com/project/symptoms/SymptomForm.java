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
                new SymptomOption(R.drawable.ic_launcher_background, "Agudo"),
                new SymptomOption(R.drawable.ic_launcher_background, "Punzante"),
                new SymptomOption(R.drawable.ic_launcher_background, "Palpitante"),
                new SymptomOption(R.drawable.ic_launcher_background, "Opresivo"),
                new SymptomOption(R.drawable.ic_launcher_background, "Eléctrico")
        };
        optionsList.put("Tipo de dolor", dolores);

        SymptomOption digestivos[] = {
                new SymptomOption(R.drawable.ic_launcher_background, "Nauseas"),
                new SymptomOption(R.drawable.ic_launcher_background, "Mal aliento"),
                new SymptomOption(R.drawable.ic_launcher_background, "Agruras"),
                new SymptomOption(R.drawable.ic_launcher_background, "Acidez"),
                new SymptomOption(R.drawable.ic_reflujo, "Reflujo"),
                new SymptomOption(R.drawable.ic_launcher_background, "Inflamación"),
                new SymptomOption(R.drawable.ic_launcher_background, "Estreñimiento"),
                new SymptomOption(R.drawable.ic_diarrea, "Diarrea"),
                new SymptomOption(R.drawable.ic_launcher_background, "Vómito"),
                new SymptomOption(R.drawable.ic_launcher_background, "Dificultad para tragar"),
                new SymptomOption(R.drawable.ic_launcher_background, "Sangrado"),
                new SymptomOption(R.drawable.ic_launcher_background, "Incotinencia"),
                new SymptomOption(R.drawable.ic_launcher_background, "Úlceras bucales")
        };
        optionsList.put("Digestivo", digestivos);

        SymptomOption respiratorio[] = {
                new SymptomOption(R.drawable.ic_launcher_background, "Falta de aire"),
                new SymptomOption(R.drawable.ic_catarro, "Catarro"),
                new SymptomOption(R.drawable.ic_tos, "Tos"),
                new SymptomOption(R.drawable.ic_launcher_background, "Flema"),
                new SymptomOption(R.drawable.ic_launcher_background, "Congestión"),
                new SymptomOption(R.drawable.ic_irritacion, "Irritación"),
                new SymptomOption(R.drawable.ic_launcher_background, "Silbido")
        };
        optionsList.put("Respiratorio", respiratorio);


        SymptomOption sensorial[] = {
                new SymptomOption(R.drawable.ic_launcher_background, "Mareo"),
                new SymptomOption(R.drawable.ic_launcher_background, "Alteraciones Visuales"),
                new SymptomOption(R.drawable.ic_launcher_background, "Alteraciones Auditivas"),
                new SymptomOption(R.drawable.ic_launcher_background, "Hormigueo"),
                new SymptomOption(R.drawable.ic_launcher_background, "Adormecimiento"),
                new SymptomOption(R.drawable.ic_launcher_background, "Rigidez"),
                new SymptomOption(R.drawable.ic_launcher_background, "Pesadez"),
                new SymptomOption(R.drawable.ic_launcher_background, "Debilidad")
        };
        optionsList.put("Sensorial", sensorial);


        SymptomOption nervioso[] = {
                new SymptomOption(R.drawable.ic_launcher_background, "Ansiedad"),
                new SymptomOption(R.drawable.ic_launcher_background, "Depresión"),
                new SymptomOption(R.drawable.ic_launcher_background, "Insomnio"),
                new SymptomOption(R.drawable.ic_launcher_background, "Somnolencia"),
                new SymptomOption(R.drawable.ic_launcher_background, "Cambios de humor"),
                new SymptomOption(R.drawable.ic_launcher_background, "Agotamiento")
        };
        optionsList.put("Nervioso", nervioso);

        SymptomOption dermatologico[] = {
                new SymptomOption(R.drawable.ic_launcher_background, "Hematomas"),
                new SymptomOption(R.drawable.ic_launcher_background, "Sarpullido/Erupción"),
                new SymptomOption(R.drawable.ic_launcher_background, "Manchas"),
                new SymptomOption(R.drawable.ic_launcher_background, "Enrojecimiento"),
                new SymptomOption(R.drawable.ic_launcher_background, "Acné"),
                new SymptomOption(R.drawable.ic_launcher_background, "Caida del cabello"),
                new SymptomOption(R.drawable.ic_launcher_background, "Úlceras"),
                new SymptomOption(R.drawable.ic_launcher_background, "Bultos en la piel"),
                new SymptomOption(R.drawable.ic_launcher_background, "Urticaria"),
                new SymptomOption(R.drawable.ic_launcher_background, "Comezón"),
        };
        optionsList.put("Dermatológico", dermatologico);

        SymptomOption actividadesDetonantes[] = {
                new SymptomOption(R.drawable.ic_despertar, "Al despertar"),
                new SymptomOption(R.drawable.ic_dormir, "Al dormir"),
                new SymptomOption(R.drawable.ic_launcher_background, "Durante actividad física"),
                new SymptomOption(R.drawable.ic_launcher_background, "Post actividad física")
        };
        optionsList.put("Actividades Detonantes", actividadesDetonantes);

        SymptomOption emocionesDetonantes[] = {
                new SymptomOption(R.drawable.ic_launcher_background, "Susto"),
                new SymptomOption(R.drawable.ic_launcher_background, "Enojo"),
                new SymptomOption(R.drawable.ic_launcher_background, "Tristeza"),
                new SymptomOption(R.drawable.ic_launcher_background, "Estrés"),
                new SymptomOption(R.drawable.ic_launcher_background, "Angustia")
        };
        optionsList.put("Dermatológico", emocionesDetonantes);

        SymptomOption sensacionClimatica[] = {
                new SymptomOption(R.drawable.ic_frio, "Frio"),
                new SymptomOption(R.drawable.ic_calor, "Calor"),
                new SymptomOption(R.drawable.ic_launcher_background, "Humedad"),
                new SymptomOption(R.drawable.ic_launcher_background, "Sequedad")
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
