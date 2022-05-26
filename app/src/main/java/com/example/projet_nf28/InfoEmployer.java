package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class InfoEmployer extends AppCompatActivity {
    RadioGroup type;
    EditText certificat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_employer);

        certificat = (EditText) findViewById(R.id.editTextTextPersonName20);
        type = (RadioGroup) findViewById(R.id.radioGroupe);

        int idPerso = MainActivity.getLoginMemberID();
        if(idPerso!=0){
            DBOpenHelper dboh = new DBOpenHelper();
            Employer emp = dboh.findUnEmployer(idPerso);
            //Toast.makeText(getApplicationContext(), "Test "+idPerso, Toast.LENGTH_SHORT).show();
            certificat.setText(emp.getCertificat());
            //'Entreprise','Organisation','Collectivites','Particulier'
            RadioButton b;
            switch (emp.getType()){
                case "Entreprise":
                    b= (RadioButton) findViewById(R.id.radioButton);
                    b.setChecked(true);
                    break;
                case "Organisation":
                    b = (RadioButton) findViewById(R.id.radioButton2);
                    b.setChecked(true);
                    break;
                case "Collectivites":
                    b = (RadioButton) findViewById(R.id.radioButton3);
                    b.setChecked(true);
                    break;
                case "Particulier":
                    b = (RadioButton) findViewById(R.id.radioButton4);
                    b.setChecked(true);
                    break;
                default:
                    break;
            }
        }

    }

    public boolean valide(){
        boolean test = true;
        /*buttonSelected = (RadioButton) findViewById(buttonSelectedId);*/
        if(certificat.getText().toString().isEmpty()){
            test=false;
            certificat.setError("case obligatoire");
        }
        return test;
    }

    public void OkModifier(View view) {
        if(valide()){
            Intent intent=getIntent();
            int id = intent.getIntExtra("id",0);
            if(id!=0){
                Employer emp = new Employer();
                emp.setId(id);
                emp.setCertificat(certificat.getText().toString());
                Button buttonSelected = (Button) findViewById(type.getCheckedRadioButtonId());
                emp.setType(buttonSelected.getText().toString());
                DBOpenHelper dboh = new DBOpenHelper();
                dboh.ModiferEmployer(emp);
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
