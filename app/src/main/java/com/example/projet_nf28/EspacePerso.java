package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EspacePerso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espace_perso);
    }

    public void AAnnonce(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
    }

    public void AInfoPersoSelf(View view) {
        Intent intent = new Intent(this, InfoUser.class);
        startActivity(intent);
    }

    public void AMesOffre(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void AAJouteRole(View view) {
        Intent intent = new Intent(this, ChoixAjouteRole.class);
        startActivity(intent);
    }

    public void LogOut(View view) {
        MainActivity.setLoginMemberID(0);
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }
}
