package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EspacePerso extends AppCompatActivity {

    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.espace_perso);

        b1  = (Button) findViewById(R.id.button1);
        b2  = (Button) findViewById(R.id.button2);
        b3  = (Button) findViewById(R.id.button3);
        b4  = (Button) findViewById(R.id.button15);


        int id = MainActivity.getLoginMemberID();
        DBOpenHelper dboh =  new DBOpenHelper();
        User user =  dboh.findUnUser(id);
        if(user.getIsEmployer() == 0){
            b2.setEnabled(false);
            b2.setBackgroundColor(Color.GRAY);
        }
        else{
            if(user.getIsArtiste() == 1){
                b4.setEnabled(false);
                b4.setBackgroundColor(Color.GRAY);
            }
        }



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
