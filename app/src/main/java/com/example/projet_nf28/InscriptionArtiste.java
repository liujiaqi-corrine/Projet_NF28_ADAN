package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.StringWriter;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionArtiste extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_artiste);
    }

    public void enregUNArtisteAJSON()  {
        try {
            StringWriter output = new StringWriter();

            Personne perso = WriteJSON.createArtiste();


            WriteJSON.writeJsonStream(output, perso);

            String jsonText = output.toString();

            Toast.makeText(getApplicationContext(), jsonText, Toast.LENGTH_SHORT).show();

        } catch(Exception e)  {
            Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void OkAAnnonces(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
        enregUNArtisteAJSON();
    }

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixTypeInscription.class);
        startActivity(intent);
    }
}
