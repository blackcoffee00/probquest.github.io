package com.example.loginscreen.ui.study;

import static com.example.loginscreen.DBHandler.TIME_IN_SEC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicTwoActivity extends AppCompatActivity {

    private ImageView topicTwoBack, act1Feedback;
    private TextView t2TextViewTitle1, t2TextViewTitle2, t2TextViewTitle3, t2TextView1, t2TextView2, t2TextView3, t2TextView4, t2TextView5, t2TextView6, t2TextView7, t2TextView8, t2TextView9, t2TextView10, t2TextView11, t2TextView12, t2TextView13, t2TextView14, t2TextView15, t2TextView16, t2TextView17, t2TextView18, t2TextView19, t2TextView20, t2TextView21, t2TextView22, t2TextView23, t2TextView24, t2TextView25, t2TextView26, t2TextView27, t2TextView28, t2TextView29, t2TextView30, t2TextView31, act1Score, act1Direction, act1Question;
    private EditText act1Answer, t2EditText1, t2EditText2, t2EditText3, t2EditText4, t2EditText5, t2EditText6, t2EditText7, t2EditText8, t2EditText9;
    private ActivityQuestions activityQuestions = new ActivityQuestions();
    private int actQuesLength = activityQuestions.act1Questions.length;
    private String ansCaps, ansLow, ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8, ans9;
    private ZoomControls t2ZoomControls;
    private Button act1Start, act1Submit, saveAns;
    private LinearLayout act1Layout;
    private int textSize = 16, score, currentIndex, scoreValue;
    private long startTime2, endTime2;
    private boolean nextQuestion = false;
    private Set<Integer> shownQuestionIndices = new HashSet<>();
    private static final String PREFS_FILE_NAME2 = "ActivityScore";
    private static final String KEY_SCORE = "score";
    private static final String PREFS_FILE_NAME1 = "TopicTwoIE";
    private static final String KEY_ANSWER1 = "answer1";
    private static final String KEY_ANSWER2 = "answer2";
    private static final String KEY_ANSWER3 = "answer3";
    private static final String KEY_ANSWER4 = "answer4";
    private static final String KEY_ANSWER5 = "answer5";
    private static final String KEY_ANSWER6 = "answer6";
    private static final String KEY_ANSWER7 = "answer7";
    private static final String KEY_ANSWER8 = "answer8";
    private static final String KEY_ANSWER9 = "answer9";
    Random random = new Random();
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_two);

        dbHandler = new DBHandler(this);

        topicTwoBack = findViewById(R.id.topicTwoBack);
        t2TextViewTitle1 = findViewById(R.id.t2TextViewTitle1);
        t2TextViewTitle2 = findViewById(R.id.t2TextViewTitle2);
        t2TextViewTitle3 = findViewById(R.id.t2TextViewTitle3);
        t2TextView1 = findViewById(R.id.t2TextView1);
        t2TextView2 = findViewById(R.id.t2TextView2);
        t2TextView3 = findViewById(R.id.t2TextView3);
        t2TextView4 = findViewById(R.id.t2TextView4);
        t2TextView5 = findViewById(R.id.t2TextView5);
        t2TextView6 = findViewById(R.id.t2TextView6);
        t2TextView7 = findViewById(R.id.t2TextView7);
        t2TextView8 = findViewById(R.id.t2TextView8);
        t2TextView9 = findViewById(R.id.t2TextView9);
        t2TextView10 = findViewById(R.id.t2TextView10);
        t2TextView11 = findViewById(R.id.t2TextView11);
        t2TextView12 = findViewById(R.id.t2TextView12);
        t2TextView13 = findViewById(R.id.t2TextView13);
        t2TextView14 = findViewById(R.id.t2TextView14);
        t2TextView15 = findViewById(R.id.t2TextView15);
        t2TextView16 = findViewById(R.id.t2TextView16);
        t2TextView17 = findViewById(R.id.t2TextView17);
        t2TextView18 = findViewById(R.id.t2TextView18);
        t2TextView19 = findViewById(R.id.t2TextView19);
        t2TextView20 = findViewById(R.id.t2TextView20);
        t2TextView21 = findViewById(R.id.t2TextView21);
        t2TextView22 = findViewById(R.id.t2TextView22);
        t2TextView23 = findViewById(R.id.t2TextView23);
        t2TextView24 = findViewById(R.id.t2TextView24);
        t2TextView25 = findViewById(R.id.t2TextView25);
        t2TextView26 = findViewById(R.id.t2TextView26);
        t2TextView27 = findViewById(R.id.t2TextView27);
        t2TextView28 = findViewById(R.id.t2TextView28);
        t2TextView29 = findViewById(R.id.t2TextView29);
        t2TextView30 = findViewById(R.id.t2TextView30);
        t2TextView31 = findViewById(R.id.t2TextView31);
        t2EditText1 = findViewById(R.id.t2EditText1);
        t2EditText2 = findViewById(R.id.t2EditText2);
        t2EditText3 = findViewById(R.id.t2EditText3);
        t2EditText4 = findViewById(R.id.t2EditText4);
        t2EditText5 = findViewById(R.id.t2EditText5);
        t2EditText6 = findViewById(R.id.t2EditText6);
        t2EditText7 = findViewById(R.id.t2EditText7);
        t2EditText8 = findViewById(R.id.t2EditText8);
        t2EditText9 = findViewById(R.id.t2EditText9);
        t2ZoomControls = findViewById(R.id.t2ZoomControls);
        act1Start = findViewById(R.id.act1Start);
        act1Score = findViewById(R.id.act1Score);
        act1Direction = findViewById(R.id.act1Direction);
        act1Layout = findViewById(R.id.act1Layout);
        act1Answer = findViewById(R.id.act1Answer);
        act1Feedback = findViewById(R.id.act1Feedback);
        act1Question = findViewById(R.id.act1Question);
        act1Submit = findViewById(R.id.act1Submit);
        saveAns = findViewById(R.id.saveAns);

        startTime2 = System.currentTimeMillis();

        String t2TextView2Format = "A <b><i>Simple event</i></b> is an event with a single outcome. A <b><i>Compound event</i></b> is the combination of two or more simple events (with two or more outcomes). To understand clearly, consider the examples below.";
        String t2TextView3Format = "<b>Simple Events</b><br><br>1. The probability of rolling a “3” on a die.<br><br><i><u>One event:</u></i> rolling a “3” on a die<br><br>" +
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
            t2TextView2.setText(Html.fromHtml(t2TextView2Format, Html.FROM_HTML_MODE_COMPACT));
            t2TextView3.setText(Html.fromHtml(t2TextView3Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t2TextView2.setText(Html.fromHtml(t2TextView2Format));
            t2TextView3.setText(Html.fromHtml(t2TextView3Format));
        }

        loadAnswers();
        t2EditText1.setText(ans1);
        t2EditText2.setText(ans2);
        t2EditText3.setText(ans3);
        t2EditText4.setText(ans4);
        t2EditText5.setText(ans5);
        t2EditText6.setText(ans6);
        t2EditText7.setText(ans7);
        t2EditText8.setText(ans8);
        t2EditText9.setText(ans9);

        loadScore();
        act1Score.setText("Score: " + scoreValue + "/10");

        topicTwoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView[] textViewTitle = new TextView[]{t2TextViewTitle1, t2TextViewTitle2, t2TextViewTitle3};
        TextView[] textView = new TextView[]{t2TextView1, t2TextView2, t2TextView3, t2TextView4, t2TextView5, t2TextView6, t2TextView7, t2TextView8, t2TextView9, t2TextView10, t2TextView11, t2TextView12, t2TextView13, t2TextView14, t2TextView15, t2TextView16, t2TextView17, t2TextView18, t2TextView19, t2TextView20, t2TextView21, t2TextView22, t2TextView23, t2TextView24, t2TextView25, t2TextView26, t2TextView27, t2TextView28, t2TextView29, t2TextView30, t2TextView31, act1Score, act1Direction, act1Question};

        t2ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
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

        t2ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
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

        saveAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editText1 =  t2EditText1.getText().toString();
                String editText2 =  t2EditText2.getText().toString();
                String editText3 =  t2EditText3.getText().toString();
                String editText4 =  t2EditText4.getText().toString();
                String editText5 =  t2EditText5.getText().toString();
                String editText6 =  t2EditText6.getText().toString();
                String editText7 =  t2EditText7.getText().toString();
                String editText8 =  t2EditText8.getText().toString();
                String editText9 =  t2EditText9.getText().toString();

                storeAnswer(editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9);
            }
        });

        act1Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act1Layout.setVisibility(View.VISIBLE);
                act1Submit.setVisibility(View.VISIBLE);
                act1Start.setVisibility(View.GONE);
                String actDirectionFormat = "<i>Directions</i>: Write <b>S</b> if the required probability is simple event and write <b>C</b> if compound event in each of the following items/problems:";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    act1Direction.setText(Html.fromHtml(actDirectionFormat, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    act1Direction.setText(Html.fromHtml(actDirectionFormat));
                }

                act1Feedback.setImageDrawable(null);
                act1Answer.setEnabled(true);
                act1Answer.setText("");
                act1Submit.setText("Submit");
                act1Submit.setBackgroundTintList(getResources().getColorStateList(R.color.sea_green));

                currentIndex = 0;
                score = 0;
                nextQuestion = false;

                shownQuestionIndices.clear();
                generateQuestions();
            }
        });

        act1Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nextQuestion == false) {
                    String enteredAnswer = act1Answer.getText().toString();
                    if (enteredAnswer.isEmpty()) {
                        Toast.makeText(TopicTwoActivity.this, "Please answer the question", Toast.LENGTH_SHORT).show();
                    } else {
                        nextQuestion = true;
                        act1Submit.setText("Next");
                        act1Submit.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                        act1Answer.setEnabled(false);
                        if (enteredAnswer.equals(ansCaps) || enteredAnswer.equals(ansLow)) {
                            act1Feedback.setImageResource(R.drawable.round_check_24);
                            score++;
                        } else {
                            act1Feedback.setImageResource(R.drawable.round_clear_24);
                        }
                    }
                } else {
                    currentIndex++;
                    if (currentIndex < actQuesLength) {
                        generateQuestions();
                        nextQuestion = false;
                        act1Submit.setText("Submit");
                        act1Submit.setBackgroundTintList(getResources().getColorStateList(R.color.sea_green));
                        act1Answer.setEnabled(true);
                        act1Feedback.setImageDrawable(null);
                        act1Answer.setText("");
                    } else {
                        showScore();
                    }
                }
            }
        });

    }

    private void loadAnswers() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME1, Context.MODE_PRIVATE);
        ans1 = prefs.getString(KEY_ANSWER1, "");
        ans2 = prefs.getString(KEY_ANSWER2, "");
        ans3 = prefs.getString(KEY_ANSWER3, "");
        ans4 = prefs.getString(KEY_ANSWER4, "");
        ans5 = prefs.getString(KEY_ANSWER5, "");
        ans6 = prefs.getString(KEY_ANSWER6, "");
        ans7 = prefs.getString(KEY_ANSWER7, "");
        ans8 = prefs.getString(KEY_ANSWER8, "");
        ans9 = prefs.getString(KEY_ANSWER9, "");
    }

    private void storeAnswer(String editText1, String editText2, String editText3, String editText4, String editText5, String editText6, String editText7, String editText8, String editText9) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME1, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_ANSWER1, editText1);
        editor.putString(KEY_ANSWER2, editText2);
        editor.putString(KEY_ANSWER3, editText3);
        editor.putString(KEY_ANSWER4, editText4);
        editor.putString(KEY_ANSWER5, editText5);
        editor.putString(KEY_ANSWER6, editText6);
        editor.putString(KEY_ANSWER7, editText7);
        editor.putString(KEY_ANSWER8, editText8);
        editor.putString(KEY_ANSWER9, editText9);
        editor.apply();
        Toast.makeText(TopicTwoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private void loadScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME2, Context.MODE_PRIVATE);
        scoreValue = prefs.getInt(KEY_SCORE, 0);
    }

    private void storeScore(int activityScore) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SCORE, activityScore);
        editor.apply();
    }

    private void generateQuestions() {
        int randomIndex;
        do {
            randomIndex = random.nextInt(actQuesLength);
        } while (shownQuestionIndices.contains(randomIndex));

        shownQuestionIndices.add(randomIndex);

        if (shownQuestionIndices.size() == actQuesLength) {
            shownQuestionIndices.clear();
        }

        act1Question.setText(activityQuestions.getAct1Question(randomIndex));
        ansCaps = activityQuestions.getCorrectAnsCaps(randomIndex);
        ansLow = activityQuestions.getCorrectAnsLow(randomIndex);
    }

    private void showScore() {
        act1Layout.setVisibility(View.GONE);
        act1Submit.setVisibility(View.GONE);
        act1Start.setVisibility(View.VISIBLE);
        act1Start.setText("Try Again");

        String actDirectionFormat = "Activity Complete!<br>Your score is <b>" + score + "</b> out of 10.";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            act1Direction.setText(Html.fromHtml(actDirectionFormat, Html.FROM_HTML_MODE_COMPACT));
        } else {
            act1Direction.setText(Html.fromHtml(actDirectionFormat));
        }

        act1Score.setText("Score: " + score + "/10");
        storeScore(score);
    }

    @Override
    public void onBackPressed() {
        endTime2 = System.currentTimeMillis();
        long timeDiff = endTime2 - startTime2;
        int newTime = (int) (timeDiff / 1000);

        Boolean checkId = dbHandler.checkTopicsId(901);
        if (checkId == true) {
            Map<String, String> studyTime = dbHandler.getTopicsId(901);
            if (!studyTime.isEmpty()) {
                int prevTime = Integer.parseInt(studyTime.get(TIME_IN_SEC));
                int totalTime = newTime + prevTime;
                dbHandler.updateTime(new StudyModelClass(901, totalTime));
            }
        } else {
            dbHandler.storeStudyTime(new StudyModelClass(901, newTime));
        }

        super.onBackPressed();
    }
}