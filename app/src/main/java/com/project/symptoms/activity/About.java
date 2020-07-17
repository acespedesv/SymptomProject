package com.project.symptoms.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.symptoms.R;

public class About extends AppCompatActivity {
    public Collaborator all[] = new Collaborator[]{
            new Collaborator("Isaac Mena","xsaco07@gmail.com","Instituto Tecnológico de Costa Rica"),
            new Collaborator("Emmanuel Alfaro","ealfarobrenes@hotmail.com","Instituto Tecnológico de Costa Rica"),
            new Collaborator("Daniel Blanco","blancodaniel2010@hotmail.com","Instituto Tecnológico de Costa Rica")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LinearLayout container = findViewById(R.id.mainContainer);
        for(Collaborator each : all){
            container.addView(buildView(each));
        }
    }

    private View buildView(Collaborator each) {
        LinearLayout card = (LinearLayout) getLayoutInflater().inflate(R.layout.collaborator_card, null);
        ((TextView) card.findViewById(R.id.about_email)).setText(each.email);
        ((TextView) card.findViewById(R.id.about_name)).setText(each.fullName);
        ((TextView) card.findViewById(R.id.about_institution)).setText(each.institution);
        return card;

    }

    private class Collaborator{
        String fullName;
        String institution;
        String email;

        public Collaborator(String fullName, String email, String institution){
            this.fullName = fullName;
            this.email = email;
            this.institution = institution;
        }
    }
}
