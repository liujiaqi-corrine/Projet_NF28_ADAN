package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ModifierUnOffre extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifer_un_offre);
    }


    public void SauvegardEtRTrAMesOffres(View view) {
        //a sauvegarder
        //à choix à active si prorietaire
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void Postuler(View view) {
        //à choix à active ou pas (sauf propriétaire
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void RtrAMesOffres(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void RedigerUnContrat(View view) {
        Intent intent = new Intent(this, RedigerContrat.class);
        startActivity(intent);
    }
}
