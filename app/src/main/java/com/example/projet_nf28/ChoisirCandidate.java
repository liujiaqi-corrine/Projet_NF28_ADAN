package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ChoisirCandidate extends AppCompatActivity {
    private ListView mListView = null;
    private MyAdapter mAdapter = null;
    public static final List<String> mList = new ArrayList<String>();

    int idOffre;
    Offre off;
    DBOpenHelper dboh;

    int idCandiateOnClick=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choisir_candidate);

        Intent intent = getIntent();
        idOffre = intent.getIntExtra("idOffre",0);
        dboh = new DBOpenHelper();
        Log.d("ChoisirCandidate",String.valueOf(idOffre));

        if(idOffre!=0){
            off = dboh.findUnOffres(idOffre);
            Log.d("ChoisirCandidate",off.getTitre());
        }

        init();

    }


    private void init() {
        mList.clear();
        DBOpenHelper dboh = new DBOpenHelper();

        String listeCandidate = off.getCandidate();
        if(listeCandidate.isEmpty()==false && listeCandidate!=""){
            String[] split = listeCandidate.split(";");
            for (int i=0; i<split.length; i++){
                Artiste a;
                a = dboh.findUnArtistes(Integer.valueOf(split[i]));
                mList.add(String.valueOf(a.getId())+":"+a.getNom()+" "+a.getPrenom());
            }
        }

        //mList = new ArrayList<>(Arrays.asList(mListData));
        mAdapter = new MyAdapter(mList, this);
        mListView = findViewById(R.id.listView1);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i(TAG, "Position=" + position);

                // Fait Planter le SmartPhone
                //String item = (String)parent.getItemAtPosition(position);
                //String  item = ((TextView)view).getText().toString();

                //Toast.makeText(getApplicationContext(), position + " " + id, Toast.LENGTH_SHORT).show();
                String obj = (String) mAdapter.getItem((int) id);
                idCandiateOnClick = extraireId(obj);
            }
        });
    }

    public int extraireId(String chaine){
        int id2p = chaine.indexOf(":");
        String res = "";
        if(id2p>0){
            res = chaine.substring(0, id2p);
            return Integer.parseInt(res);
        }
        else{
            return -1;
        }

    }

    public void RetourneAMesOffre(View view){
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }

    public void RedigerUnContrat(View view) {
        if(idCandiateOnClick!=0){
            Intent intent = new Intent(this, RedigerContrat.class);
            User author = dboh.findUnUser(off.getAuthor());
            String textContrat="";
            if(idCandiateOnClick!=0){
                User candidate = dboh.findUnUser(idCandiateOnClick);
                textContrat = author.getNom()+" "+author.getPrenom() + " et " +
                        candidate.getNom() + " " + candidate.getPrenom() +
                        "\nva signer le contrat pour \nl'offre : " +  off.getTitre() +
                        "pour une duree \nde " + off.getDurre();
            }
            intent.putExtra("contenu",textContrat);
            intent.putExtra("idOffre",idOffre);
            startActivity(intent);
        }
    }

    public void SupprimerCandidate(View view){
        if(off.getCandidate().contains(";")){// plus un candidate
            Intent intent = new Intent(this, ModifierUnOffre.class);
            intent.putExtra("idOffre",idOffre);
            String res = "";
            String candidate = off.getCandidate();
            if(idCandiateOnClick!=0){
                String[] split = candidate.split(";");
                for (int i=0; i<split.length; i++){
                    if(idCandiateOnClick != Integer.parseInt(split[i])){
                        res += split[i] + ";";
                    }
                }
                /*res = res.substring(0, res.length()-1);*/
                dboh.OffreAjouteCandidat(idOffre,res);
            }
            startActivity(intent);
        }
    }

    public void infoDetaille(View view){
        if(idCandiateOnClick!=0){
            Intent intent = new Intent(this, InfoArtiste.class);
            intent.putExtra("idPerso",idCandiateOnClick);
            intent.putExtra("idOffre",idOffre);
            startActivity(intent);
        }
    }


}
