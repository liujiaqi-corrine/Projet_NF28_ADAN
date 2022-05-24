package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class AjouteUnOffre extends AppCompatActivity {
    EditText titre;
    EditText description;
    EditText argent;
    EditText recurrence;
    EditText nbCandiadte;
    EditText duree;
    EditText adresse;

    RadioGroup type;
    Button buttonSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoute_un_offre);

        titre = (EditText) findViewById(R.id.editTextTextPersonName9);
        description = (EditText) findViewById(R.id.editTextTextPersonName13);
        argent = (EditText) findViewById(R.id.editTextTextPersonName12);
        recurrence = (EditText) findViewById(R.id.editTextTextPersonName11);
        nbCandiadte = (EditText) findViewById(R.id.editTextTextPersonName10);
        duree = (EditText) findViewById(R.id.editTextTextPersonName15);
        adresse = (EditText) findViewById(R.id.editTextTextPersonName14);
        type = (RadioGroup) findViewById(R.id.radioGroupe);
    }

    public boolean valide(){
        boolean test = true;
        if(titre.getText().toString().isEmpty()){
            test=false;
            titre.setError("case obligatoire");
        }
        if(description.getText().toString().isEmpty()){
            test=false;
            description.setError("case obligatoire");
        }
        if(argent.getText().toString().isEmpty()){
            test=false;
            argent.setError("case obligatoire");
        }
        if(recurrence.getText().toString().isEmpty()){
            test=false;
            recurrence.setError("case obligatoire");
        }
        if(nbCandiadte.getText().toString().isEmpty()){
            test=false;
            nbCandiadte.setError("case obligatoire");
        }
        if(duree.getText().toString().isEmpty()){
            test=false;
            duree.setError("case obligatoire");
        }
        if(adresse.getText().toString().isEmpty()){
            test=false;
            adresse.setError("case obligatoire");
        }
        return test;
    }

    public void SauvegardEtRTrAMesOffres(View view) {
        if(valide()){
            addOffre();
            Intent intent = new Intent(this, MesOffres.class);
            startActivity(intent);
        }
    }

    public void addOffre(){
        Offre of = new Offre();
        of.setTitre(titre.getText().toString());
        of.setDescription(description.getText().toString());
        of.setArgent(Integer.parseInt(argent.getText().toString()));
        of.setRecurrence(recurrence.getText().toString());
        of.setNbCandidate(Integer.parseInt(nbCandiadte.getText().toString()));
        of.setDurre(duree.getText().toString());
        of.setAdresse(adresse.getText().toString());
        int buttonSelectedId = type.getCheckedRadioButtonId();
        if(buttonSelectedId==R.id.radioButton8){
            of.setTypeOffre("Prestation");
        }
        else if(buttonSelectedId==R.id.radioButton7){
            of.setTypeOffre("Emploi");
        }
        of.setAuthor(MainActivity.getLoginMemberID());
        of.setCandidate("");

        DBOpenHelper dboh = new DBOpenHelper();
        dboh.addOffre(of);
    }

    public void RtrAMesOffres(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }
}
