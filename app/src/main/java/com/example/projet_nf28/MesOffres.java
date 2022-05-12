package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MesOffres extends AppCompatActivity {
    private ListView mListView1 = null;
    private MyAdapter mAdapter1 = null;
    private static final List<String> mList1 = new ArrayList<String>(Arrays.asList("Offre 1","Offre 2"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mes_offres);
        init();
    }

    private void init(){
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
                String nomOffre="";
                AModifierOffre(nomOffre);
            }
        });
    }

    public void AModifierOffre(String nomInfo){
        Intent intent = new Intent(this, ModifierUnOffre.class);
        startActivity(intent);
    }

    public void RtrAEspacePerso(View view) {
        Intent intent = new Intent(this, EspacePerso.class);
        startActivity(intent);
    }
}
