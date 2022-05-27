package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RedigerContrat extends AppCompatActivity {
    TextView contenuContrat;
    DBOpenHelper dboh;
    int idOffre;
    String textContrat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rediger_contrat);

        dboh = new DBOpenHelper();

        Intent intent = getIntent();
        idOffre = intent.getIntExtra("idOffre",0);
        textContrat=intent.getStringExtra("contenu");
        contenuContrat = (TextView) findViewById(R.id.editTextTextMultiLine);
        contenuContrat.setText(textContrat);
    }

    public void RtrAModifierUnOffre(View view) {
        Intent intent = new Intent(this, ModifierUnOffre.class);
        startActivity(intent);
    }

    //avoir un fichier pdf à sauvegarder
    public void RedigerContrat(View view) {



    }
}
