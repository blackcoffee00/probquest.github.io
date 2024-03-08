package com.example.loginscreen.ui.tests;

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

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class ViewResultAdapter extends BaseAdapter {
    private Context context;
    private List<String> questions, opt1, opt2, opt3, opt4, solution, answer;
    private List<Integer> solVis, ansVis;
    private int[] mark, button1, button2, button3, button4;

    public ViewResultAdapter(Context context, List<String> questions, List<String> opt1, List<String> opt2, List<String> opt3, List<String> opt4, List<String> solution, List<String> answer, List<Integer> solVis, List<Integer> ansVis, int[] mark, int[] button1, int[] button2, int[] button3, int[] button4) {
        this.context = context;
        this.questions = questions;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.solution = solution;
        this.answer = answer;
        this.solVis = solVis;
        this.ansVis = ansVis;
        this.mark = mark;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;
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

        if (position >= getCount()) {
            return convertView;
        }

        ImageView imageViewMark = convertView.findViewById(R.id.imageViewMarkT2);
        TextView solutionText = convertView.findViewById(R.id.solutionText);
        TextView answerText = convertView.findViewById(R.id.answerText);
        TextView textViewQuestion = convertView.findViewById(R.id.textViewQuestionT2);
        TextView textViewSolution = convertView.findViewById(R.id.textViewSolution);
        JLatexMathView solutionLatex = convertView.findViewById(R.id.solutionLatex);
        Button btn_1 = convertView.findViewById(R.id.btn_1T2);
        Button btn_2 = convertView.findViewById(R.id.btn_2T2);
        Button btn_3 = convertView.findViewById(R.id.btn_3);
        Button btn_4 = convertView.findViewById(R.id.btn_4);

        imageViewMark.setImageResource(mark[position]);
        textViewQuestion.setText(questions.get(position));
        textViewSolution.setText(solution.get(position));
        solutionLatex.setLatexDrawable(JLatexMathDrawable.builder(answer.get(position)).textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());

        solutionText.setVisibility(solVis.get(position));
        textViewSolution.setVisibility(solVis.get(position));
        answerText.setVisibility(ansVis.get(position));
        solutionLatex.setVisibility(ansVis.get(position));

        btn_1.setText(opt1.get(position));
        btn_2.setText(opt2.get(position));
        btn_3.setText(opt3.get(position));
        btn_4.setText(opt4.get(position));

        btn_1.setBackgroundResource(button1[position]);
        btn_2.setBackgroundResource(button2[position]);
        btn_3.setBackgroundResource(button3[position]);
        btn_4.setBackgroundResource(button4[position]);

        return convertView;
    }
}
