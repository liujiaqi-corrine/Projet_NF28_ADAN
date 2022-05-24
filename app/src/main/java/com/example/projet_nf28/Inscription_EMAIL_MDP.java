package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription_EMAIL_MDP extends AppCompatActivity {
    EditText email;
    EditText mdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_email_mdp);

        email = (EditText) findViewById(R.id.editTextTextPersonName1);
        mdp = (EditText) findViewById(R.id.editTextTextPassword2);
}

    public boolean valide(){
        DBOpenHelper dboh = new DBOpenHelper();
        boolean isEmailOk = dboh.verifierEmail(email.getText().toString());
        Log.d("InscriptionArtiste", "isEmailOk : "+ isEmailOk);
        boolean test = true;
        if(email.getText().toString().isEmpty()){
            test=false;
            email.setError("case obligatoire");
        }
        if(mdp.getText().toString().isEmpty()){
            test=false;
            mdp.setError("case obligatoire");
        }
        if(isEmailOk==false){//email existe
            test=false;
            email.setError("email existe deja");
        }
        return test;
    }

    public void ChoixTypeInscrire(View view) {
        if(valide()){
            User usr = new User();
            usr.setEmail(email.getText().toString());
            usr.setMdp(mdp.getText().toString());
            usr.setNom("");
            usr.setPrenom("");
            usr.setIsArtiste(0);
            usr.setIsEmployer(0);

            DBOpenHelper dboh = new DBOpenHelper();
            dboh.addUser(usr);

            Intent intent = new Intent(this, ChoixTypeInscription.class);
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("mdp", mdp.getText().toString());

            startActivity(intent);

        }

    }

    public void Retour(View view) {
        Intent intent = new Intent(this, ChoixInscriptionConnnexion.class);
        startActivity(intent);
    }

}
