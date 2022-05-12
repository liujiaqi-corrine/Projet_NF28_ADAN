package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixAjouteRole extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_ajoute_role);
    }


    public void AAhouteArtist(View view) {
        Intent intent = new Intent(this, AjouteArtiste.class);
        startActivity(intent);
    }

    public void AAjouteEmployeur(View view) {
        Intent intent = new Intent(this, AjouteEmployeur.class);
        startActivity(intent);
    }

    public void RtrAEspacePerso(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }
}
