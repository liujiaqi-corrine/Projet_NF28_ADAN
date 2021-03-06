package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InfoPersoSelf extends AppCompatActivity {
    EditText profession;
    RadioGroup niveau;
    EditText cv;
    EditText oeuvre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_perso_self);

        profession = (EditText) findViewById(R.id.editTextTextPersonName4);
        niveau = (RadioGroup) findViewById(R.id.radioGroupe);
        cv = (EditText) findViewById(R.id.editTextTextPersonName5);
        oeuvre = (EditText) findViewById(R.id.editTextTextPersonName6);

        int idPerso = MainActivity.getLoginMemberID();
        if(idPerso!=0){
            DBOpenHelper dboh = new DBOpenHelper();
            Artiste art = dboh.findUnArtistes(idPerso);
            //Toast.makeText(getApplicationContext(), "Test "+idPerso, Toast.LENGTH_SHORT).show();
            profession.setText(art.getProfession());
            //'Debutant','Amateur','Professionnelle'
            RadioButton b;
            switch (art.getNiveau()){
                case "Debutant":
                    b= (RadioButton) findViewById(R.id.radioButton);
                    b.setChecked(true);
                    break;
                case "Amateur":
                    b = (RadioButton) findViewById(R.id.radioButton3);
                    b.setChecked(true);
                    break;
                case "Professionnelle":
                    b = (RadioButton) findViewById(R.id.radioButton2);
                    b.setChecked(true);
                    break;
                default:
                    break;
            }
            cv.setText(art.getCv());
            oeuvre.setText(art.getOevre());
        }

    }

    public boolean valide(){
        boolean test = true;
        /*buttonSelected = (RadioButton) findViewById(buttonSelectedId);*/
        if(profession.getText().toString().isEmpty()){
            test=false;
            profession.setError("case obligatoire");
        }
        return test;
    }

    public void OkModifier(View view) {
        if(valide()){
            Intent intent=getIntent();
            int id = intent.getIntExtra("id",0);
            if(id!=0){
                Artiste art = new Artiste();
                art.setId(id);
                art.setProfession(profession.getText().toString());
                Button buttonSelected = (Button) findViewById(niveau.getCheckedRadioButtonId());
                art.setNiveau(buttonSelected.getText().toString());
                art.setCv(cv.getText().toString());
                art.setOevre(oeuvre.getText().toString());
                DBOpenHelper dboh = new DBOpenHelper();
                dboh.ModiferArtiste(art);
            }

            Intent intent2 = new Intent(this, InfoUser.class);
            startActivity(intent2);
        }

    }

    public void retourEspacePerso(View view) {
        Intent intent = new Intent(this, InfoUser.class);
        startActivity(intent);
    }
}
