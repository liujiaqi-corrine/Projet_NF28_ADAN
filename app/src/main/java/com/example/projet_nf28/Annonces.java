package com.example.projet_nf28;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Annonces extends AppCompatActivity {

    private ListView mListView1 = null;
    private ListView mListView2 = null;
    private MyAdapter mAdapter1 = null;
    private MyAdapter mAdapter2 = null;
    private static final List<String> mList1 = new ArrayList<String>(Arrays.asList("Artiste 1","Artiste 2","Artiste 3"));
    private static final List<String> mList2 = new ArrayList<String>(Arrays.asList("Offre 1","Offre 2","Offre 3"));

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

        mAdapter2 = new MyAdapter(mList2, this);
        mListView2 = findViewById(R.id.listView2);
        mListView2.setAdapter(mAdapter2);
        mListView2.setVisibility(View.GONE);//View.VISIBLE
    }
// android:textColor="@android:color/black
}
