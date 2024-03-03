package com.example.loginscreen.ui.tests.topictwo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginscreen.R;

import java.util.List;

public class ViewResultAdapterT2 extends BaseAdapter {
    private Context context;
    private List<String> questions;
    private int[] mark;
    private int[] button1;
    private int[] button2;

    public ViewResultAdapterT2(Context context, List<String> questions, int[] mark, int[] button1, int[] button2) {
        this.context = context;
        this.questions = questions;
        this.mark = mark;
        this.button1 = button1;
        this.button2 = button2;
    }

    @Override
    public int getCount() {
        return questions.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_feedback_t2,parent,false);
        }

        ImageView imageViewMarkT2 = convertView.findViewById(R.id.imageViewMarkT2);
        TextView textViewQuestionT2 = convertView.findViewById(R.id.textViewQuestionT2);
        Button btn_1T2 = convertView.findViewById(R.id.btn_1T2);
        Button btn_2T2 = convertView.findViewById(R.id.btn_2T2);

        imageViewMarkT2.setImageResource(mark[position]);
        textViewQuestionT2.setText(questions.get(position));
        btn_1T2.setBackgroundResource(button1[position]);
        btn_2T2.setBackgroundResource(button2[position]);

        return convertView;
    }
}
