package com.example.loginscreen.ui.study;

import static com.example.loginscreen.DBHandler.TIME_IN_SEC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ZoomControls;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.Locale;
import java.util.Map;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class TopicFiveActivity extends AppCompatActivity {
    private TextView t5TextViewTitle1, t5TextViewTitle2, t5TextViewTitle3, t5TextView1, t5TextView2, t5TextView3, t5TextView4, t5TextView5, t5TextView6, t5TextView7, t5TextView8, t5TextView9, t5TextView10, t5TextView11, t5TextView12, t5TextView13, t5TextView14, t5TextView15, t5TextView16, t5TextView17, t5TextView18, t5TextView19, t5TextView20, t5TextView21, t5TextView22, t5TextView23, t5TextView24, t5TextView25, t5TextView26, t5TextView27, t5TextView28, t5TextView29, t5TextView30, t5TextView31, t5TextView32, t5TextView33, t5TextView34;
    private JLatexMathView t5Latex_1, t5Latex_2, t5Latex_3, t5Latex_4, t5Latex_5, t5Latex_6, t5Latex_7, t5Latex_8, t5Latex_9, t5Latex_10, t5Latex_11, t5Latex_12, t5Latex_13, t5Latex_14;
    private ImageView topicFiveBack;
    private VideoView t5Video;
    private ZoomControls t5ZoomControls;
    private int textSize = 16;
    private long startTime5, endTime5;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_five);

        dbHandler = new DBHandler(this);

        topicFiveBack = findViewById(R.id.topicFiveBack);
        t5TextViewTitle1 = findViewById(R.id.t5TextViewTitle1);
        t5TextViewTitle2 = findViewById(R.id.t5TextViewTitle2);
        t5TextViewTitle3 = findViewById(R.id.t5TextViewTitle3);
        t5TextView1 = findViewById(R.id.t5TextView1);
        t5TextView2 = findViewById(R.id.t5TextView2);
        t5TextView3 = findViewById(R.id.t5TextView3);
        t5TextView4 = findViewById(R.id.t5TextView4);
        t5TextView5 = findViewById(R.id.t5TextView5);
        t5TextView6 = findViewById(R.id.t5TextView6);
        t5TextView7 = findViewById(R.id.t5TextView7);
        t5TextView8 = findViewById(R.id.t5TextView8);
        t5TextView9 = findViewById(R.id.t5TextView9);
        t5TextView10 = findViewById(R.id.t5TextView10);
        t5TextView11 = findViewById(R.id.t5TextView11);
        t5TextView12 = findViewById(R.id.t5TextView12);
        t5TextView13 = findViewById(R.id.t5TextView13);
        t5TextView14 = findViewById(R.id.t5TextView14);
        t5TextView15 = findViewById(R.id.t5TextView15);
        t5TextView16 = findViewById(R.id.t5TextView16);
        t5TextView17 = findViewById(R.id.t5TextView17);
        t5TextView18 = findViewById(R.id.t5TextView18);
        t5TextView19 = findViewById(R.id.t5TextView19);
        t5TextView20 = findViewById(R.id.t5TextView20);
        t5TextView21 = findViewById(R.id.t5TextView21);
        t5TextView22 = findViewById(R.id.t5TextView22);
        t5TextView23 = findViewById(R.id.t5TextView23);
        t5TextView24 = findViewById(R.id.t5TextView24);
        t5TextView25 = findViewById(R.id.t5TextView25);
        t5TextView26 = findViewById(R.id.t5TextView26);
        t5TextView27 = findViewById(R.id.t5TextView27);
        t5TextView28 = findViewById(R.id.t5TextView28);
        t5TextView29 = findViewById(R.id.t5TextView29);
        t5TextView30 = findViewById(R.id.t5TextView30);
        t5TextView31 = findViewById(R.id.t5TextView31);
        t5TextView32 = findViewById(R.id.t5TextView32);
        t5TextView33 = findViewById(R.id.t5TextView33);
        t5TextView34 = findViewById(R.id.t5TextView34);
        t5Latex_1 = findViewById(R.id.t5Latex_1);
        t5Latex_2 = findViewById(R.id.t5Latex_2);
        t5Latex_3 = findViewById(R.id.t5Latex_3);
        t5Latex_4 = findViewById(R.id.t5Latex_4);
        t5Latex_5 = findViewById(R.id.t5Latex_5);
        t5Latex_6 = findViewById(R.id.t5Latex_6);
        t5Latex_7 = findViewById(R.id.t5Latex_7);
        t5Latex_8 = findViewById(R.id.t5Latex_8);
        t5Latex_9 = findViewById(R.id.t5Latex_9);
        t5Latex_10 = findViewById(R.id.t5Latex_10);
        t5Latex_11 = findViewById(R.id.t5Latex_11);
        t5Latex_12 = findViewById(R.id.t5Latex_12);
        t5Latex_13 = findViewById(R.id.t5Latex_13);
        t5Latex_14 = findViewById(R.id.t5Latex_14);
        t5Video = findViewById(R.id.t5Video);
        t5ZoomControls = findViewById(R.id.t5ZoomControls);

        startTime5 = System.currentTimeMillis();

        t5Latex_1.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ and } B) = P(A) \\times P(B)").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_2.setLatexDrawable(JLatexMathDrawable.builder("P(A) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_3.setLatexDrawable(JLatexMathDrawable.builder("P(B) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_4.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ and } B) = P(A) \\times P(B)\\\\= \\frac{1}{6} \\times \\frac{1}{6}\\\\= \\frac{1}{36}").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        t5Latex_5.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{letter J}) = \\frac{1}{5}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_6.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{letter R}) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_7.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{picking letters J and R})\\\\= P(\\text{letter J}) \\times P(\\text{letter R})\\\\= \\frac{1}{6} \\times \\frac{1}{6}\\\\= \\frac{1}{36}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_8.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{letter L}) = \\frac{1}{5}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_9.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{letter L}) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_10.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{both letters are L}) = \\frac{1}{5} \\times \\frac{1}{6}\\\\= \\frac{1}{30}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_11.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{vowels in bag 1}) = \\frac{3}{5}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_12.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{vowels in bag 2}) = \\frac{2}{6} = \\frac{1}{3}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_13.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{both letters are vowel}) = \\frac{3}{5} \\times \\frac{1}{3}\\\\= \\frac{1}{5}").textSize(50).background(0xFFffffff).padding(8).build());
        t5Latex_14.setLatexDrawable(JLatexMathDrawable.builder("P(jack) = \\frac{4}{52}\\\\P(8) = \\frac{4}{52}\\\\P(\\text{jack and 8}) = P(jack) \\times P(8)\\\\= \\frac{4}{52} \\times \\frac{4}{52}\\\\= \\frac{16}{2704}\\\\= \\frac{1}{169}").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());

        String t5TextView3Format = "Events are <b>independent</b> if the outcome of one event does not affect the outcome of another. For example, if you throw a die and a coin, the number on the die does not affect the result you get on the coin. Other examples are picking a card from a deck and flipping a fair coin, a fair die is rolled two times and others.";
        String t5TextView5Format = "1. Landing on heads after tossing a coin <b>AND</b> rolling a 4 on a single 6-sided die.<br>2. Choosing a marble from a jar <b>AND</b> landing on tails after tossing a coin.<br>3. Choosing a jack from a deck of cards, replacing it, <b>AND</b> then choosing a queen as the second card.<br>4. Rolling a 3 on a single 6-sided die, <b>AND</b> then rolling a 5 on a second roll of the die.";
        String t5TextView8Format = "If A and B are independent events, then the probability of A happening <b>AND</b> the probability of B happening is P(A) × P(B).";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t5TextView3.setText(Html.fromHtml(t5TextView3Format, Html.FROM_HTML_MODE_COMPACT));
            t5TextView5.setText(Html.fromHtml(t5TextView5Format, Html.FROM_HTML_MODE_COMPACT));
            t5TextView8.setText(Html.fromHtml(t5TextView8Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t5TextView3.setText(Html.fromHtml(t5TextView3Format));
            t5TextView5.setText(Html.fromHtml(t5TextView5Format));
            t5TextView8.setText(Html.fromHtml(t5TextView8Format));
        }

        t5Video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.dependent_independent_events);
        MediaController mediaController = new MediaController(TopicFiveActivity.this);
        mediaController.setAnchorView(t5Video);
        t5Video.setMediaController(mediaController);

        TextView[] textViewTitle = {t5TextViewTitle1, t5TextViewTitle2, t5TextViewTitle3};
        TextView[] textView = {t5TextView1, t5TextView2, t5TextView3, t5TextView4, t5TextView5, t5TextView6, t5TextView7, t5TextView8, t5TextView9, t5TextView10, t5TextView11, t5TextView12, t5TextView13, t5TextView14, t5TextView15, t5TextView16, t5TextView17, t5TextView18, t5TextView19, t5TextView20, t5TextView21, t5TextView22, t5TextView23, t5TextView24, t5TextView25, t5TextView26, t5TextView27, t5TextView28, t5TextView29, t5TextView30, t5TextView31, t5TextView32, t5TextView33, t5TextView34};

        t5ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
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

        t5ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
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

        topicFiveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        endTime5 = System.currentTimeMillis();
        long timeDiff = endTime5 - startTime5;
        int newTime = (int) (timeDiff / 1000);

        Boolean checkId = dbHandler.checkTopicsId(176);
        if (checkId == true) {
            Map<String, String> studyTime = dbHandler.getTopicsId(176);
            if (!studyTime.isEmpty()) {
                int prevTime = Integer.parseInt(studyTime.get(TIME_IN_SEC));
                int totalTime = newTime + prevTime;
                Boolean updated = dbHandler.updateTime(new StudyModelClass(176, totalTime));
            }
        } else {
            dbHandler.storeStudyTime(new StudyModelClass(176, newTime));
        }

        super.onBackPressed();
    }
}