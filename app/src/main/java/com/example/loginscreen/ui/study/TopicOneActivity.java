package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.ZoomControls;

import com.example.loginscreen.R;

import java.util.Locale;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class TopicOneActivity extends AppCompatActivity {

    private ImageView topicOneBack;
    private TextView t1TextViewTitle1, t1TextViewTitle2, t1TextViewTitle3, t1TextView1, t1TextView2, t1TextView3, t1TextView4, t1TextView5, t1TextView6, t1TextView7, t1TextView8, t1TextView9, t1TextView10, t1TextView11, t1TextView12, t1TextView13, t1TextView14, t1TextView15, t1TextView16, t1TextView17, t1TextView18, t1TextView19;
    private JLatexMathView t1Latex_1, t1Latex_2, t1Latex_3, t1Latex_4, t1Latex_5;
    private ZoomControls t1ZoomControls;
    private long startTime1, endTime1;
    private int textSize = 16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_one);

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
        VideoView t1Video = findViewById(R.id.t1Video);

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

        t1Video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.probability_of_simple_events);
        MediaController mediaController = new MediaController(TopicOneActivity.this);
        mediaController.setAnchorView((ViewGroup) t1Video.getParent());
        t1Video.setMediaController(mediaController);

        topicOneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t1ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textSize == 16) {
                    textSize = textSize + 2;
                    t1TextView1.setTextSize(textSize);
                    t1TextView2.setTextSize(textSize);
                    t1TextView3.setTextSize(textSize);
                    t1TextView4.setTextSize(textSize);
                    t1TextView5.setTextSize(textSize);
                    t1TextView6.setTextSize(textSize);
                    t1TextView7.setTextSize(textSize);
                    t1TextView8.setTextSize(textSize);
                    t1TextView9.setTextSize(textSize);
                    t1TextView10.setTextSize(textSize);
                    t1TextView11.setTextSize(textSize);
                    t1TextView12.setTextSize(textSize);
                    t1TextView13.setTextSize(textSize);
                    t1TextView14.setTextSize(textSize);
                    t1TextView15.setTextSize(textSize);
                    t1TextView16.setTextSize(textSize);
                    t1TextView17.setTextSize(textSize);
                    t1TextView18.setTextSize(textSize);
                    t1TextView19.setTextSize(textSize);
                    t1TextViewTitle1.setTextSize(20);
                    t1TextViewTitle2.setTextSize(20);
                    t1TextViewTitle3.setTextSize(20);
                } else if (textSize == 18) {
                    textSize = textSize + 2;
                    t1TextView1.setTextSize(textSize);
                    t1TextView2.setTextSize(textSize);
                    t1TextView3.setTextSize(textSize);
                    t1TextView4.setTextSize(textSize);
                    t1TextView5.setTextSize(textSize);
                    t1TextView6.setTextSize(textSize);
                    t1TextView7.setTextSize(textSize);
                    t1TextView8.setTextSize(textSize);
                    t1TextView9.setTextSize(textSize);
                    t1TextView10.setTextSize(textSize);
                    t1TextView11.setTextSize(textSize);
                    t1TextView12.setTextSize(textSize);
                    t1TextView13.setTextSize(textSize);
                    t1TextView14.setTextSize(textSize);
                    t1TextView15.setTextSize(textSize);
                    t1TextView16.setTextSize(textSize);
                    t1TextView17.setTextSize(textSize);
                    t1TextView18.setTextSize(textSize);
                    t1TextView19.setTextSize(textSize);
                    t1TextViewTitle1.setTextSize(22);
                    t1TextViewTitle2.setTextSize(22);
                    t1TextViewTitle3.setTextSize(22);
                }
            }
        });

        t1ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textSize == 20) {
                    textSize = textSize - 2;
                    t1TextView1.setTextSize(textSize);
                    t1TextView2.setTextSize(textSize);
                    t1TextView3.setTextSize(textSize);
                    t1TextView4.setTextSize(textSize);
                    t1TextView5.setTextSize(textSize);
                    t1TextView6.setTextSize(textSize);
                    t1TextView7.setTextSize(textSize);
                    t1TextView8.setTextSize(textSize);
                    t1TextView9.setTextSize(textSize);
                    t1TextView10.setTextSize(textSize);
                    t1TextView11.setTextSize(textSize);
                    t1TextView12.setTextSize(textSize);
                    t1TextView13.setTextSize(textSize);
                    t1TextView14.setTextSize(textSize);
                    t1TextView15.setTextSize(textSize);
                    t1TextView16.setTextSize(textSize);
                    t1TextView17.setTextSize(textSize);
                    t1TextView18.setTextSize(textSize);
                    t1TextView19.setTextSize(textSize);
                    t1TextViewTitle1.setTextSize(20);
                    t1TextViewTitle2.setTextSize(20);
                    t1TextViewTitle3.setTextSize(20);
                } else if (textSize == 18) {
                    textSize = textSize - 2;
                    t1TextView1.setTextSize(textSize);
                    t1TextView2.setTextSize(textSize);
                    t1TextView3.setTextSize(textSize);
                    t1TextView4.setTextSize(textSize);
                    t1TextView5.setTextSize(textSize);
                    t1TextView6.setTextSize(textSize);
                    t1TextView7.setTextSize(textSize);
                    t1TextView8.setTextSize(textSize);
                    t1TextView9.setTextSize(textSize);
                    t1TextView10.setTextSize(textSize);
                    t1TextView11.setTextSize(textSize);
                    t1TextView12.setTextSize(textSize);
                    t1TextView13.setTextSize(textSize);
                    t1TextView14.setTextSize(textSize);
                    t1TextView15.setTextSize(textSize);
                    t1TextView16.setTextSize(textSize);
                    t1TextView17.setTextSize(textSize);
                    t1TextView18.setTextSize(textSize);
                    t1TextView19.setTextSize(textSize);
                    t1TextViewTitle1.setTextSize(18);
                    t1TextViewTitle2.setTextSize(18);
                    t1TextViewTitle3.setTextSize(18);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        endTime1 = System.currentTimeMillis();
        long timeDiff = endTime1 - startTime1;
        int totalSeconds = (int) (timeDiff / 1000);
        int secs = totalSeconds % 60;

        super.onBackPressed();
    }
}