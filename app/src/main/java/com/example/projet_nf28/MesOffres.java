package com.example.projet_nf28;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MesOffres extends AppCompatActivity {
    private ListView mListView1 = null;
    private MyAdapter mAdapter1 = null;
    private static final List<String> mList1 = new ArrayList<String>();
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_offres);

        //pour le moment , on n'a pas cette fonction
        b1  = (Button) findViewById(R.id.button12);
        b1.setEnabled(false);
        b1.setBackgroundColor(Color.GRAY);

        init();
    }

    private void init(){
        mList1.clear();
        DBOpenHelper dboh = new DBOpenHelper();
        List<Offre> loff = dboh.findOffresAvecAuthors(MainActivity.getLoginMemberID());
        for(int i=0;i<loff.size();i++){
            mList1.add(loff.get(i).getId()+":"+loff.get(i).getTitre());
        }

        mAdapter1 = new MyAdapter(mList1, this);
        mListView1 = findViewById(R.id.listView1);
        mListView1.setAdapter(mAdapter1);
        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i(TAG, "Position=" + position);

                // Fait Planter le SmartPhone
                //String item = (String)parent.getItemAtPosition(position);
                //String  item = ((TextView)view).getText().toString();

                //Toast.makeText(getApplicationContext(), "Test"+position, Toast.LENGTH_SHORT).show();
                String obj = (String) mAdapter1.getItem((int)id);
                AModifierOffre(obj);
            }
        });
    }

    public void AModifierOffre(String contenu){
        Intent intent = new Intent(this, ModifierUnOffre.class);
        Log.d("AModifierOffre",contenu);
        intent.putExtra("idOffre",extraireId(contenu));
        Log.d("AModifierOffre", String.valueOf(extraireId(contenu)));
        startActivity(intent);
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

    public void RtrAEspacePerso(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }

    public void Ajouter(View view) {
        Intent intent = new Intent(this, AjouteUnOffre.class);
        startActivity(intent);
    }

    public void Supprimer(View view) {


    }
}
