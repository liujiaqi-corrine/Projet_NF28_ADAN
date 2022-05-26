package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InfoOffre extends AppCompatActivity {
    private Button b1;

    TextView titre;
    TextView decscription;
    TextView argent;
    TextView recurrence;
    TextView nbCandidate;
    TextView durre;
    TextView adresse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_offre);
        b1 = findViewById(R.id.button7);//OA

        titre = (TextView) findViewById(R.id.textView25);
        decscription = (TextView) findViewById(R.id.textView27);
        argent = (TextView) findViewById(R.id.textView28);
        recurrence = (TextView) findViewById(R.id.textView29);
        nbCandidate = (TextView) findViewById(R.id.textView30);
        durre = (TextView) findViewById(R.id.textView31);
        adresse = (TextView) findViewById(R.id.textView32);

        Intent intent = getIntent();
        int idOffre = intent.getIntExtra("idOffre",0);
        //Toast.makeText(getApplicationContext(), "Test"+idOffre, Toast.LENGTH_SHORT).show();
        if(idOffre!=0){
            DBOpenHelper dboh = new DBOpenHelper();
            Offre off = dboh.findUnOffres(idOffre);
            titre.setText(off.getTitre());
            decscription.setText(off.getDescription());
            argent.setText(String.valueOf(off.getArgent()));
            recurrence.setText(off.getRecurrence());
            nbCandidate.setText(String.valueOf(off.getNbCandidate()));
            durre.setText(off.getDurre());
            adresse.setText(off.getAdresse());
        }

    }

    public void retourAnnonce(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
    }

    public void Postuler(View view) {
        if(b1.getText() == "Postuler"){
            b1.setText("Annuler");
        }
        else{
            b1.setText("Postuler");
        }
    }
}
