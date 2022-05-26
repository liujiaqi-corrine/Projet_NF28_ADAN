package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Connexion extends AppCompatActivity {

    EditText email;
    EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        email = (EditText) findViewById(R.id.editTextTextPersonName);
        mdp = (EditText) findViewById(R.id.editTextTextPassword);
    }

    public boolean ValideUser(){
        DBOpenHelper dboh = new DBOpenHelper();
        boolean email_inexist = dboh.verifierEmail(email.getText().toString());;
        boolean test = true;
        if(email.getText().toString().isEmpty()){
            test=false;
            email.setError("case obligatoire");
        }
        if(mdp.getText().toString().isEmpty()){
            test=false;
            mdp.setError("case obligatoire");
        }
        if(email_inexist==true){
            test=false;
            email.setError("email inexiste");
        }
        else{
            test=dboh.verifierMdp(email.getText().toString(),mdp.getText().toString());
            if(test==false){
                mdp.setError("mot de pasee incorrect");
            }
        }

        return test;
    }


    public void OkAAnnonces(View view) {
        Log.d("OkAAnnonces","OK");
        if(ValideUser()){
            DBOpenHelper dboh = new DBOpenHelper();
            int id = dboh.findIdUser(email.getText().toString());
            MainActivity.setLoginMemberID(id);

            Intent intent = new Intent(this, Annonces.class);
            startActivity(intent);
        }
        /*ReadUnUser();*/
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

    public void retourDerriere(View view) {
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }

}
