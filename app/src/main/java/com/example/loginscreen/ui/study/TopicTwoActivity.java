package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loginscreen.R;

public class TopicTwoActivity extends AppCompatActivity {

    private ImageView topicTwoBack;
    private TextView t2Intro, sEExamples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_two);

        topicTwoBack = findViewById(R.id.topicTwoBack);
        t2Intro = findViewById(R.id.t2Intro);
        sEExamples = findViewById(R.id.sEExamples);

        String t2IntroFormat = "A <b><i>Simple event</i></b> is an event with a single outcome. A <b><i>Compound event</i></b> is the combination of two or more simple events (with two or more outcomes). To understand clearly, consider the examples below.";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t2Intro.setText(Html.fromHtml(t2IntroFormat, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t2Intro.setText(Html.fromHtml(t2IntroFormat));
        }

        String sEExamplesFormat = "<b>Simple Events</b><br><br>1. The probability of rolling a “3” on a die.<br><br><i><u>One event:</u></i> rolling a “3” on a die<br><br>" +
                "2. The probability of rolling an even number less than 4 on a die.<br><br><i><u>One event:</u></i> rolling an even number less than 4 on a die<br><br>" +
                "3. The probability of getting a head in tossing the coin once.<br><br><i><u>One event:</u></i> getting a head in tossing the coin<br><br>" +
                "4. The probability of drawing a heart from a deck of cards.<br><br><i><u>One event:</u></i> drawing a  heart from a deck of cards<br><br>" +
                "5. Flipping head on a coin.<br><br><i><u>One event:</u></i> flipping a head on a coin<br><br>" +
                "<b>Compound Events</b><br><br>1. The probability of rolling a “5” on a die, then tossing a head in a coin.<br><br><i><u>One event:</u></i> rolling a “5” on a die<br><i><u>Another event:</u></i> tossing a head in a coin<br><br>" +
                "2. The probability of rolling an even number less than 4 on a die then a prime number greater than 2.<br><br><i><u>One event:</u></i> rolling an even number less than 4 on a die<br><i><u>Another event:</u></i> rolling a prime number on a die greater than 2<br><br>" +
                "3. The probability that at least one head is obtained when a coin is thrown three times.<br><br><i><u>One event:</u></i> a coin is thrown for the first time<br><i><u>Second event:</u></i> a coin is thrown for the second time<br><i><u>Third event:</u></i> a coin is thrown for the third time" +
                "4. The probability of drawing a heart from a deck of cards, replacing the card, then drawing a spade.<br><br><i><u>One event:</u></i> drawing a heart from a deck of cards<br><i><u>Another event:</u></i> drawing a spade from a deck of cards<br><br>" +
                "5. Flipping head on a coin and pulling a queen from a standard deck of cards.<br><br><i><u>One event:</u></i> flipping a head on a coin<br><i><u>Another event:</u></i> pulling a queen from a standard deck of cards";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sEExamples.setText(Html.fromHtml(sEExamplesFormat, Html.FROM_HTML_MODE_COMPACT));
        } else {
            sEExamples.setText(Html.fromHtml(sEExamplesFormat));
        }

        topicTwoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}