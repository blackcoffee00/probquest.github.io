package com.example.loginscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewAdapter extends BaseAdapter {

    private Context context;
    private String[] topicTitles;
    private int[] icon;

    public CardViewAdapter(Context context, String[] topicTitles, int[] icon) {
        this.context = context;
        this.topicTitles = topicTitles;
        this.icon = icon;
    }

    @Override
    public int getCount() {
        return topicTitles.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_cardview,parent,false);
        }
        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        ImageView imageViewIcon = convertView.findViewById(R.id.imageViewIcon1);

        textViewTitle.setText(topicTitles[position]);
        imageViewIcon.setImageResource(icon[position]);

        return convertView;
    }
}
