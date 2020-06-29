package com.project.symptoms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.project.symptoms.R;
import com.project.symptoms.db.controller.SymptomController;
import com.project.symptoms.fragment.MainMenuFragment;
import com.project.symptoms.util.DateTimeUtils;
import com.project.symptoms.view.BodyView;
import com.project.symptoms.view.SymptomOptionView;

import java.util.ArrayList;

public class SymptomForm extends AppCompatActivity implements MainMenuFragment.OnFragmentInteractionListener{

    private Button saveButton;
    private RadioGroup intensityRadioGroupView;
    private Switch intermittenceSwitchView;
    private EditText symptomDescriptionView, symptomMedicamentView, symptomFoodView;
    private TextView startDateView, startTimeView;
    private BodyView.Circle currentCircle;
    private String mainActivityDate, mainActivityTime;
    private int bodyState;
    private EditText symptomDurationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_form);
        loadSymptomCategories();
        setUpViews();
        setUpListeners();
    }

    private void setUpViews(){
        saveButton = findViewById(R.id.save_button);
        startDateView = findViewById(R.id.start_date);
        startTimeView = findViewById(R.id.start_time);
        intensityRadioGroupView = findViewById(R.id.symp_radio_group);
        intermittenceSwitchView = findViewById(R.id.intermittence_switch);
        symptomDescriptionView = findViewById(R.id.symp_description);
        symptomMedicamentView = findViewById(R.id.symp_medicament);
        symptomFoodView = findViewById(R.id.symp_food);
        symptomDurationView = findViewById(R.id.symp_duration);
        DateTimeUtils.getInstance().registerAsTimePicker(startDateView);
        DateTimeUtils.getInstance().registerAsTimePicker(startTimeView);
        setStartDateTime(mainActivityDate, mainActivityTime);
    }

    private void setUpBundleData(){
        currentCircle = getIntent().getParcelableExtra("Circle");
        mainActivityDate = getIntent().getStringExtra("Date");
        mainActivityTime = getIntent().getStringExtra("Time");
        bodyState = getIntent().getIntExtra("State", -1);
    }

    private void setStartDateTime(String date, String time){
        startDateView.setText(date);

        // Not allow user to edit this because the date needs to be the same than MainActivity one
        // TODO: check this with client
        startDateView.setEnabled(false);
        startDateView.setClickable(false);

        startTimeView.setText(time);
    }

    private void setUpListeners(){
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSymptomsData();
            }
        });
    }

    private void saveSymptomsData() {
        setUpBundleData();

        String stringDuration = symptomDurationView.getText().toString();
        int finalDuration = (!"".equals(stringDuration)) ? Integer.parseInt(stringDuration) : -1;

        int intensityCheckedViewId = intensityRadioGroupView.getCheckedRadioButtonId();

        long newId = SymptomController.getInstance(this).insert(currentCircle.x, currentCircle.y,
                mainActivityDate, mainActivityTime, finalDuration, symptomDescriptionView.getText().toString(),
                findViewById(intensityCheckedViewId).toString(), symptomMedicamentView.getText().toString(), symptomFoodView.getText().toString(),
                intermittenceSwitchView.isChecked() ? 1 : 0, currentCircle.radius, bodyState);

        if(newId != -1){
            String text = getResources().getString(R.string.value_successfully_saved);
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    private void loadSymptomCategories() {
        SymptomOption[] dolores = {
                new SymptomOption(R.drawable.ic_agudo, "Agudo"),
                new SymptomOption(R.drawable.ic_punzante, "Punzante"),
                new SymptomOption(R.drawable.ic_palpitante, "Palpitante"),
                new SymptomOption(R.drawable.ic_opresivo, "Opresivo"),
                new SymptomOption(R.drawable.ic_electrico, "Eléctrico")
        };

        SymptomOption[] digestivos = {
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

        SymptomOption[] respiratorio = {
                new SymptomOption(R.drawable.ic_falta_de_aire, "Falta de aire"),
                new SymptomOption(R.drawable.ic_catarro, "Catarro"),
                new SymptomOption(R.drawable.ic_tos, "Tos"),
                new SymptomOption(R.drawable.ic_flemas, "Flema"),
                new SymptomOption(R.drawable.ic_congestion, "Congestión"),
                new SymptomOption(R.drawable.ic_irritacion, "Irritación"),
                new SymptomOption(R.drawable.ic_silbido, "Silbido")
        };

        SymptomOption[] sensorial = {
                new SymptomOption(R.drawable.ic_mareos, "Mareo"),
                new SymptomOption(R.drawable.ic_alteraciones_visuales, "Alteraciones Visuales"),
                new SymptomOption(R.drawable.ic_alteraciones_auditivas, "Alteraciones Auditivas"),
                new SymptomOption(R.drawable.ic_hormigueo, "Hormigueo"),
                new SymptomOption(R.drawable.ic_adormecimiento, "Adormecimiento"),
                new SymptomOption(R.drawable.ic_rigidez, "Rigidez"),
                new SymptomOption(R.drawable.ic_pesadez, "Pesadez"),
                new SymptomOption(R.drawable.ic_debilidad, "Debilidad")
        };


        SymptomOption[] nervioso = {
                new SymptomOption(R.drawable.ic_ansiedad, "Ansiedad"),
                new SymptomOption(R.drawable.ic_depresion, "Depresión"),
                new SymptomOption(R.drawable.ic_insomnio, "Insomnio"),
                new SymptomOption(R.drawable.ic_somnolencia, "Somnolencia"),
                new SymptomOption(R.drawable.ic_cambios_de_humor, "Cambios de humor"),
                new SymptomOption(R.drawable.ic_agotamiento, "Agotamiento")
        };

        SymptomOption[] dermatologico = {
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

        SymptomOption[] actividadesDetonantes = {
                new SymptomOption(R.drawable.ic_despertar, "Al despertar"),
                new SymptomOption(R.drawable.ic_dormir, "Al dormir"),
                new SymptomOption(R.drawable.ic_durante_actividad_fisica, "Durante actividad física"),
                new SymptomOption(R.drawable.ic_despues_de_actividad, "Post actividad física")
        };

        SymptomOption[] emocionesDetonantes = {
                new SymptomOption(R.drawable.ic_susto, "Susto"),
                new SymptomOption(R.drawable.ic_enojo, "Enojo"),
                new SymptomOption(R.drawable.ic_tristeza, "Tristeza"),
                new SymptomOption(R.drawable.ic_estres, "Estrés"),
                new SymptomOption(R.drawable.ic_angustia, "Angustia")
        };

        SymptomOption[] sensacionClimatica = {
                new SymptomOption(R.drawable.ic_frio, "Frio"),
                new SymptomOption(R.drawable.ic_calor, "Calor"),
                new SymptomOption(R.drawable.ic_humedad, "Humedad"),
                new SymptomOption(R.drawable.ic_sequedad, "Sequedad")
        };

        Object[][] optionsList = new Object[][]{
                {"Tipo de dolor", dolores},
                {"Digestivo", digestivos},
                {"Respiratorio", respiratorio},
                {"Sensorial", sensorial},
                {"Nervioso", nervioso},
                {"Dermatológico", dermatologico},
                {"Actividades Detonantes", actividadesDetonantes},
                {"Emociones Detonantes", emocionesDetonantes},
                {"Clima", sensacionClimatica},

        };

        LinearLayout categoriesSection = findViewById(R.id.categories_section);

        inflateSymptomsCategories(categoriesSection, optionsList);
    }

    /**
     * Should inflate each SymptomOption in the hashmap to the UI
     *
     * @param categories should be matrix of Objects each row as: category name (String) and a SymptomOption[]
     */
    private void inflateSymptomsCategories(LinearLayout parentLayout, Object[][] categories) {
        String categoryName = "";
        SymptomOption[] options = null;
        int categoryNamePosition = 0, optionsPosition = 1; // Names for indexes

        LinearLayout verticalLayout = new LinearLayout(this);
        verticalLayout.setOrientation(LinearLayout.VERTICAL);

        for (Object[] row : categories) {
            categoryName = (String) row[categoryNamePosition];
            options = (SymptomOption[]) row[optionsPosition];

            verticalLayout.addView(inflateCategoryLabel(categoryName));

            verticalLayout.addView(inflateSymptomCategory(options));

        }
        parentLayout.addView(verticalLayout);
    }

    private HorizontalScrollView inflateSymptomCategory(SymptomOption[] options){
        LinearLayout optionsRow = new LinearLayout(this);
        optionsRow.setOrientation(LinearLayout.HORIZONTAL);
        optionsRow.setGravity(Gravity.CENTER);

        for (SymptomOption option : options)
            optionsRow.addView(createViewFromOption(option));

        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        scrollView.addView(optionsRow);

        return scrollView;
    }

    private TextView inflateCategoryLabel(String categoryName){
        TextView categoryLabel = new TextView(this);
        categoryLabel.setText(categoryName);
        categoryLabel.setTextSize(20);
        categoryLabel.setPadding(20, 50, 20, 20);
        return categoryLabel;
    }


    private View createViewFromOption(SymptomOption option){
        LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        SymptomOptionView optionView =  new SymptomOptionView(this);
        optionView.setSymptomOption(option);
        return optionView;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A simple data-only class for representing each symptom option in the form
     */
    public class SymptomOption {
        public int imageResId;
        public String label;

        SymptomOption(int resId, String label){
            this.imageResId = resId;
            this.label = label;
        }
    }
}
