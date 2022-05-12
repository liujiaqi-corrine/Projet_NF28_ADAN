package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
    }

    public void OkAAnnonces(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
        ReadUnUser();
    }


    public void ReadUnUser()  {
        try {
            Personne user = ReadJSON.readUnePersonneJSONFile(this, 0);
            Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
        } catch(Exception e)  {
            Toast.makeText(getApplicationContext(), "err", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
