package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetailleDeMesOffre extends AppCompatActivity {
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detaille_un_de_mes_offres);
        b1 = findViewById(R.id.button9);//OA
    }


    public void RtrAMesOffres(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void ModifierUnOffres(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void ChoixCandidate(View view) {

    }

    public void Postuler(View view) {
        if(b1.getText() == "Postuler"){
            b1.setText("Annuler");
        }
        else{
            b1.setText("Postuler");
        }

    }
}
