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
import java.util.List;

public class Annonces extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private ListView mListView1 = null;
    private ListView mListView2 = null;
    private MyAdapter mAdapter1 = null;
    private MyAdapter mAdapter2 = null;
    private static final List<String> mList1 = new ArrayList<String>(Arrays.asList("Artiste 1","Artiste 2","Artiste 3"));
    private static final List<String> mList2 = new ArrayList<String>(Arrays.asList("Offre 1","Offre 2","Offre 3"));
    private static final String TAG = "Annonces";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.annonces);
        init();

    }

    private void init(){
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

                //Toast.makeText(getApplicationContext(), "Test"+position, Toast.LENGTH_SHORT).show();
                List<String> thisInfos = new ArrayList<String>();
                AInfoPersonne(thisInfos);
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
                AInfoOffre(0);
            }
        });

        b1 = findViewById(R.id.button5);//OA
        b2 = findViewById(R.id.button8);//AO
    }

    public void AInfoPersonne(List<String> info){
        Intent intent = new Intent(this, InfoPersonne.class);
        startActivity(intent);
    }

    public void AInfoOffre(int idOffre){
        Intent intent = new Intent(this, InfoOffre.class);
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
        //mListView2.setVisibility(View.GONE);
        b1.setTextColor(getResources().getColor(R.color.black));
        b2.setTextColor(getResources().getColor(R.color.white));
    }

    public void AEspacePerso(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }


// android:textColor="@android:color/black
}
