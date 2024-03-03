package com.example.loginscreen.ui.study;

import static com.example.loginscreen.DBHandler.TIME_IN_SEC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.Locale;
import java.util.Map;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class TopicSixActivity extends AppCompatActivity {
    private TextView t6TextViewTitle1, t6TextViewTitle2, t6TextViewTitle3, t6TextView1, t6TextView2, t6TextView3, t6TextView4, t6TextView5, t6TextView6, t6TextView7, t6TextView8, t6TextView9, t6TextView10, t6TextView11, t6TextView12, t6TextView13, t6TextView14, t6TextView15, t6TextView16, t6TextView17, t6TextView18, t6TextView19, t6TextView20, t6TextView21, t6TextView22, t6TextView23, t6TextView24;
    private JLatexMathView t6Latex_1, t6Latex_2, t6Latex_3, t6Latex_4, t6Latex_5, t6Latex_6, t6Latex_7, t6Latex_8, t6Latex_9, t6Latex_10;
    private ImageView topicSixBack;
    private ZoomControls t6ZoomControls;
    private int textSize = 16;
    private long startTime6, endTime6;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_six);

        dbHandler = new DBHandler(this);

        topicSixBack = findViewById(R.id.topicSixBack);
        t6TextViewTitle1 = findViewById(R.id.t6TextViewTitle1);
        t6TextViewTitle2 = findViewById(R.id.t6TextViewTitle2);
        t6TextViewTitle3 = findViewById(R.id.t6TextViewTitle3);
        t6TextView1 = findViewById(R.id.t6TextView1);
        t6TextView2 = findViewById(R.id.t6TextView2);
        t6TextView3 = findViewById(R.id.t6TextView3);
        t6TextView4 = findViewById(R.id.t6TextView4);
        t6TextView5 = findViewById(R.id.t6TextView5);
        t6TextView6 = findViewById(R.id.t6TextView6);
        t6TextView7 = findViewById(R.id.t6TextView7);
        t6TextView8 = findViewById(R.id.t6TextView8);
        t6TextView9 = findViewById(R.id.t6TextView9);
        t6TextView10 = findViewById(R.id.t6TextView10);
        t6TextView11 = findViewById(R.id.t6TextView11);
        t6TextView12 = findViewById(R.id.t6TextView12);
        t6TextView13 = findViewById(R.id.t6TextView13);
        t6TextView14 = findViewById(R.id.t6TextView14);
        t6TextView15 = findViewById(R.id.t6TextView15);
        t6TextView16 = findViewById(R.id.t6TextView16);
        t6TextView17 = findViewById(R.id.t6TextView17);
        t6TextView18 = findViewById(R.id.t6TextView18);
        t6TextView19 = findViewById(R.id.t6TextView19);
        t6TextView20 = findViewById(R.id.t6TextView20);
        t6TextView21 = findViewById(R.id.t6TextView21);
        t6TextView22 = findViewById(R.id.t6TextView22);
        t6TextView23 = findViewById(R.id.t6TextView23);
        t6TextView24 = findViewById(R.id.t6TextView24);
        t6Latex_1 = findViewById(R.id.t6Latex_1);
        t6Latex_2 = findViewById(R.id.t6Latex_2);
        t6Latex_3 = findViewById(R.id.t6Latex_3);
        t6Latex_4 = findViewById(R.id.t6Latex_4);
        t6Latex_5 = findViewById(R.id.t6Latex_5);
        t6Latex_6 = findViewById(R.id.t6Latex_6);
        t6Latex_7 = findViewById(R.id.t6Latex_7);
        t6Latex_8 = findViewById(R.id.t6Latex_8);
        t6Latex_9 = findViewById(R.id.t6Latex_9);
        t6Latex_10 = findViewById(R.id.t6Latex_10);
        t6ZoomControls = findViewById(R.id.t6ZoomControls);

        startTime6 = System.currentTimeMillis();

        t6Latex_1.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ and } B) = P(A) \\times P(B \\text{ after } A)\\\\P(B \\text{ after } A) \\text{ can also be written as } P(B|A)\\\\\\text{then } P(A \\text{ and } B) = P(A) \\times P(B|A)").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_2.setLatexDrawable(JLatexMathDrawable.builder("P(red) = \\frac{6}{15} = \\frac{2}{5}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_3.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{blue after red}) = \\frac{5}{14}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_4.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ and } B) = P(A) \\times P(B|A)\\\\P(\\text{red then blue})\\\\= P(red) \\times P(\\text{blue after red})\\\\= \\frac{2}{5} \\times \\frac{5}{14}\\\\= \\frac{1}{7}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_5.setLatexDrawable(JLatexMathDrawable.builder("P(blue) = \\frac{5}{15} = \\frac{1}{3}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_6.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{the second blue after the first blue}) = \\frac{4}{14} = \\frac{2}{7}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_7.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ and } B) = P(A) \\times P(B|A)\\\\P(\\text{blue then another blue})\\\\= P(\\text{first blue}) \\times P(\\text{blue after the first blue})\\\\= \\frac{1}{3} \\times \\frac{2}{7}\\\\= \\frac{2}{21}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_8.setLatexDrawable(JLatexMathDrawable.builder("P(jack) = \\frac{4}{52} = \\frac{1}{13}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_9.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{king after jack}) = \\frac{4}{51}").textSize(50).background(0xFFffffff).padding(8).build());
        t6Latex_10.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ and } B) = P(A) \\times P(B|A)\\\\P(\\text{jack then king})\\\\= P(jack) \\times P(\\text{king after jack})\\\\= \\frac{1}{13} \\times \\frac{4}{51}\\\\= \\frac{4}{663}").textSize(50).background(0xFFffffff).padding(8).build());

        String t6TextView3Format = "Events are <b>dependent</b> if the outcome of one event affects the outcome of another. For example, if you draw two colored balls from a bag and the first ball is not <b><i>replaced</i></b> before you draw the second ball then the outcome of the second draw will be affected by the outcome of the first draw.";
        String t6TextView4Format = "If A and B are dependent events, then the probability of A happening <b>AND</b> the probability of B happening, given A, is P(A) × P(B after A).";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t6TextView3.setText(Html.fromHtml(t6TextView3Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t6TextView3.setText(Html.fromHtml(t6TextView3Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t6TextView4.setText(Html.fromHtml(t6TextView4Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t6TextView4.setText(Html.fromHtml(t6TextView4Format));
        }

        TextView[] textViewTitle = {t6TextViewTitle1, t6TextViewTitle2, t6TextViewTitle3};
        TextView[] textView = {t6TextView1, t6TextView2, t6TextView3, t6TextView4, t6TextView5, t6TextView6, t6TextView6, t6TextView7, t6TextView8, t6TextView9, t6TextView10, t6TextView11, t6TextView12, t6TextView13, t6TextView14, t6TextView15, t6TextView16, t6TextView17, t6TextView18, t6TextView19, t6TextView20, t6TextView21, t6TextView22, t6TextView23, t6TextView24};

        t6ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textSize == 16) {
                    textSize = textSize + 2;
                    for (int i = 0; i < textView.length; i++) {
                        textView[i].setTextSize(textSize);
                    }
                    for (int i = 0; i < textViewTitle.length; i++) {
                        textViewTitle[i].setTextSize(20);
                    }
                } else if (textSize == 18) {
                    textSize = textSize + 2;
                    for (int i = 0; i < textView.length; i++) {
                        textView[i].setTextSize(textSize);
                    }
                    for (int i = 0; i < textViewTitle.length; i++) {
                        textViewTitle[i].setTextSize(22);
                    }
                }
            }
        });

        t6ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textSize == 20) {
                    textSize = textSize - 2;
                    for (int i = 0; i < textView.length; i++) {
                        textView[i].setTextSize(textSize);
                    }
                    for (int i = 0; i < textViewTitle.length; i++) {
                        textViewTitle[i].setTextSize(20);
                    }
                } else if (textSize == 18) {
                    textSize = textSize - 2;
                    for (int i = 0; i < textView.length; i++) {
                        textView[i].setTextSize(textSize);
                    }
                    for (int i = 0; i < textViewTitle.length; i++) {
                        textViewTitle[i].setTextSize(18);
                    }
                }
            }
        });

        topicSixBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        endTime6 = System.currentTimeMillis();
        long timeDiff = endTime6 - startTime6;
        int newTime = (int) (timeDiff / 1000);

        Boolean checkId = dbHandler.checkTopicsId(607);
        if (checkId == true) {
            Map<String, String> studyTime = dbHandler.getTopicsId(607);
            if (!studyTime.isEmpty()) {
                int prevTime = Integer.parseInt(studyTime.get(TIME_IN_SEC));
                int totalTime = newTime + prevTime;
                dbHandler.updateTime(new StudyModelClass(607, totalTime));
            }
        } else {
            dbHandler.storeStudyTime(new StudyModelClass(607, newTime));
        }

        super.onBackPressed();
    }
}