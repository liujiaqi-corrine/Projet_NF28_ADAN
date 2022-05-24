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
        Intent intent1 = getIntent();
        String email=intent1.getStringExtra("email");
        String mdp=intent1.getStringExtra("mdp");

        Intent intent2 = new Intent(this, InscriptionArtiste.class);
        intent2.putExtra("email", email);
        intent2.putExtra("mdp", mdp);
        startActivity(intent2);
    }

    public void InscrireEmployeur(View view) {
        Intent intent1 = getIntent();
        String email=intent1.getStringExtra("email");
        String mdp=intent1.getStringExtra("mdp");

        Intent intent2 = new Intent(this, InscriptionEmployeur.class);
        intent2.putExtra("email", email);
        intent2.putExtra("mdp", mdp);
        startActivity(intent2);
    }

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }

}

