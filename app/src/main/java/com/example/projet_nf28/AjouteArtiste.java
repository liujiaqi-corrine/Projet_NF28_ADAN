package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AjouteArtiste extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoute_artiste);
    }

    public void OkAEspacePerso(View view) {
        //A sauvegarder
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }

    public void RtrAChoixAjouteRole(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }
}
