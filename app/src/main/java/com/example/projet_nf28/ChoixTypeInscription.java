package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixTypeInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_type_inscription);
    }

    public void InscrireArtiste(View view) {
        Intent intent = new Intent(this, InscriptionArtiste.class);
        startActivity(intent);
    }

    public void InscrireEmployeur(View view) {
        Intent intent = new Intent(this, InscriptionEmployeur.class);
        startActivity(intent);
    }

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }
}

