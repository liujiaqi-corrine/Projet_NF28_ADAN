package com.example.projet_nf28;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;

    public MyAdapter(List<String> data, AppCompatActivity appCompatActivity) {
        this.data = data;
        this.context = appCompatActivity;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public void addData(String d){
        this.data.add(d);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
        if(view == null) {
//            viewHolder = new ViewHolder();
            //create
            view = LayoutInflater.from(context).inflate(R.layout.treelist_item, viewGroup, false);
//            viewHolder.textView = view.findViewById(R.id.tv);
//            view.setTag(viewHolder);
        }else{
//            viewHolder = (ViewHolder) view.getTag();
        }
//        viewHolder.textView.setText(data.get(i).getName());

        TextView textView = view.findViewById(R.id.tv);
        textView.setText(data.get(i));
        Log.e("TAG", "getView: "+i);
        return view;
    }

    /*private final class ViewHolder{
        TextView textView;
    }*/
}
