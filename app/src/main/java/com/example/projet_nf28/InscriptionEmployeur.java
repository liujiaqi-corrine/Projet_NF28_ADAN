package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionEmployeur extends AppCompatActivity {
    EditText nom;
    EditText prenom;

    RadioGroup type;
    EditText certificat;
    Button buttonSelected;

    String email;
    String mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insciption_employeur);

        nom = (EditText) findViewById(R.id.editTextTextPersonName1);
        prenom = (EditText) findViewById(R.id.editTextTextPersonName2);
        type = (RadioGroup) findViewById(R.id.radioGroupe);
        certificat = (EditText) findViewById(R.id.editTextTextPersonName5);
    }

    public boolean valide(){
        boolean test = true;
        if(nom.getText().toString().isEmpty()){
            test=false;
            nom.setError("case obligatoire");
        }

        if(prenom.getText().toString().isEmpty()){
            test=false;
            prenom.setError("case obligatoire");
        }

        //comment warning ??????
        int buttonSelectedId = type.getCheckedRadioButtonId();
        if(buttonSelectedId==-1){
            test=false;
        }
        return test;
    }

    public void OkAAnnonces(View view) {
        if(valide()){
            Log.d("OkAAnnonces","valide");
            addEmployer();
            Intent intent = new Intent(this, Annonces.class);
            startActivity(intent);
        }
        else{
            Log.e("OkAAnnonces","invalide");
        }
    }

    public void addEmployer(){
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
            usr.setIsArtiste(0);
            usr.setIsEmployer(1);

            Log.d("addEmployer id : ", String.valueOf(usr.getId()));
            Log.d("addEmployer nom : ",usr.getNom());
            Log.d("addEmployer prenom : ",usr.getPrenom());

            dboh.ModiferUser(usr);

            Employer emp;
            emp = new Employer(usr);
            emp.setId(id);
            buttonSelected = (Button) findViewById(type.getCheckedRadioButtonId());
            emp.setType(buttonSelected.getText().toString());
            emp.setEmail(email);

            Log.d("addEmployer type : ",buttonSelected.getText().toString());
            if(certificat.getText().toString().isEmpty()==false){
                emp.setCertificat(certificat.getText().toString());
            }
            else{
                emp.setCertificat("");
            }
            dboh.addEmployer(emp);

            Log.d("addEmployer","ok ajouter employer bdd");
        }
        else{
            Log.e("addEmployer","err à employer à bdd");
        }
    }

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixTypeInscription.class);
        startActivity(intent);
    }
}
