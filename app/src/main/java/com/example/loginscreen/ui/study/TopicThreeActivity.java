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
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.widget.ZoomControls;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class TopicThreeActivity extends AppCompatActivity {

    private TextView t3TextViewTitle1, t3TextViewTitle2, t3TextViewTitle3, t3TextView1, t3TextView2, t3TextView3, t3TextView4, t3TextView5, t3TextView6, t3TextView7, t3TextView8, t3TextView9, t3TextView10, t3TextView11, t3TextView12, t3TextView13, t3TextView14, t3TextView15, t3TextView16, t3TextView17, t3TextView18, t3TextView19, t3TextView20, t3TextView21, t3TextView22, t3TextView23, t3TextView24, t3TextView25, t3TextView26, t3TextView27, t3TextView28, act21Score, act21Direction, act21Question, act22Score, act22Direction, act22Question;
    private ImageView topicThreeBack, act21Feedback, act22Feedback;
    private JLatexMathView t3Latex_1, t3Latex_2, t3Latex_3, t3Latex_4, t3Latex_5;
    private VideoView t3Video;
    private ActivityQuestions activityQuestions = new ActivityQuestions();
    private int act21QuesLength = activityQuestions.act21Questions.length, act22QuesLength = activityQuestions.act22Questions.length;
    private LinearLayout act21Layout, act21Opt, act22Layout;
    private EditText act22Answer;
    private Button act21Opt1, act21Opt2, act21Start, act21Submit, act22Start, act22Submit;
    private ZoomControls t3ZoomControls;
    private int textSize = 16, score21, score22, currentIndex;
    private long startTime3, endTime3, scoreValue21, scoreValue22;
    private boolean nextQuestion = false, activityOngoing = false, activity21Selected = false, activity22Selected = false;
    private String selectedAnswer, answer21, simpAnswer22, answer22;
    private static final String PREFS_FILE_NAME = "Activity2Score";
    private static final String KEY_SCORE21 = "score21";
    private static final String KEY_SCORE22 = "score22";
    private Set<Integer> shownQuestionIndices = new HashSet<>();
    Random random = new Random();
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_three);

        dbHandler = new DBHandler(this);

        topicThreeBack = findViewById(R.id.topicThreeBack);
        t3TextViewTitle1 = findViewById(R.id.t3TextViewTitle1);
        t3TextViewTitle2 = findViewById(R.id.t3TextViewTitle2);
        t3TextViewTitle3 = findViewById(R.id.t3TextViewTitle3);
        t3TextView1 = findViewById(R.id.t3TextView1);
        t3TextView2 = findViewById(R.id.t3TextView2);
        t3TextView3 = findViewById(R.id.t3TextView3);
        t3TextView4 = findViewById(R.id.t3TextView4);
        t3TextView5 = findViewById(R.id.t3TextView5);
        t3TextView6 = findViewById(R.id.t3TextView6);
        t3TextView7 = findViewById(R.id.t3TextView7);
        t3TextView8 = findViewById(R.id.t3TextView8);
        t3TextView9 = findViewById(R.id.t3TextView9);
        t3TextView10 = findViewById(R.id.t3TextView10);
        t3TextView11 = findViewById(R.id.t3TextView11);
        t3TextView12 = findViewById(R.id.t3TextView12);
        t3TextView13 = findViewById(R.id.t3TextView13);
        t3TextView14 = findViewById(R.id.t3TextView14);
        t3TextView15 = findViewById(R.id.t3TextView15);
        t3TextView16 = findViewById(R.id.t3TextView16);
        t3TextView17 = findViewById(R.id.t3TextView17);
        t3TextView18 = findViewById(R.id.t3TextView18);
        t3TextView19 = findViewById(R.id.t3TextView19);
        t3TextView20 = findViewById(R.id.t3TextView20);
        t3TextView21 = findViewById(R.id.t3TextView21);
        t3TextView22 = findViewById(R.id.t3TextView22);
        t3TextView23 = findViewById(R.id.t3TextView23);
        t3TextView24 = findViewById(R.id.t3TextView24);
        t3TextView25 = findViewById(R.id.t3TextView25);
        t3TextView26 = findViewById(R.id.t3TextView26);
        t3TextView27 = findViewById(R.id.t3TextView27);
        t3TextView28 = findViewById(R.id.t3TextView28);
        t3Latex_1 = findViewById(R.id.t3Latex_1);
        t3Latex_2 = findViewById(R.id.t3Latex_2);
        t3Latex_3 = findViewById(R.id.t3Latex_3);
        t3Latex_4 = findViewById(R.id.t3Latex_4);
        t3Latex_5 = findViewById(R.id.t3Latex_5);
        t3Video = findViewById(R.id.t3Video);
        t3ZoomControls = findViewById(R.id.t3ZoomControls);
        act21Score = findViewById(R.id.act21Score);
        act21Direction = findViewById(R.id.act21Direction);
        act21Question = findViewById(R.id.act21Question);
        act21Feedback = findViewById(R.id.act21Feedback);
        act21Layout = findViewById(R.id.act21Layout);
        act21Opt = findViewById(R.id.act21Opt);
        act21Opt1 = findViewById(R.id.act21Opt1);
        act21Opt2 = findViewById(R.id.act21Opt2);
        act21Start = findViewById(R.id.act21Start);
        act21Submit = findViewById(R.id.act21Submit);
        act22Score = findViewById(R.id.act22Score);
        act22Direction = findViewById(R.id.act22Direction);
        act22Question = findViewById(R.id.act22Question);
        act22Feedback = findViewById(R.id.act22Feedback);
        act22Answer = findViewById(R.id.act22Answer);
        act22Layout = findViewById(R.id.act22Layout);
        act22Start = findViewById(R.id.act22Start);
        act22Submit = findViewById(R.id.act22Submit);

        startTime3 = System.currentTimeMillis();

        t3Latex_1.setLatexDrawable(JLatexMathDrawable.builder("P(A \\cap B) = P(A \\text{ or } B) = P(A) + P(B)").textSize(50).background(0xFFffffff).padding(8).align(JLatexMathDrawable.ALIGN_CENTER).build());
        t3Latex_2.setLatexDrawable(JLatexMathDrawable.builder("P(2) = \\frac{1}{6} \\text{ and } P(B) = \\frac{1}{6}").textSize(50).background(0xFFffffff).padding(8).build());
        t3Latex_3.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ or } B) = P(A) + P(B)\\\\P(2 \\text{ or } 5) = P(2) + P(5)\\\\= \\frac{1}{6} + \\frac{1}{6}\\\\= \\frac{2}{6}\\\\= \\frac{1}{3}").textSize(50).background(0xFFffffff).padding(8).build());
        t3Latex_4.setLatexDrawable(JLatexMathDrawable.builder("P(queen) = \\frac{4}{52} = \\frac{1}{13} \\text{ and }\\\\P(ace) = \\frac{4}{52} = \\frac{1}{13}").textSize(50).background(0xFFffffff).padding(8).build());
        t3Latex_5.setLatexDrawable(JLatexMathDrawable.builder("P(A \\text{ or } B) = P(A) + P(B)\\\\P(queen \\text{ or } ace) = P(queen) + P(ace)\\\\= \\frac{1}{13} + \\frac{1}{13}\\\\= \\frac{2}{13}").textSize(50).background(0xFFffffff).padding(8).build());

        String t3TextView3Format = "<b>Mutually exclusive events</b> are two or more events that cannot happen at the same time. For example, if we toss a coin, either heads or tails might turn up, but not heads and tails at the same time. There is no other way you could get both results. Similarly, in a single throw of a die, we can only have one number shown at the top face. If you roll a 3 or 5 on a die, the results either be a 3 or a 5 but not both. Such events are also called disjoint events since they do not happen simultaneously.";
        String t3TextView4Format = "Also, two sets are known to be <b><i>mutually exclusive</i></b> when they have no common elements.<br>Consider the set of all even positive integers, and the set of all odd positive integers:";
        String t3TextView7Format = "<b>Non-mutually exclusive events</b> are events that can happen at the same time. For example, landing an even number or a number divisible by 4 on a die. These two events have a possibility that both events can happen together. Also, two sets are <b>non-mutually exclusive</b> if they share common elements.";
        String t3TextView9Format = "Set A = {10, 11, <b>12</b>, 13, 14, <b>15</b>, 16, 17, <b>18</b>, 19, 20}<br>Set B = {3, 6, 9, <b>12</b>, <b>15</b>, <b>18</b>}";
        String t3TextView10Format = "We call them <b>non-mutually exclusive</b> since they share the common elements of 12, 15, and 18.";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t3TextView3.setText(Html.fromHtml(t3TextView3Format, Html.FROM_HTML_MODE_COMPACT));
            t3TextView4.setText(Html.fromHtml(t3TextView4Format, Html.FROM_HTML_MODE_COMPACT));
            t3TextView7.setText(Html.fromHtml(t3TextView7Format, Html.FROM_HTML_MODE_COMPACT));
            t3TextView9.setText(Html.fromHtml(t3TextView9Format, Html.FROM_HTML_MODE_COMPACT));
            t3TextView10.setText(Html.fromHtml(t3TextView10Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t3TextView3.setText(Html.fromHtml(t3TextView3Format));
            t3TextView4.setText(Html.fromHtml(t3TextView4Format));
            t3TextView7.setText(Html.fromHtml(t3TextView7Format));
            t3TextView9.setText(Html.fromHtml(t3TextView9Format));
            t3TextView10.setText(Html.fromHtml(t3TextView10Format));
        }

        t3Video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.mutually_exclusive_events);
        MediaController mediaController = new MediaController(TopicThreeActivity.this);
        mediaController.setAnchorView(t3Video);
        t3Video.setMediaController(mediaController);

        loadScore();
        act21Score.setText("Score: " + scoreValue21 + "/" + act21QuesLength);
        act22Score.setText("Score: " + scoreValue22 + "/" + act22QuesLength);

        TextView[] textViewTitle = {t3TextViewTitle1, t3TextViewTitle2, t3TextViewTitle3};
        TextView[] textView = {t3TextView1, t3TextView2, t3TextView3, t3TextView4, t3TextView5, t3TextView6, t3TextView7, t3TextView8, t3TextView9, t3TextView10, t3TextView11, t3TextView12, t3TextView13, t3TextView14, t3TextView15, t3TextView16, t3TextView17, t3TextView18, t3TextView19, t3TextView20, t3TextView21, t3TextView22, t3TextView23, t3TextView24, t3TextView25, t3TextView26, t3TextView27, t3TextView28, act21Score, act21Direction, act21Question, act22Score, act22Direction, act22Question};

        t3ZoomControls.setOnZoomInClickListener(new View.OnClickListener() {
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

        t3ZoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
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

        topicThreeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        act21Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityOngoing == true) {
                    Toast.makeText(TopicThreeActivity.this, "An activity is ongoing.", Toast.LENGTH_SHORT).show();
                } else {
                    activityOngoing = true;
                    activity21Selected = true;
                    activity22Selected = false;
                    act21Layout.setVisibility(View.VISIBLE);
                    act21Opt.setVisibility(View.VISIBLE);
                    act21Submit.setVisibility(View.INVISIBLE);
                    act21Start.setVisibility(View.GONE);
                    act21Direction.setText("Determine whether the events are mutually exclusive or not.");
                    act21Feedback.setImageDrawable(null);
                    act21Opt1.setEnabled(true);
                    act21Opt1.setBackgroundTintList(null);
                    act21Opt1.setBackgroundResource(R.drawable.custom_selected_button);
                    act21Opt1.setTextColor(getResources().getColor(R.color.white));
                    act21Opt2.setEnabled(true);
                    act21Opt2.setBackgroundTintList(null);
                    act21Opt2.setBackgroundResource(R.drawable.custom_selected_button);
                    act21Opt2.setTextColor(getResources().getColor(R.color.white));
                    act21Submit.setText("Submit");
                    act21Submit.setBackgroundTintList(getResources().getColorStateList(R.color.sea_green));
                    currentIndex = 0;
                    score21 = 0;
                    nextQuestion = false;
                    shownQuestionIndices.clear();
                    generateAct21Questions();
                }
            }
        });

        act21Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nextQuestion == false) {
                    nextQuestion = true;
                    act21Submit.setText("Next");
                    act21Submit.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                    act21Opt1.setEnabled(false);
                    act21Opt2.setEnabled(false);
                    if (selectedAnswer.equals(answer21)) {
                        act21Feedback.setImageResource(R.drawable.round_check_24);
                        score21++;
                    } else {
                        act21Feedback.setImageResource(R.drawable.round_clear_24);
                    }
                }else {
                    currentIndex++;
                    if (currentIndex < act21QuesLength) {
                        generateAct21Questions();
                        nextQuestion = false;
                        act21Submit.setVisibility(View.INVISIBLE);
                        act21Submit.setText("Submit");
                        act21Submit.setBackgroundTintList(getResources().getColorStateList(R.color.sea_green));
                        act21Opt1.setEnabled(true);
                        act21Opt2.setEnabled(true);
                        act21Opt1.setBackgroundTintList(null);
                        act21Opt1.setBackgroundResource(R.drawable.custom_selected_button);
                        act21Opt1.setTextColor(getResources().getColor(R.color.white));
                        act21Opt2.setBackgroundTintList(null);
                        act21Opt2.setBackgroundResource(R.drawable.custom_selected_button);
                        act21Opt2.setTextColor(getResources().getColor(R.color.white));
                        act21Feedback.setImageDrawable(null);
                    } else {
                        showScore();
                    }
                }
            }
        });

        act21Opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = "Mutually Exclusive";
                act21Submit.setVisibility(View.VISIBLE);
                act21Opt1.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                act21Opt1.setTextColor(getResources().getColor(R.color.blue));
                act21Opt2.setBackgroundTintList(null);
                act21Opt2.setBackgroundResource(R.drawable.custom_selected_button);
                act21Opt2.setTextColor(getResources().getColor(R.color.white));
            }
        });

        act21Opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = "Not Mutually Exclusive";
                act21Submit.setVisibility(View.VISIBLE);
                act21Opt2.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                act21Opt2.setTextColor(getResources().getColor(R.color.blue));
                act21Opt1.setBackgroundTintList(null);
                act21Opt1.setBackgroundResource(R.drawable.custom_selected_button);
                act21Opt1.setTextColor(getResources().getColor(R.color.white));
            }
        });

        act22Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityOngoing == true) {
                    Toast.makeText(TopicThreeActivity.this, "An activity is ongoing.", Toast.LENGTH_SHORT).show();
                } else {
                    activityOngoing = true;
                    activity22Selected = true;
                    activity21Selected = false;
                    act22Layout.setVisibility(View.VISIBLE);
                    act22Submit.setVisibility(View.VISIBLE);
                    act22Start.setVisibility(View.GONE);
                    act22Direction.setText("Calculate the probability of mutually exclusive events");
                    act22Feedback.setImageDrawable(null);
                    act22Answer.setEnabled(true);
                    act22Answer.setText("");
                    act22Submit.setText("Submit");
                    act22Submit.setBackgroundTintList(getResources().getColorStateList(R.color.sea_green));
                    currentIndex = 0;
                    score22 = 0;
                    nextQuestion = false;
                    shownQuestionIndices.clear();
                    generateAct22Questions();
                }
            }
        });

        act22Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nextQuestion == false) {
                    String enteredAnswer = act22Answer.getText().toString();
                    if (enteredAnswer.isEmpty()) {
                        Toast.makeText(TopicThreeActivity.this, "Please answer the question", Toast.LENGTH_SHORT).show();
                    } else {
                        nextQuestion = true;
                        act22Submit.setText("Next");
                        act22Submit.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                        act22Answer.setEnabled(false);
                        if (enteredAnswer.equals(simpAnswer22) || enteredAnswer.equals(answer22)) {
                            act22Feedback.setImageResource(R.drawable.round_check_24);
                            score22++;
                        } else {
                            act22Feedback.setImageResource(R.drawable.round_clear_24);
                        }
                    }
                } else {
                    currentIndex++;
                    if (currentIndex < act22QuesLength) {
                        generateAct22Questions();
                        nextQuestion = false;
                        act22Submit.setText("Submit");
                        act22Submit.setBackgroundTintList(getResources().getColorStateList(R.color.sea_green));
                        act22Answer.setEnabled(true);
                        act22Feedback.setImageDrawable(null);
                        act22Answer.setText("");
                    } else {
                        showScore();
                    }
                }
            }
        });

    }

    private void generateAct21Questions() {
        int randomIndex;
        do {
            randomIndex = random.nextInt(act21QuesLength);
        } while (shownQuestionIndices.contains(randomIndex));

        shownQuestionIndices.add(randomIndex);

        if (shownQuestionIndices.size() == act21QuesLength) {
            shownQuestionIndices.clear();
        }

        act21Question.setText(activityQuestions.getAct21Question(randomIndex));
        answer21 = activityQuestions.getAct21Answer(randomIndex);
    }

    private void generateAct22Questions() {
        int randomIndex;
        do {
            randomIndex = random.nextInt(act22QuesLength);
        } while (shownQuestionIndices.contains(randomIndex));

        shownQuestionIndices.add(randomIndex);

        if (shownQuestionIndices.size() == act22QuesLength) {
            shownQuestionIndices.clear();
        }

        act22Question.setText(activityQuestions.getAct22Question(randomIndex));
        simpAnswer22 = activityQuestions.getAct22SimpAns(randomIndex);
        answer22 = activityQuestions.getAct22Ans(randomIndex);
    }

    private void showScore() {
        if (activity21Selected == true) {
            act21Layout.setVisibility(View.GONE);
            act21Opt.setVisibility(View.GONE);
            act21Submit.setVisibility(View.GONE);
            act21Start.setVisibility(View.VISIBLE);
            act21Start.setText("Try Again");
            activityOngoing = false;
            String actDirectionFormat = "Activity Part I Complete!<br>Your score is <b>" + score21 + "</b> out of " + act21QuesLength + ".";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                act21Direction.setText(Html.fromHtml(actDirectionFormat, Html.FROM_HTML_MODE_COMPACT));
            } else {
                act21Direction.setText(Html.fromHtml(actDirectionFormat));
            }
            act21Score.setText("Score: " + score21 + "/" + act21QuesLength);
            storeScore21(score21);
        } else if (activity22Selected == true) {
            act22Layout.setVisibility(View.GONE);
            act22Submit.setVisibility(View.GONE);
            act22Start.setVisibility(View.VISIBLE);
            act22Start.setText("Try Again");
            activityOngoing = false;
            String actDirectionFormat = "Activity Complete!<br>Your score is <b>" + score22 + "</b> out of " + act22QuesLength + ".";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                act22Direction.setText(Html.fromHtml(actDirectionFormat, Html.FROM_HTML_MODE_COMPACT));
            } else {
                act22Direction.setText(Html.fromHtml(actDirectionFormat));
            }
            act22Score.setText("Score: " + score22 + "/" + act22QuesLength);
            storeScore22(score22);
        }
    }

    private void storeScore21(int score21) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SCORE21, score21);
        editor.apply();
    }

    private void storeScore22(int score22) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_SCORE22, score22);
        editor.apply();
    }

    private void loadScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        scoreValue21 = prefs.getInt(KEY_SCORE21, 0);
        scoreValue22 = prefs.getInt(KEY_SCORE22, 0);
    }

    @Override
    public void onBackPressed() {
        endTime3 = System.currentTimeMillis();
        long timeDiff = endTime3 - startTime3;
        int newTime = (int) (timeDiff / 1000);

        Boolean checkId = dbHandler.checkTopicsId(590);
        if (checkId == true) {
            Map<String, String> studyTime = dbHandler.getTopicsId(590);
            if (!studyTime.isEmpty()) {
                int prevTime = Integer.parseInt(studyTime.get(TIME_IN_SEC));
                int totalTime = newTime + prevTime;
                dbHandler.updateTime(new StudyModelClass(590, totalTime));
            }
        } else {
            dbHandler.storeStudyTime(new StudyModelClass(590, newTime));
        }

        super.onBackPressed();
    }
}