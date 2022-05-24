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
    EditText profession;

    RadioGroup niveau;
    EditText cv;
    EditText oeuvre;
    Button buttonSelected;

    String email;
    String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_artiste);

        nom = (EditText) findViewById(R.id.editTextTextPersonName1);
        prenom = (EditText) findViewById(R.id.editTextTextPersonName2);
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
            addArtiste();
            Intent intent = new Intent(this, Annonces.class);
            startActivity(intent);
            //enregUNArtisteAJSON();

        }
    }

    public void addArtiste(){
        int id=0;
        DBOpenHelper dboh = new DBOpenHelper();

        Intent intent = getIntent();
        email=intent.getStringExtra("email");
        mdp=intent.getStringExtra("mdp");

        Log.d("addArtiste:",email+" "+mdp);

        id = dboh.findIdUser(email);

        if(id>0){
            User usr = new User();
            usr.setId(id);

            usr.setNom(nom.getText().toString());
            usr.setPrenom(prenom.getText().toString());
            usr.setIsArtiste(1);
            usr.setIsEmployer(0);

            Log.d("addArtiste id : ", String.valueOf(usr.getId()));
            Log.d("addArtiste nom : ",usr.getNom());
            Log.d("addArtiste prenom : ",usr.getPrenom());

            dboh.ModiferUser(usr);

            Artiste art = new Artiste(usr);
            art.setId(id);
            buttonSelected = (Button) findViewById(niveau.getCheckedRadioButtonId());
            art.setNiveau(buttonSelected.getText().toString());
            art.setProfession(profession.getText().toString());
            art.setEmail(email);

            Log.d("addArtiste niveau : ",buttonSelected.getText().toString());
            if(cv.getText().toString().isEmpty()==false){
                art.setCv(cv.getText().toString());
            }
            else{
                art.setCv("");
            }
            if(oeuvre.getText().toString().isEmpty()==false){
                art.setOevre(oeuvre.getText().toString());
            }
            else{
                art.setOevre("");
            }
            dboh.addArtiste(art);
            Log.d("InscriptionArtiste","Ok ajouter artiste bdd");
        }
        else{
            Log.e("InscriptionArtiste","impossible à ajouter à bdd");
        }
    }

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixTypeInscription.class);
        startActivity(intent);
    }
}
