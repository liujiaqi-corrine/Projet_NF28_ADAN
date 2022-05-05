package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixInscriptionConnnexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_inscription_connnexion);
    }

    public void ChoixTypeInscrire(View view) {
        Intent intent = new Intent(this, ChoixTypeInscription.class);
        startActivity(intent);
    }

    public void Connecter(View view) {
        Intent intent = new Intent(this, Connexion.class);
        startActivity(intent);
    }


}
