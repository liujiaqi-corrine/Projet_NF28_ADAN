package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InfoOffre extends AppCompatActivity {
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_offre);
        b1 = findViewById(R.id.button7);//OA
    }

    public void retourAnnonce(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
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