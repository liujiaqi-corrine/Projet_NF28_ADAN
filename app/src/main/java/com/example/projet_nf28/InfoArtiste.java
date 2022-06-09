package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoArtiste extends AppCompatActivity {
    TextView nom;
    TextView prenom;
    TextView profession;
    TextView email;
    TextView niveau;
    TextView cv;
    TextView oeuvre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_artiste);


        nom = (TextView) findViewById(R.id.textView14);
        prenom = (TextView) findViewById(R.id.textView19);
        profession = (TextView) findViewById(R.id.textView20);
        email = (TextView) findViewById(R.id.textView21);
        niveau = (TextView) findViewById(R.id.textView22);
        cv = (TextView) findViewById(R.id.textView23);
        oeuvre = (TextView) findViewById(R.id.textView24);

        Intent intent = getIntent();
        int idPerso = intent.getIntExtra("idPerso",0);
        if(idPerso!=0){
            DBOpenHelper dboh = new DBOpenHelper();
            Artiste art = dboh.findUnArtistes(idPerso);
            nom.setText(art.getNom());
            prenom.setText(art.getPrenom());
            profession.setText(art.getProfession());
            email.setText(art.getEmail());
            niveau.setText(art.getNiveau());
            cv.setText(art.getCv());
            oeuvre.setText(art.getOevre());
        }

    }

    public void retourChoisirCandidate(View view) {
        Intent intent1 = getIntent();
        int idOffre = intent1.getIntExtra("idOffre",0);
        Intent intent = new Intent(this, ChoisirCandidate.class);
        intent.putExtra("idOffre",idOffre);
        startActivity(intent);
    }

}

