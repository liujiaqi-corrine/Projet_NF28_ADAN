package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionEmployeur extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insciption_employeur);
    }

    public void OkAAnnonces(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
    }

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixTypeInscription.class);
        startActivity(intent);
    }
}
