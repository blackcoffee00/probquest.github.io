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

public class TopicFourActivity extends AppCompatActivity {
    private TextView t4TextViewTitle1, t4TextViewTitle2, t4TextViewTitle3, t4TextView1, t4TextView2, t4TextView3, t4TextView4, t4TextView5, t4TextView6, t4TextView7, t4TextView8, t4TextView9, t4TextView10, t4TextView11, t4TextView12, t4TextView13, t4TextView14, t4TextView15, t4TextView16, t4TextView17, t4TextView18, t4TextView19, t4TextView20, t4TextView21, t4TextView22, t4TextView23;
    private JLatexMathView t4Latex_1, t4Latex_2, t4Latex_3, t4Latex_4, t4Latex_5;
    private ImageView topicFourBack;
    private VideoView t4Video;
    private ZoomControls t4ZoomControls;
    private int textSize = 16;
    private long startTime4, endTime4;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_four);

        dbHandler = new DBHandler(this);

        topicFourBack = findViewById(R.id.topicFourBack);
        t4TextViewTitle1 = findViewById(R.id.t4TextViewTitle1);
        t4TextViewTitle2 = findViewById(R.id.t4TextViewTitle2);
        t4TextViewTitle3 = findViewById(R.id.t4TextViewTitle3);
        t4TextView1 = findViewById(R.id.t4TextView1);
        t4TextView2 = findViewById(R.id.t4TextView2);
        t4TextView3 = findViewById(R.id.t4TextView3);
        t4TextView4 = findViewById(R.id.t4TextView4);
        t4TextView5 = findViewById(R.id.t4TextView5);
        t4TextView6 = findViewById(R.id.t4TextView6);
        t4TextView7 = findViewById(R.id.t4TextView7);
        t4TextView8 = findViewById(R.id.t4TextView8);
        t4TextView9 = findViewById(R.id.t4TextView9);
        t4TextView10 = findViewById(R.id.t4TextView10);
        t4TextView11 = findViewById(R.id.t4TextView11);
        t4TextView12 = findViewById(R.id.t4TextView12);
        t4TextView13 = findViewById(R.id.t4TextView13);
        t4TextView14 = findViewById(R.id.t4TextView14);
        t4TextView15 = findViewById(R.id.t4TextView15);
        t4TextView16 = findViewById(R.id.t4TextView16);
        t4TextView17 = findViewById(R.id.t4TextView17);
        t4TextView18 = findViewById(R.id.t4TextView18);
        t4TextView19 = findViewById(R.id.t4TextView19);
        t4TextView20 = findViewById(R.id.t4TextView20);
        t4TextView21 = findViewById(R.id.t4TextView21);
        t4TextView22 = findViewById(R.id.t4TextView22);
        t4TextView23 = findViewById(R.id.t4TextView23);
        t4Latex_1 = findViewById(R.id.t4Latex_1);
        t4Latex_2 = findViewById(R.id.t4Latex_2);
        t4Latex_3 = findViewById(R.id.t4Latex_3);
        t4Latex_4 = findViewById(R.id.t4Latex_4);
        t4Latex_5 = findViewById(R.id.t4Latex_5);
        t4Video = findViewById(R.id.t4Video);
        t4ZoomControls = findViewById(R.id.t4ZoomControls);

        startTime4 = System.currentTimeMillis();

        t4Latex_1.setLatexDrawable(JLatexMathDrawable.builder("P(A \\cup B) = P(A \\text{ or } B) = P(A) + P(B) - P(A \\text{ and } B)").textSize(50).background(0xFFffffff).padding(8).build());
        t4Latex_2.setLatexDrawable(JLatexMathDrawable.builder("P(3) = \\frac{1}{6} \\text{,}\\\\P(\\text{odd number}) = \\frac{3}{6} \\text{, and}\\\\P(\\text{3 and odd number}) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t4Latex_3.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ or } B) = P(A) + P(B) - P(A \\text{ and } B)\\\\P(\\text{3 or odd number}) = P(3) + P(\\text{odd number})\\\\- P(\\text{3 and odd number})\\\\= \\frac{1}{6} + \\frac{3}{6} - \\frac{1}{6}\\\\= \\frac{3}{6}\\\\= \\frac{1}{2}").textSize(50).background(0xFFffffff).padding(8).build());
        t4Latex_4.setLatexDrawable(JLatexMathDrawable.builder("P(\\text{red card}) = \\frac{26}{52} = \\frac{1}{2}\\\\P(\\text{face card}) = \\frac{12}{52} = \\frac{4}{13}\\\\P(\\text{red and face card}) = \\frac{6}{52} = \\frac{3}{26}").textSize(50).background(0xFFffffff).padding(8).build());
        t4Latex_5.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ or } B) = P(A) + P(B) - P(A \\text{ and } B)\\\\P(\\text{red or face card}) = P(red card) + P(\\text{face card})\\\\- P(\\text{red and face card})\\\\= \\frac{26}{52} + \\frac{12}{52} - \\frac{6}{52}\\\\= \\frac{32}{52}\\\\= \\frac{8}{13}").textSize(50).background(0xFFffffff).padding(8).build());

        String t4TextView3Format = "<b>Non-mutually exclusive events</b> are events that can happen at the same time. For example, landing an even number or a number divisible by 4 on a die. These two events have a possibility that both events can happen together. Also, two sets are <i>non-mutually exclusive</i> if they share common elements.";
        String t4TextView5Format = "Set A = {10, 11, <b>12</b>, 13, 14, <b>15</b>, 16, 17, <b>18</b>, 19, 20}<br>Set B = {3, 6, 9, <b>12</b>, <b>15</b>, <b>18</b>}";
        String t4TextView6Format = "We call them <b>non-mutually exclusive</b> since they share the common elements of 12,15 and 18.";
        String t4TextView10Format = "If A and B are two non - mutually exclusive events, then the probability of <b>A or B</b> occurring is both of their probabilities added together and subtracting the probability of both of them occurring.";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t4TextView3.setText(Html.fromHtml(t4TextView3Format, Html.FROM_HTML_MODE_COMPACT));
            t4TextView5.setText(Html.fromHtml(t4TextView5Format, Html.FROM_HTML_MODE_COMPACT));
            t4TextView6.setText(Html.fromHtml(t4TextView6Format, Html.FROM_HTML_MODE_COMPACT));
            t4TextView10.setText(Html.fromHtml(t4TextView10Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t4TextView3.setText(Html.fromHtml(t4TextView3Format));
            t4TextView5.setText(Html.fromHtml(t4TextView5Format));
            t4TextView6.setText(Html.fromHtml(t4TextView6Format));
            t4TextView10.setText(Html.fromHtml(t4TextView10Format));
        }

        t4Video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.nonmutually_exclusive_events);
        MediaController mediaController = new MediaController(TopicFourActivity.this);
        mediaController.setAnchorView(t4Video);
        t4Video.setMediaController(mediaController);

        TextView[] textViewTitle = {t4TextViewTitle1, t4TextViewTitle2, t4TextViewTitle3};
        TextView[] textView = {t4TextView1, t4TextView2, t4TextView3, t4TextView4, t4TextView5, t4TextView6, t4TextView7, t4TextView8, t4TextView9, t4TextView10, t4TextView11, t4TextView12, t4TextView13, t4TextView14, t4TextView15, t4TextView16, t4TextView17, t4TextView18, t4TextView19, t4TextView20, t4TextView21, t4TextView22, t4TextView23};

        t4ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
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

        t4ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
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

        topicFourBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        endTime4 = System.currentTimeMillis();
        long timeDiff = endTime4 - startTime4;
        int newTime = (int) (timeDiff / 1000);

        Boolean checkId = dbHandler.checkTopicsId(613);
        if (checkId == true) {
            Map<String, String> studyTime = dbHandler.getTopicsId(613);
            if (!studyTime.isEmpty()) {
                int prevTime = Integer.parseInt(studyTime.get(TIME_IN_SEC));
                int totalTime = newTime + prevTime;
                dbHandler.updateTime(new StudyModelClass(613, totalTime));
            }
        } else {
            dbHandler.storeStudyTime(new StudyModelClass(613, newTime));
        }

        super.onBackPressed();
    }
}