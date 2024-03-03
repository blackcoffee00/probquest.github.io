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

public class TopicOneActivity extends AppCompatActivity {

    private ImageView topicOneBack;
    private TextView t1TextViewTitle1, t1TextViewTitle2, t1TextViewTitle3, t1TextView1, t1TextView2, t1TextView3, t1TextView4, t1TextView5, t1TextView6, t1TextView7, t1TextView8, t1TextView9, t1TextView10, t1TextView11, t1TextView12, t1TextView13, t1TextView14, t1TextView15, t1TextView16, t1TextView17, t1TextView18, t1TextView19;
    private JLatexMathView t1Latex_1, t1Latex_2, t1Latex_3, t1Latex_4, t1Latex_5;
    private VideoView t1Video;
    private ZoomControls t1ZoomControls;
    private int textSize = 16;
    private long startTime1, endTime1;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_one);

        dbHandler = new DBHandler(this);

        topicOneBack = findViewById(R.id.topicOneBack);
        t1TextViewTitle1 = findViewById(R.id.t1TextViewTitle1);
        t1TextViewTitle2 = findViewById(R.id.t1TextViewTitle2);
        t1TextViewTitle3 = findViewById(R.id.t1TextViewTitle3);
        t1TextView1 = findViewById(R.id.t1TextView1);
        t1TextView2 = findViewById(R.id.t1TextView2);
        t1TextView3 = findViewById(R.id.t1TextView3);
        t1TextView4 = findViewById(R.id.t1TextView4);
        t1TextView5 = findViewById(R.id.t1TextView5);
        t1TextView6 = findViewById(R.id.t1TextView6);
        t1TextView7 = findViewById(R.id.t1TextView7);
        t1TextView8 = findViewById(R.id.t1TextView8);
        t1TextView9 = findViewById(R.id.t1TextView9);
        t1TextView10 = findViewById(R.id.t1TextView10);
        t1TextView11 = findViewById(R.id.t1TextView11);
        t1TextView12 = findViewById(R.id.t1TextView12);
        t1TextView13 = findViewById(R.id.t1TextView13);
        t1TextView14 = findViewById(R.id.t1TextView14);
        t1TextView15 = findViewById(R.id.t1TextView15);
        t1TextView16 = findViewById(R.id.t1TextView16);
        t1TextView17 = findViewById(R.id.t1TextView17);
        t1TextView18 = findViewById(R.id.t1TextView18);
        t1TextView19 = findViewById(R.id.t1TextView19);
        t1Latex_1 = findViewById(R.id.t1Latex_1);
        t1Latex_2 = findViewById(R.id.t1Latex_2);
        t1Latex_3 = findViewById(R.id.t1Latex_3);
        t1Latex_4 = findViewById(R.id.t1Latex_4);
        t1ZoomControls = findViewById(R.id.t1ZoomControls);
        t1Video = findViewById(R.id.t1Video);

        startTime1 = System.currentTimeMillis();

        String t1TextViewFormat10 = "<b>Note:</b> The face cards in a deck of cards are jack, queen, and king of diamonds, hearts, spades, and clubs. A total of 12 face cards.";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t1TextView10.setText(Html.fromHtml(t1TextViewFormat10, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t1TextView10.setText(Html.fromHtml(t1TextViewFormat10));
        }

        t1Latex_1.setLatexDrawable(JLatexMathDrawable.builder("\\text{Probability of Event} = \\frac{\\text{number of event}}{\\text{number of sample space}}").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        t1Latex_2.setLatexDrawable(JLatexMathDrawable.builder("= \\frac{\\text{number of possible outcomes to an Event }E}{\\text{total number of outcomes}}").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        t1Latex_3.setLatexDrawable(JLatexMathDrawable.builder("P(E) = \\frac{n(E)}{n(S)}").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        t1Latex_4.setLatexDrawable(JLatexMathDrawable.builder("n(E) = 12").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());

        t1Video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.simple_events);
        MediaController mediaController = new MediaController(TopicOneActivity.this);
        mediaController.setAnchorView(t1Video);
        t1Video.setMediaController(mediaController);

        topicOneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView[] textViewTitle = new TextView[]{t1TextViewTitle1, t1TextViewTitle2, t1TextViewTitle3};
        TextView[] textView = new TextView[]{t1TextView1, t1TextView2, t1TextView3, t1TextView4, t1TextView5, t1TextView6, t1TextView7, t1TextView8, t1TextView9, t1TextView10, t1TextView11, t1TextView12, t1TextView13, t1TextView14, t1TextView15, t1TextView16, t1TextView17, t1TextView18, t1TextView19};

        t1ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
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

        t1ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
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
    }

    @Override
    public void onBackPressed() {
        endTime1 = System.currentTimeMillis();
        long timeDiff = endTime1 - startTime1;
        int newTime = (int) (timeDiff / 1000);

        Boolean checkId = dbHandler.checkTopicsId(470);
        if (checkId == true) {
            Map<String, String> studyTime = dbHandler.getTopicsId(470);
            if (!studyTime.isEmpty()) {
                int prevTime = Integer.parseInt(studyTime.get(TIME_IN_SEC));
                int totalTime = newTime + prevTime;
                dbHandler.updateTime(new StudyModelClass(470, totalTime));
            }
        } else {
            dbHandler.storeStudyTime(new StudyModelClass(470, newTime));
        }

        super.onBackPressed();
    }
}