package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.autofill.AutofillId;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.StringWriter;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionArtiste extends AppCompatActivity {
    EditText nom;
    EditText prenom;
    EditText email;
    EditText profession;
    RadioGroup niveau;
    EditText cv;
    EditText oeuvre;
    Button buttonSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_artiste);

        nom = (EditText) findViewById(R.id.editTextTextPersonName1);
        prenom = (EditText) findViewById(R.id.editTextTextPersonName2);
        email = (EditText) findViewById(R.id.editTextTextPersonName3);
        profession = (EditText) findViewById(R.id.editTextTextPersonName4);
        niveau = (RadioGroup) findViewById(R.id.radioGroupe);
        cv = (EditText) findViewById(R.id.editTextTextPersonName5);
        oeuvre = (EditText) findViewById(R.id.editTextTextPersonName6);

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

        if(email.getText().toString().isEmpty()){
            test=false;
            email.setError("case obligatoire");
        }
        else{
            DBOpenHelper dboh = new DBOpenHelper();
            boolean isEmailOk = dboh.verifierEmail(email.getText().toString());
            Log.d("InscriptionArtiste", "isEmailOk : "+ isEmailOk);
            if(isEmailOk==false){
                test=false;
                email.setError("email existe deja");
            }
        }

        if(profession.getText().toString().isEmpty()){
            test=false;
            profession.setError("case obligatoire");
        }

        //comment warning ??????
        int buttonSelectedId = niveau.getCheckedRadioButtonId();
        if(buttonSelectedId==-1){
            test=false;
        }

        return test;
    }

    public void OkAAnnonces(View view) {
        if(valide()){
            Intent intent = new Intent(this, Annonces.class);
            startActivity(intent);
            //enregUNArtisteAJSON();
            //addArtiste();
        }
    }

    public void addArtiste(){
        DBOpenHelper dboh = new DBOpenHelper();
        User usr = new User();
        usr.setId(0);
        usr.setNom(nom.getText().toString());
        usr.setNom(prenom.getText().toString());
        usr.setEmail(email.getText().toString());
        usr.setIsArtiste(1);
        usr.setIsEmployer(0);

        dboh.addUser(usr);

        /*int id=0;
        id = dboh.findIdUser(email.getText().toString());

        if(id!=0){
            Artiste art = new Artiste(usr);
            art.setId(id);
            buttonSelected = (Button) findViewById(niveau.getCheckedRadioButtonId());
            art.setNiveau(buttonSelected.getText().toString());
            art.setProfession(profession.getText().toString());
            art.setEmail(email.getText().toString());
            if(cv.getText().toString().isEmpty()==false){
                art.setCv(cv.getText().toString());
            }
            if(oeuvre.getText().toString().isEmpty()==false){
                art.setOevre(oeuvre.getText().toString());
            }
            dboh.addArtiste(art);
            Log.d("InscriptionArtiste","Ok ajouter artiste bdd");
        }
        else{
            Log.e("InscriptionArtiste","impossible à ajouter à bdd");
        }*/
    }


    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixTypeInscription.class);
        startActivity(intent);
    }
}
