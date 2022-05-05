package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InfoPersonne extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_personne);
    }

    public void retourAnnonce(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
    }

    public void AContact(View view) {
        Intent intent = new Intent(this, InfoContact.class);
        startActivity(intent);
    }

    public void retourInfoPeronnse(View view) {
        Intent intent = new Intent(this, InfoPersonne.class);
        startActivity(intent);
    }
}
