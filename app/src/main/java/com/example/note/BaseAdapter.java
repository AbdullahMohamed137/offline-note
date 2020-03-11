package com.example.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BaseAdapter extends android.widget.BaseAdapter {
    ArrayList<Note> list ;
    Context context ;
    public BaseAdapter(Context context , ArrayList<Note>list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.valueOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_single_row,null);
        //TextView date = view.findViewById(R.id.time_text_view);
        TextView title = view.findViewById(R.id.title_text_view);
        title.setText(list.get(position).getTitle());
        //date.setText(list.get(position).getDate());
        return view;
    }
}
