package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifierUnOffre extends AppCompatActivity {

    EditText titre;
    EditText decscription;
    EditText argent;
    EditText recurrence;
    EditText nbCandidate;
    EditText durre;
    EditText adresse;

    Button b1,b2,b3,b4;

    int idOffre;
    Offre off;
    DBOpenHelper dboh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifer_un_offre);

        b1  = (Button) findViewById(R.id.button8);
        b2  = (Button) findViewById(R.id.button6);
        b3  = (Button) findViewById(R.id.button18);
        b4  = (Button) findViewById(R.id.button9);

        titre = (EditText) findViewById(R.id.editTextTextPersonName9);
        decscription = (EditText) findViewById(R.id.editTextTextPersonName13);
        argent = (EditText) findViewById(R.id.editTextTextPersonName12);
        recurrence = (EditText) findViewById(R.id.editTextTextPersonName11);
        nbCandidate = (EditText) findViewById(R.id.editTextTextPersonName10);
        durre = (EditText) findViewById(R.id.editTextTextPersonName15);
        adresse = (EditText) findViewById(R.id.editTextTextPersonName14);

        Intent intent = getIntent();
        idOffre = intent.getIntExtra("idOffre",0);
        dboh = new DBOpenHelper();
        Log.d("ModifierUnOffre",String.valueOf(idOffre));

        if(idOffre!=0){
            off = dboh.findUnOffres(idOffre);
            Log.d("ModifierUnOffre",off.getTitre());

            titre.setText(off.getTitre());
            decscription.setText(off.getDescription());
            argent.setText(String.valueOf(off.getArgent()));
            recurrence.setText(off.getRecurrence());
            nbCandidate.setText(String.valueOf(off.getNbCandidate()));
            durre.setText(off.getDurre());
            adresse.setText(off.getAdresse());

            if (off.getCandidate() == "" || off.getCandidate().isEmpty()) {
                b4.setEnabled(false);
                b4.setBackgroundColor(Color.GRAY);
            }



        }
        else{
            b4.setEnabled(false);
            b4.setBackgroundColor(Color.GRAY);
        }

        int id = MainActivity.getLoginMemberID();
        dboh =  new DBOpenHelper();
        User user =  dboh.findUnUser(id);

        if(user.getIsEmployer() == 0){
            b4.setEnabled(false);
            b4.setBackgroundColor(Color.GRAY);
        }

        //pour le moment, on n'a pas cette fonction
        b3.setEnabled(false);
        b3.setBackgroundColor(Color.GRAY);

        b2.setEnabled(false);
        b2.setBackgroundColor(Color.GRAY);

    }


    public void SauvegardEtRTrAMesOffres(View view) {
        //a sauvegarder
        //à choix à active si prorietaire
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void RtrAMesOffres(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void RedigerUnContrat(View view) {
        Intent intent = new Intent(this, RedigerContrat.class);
        User author = dboh.findUnUser(off.getAuthor());
        String candidates = off.getCandidate();
        String textContrat="";
        if(candidates.isEmpty()==false){
            String[] split = candidates.split(";");
            User candidate = dboh.findUnUser(Integer.valueOf(split[0]));
            textContrat = author.getNom()+" "+author.getPrenom() + "et" +
                    candidate.getNom() + " " + candidate.getPrenom() +
                    "\nva signer le contrat pour \nl'offre : " +  off.getTitre() +
                    "pour une duree \nde " + off.getDurre();
        }
        intent.putExtra("contenu",textContrat);
        intent.putExtra("idOffre",idOffre);
        startActivity(intent);
    }
}
