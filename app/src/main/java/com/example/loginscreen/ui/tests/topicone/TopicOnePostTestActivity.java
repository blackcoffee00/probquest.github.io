package com.example.loginscreen.ui.tests.topicone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.NavigationActivity;
import com.example.loginscreen.R;
import com.example.loginscreen.ui.tests.TestsModelClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class TopicOnePostTestActivity extends AppCompatActivity {
    private Button topicOnePostTestBackCode, topicOnePostTestSend, topicOnePostTestBack,topicOnePostTestStart, topicOnePostTestOptA, topicOnePostTestOptB, topicOnePostTestOptC, topicOnePostTestOptD, topicOnePostTestNext, topicOnePostTestResult;
    private LinearLayout topicOnePostTestEnterPasscode, topicOnePostTestInstruction, topicOnePostTest, topicOnePostTestScore;
    private TextView topicOnePostTestItem, topicOnePostTestTimer, topicOnePostTestQuestion, topicOnePostTestScoreDisp, topicOnePostTestTimeDisp, t1PostTestScore;
    private EditText topicOnePostTestPasscode;
    private ListView topicOnePostTestListView;
    private RelativeLayout t1PostTestHeader;
    private ImageView t1PostTestExit;
    private ProgressBar topicOnePostTestProgress;
    private String topicOnePostTestTotalScore, topicOnePostTestTotalTime, answer, selectedAnswer;
    private int currentQuestionIndex, score;
    private List<String> testQuestions = new ArrayList<>();
    private List<String> testSolutions = new ArrayList<>();
    private List<String> testAnswers = new ArrayList<>();
    private List<String> btn1 = new ArrayList<>();
    private List<String> btn2 = new ArrayList<>();
    private List<String> btn3 = new ArrayList<>();
    private List<String> btn4 = new ArrayList<>();
    private int[] btn1bg;
    private int[] btn2bg;
    private int[] btn3bg;
    private int[] btn4bg;
    private TopiOnePostTestQuestion question = new TopiOnePostTestQuestion();
    private int questionLength = question.questions.length;
    private Set<Integer> shownQuestionIndices = new HashSet<>();
    Random random;
    private Integer passcode;
    private static final long COUNTDOWN_IN_MILLIS = 181000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis, startTime;
    private boolean isTestOngoing = false;
    private Boolean checkTestCode;
    private Cursor cursor;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_one_post_test);

        random = new Random();

        dbHandler = new DBHandler(this);

        topicOnePostTestEnterPasscode = findViewById(R.id.topicOnePostTestEnterPasscode);
        topicOnePostTestPasscode = findViewById(R.id.topicOnePostTestPasscode);
        topicOnePostTestBackCode = findViewById(R.id.topicOnePostTestBackCode);
        topicOnePostTestSend = findViewById(R.id.topicOnePostTestSend);

        topicOnePostTestBack = findViewById(R.id.topicOnePostTestBack);
        topicOnePostTestStart = findViewById(R.id.topicOnePostTestStart);
        topicOnePostTestInstruction = findViewById(R.id.topicOnePostTestInstruction);

        topicOnePostTest = findViewById(R.id.topicOnePostTest);
        topicOnePostTestItem = findViewById(R.id.topicOnePostTestItem);
        topicOnePostTestTimer = findViewById(R.id.topicOnePostTestTimer);
        topicOnePostTestQuestion = findViewById(R.id.topicOnePostTestQuestion);
        topicOnePostTestProgress = findViewById(R.id.topicOnePostTestProgress);
        topicOnePostTestOptA = findViewById(R.id.topicOnePostTestOptA);
        topicOnePostTestOptB = findViewById(R.id.topicOnePostTestOptB);
        topicOnePostTestOptC = findViewById(R.id.topicOnePostTestOptC);
        topicOnePostTestOptD = findViewById(R.id.topicOnePostTestOptD);
        topicOnePostTestNext = findViewById(R.id.topicOnePostTestNext);

        topicOnePostTestScore = findViewById(R.id.topicOnePostTestScore);
        topicOnePostTestScoreDisp = findViewById(R.id.topicOnePostTestScoreDisp);
        topicOnePostTestTimeDisp = findViewById(R.id.topicOnePostTestTimeDisp);
        topicOnePostTestResult = findViewById(R.id.topicOnePostTestResult);

        topicOnePostTestListView = findViewById(R.id.topicOnePostTestListView);
        t1PostTestExit = findViewById(R.id.t1PostTestExit);
        t1PostTestScore = findViewById(R.id.t1PostTestScore);
        t1PostTestHeader = findViewById(R.id.t1PostTestHeader);

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicOnePostTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers);
        topicOnePostTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(6856);
        if (checkTestCode == true) {
            topicOnePostTestEnterPasscode.setVisibility(View.GONE);
            topicOnePostTestScore.setVisibility(View.VISIBLE);

            cursor = dbHandler.getScoreTime();
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    int testType = cursor.getInt(0);
                    if (testType == 2) {
                        topicOnePostTestScoreDisp.setText("Score: " + cursor.getString(2) + "/" + questionLength);
                        topicOnePostTestTimeDisp.setText("Time: " + cursor.getString(3));
                    }
                }
            }
        } else {
            topicOnePostTestEnterPasscode.setVisibility(View.VISIBLE);
        }


        topicOnePostTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        topicOnePostTestSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topicOnePostTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicOnePostTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(topicOnePostTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicOnePostTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(6856)) {
                            topicOnePostTestEnterPasscode.setVisibility(View.GONE);
                            topicOnePostTestInstruction.setVisibility(View.VISIBLE);
                        } else if (passcode.equals(8080)) {                             // Free code
                            topicOnePostTestEnterPasscode.setVisibility(View.GONE);
                            topicOnePostTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicOnePostTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        topicOnePostTestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        topicOnePostTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicOnePostTestScore.setVisibility(View.GONE);
                topicOnePostTestListView.setVisibility(View.VISIBLE);
                t1PostTestHeader.setVisibility(View.VISIBLE);
                t1PostTestScore.setText("Score " + topicOnePostTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) topicOnePostTestListView.getAdapter()).notifyDataSetChanged();

                checkTestCode = dbHandler.checkCode(6856);
                if (checkTestCode == true) {
                    cursor = dbHandler.getScoreTime();
                    if (cursor.getCount() > 0) {
                        while (cursor.moveToNext()) {
                            int testType = cursor.getInt(0);
                            if (testType == 2) {
                                t1PostTestScore.setText("Score " + cursor.getString(2) + "/" + questionLength);
                            }
                        }
                    }
                }
            }
        });

        t1PostTestExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TopicOnePostTestActivity.this, NavigationActivity.class);
                intent.putExtra("loadToTestsFragment", R.id.navigation_tests);
                startActivity(intent);
                finish();
            }
        });

        topicOnePostTestStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicOnePostTestInstruction.setVisibility(View.GONE);
                topicOnePostTest.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                currentQuestionIndex = 0;
                score = 0;

                topicOnePostTestProgress.setMax(questionLength);
                topicOnePostTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        topicOnePostTestNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAnswer.equals(answer)) {
                    Toast.makeText(TopicOnePostTestActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    score++;
                } else {
                    Toast.makeText(TopicOnePostTestActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    topicOnePostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        topicOnePostTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = topicOnePostTestOptA.getText().toString();
                topicOnePostTestNext.setVisibility(View.VISIBLE);
                topicOnePostTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePostTestOptA.setTextColor(getResources().getColor(R.color.white));
                topicOnePostTestOptB.setBackgroundTintList(null);
                topicOnePostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptB.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptC.setBackgroundTintList(null);
                topicOnePostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptC.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptD.setBackgroundTintList(null);
                topicOnePostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        topicOnePostTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = topicOnePostTestOptB.getText().toString();
                topicOnePostTestNext.setVisibility(View.VISIBLE);
                topicOnePostTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePostTestOptB.setTextColor(getResources().getColor(R.color.white));
                topicOnePostTestOptA.setBackgroundTintList(null);
                topicOnePostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptA.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptC.setBackgroundTintList(null);
                topicOnePostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptC.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptD.setBackgroundTintList(null);
                topicOnePostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        topicOnePostTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = topicOnePostTestOptC.getText().toString();
                topicOnePostTestNext.setVisibility(View.VISIBLE);
                topicOnePostTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePostTestOptC.setTextColor(getResources().getColor(R.color.white));
                topicOnePostTestOptB.setBackgroundTintList(null);
                topicOnePostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptB.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptA.setBackgroundTintList(null);
                topicOnePostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptA.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptD.setBackgroundTintList(null);
                topicOnePostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        topicOnePostTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = topicOnePostTestOptD.getText().toString();
                topicOnePostTestNext.setVisibility(View.VISIBLE);
                topicOnePostTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePostTestOptD.setTextColor(getResources().getColor(R.color.white));
                topicOnePostTestOptB.setBackgroundTintList(null);
                topicOnePostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptB.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptC.setBackgroundTintList(null);
                topicOnePostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptC.setTextColor(getResources().getColor(R.color.black));
                topicOnePostTestOptA.setBackgroundTintList(null);
                topicOnePostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePostTestOptA.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicOnePostTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

    private void NextQuestion() {
        int randomIndex;
        do {
            randomIndex = random.nextInt(questionLength);
        } while (shownQuestionIndices.contains(randomIndex));

        shownQuestionIndices.add(randomIndex);

        if (shownQuestionIndices.size() == questionLength) {
            shownQuestionIndices.clear();
        }

        topicOnePostTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        topicOnePostTestQuestion.setText(question.getQuestion(randomIndex));
        topicOnePostTestOptA.setText(question.getChoice1(randomIndex));
        topicOnePostTestOptB.setText(question.getChoice2(randomIndex));
        topicOnePostTestOptC.setText(question.getChoice3(randomIndex));
        topicOnePostTestOptD.setText(question.getChoice4(randomIndex));
        answer = question.getCorrectAnswer(randomIndex);

        testQuestions.add(question.getQuestion(randomIndex));
        btn1.add(question.getChoice1(randomIndex));
        btn2.add(question.getChoice2(randomIndex));
        btn3.add(question.getChoice3(randomIndex));
        btn4.add(question.getChoice4(randomIndex));
        testSolutions.add(question.getSolution(randomIndex));
        testAnswers.add(question.getSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            topicOnePostTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            topicOnePostTestNext.setText("Submit");
        }

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    private void startCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    topicOnePostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis/1000) /60;
        int seconds = (int) (timeLeftInMillis/1000) %60;

        String timerFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        topicOnePostTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            topicOnePostTestTimer.setTextColor(Color.RED);
        } else {
            topicOnePostTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        topicOnePostTest.setVisibility(View.GONE);
        topicOnePostTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        topicOnePostTestTotalScore = String.valueOf(score);
        topicOnePostTestScoreDisp.setText("Score: " + topicOnePostTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int hours = totalSeconds / 3600;
        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        topicOnePostTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, min, secs);
        topicOnePostTestTimeDisp.setText("Time: " + topicOnePostTestTotalTime);

        dbHandler.storeTestScore(new TestsModelClass(2, 6856, topicOnePostTestTotalScore, topicOnePostTestTotalTime));
    }

    private void resetButtonStyle() {
        topicOnePostTestNext.setVisibility(View.INVISIBLE);
        topicOnePostTestOptA.setBackgroundTintList(null);
        topicOnePostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePostTestOptA.setTextColor(getResources().getColor(R.color.black));
        topicOnePostTestOptB.setBackgroundTintList(null);
        topicOnePostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePostTestOptB.setTextColor(getResources().getColor(R.color.black));
        topicOnePostTestOptC.setBackgroundTintList(null);
        topicOnePostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePostTestOptC.setTextColor(getResources().getColor(R.color.black));
        topicOnePostTestOptD.setBackgroundTintList(null);
        topicOnePostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePostTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}