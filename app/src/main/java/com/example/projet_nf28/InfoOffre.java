package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InfoOffre extends AppCompatActivity {
    private Button b1;

    TextView titre;
    TextView decscription;
    TextView argent;
    TextView recurrence;
    TextView nbCandidate;
    TextView durre;
    TextView adresse;

    int idOffre;
    Offre off;
    DBOpenHelper dboh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_offre);
        b1 = findViewById(R.id.button7);//OA

        titre = (TextView) findViewById(R.id.textView25);
        decscription = (TextView) findViewById(R.id.textView27);
        argent = (TextView) findViewById(R.id.textView28);
        recurrence = (TextView) findViewById(R.id.textView29);
        nbCandidate = (TextView) findViewById(R.id.textView30);
        durre = (TextView) findViewById(R.id.textView31);
        adresse = (TextView) findViewById(R.id.textView32);

        Intent intent = getIntent();
        idOffre = intent.getIntExtra("idOffre",0);
        //Toast.makeText(getApplicationContext(), "Test"+idOffre, Toast.LENGTH_SHORT).show();
        dboh = new DBOpenHelper();
        if(idOffre!=0){
            off = dboh.findUnOffres(idOffre);
            titre.setText(off.getTitre());
            decscription.setText(off.getDescription());
            argent.setText(String.valueOf(off.getArgent()));
            recurrence.setText(off.getRecurrence());
            nbCandidate.setText(String.valueOf(off.getNbCandidate()));
            durre.setText(off.getDurre());
            adresse.setText(off.getAdresse());
        }
        User usr = dboh.findUnUser(MainActivity.getLoginMemberID());
        if(usr.getIsArtiste() == 0){
            b1.setEnabled(false);
            b1.setBackgroundColor(Color.GRAY);
        }

    }

    public void retourAnnonce(View view) {
        Intent intent = new Intent(this, Annonces.class);
        startActivity(intent);
    }

    public void Postuler(View view) {
        int idself = MainActivity.getLoginMemberID();
        if(b1.getText().toString() == "Postuler"){
            b1.setText("Annuler");
            String candidate = off.getCandidate();
            Log.d("Postuler", candidate);
            boolean existe = false;
            if(candidate.isEmpty()==false && candidate!=""){
                String res="";
                Log.d("Postuler","non vide");
                String[] split = candidate.split(";");

                for (int i=0; i<split.length; i++){
                    if(idself != Integer.parseInt(split[i])){
                        res+=split[i]+";";
                    }
                }
                res=res + String.valueOf(idself);
                Log.d("candidate Choix1",res);
                dboh.OffreAjouteCandidat(idOffre,res);
                Log.d("Place1", "Place1");

            }
            else {
                Log.d("Place2", "Place2");
                candidate="";
                candidate = candidate + String.valueOf(idself);
                Log.d("candidate Choix2",candidate);
                dboh.OffreAjouteCandidat(idOffre,candidate);
            }

        }
        else if(b1.getText().toString() == "Annuler"){
            Log.d("Place3", "Place3");
            b1.setText("Postuler");

            String res = "";
            String candidate = off.getCandidate();
            Log.d("Postuler", candidate);
            boolean existe = false;
            if(candidate.isEmpty()==false){
                Log.d("Postuler","non vide");
                String[] split = candidate.split(";");
                for (int i=0; i<split.length; i++){
                    if(idself != Integer.parseInt(split[i])){
                        res += split[i] + ";";
                    }
                }
                /*res = res.substring(0, res.length()-1);*/
                if(res.isEmpty()||res==""){
                    res ="null";
                }
                dboh.OffreAjouteCandidat(idOffre,res);
            }


        }
        else{
            b1.setText("Postuler");
            Log.d("Place4", "Place4");
        }

    }
}
