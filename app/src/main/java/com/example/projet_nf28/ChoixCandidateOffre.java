package com.example.projet_nf28;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChoixCandidateOffre extends AppCompatActivity {
    private ListView mListView1 = null;
    private MyAdapter mAdapter1 = null;
    private static final List<String> mList1 = new ArrayList<String>(Arrays.asList("Perso 1","Perso 2","Perso 3"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_candidate_offre);
        init();
    }

    private void init(){
        mAdapter1 = new MyAdapter(mList1, this);
        mListView1 = findViewById(R.id.listView1);
        mListView1.setAdapter(mAdapter1);
    }

    public void RtrAMesOffres(View view) {
        Intent intent = new Intent(this, MesOffres.class);
        startActivity(intent);
    }
}
