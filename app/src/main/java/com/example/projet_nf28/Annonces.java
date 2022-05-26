package com.example.projet_nf28;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Annonces extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private ListView mListView1 = null;
    private ListView mListView2 = null;
    private MyAdapter mAdapter1 = null;
    private MyAdapter mAdapter2 = null;
    public static final List<String> mList1 = new ArrayList<String>();
    public static final List<String> mList2 = new ArrayList<String>();
    private static final String TAG = "Annonces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annonces);
        init();

    }

    private void init(){
        mList1.clear();
        mList2.clear();
        DBOpenHelper dboh = new DBOpenHelper();
        List<Artiste> lart = dboh.findArtistes();
        List<Offre> loff = dboh.findOffres();
        for(int i=0;i<lart.size();i++){
            mList1.add(lart.get(i).getId()+":"+lart.get(i).getNom()+" "+lart.get(i).getPrenom());
        }
        for(int i=0;i<loff.size();i++){
            mList2.add(loff.get(i).getId()+":"+loff.get(i).getTitre());
        }

        //mList = new ArrayList<>(Arrays.asList(mListData));
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

                //Toast.makeText(getApplicationContext(), position + " " + id, Toast.LENGTH_SHORT).show();
                String obj = (String) mAdapter1.getItem((int)id);
                AInfoPersonne(obj);

            }
        });

        mAdapter2 = new MyAdapter(mList2, this);
        mListView2 = findViewById(R.id.listView2);
        mListView2.setAdapter(mAdapter2);
        mListView2.setVisibility(View.GONE);//View.VISIBLE
        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.i(TAG, "Position=" + position);

                // Fait Planter le SmartPhone
                //String item = (String)parent.getItemAtPosition(position);
                //String  item = ((TextView)view).getText().toString();

                //Toast.makeText(getApplicationContext(), "Test"+position, Toast.LENGTH_SHORT).show();
                String obj = (String) mAdapter2.getItem((int)id);
                AInfoOffre(obj);
            }
        });

        b1 = findViewById(R.id.button5);//OA
        b2 = findViewById(R.id.button8);//AO
    }

    public void AInfoPersonne(String contenu){
        Intent intent = new Intent(this, InfoPersonne.class);
        Log.d("AInfoPersonne", String.valueOf(extraireId(contenu)));
        intent.putExtra("idPerso",extraireId(contenu));
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

    public void AInfoOffre(String contenu){
        Intent intent = new Intent(this, InfoOffre.class);
        Log.d("AInfoPersonne", String.valueOf(extraireId(contenu)));
        intent.putExtra("idOffre",extraireId(contenu));
        startActivity(intent);
    }

    public void changepanel_AO(View view){
        mListView1.setVisibility(View.GONE);
        mListView2.setVisibility(View.VISIBLE);
        b1.setTextColor(getResources().getColor(R.color.white));
        b2.setTextColor(getResources().getColor(R.color.black));
    }

    public void changepanel_OA(View view){
        mListView1.setVisibility(View.VISIBLE);
        mListView2.setVisibility(View.GONE);
        b1.setTextColor(getResources().getColor(R.color.black));
        b2.setTextColor(getResources().getColor(R.color.white));
    }

    public void AEspacePerso(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }


// android:textColor="@android:color/black
}
