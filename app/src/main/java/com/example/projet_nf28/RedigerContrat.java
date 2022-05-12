package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class RedigerContrat extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rediger_contrat);
    }

    public void RtrAModifierUnOffre(View view) {
        Intent intent = new Intent(this, ModifierUnOffre.class);
        startActivity(intent);
    }

    //avoir un fichier pdf à sauvegarder
    public void RedigerContrat(View view) {

    }
}
