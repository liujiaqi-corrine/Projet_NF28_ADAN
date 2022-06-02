package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixAjouteRole extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_ajoute_role);

        b1  = (Button) findViewById(R.id.button1);
        b2  = (Button) findViewById(R.id.button2);

        b1.setEnabled(false);
        b1.setBackgroundColor(Color.GRAY);
        b2.setEnabled(false);
        b2.setBackgroundColor(Color.GRAY);
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
