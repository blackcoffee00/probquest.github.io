package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginscreen.R;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class TopicThreeActivity extends AppCompatActivity {

    private TextView t3TextView3, t3TextView4, t3TextView7, t3TextView9, t3TextView10;
    private ImageView topicThreeBack;
    private JLatexMathView t3Latex_1, t3Latex_2, t3Latex_3, t3Latex_4, t3Latex_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_three);

        topicThreeBack = findViewById(R.id.topicThreeBack);
        t3TextView3 = findViewById(R.id.t3TextView3);
        t3TextView4 = findViewById(R.id.t3TextView4);
        t3TextView7 = findViewById(R.id.t3TextView7);
        t3TextView9 = findViewById(R.id.t3TextView9);
        t3TextView10 = findViewById(R.id.t3TextView10);
        t3Latex_1 = findViewById(R.id.t3Latex_1);
        t3Latex_2 = findViewById(R.id.t3Latex_2);
        t3Latex_3 = findViewById(R.id.t3Latex_3);
        t3Latex_4 = findViewById(R.id.t3Latex_4);
        t3Latex_5 = findViewById(R.id.t3Latex_5);

        t3Latex_1.setLatexDrawable(JLatexMathDrawable.builder("P(A \\cap B) = P(A \\text{ or } B) = P(A) + P(B)").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        t3Latex_2.setLatexDrawable(JLatexMathDrawable.builder("P(2) = \\frac{1}{6} \\text{ and } P(B) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t3Latex_3.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ or } B) = P(A) + P(B)\\\\P(2 \\text{ or } 5) = P(2) + P(5)\\\\= \\frac{1}{6} + \\frac{1}{6}\\\\= \\frac{2}{6}\\\\= \\frac{1}{3}").textSize(50).background(0xFFffffff).padding(8).build());
        t3Latex_4.setLatexDrawable(JLatexMathDrawable.builder("P(queen) = \\frac{4}{52} = \\frac{1}{13} \\text{ and }\\\\P(ace) = \\frac{4}{52} = \\frac{1}{13}").textSize(50).background(0xFFffffff).padding(8).build());
        t3Latex_5.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ or } B) = P(A) + P(B)\\\\P(queen \\text{ or } ace) = P(queen) + P(ace)\\\\= \\frac{1}{13} + \\frac{1}{13}\\\\= \\frac{2}{13}").textSize(50).background(0xFFffffff).padding(8).build());

        String t3TextView3Format = "<b>Mutually exclusive events</b> are two or more events that cannot happen at the same time. For example, if we toss a coin, either heads or tails might turn up, but not heads and tails at the same time. There is no other way you could get both results. Similarly, in a single throw of a die, we can only have one number shown at the top face. If you roll a 3 or 5 on a die, the results either be a 3 or a 5 but not both. Such events are also called disjoint events since they do not happen simultaneously.<br>";
        String t3TextView4Format = "Also, two sets are known to be <b><i>mutually exclusive</i></b> when they have no common elements.<br>Consider the set of all even positive integers, and the set of all odd positive integers:";
        String t3TextView7Format = "<b>Non-mutually exclusive events</b> are events that can happen at the same time. For example, landing an even number or a number divisible by 4 on a die. These two events have a possibility that both events can happen together. Also, two sets are <b>non-mutually exclusive</b> if they share common elements.";
        String t3TextView9Format = "Set A = {10, 11, <b>12</b>, 13, 14, <b>15</b>, 16, 17, <b>18</b>, 19, 20}<br>Set B = {3, 6, 9, <b>12</b>, <b>15</b>, <b>18</b>}";
        String t3TextView10Format = "We call them <b>non-mutually exclusive</b> since they share the common elements of 12, 15, and 18.";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t3TextView3.setText(Html.fromHtml(t3TextView3Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t3TextView3.setText(Html.fromHtml(t3TextView3Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t3TextView4.setText(Html.fromHtml(t3TextView4Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t3TextView4.setText(Html.fromHtml(t3TextView4Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t3TextView7.setText(Html.fromHtml(t3TextView7Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t3TextView7.setText(Html.fromHtml(t3TextView7Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t3TextView9.setText(Html.fromHtml(t3TextView9Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t3TextView9.setText(Html.fromHtml(t3TextView9Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t3TextView10.setText(Html.fromHtml(t3TextView10Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t3TextView10.setText(Html.fromHtml(t3TextView10Format));
        }

        topicThreeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}