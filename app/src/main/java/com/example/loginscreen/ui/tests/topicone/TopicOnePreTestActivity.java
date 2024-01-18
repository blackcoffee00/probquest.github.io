package com.example.loginscreen.ui.tests.topicone;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.R;
import com.example.loginscreen.ui.tests.TestsDBHandler;
import com.example.loginscreen.ui.tests.TestsModelClass;

import java.util.Locale;

public class TopicOnePreTestActivity extends AppCompatActivity {
    private Button topicOnePreTestBack,topicOnePreTestStart, topicOnePreTestOptA, topicOnePreTestOptB, topicOnePreTestOptC, topicOnePreTestOptD, topicOnePreTestNext, topicOnePreTestExit;
    private LinearLayout topicOnePreTestInstruction, topicOnePreTest, topicOnePreTestScore;
    private TextView topicOnePreTestItem, topicOnePreTestTimer, topicOnePreTestQuestion, topicOnePerTestScoreDisp, topicOnePerTestTimeDisp;
    private ProgressBar topicOnePreTestProgress;
    private int[] correctAnswerIndexes = {1,1,2,0,1,0,1,0,2,0};
    private int currentQuestionIndex;
    private int index;
    private int totalQuestions;
    private int score;
    private static final long COUNTDOWN_IN_MILLIS = 16000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private long startTime;
    private String topicOnePreTestTotalScore, topicOnePreTestTotalTime;
    TestsDBHandler testsdbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_one_pre_test);

        testsdbHandler = new TestsDBHandler(this);

        topicOnePreTestBack = findViewById(R.id.topicOnePreTestBack);
        topicOnePreTestStart = findViewById(R.id.topicOnePreTestStart);
        topicOnePreTestInstruction = findViewById(R.id.topicOnePreTestInstruction);

        topicOnePreTest = findViewById(R.id.topicOnePreTest);
        topicOnePreTestItem = findViewById(R.id.topicOnePreTestItem);
        topicOnePreTestTimer = findViewById(R.id.topicOnePreTestTimer);
        topicOnePreTestQuestion = findViewById(R.id.topicOnePreTestQuestion);
        topicOnePreTestProgress = findViewById(R.id.topicOnePreTestProgress);
        topicOnePreTestOptA = findViewById(R.id.topicOnePreTestOptA);
        topicOnePreTestOptB = findViewById(R.id.topicOnePreTestOptB);
        topicOnePreTestOptC = findViewById(R.id.topicOnePreTestOptC);
        topicOnePreTestOptD = findViewById(R.id.topicOnePreTestOptD);
        topicOnePreTestNext = findViewById(R.id.topicOnePreTestNext);

        topicOnePreTestScore = findViewById(R.id.topicOnePreTestScore);
        topicOnePerTestScoreDisp = findViewById(R.id.topicOnePerTestScoreDisp);
        topicOnePerTestTimeDisp = findViewById(R.id.topicOnePerTestTimeDisp);
        topicOnePreTestExit = findViewById(R.id.topicOnePreTestExit);

        topicOnePreTestInstruction.setVisibility(View.VISIBLE);
        topicOnePreTest.setVisibility(View.GONE);
        topicOnePreTestScore.setVisibility(View.GONE);

        topicOnePreTestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                /**
                 Intent intent = new Intent(TopicOnePreTestActivity.this, NavigationActivity.class);
                 intent.putExtra("loadToTestsFragment", R.id.navigation_tests);
                 startActivity(intent);
                 finish();
                 */
            }
        });

        topicOnePreTestExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cursor cursor = testsdbHandler.getScoreTime();
                //testsdbHandler.updateScore(new TestsModelClass(cursor.getInt(1), topicOnePreTestTotalScore, topicOnePreTestTotalTime));
                onBackPressed();
            }
        });

        topicOnePreTestStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicOnePreTestInstruction.setVisibility(View.GONE);
                topicOnePreTest.setVisibility(View.VISIBLE);
                topicOnePreTestScore.setVisibility(View.GONE);

                currentQuestionIndex = 0;
                index = 0;
                totalQuestions = 5;
                score = 0;

                topicOnePreTestProgress.setMax(totalQuestions);
                topicOnePreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                displayQuestion();
            }
        });

        topicOnePreTestNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int correctAnswerIndex = correctAnswerIndexes[currentQuestionIndex];
                if (correctAnswerIndex == index) {
                    score++;
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < totalQuestions) {
                    topicOnePreTestProgress.setProgress(currentQuestionIndex + 1);
                    displayQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        topicOnePreTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                topicOnePreTestNext.setVisibility(View.VISIBLE);
                topicOnePreTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePreTestOptA.setTextColor(getResources().getColor(R.color.white));
                topicOnePreTestOptB.setBackgroundTintList(null);
                topicOnePreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptB.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptC.setBackgroundTintList(null);
                topicOnePreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptC.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptD.setBackgroundTintList(null);
                topicOnePreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        topicOnePreTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                topicOnePreTestNext.setVisibility(View.VISIBLE);
                topicOnePreTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePreTestOptB.setTextColor(getResources().getColor(R.color.white));
                topicOnePreTestOptA.setBackgroundTintList(null);
                topicOnePreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptA.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptC.setBackgroundTintList(null);
                topicOnePreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptC.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptD.setBackgroundTintList(null);
                topicOnePreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        topicOnePreTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                topicOnePreTestNext.setVisibility(View.VISIBLE);
                topicOnePreTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePreTestOptC.setTextColor(getResources().getColor(R.color.white));
                topicOnePreTestOptB.setBackgroundTintList(null);
                topicOnePreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptB.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptA.setBackgroundTintList(null);
                topicOnePreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptA.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptD.setBackgroundTintList(null);
                topicOnePreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        topicOnePreTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 3;
                topicOnePreTestNext.setVisibility(View.VISIBLE);
                topicOnePreTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                topicOnePreTestOptD.setTextColor(getResources().getColor(R.color.white));
                topicOnePreTestOptB.setBackgroundTintList(null);
                topicOnePreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptB.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptC.setBackgroundTintList(null);
                topicOnePreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptC.setTextColor(getResources().getColor(R.color.black));
                topicOnePreTestOptA.setBackgroundTintList(null);
                topicOnePreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                topicOnePreTestOptA.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    private void displayQuestion() {
        String[] questions = getResources().getStringArray(R.array.topic_one_pre_test_questions);
        topicOnePreTestQuestion.setText(questions[currentQuestionIndex]);
        topicOnePreTestItem.setText((currentQuestionIndex + 1) + " of " + questions.length + " Questions");

        String[][] answerArrays = new String[][] {
                getResources().getStringArray(R.array.topic_one_pre_test_item1_options),
                getResources().getStringArray(R.array.topic_one_pre_test_item2_options),
                getResources().getStringArray(R.array.topic_one_pre_test_item3_options),
                getResources().getStringArray(R.array.topic_one_pre_test_item4_options),
                getResources().getStringArray(R.array.topic_one_pre_test_item5_options),
        };

        if (currentQuestionIndex < answerArrays.length) {
            String[] currentAnswers = answerArrays[currentQuestionIndex];

            for (int i=0; i<4; i++) {
                switch (i) {
                    case 0:
                        topicOnePreTestOptA.setText(currentAnswers[0]);
                        break;
                    case 1:
                        topicOnePreTestOptB.setText(currentAnswers[1]);
                        break;
                    case 2:
                        topicOnePreTestOptC.setText(currentAnswers[2]);
                        break;
                    case 3:
                        topicOnePreTestOptD.setText(currentAnswers[3]);
                        break;
                }
            }
        }

        if ((currentQuestionIndex + 1) < questions.length) {
            topicOnePreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questions.length) {
            topicOnePreTestNext.setText("Submit");
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

                if (currentQuestionIndex < totalQuestions) {
                    topicOnePreTestProgress.setProgress(currentQuestionIndex + 1);
                    displayQuestion();
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
        topicOnePreTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            topicOnePreTestTimer.setTextColor(Color.RED);
        } else {
            topicOnePreTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        topicOnePreTestInstruction.setVisibility(View.GONE);
        topicOnePreTest.setVisibility(View.GONE);
        topicOnePreTestScore.setVisibility(View.VISIBLE);

        topicOnePreTestTotalScore = String.valueOf(score);
        topicOnePerTestScoreDisp.setText("Total score: " + topicOnePreTestTotalScore + " / " + totalQuestions);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int hours = totalSeconds / 3600;
        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        topicOnePreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, min, secs);
        topicOnePerTestTimeDisp.setText("Total time: " + topicOnePreTestTotalTime);

        testsdbHandler.storeTestScore(new TestsModelClass(1, topicOnePreTestTotalScore, topicOnePreTestTotalTime));
    }

    private void resetButtonStyle() {
        topicOnePreTestNext.setVisibility(View.INVISIBLE);
        topicOnePreTestOptA.setBackgroundTintList(null);
        topicOnePreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePreTestOptA.setTextColor(getResources().getColor(R.color.black));
        topicOnePreTestOptB.setBackgroundTintList(null);
        topicOnePreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePreTestOptB.setTextColor(getResources().getColor(R.color.black));
        topicOnePreTestOptC.setBackgroundTintList(null);
        topicOnePreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePreTestOptC.setTextColor(getResources().getColor(R.color.black));
        topicOnePreTestOptD.setBackgroundTintList(null);
        topicOnePreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        topicOnePreTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}