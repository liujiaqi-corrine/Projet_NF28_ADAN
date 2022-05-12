package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InfoContact extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_contact);
    }

    public void retourInfoPeronnse(View view) {
        Intent intent = new Intent(this, InfoPersonne.class);
        startActivity(intent);
    }
}
