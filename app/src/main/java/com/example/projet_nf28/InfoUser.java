package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InfoUser extends AppCompatActivity {
    EditText nom;
    EditText prenom;
    EditText id;
    EditText email;
    EditText mdp;
    EditText isArtiste;
    EditText isEmployer;
    Button bart,bemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_user);
        id = (EditText) findViewById(R.id.editTextTextPersonName16);
        nom = (EditText) findViewById(R.id.editTextTextPersonName1);
        prenom = (EditText) findViewById(R.id.editTextTextPersonName2);
        email = (EditText) findViewById(R.id.editTextTextPersonName3);
        mdp = (EditText) findViewById(R.id.editTextTextPersonName17);
        isArtiste = (EditText) findViewById(R.id.editTextTextPersonName18);
        isEmployer = (EditText) findViewById(R.id.editTextTextPersonName19);
        id.setEnabled(false);
        email.setEnabled(false);
        isArtiste.setEnabled(false);
        isEmployer.setEnabled(false);

        bart = (Button) findViewById(R.id.button16);
        bemp = (Button) findViewById(R.id.button17);
        DBOpenHelper dboh = new DBOpenHelper();
        User usr = dboh.findUnUser(MainActivity.getLoginMemberID());
        id.setText(String.valueOf(usr.getId()));
        nom.setText(usr.getNom());
        prenom.setText(usr.getPrenom());
        email.setText(usr.getEmail());
        mdp.setText(usr.getMdp());
        isArtiste.setText(String.valueOf(usr.getIsArtiste()));
        isEmployer.setText(String.valueOf(usr.getIsEmployer()));
        if(usr.getIsArtiste() == 0){
            bart.setEnabled(false);
            bart.setBackgroundColor(Color.GRAY);
        }
        if(usr.getIsEmployer() == 0){
            bemp.setEnabled(false);
            bemp.setBackgroundColor(Color.GRAY);
        }


    }

    public boolean valide(){
        boolean test = true;
        /*buttonSelected = (RadioButton) findViewById(buttonSelectedId);*/
        if(nom.getText().toString().isEmpty()){
            test=false;
            nom.setError("case obligatoire");
        }

        if(prenom.getText().toString().isEmpty()){
            test=false;
            prenom.setError("case obligatoire");
        }

        if(mdp.getText().toString().isEmpty()){
            test=false;
            mdp.setError("case obligatoire");
        }
        return test;
    }

    public void OkModifier(View view) {
        if(valide()){
            User usr = new User(Integer.parseInt(id.getText().toString()),
                    nom.getText().toString(),
                    prenom.getText().toString(),
                    email.getText().toString(),
                    Integer.parseInt(isArtiste.getText().toString()),
                    Integer.parseInt(isEmployer.getText().toString()));
            DBOpenHelper dboh = new DBOpenHelper();
            dboh.ModiferUser(usr);

            Intent intent = new Intent(this, EspacePerso.class);
            startActivity(intent);
        }
    }

    public void AInfoArtiste(View view) {
        Intent intent = new Intent(this, InfoPersoSelf.class);
        intent.putExtra("id",Integer.parseInt(id.getText().toString()));
        startActivity(intent);
    }

    public void AInfoEmployer(View view) {
        Intent intent = new Intent(this, InfoEmployer.class);
        intent.putExtra("id",Integer.parseInt(id.getText().toString()));
        startActivity(intent);
    }

    public void retourEspacePerso(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }
}
