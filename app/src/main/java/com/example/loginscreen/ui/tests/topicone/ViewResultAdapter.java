package com.example.loginscreen.ui.tests.topicone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginscreen.R;

import java.util.ArrayList;
import java.util.List;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class ViewResultAdapter extends BaseAdapter {
    private Context context;
    private List<String> questions;
    private List<String> opt1;
    private List<String> opt2;
    private List<String> opt3;
    private List<String> opt4;
    private List<String> solution;
    private List<String> solAns;
    private int[] opt1bg;
    private int[] opt2bg;
    private int[] opt3bg;
    private int[] opt4bg;

    public ViewResultAdapter(Context context, List<String> questions, List<String> opt1, List<String> opt2, List<String> opt3, List<String> opt4, List<String> solution, List<String> solAns) {
        this.context = context;
        this.questions = questions;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.solution = solution;
        this.solAns = solAns;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_feedback,parent,false);
        }

        TextView textViewQuestion = convertView.findViewById(R.id.textViewQuestion);
        TextView textViewSolution = convertView.findViewById(R.id.textViewSolution);
        JLatexMathView solutionLatex = convertView.findViewById(R.id.solutionLatex);
        Button btn_1 = convertView.findViewById(R.id.btn_1);
        Button btn_2 = convertView.findViewById(R.id.btn_2);
        Button btn_3 = convertView.findViewById(R.id.btn_3);
        Button btn_4 = convertView.findViewById(R.id.btn_4);

        textViewQuestion.setText(questions.get(position));
        textViewSolution.setText(solution.get(position));
        solutionLatex.setLatexDrawable(JLatexMathDrawable.builder(solAns.get(position)).textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        btn_1.setText(opt1.get(position));
        btn_2.setText(opt2.get(position));
        btn_3.setText(opt3.get(position));
        btn_4.setText(opt4.get(position));

        return convertView;
    }
}
