package com.example.projet_nf28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void commence(View view) {
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }

}