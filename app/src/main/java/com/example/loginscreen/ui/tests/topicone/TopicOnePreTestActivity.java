package com.example.loginscreen.ui.tests.topicone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.example.loginscreen.NavigationActivity;
import com.example.loginscreen.R;
import com.example.loginscreen.DBHandler;
import com.example.loginscreen.ui.tests.ViewResultAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicOnePreTestActivity extends AppCompatActivity {
    private Button topicOnePreTestBackCode, topicOnePreTestSend, topicOnePreTestBack,topicOnePreTestStart, topicOnePreTestOptA, topicOnePreTestOptB, topicOnePreTestOptC, topicOnePreTestOptD, topicOnePreTestNext, topicOnePreTestResult;
    private LinearLayout topicOnePreTestEnterPasscode, topicOnePreTestInstruction, topicOnePreTest;
    private TextView topicOnePreTestItem, topicOnePreTestTimer, topicOnePreTestQuestion, topicOnePreTestScoreDisp, topicOnePreTestTimeDisp, t1PreTestScore;
    private EditText topicOnePreTestPasscode;
    private ListView topicOnePreTestListView;
    private RelativeLayout t1PreTestHeader, topicOnePreTestScore;
    private ImageView t1PreTestExit;
    private ProgressBar topicOnePreTestProgress;
    private String answer, selectedAnswer, topicOnePreTestTotalScore, topicOnePreTestTotalTime;
    private int currentQuestionIndex, score, markIndex, randomIndex, buttonIndex;
    private List<String> testQuestions = new ArrayList<>();
    private List<String> testSolutions = new ArrayList<>();
    private List<String> testAnswers = new ArrayList<>();
    private List<String> btn1 = new ArrayList<>();
    private List<String> btn2 = new ArrayList<>();
    private List<String> btn3 = new ArrayList<>();
    private List<String> btn4 = new ArrayList<>();
    private List<Integer> solVis = new ArrayList<>();
    private List<Integer> ansVis = new ArrayList<>();
    private int[] mark;
    private int[] btnbg1;
    private int[] btnbg2;
    private int[] btnbg3;
    private int[] btnbg4;
    private TopiOnePreTestQuestion question = new TopiOnePreTestQuestion();
    private int questionLength = question.questions.length;
    private Set<Integer> shownQuestionIndices = new HashSet<>();
    Random random;
    private Integer passcode;
    private static final long COUNTDOWN_IN_MILLIS = 91000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis, startTime;
    private boolean isTestOngoing = false;
    private Boolean checkTestCode;
    private Map<String, String> scoreAndTime;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_one_pre_test);

        random = new Random();

        dbHandler = new DBHandler(this);
        // Enter Passcode Layout
        topicOnePreTestEnterPasscode = findViewById(R.id.topicOnePreTestEnterPasscode);
        topicOnePreTestPasscode = findViewById(R.id.topicOnePreTestPasscode);
        topicOnePreTestBackCode = findViewById(R.id.topicOnePreTestBackCode);
        topicOnePreTestSend = findViewById(R.id.topicOnePreTestSend);
        // Direction Layout
        topicOnePreTestBack = findViewById(R.id.topicOnePreTestBack);
        topicOnePreTestStart = findViewById(R.id.topicOnePreTestStart);
        topicOnePreTestInstruction = findViewById(R.id.topicOnePreTestInstruction);
        // Test Layout
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
        // Score Layout
        topicOnePreTestScore = findViewById(R.id.topicOnePreTestScore);
        topicOnePreTestScoreDisp = findViewById(R.id.topicOnePerTestScoreDisp);
        topicOnePreTestTimeDisp = findViewById(R.id.topicOnePerTestTimeDisp);
        topicOnePreTestResult = findViewById(R.id.topicOnePreTestResult);
        // Result
        topicOnePreTestListView = findViewById(R.id.topicOnePreTestListView);
        t1PreTestHeader = findViewById(R.id.t1PreTestHeader);
        t1PreTestExit = findViewById(R.id.t1PreTestExit);
        t1PreTestScore = findViewById(R.id.t1PreTestScore);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicOnePreTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        topicOnePreTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(4276);
/**        if (checkTestCode == true) {
            topicOnePreTestEnterPasscode.setVisibility(View.GONE);
            topicOnePreTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(4276);
            if (!scoreAndTime.isEmpty()) {
                topicOnePreTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                topicOnePreTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            topicOnePreTestEnterPasscode.setVisibility(View.VISIBLE);
        }
*/
        topicOnePreTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        topicOnePreTestSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (topicOnePreTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicOnePreTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(topicOnePreTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);
/**
                    if (checkTestCode == true) {
                        Toast.makeText(TopicOnePreTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {*/
                        if (passcode.equals(4276)) {
                            topicOnePreTestEnterPasscode.setVisibility(View.GONE);
                            topicOnePreTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicOnePreTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    //}
                }
            }
        });

        topicOnePreTestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        topicOnePreTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicOnePreTestScore.setVisibility(View.GONE);
                topicOnePreTestListView.setVisibility(View.VISIBLE);
                t1PreTestHeader.setVisibility(View.VISIBLE);
                t1PreTestScore.setText("Score " + topicOnePreTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) topicOnePreTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.VISIBLE);
                    ansVis.add(View.VISIBLE);
                }
/**
                checkTestCode = dbHandler.checkCode(4276);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(4276);
                    if (!scoreAndTime.isEmpty()) {
                        t1PreTestScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }*/
            }
        });

        t1PreTestExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        topicOnePreTestStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topicOnePreTestInstruction.setVisibility(View.GONE);
                topicOnePreTest.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                topicOnePreTestProgress.setMax(questionLength);
                topicOnePreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        topicOnePreTestNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAnswer.equals(answer)) {
                    mark[currentQuestionIndex] = R.drawable.round_check_24;
                    score++;
                } else {
                    mark[currentQuestionIndex] = R.drawable.round_clear_24;
                }

                if (buttonIndex == 1) {
                    btnbg1[currentQuestionIndex] = R.drawable.custom_selected_button;
                    btnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg3[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg4[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (buttonIndex == 2) {
                    btnbg2[currentQuestionIndex] = R.drawable.custom_selected_button;
                    btnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg3[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg4[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (buttonIndex == 3) {
                    btnbg3[currentQuestionIndex] = R.drawable.custom_selected_button;
                    btnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg4[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (buttonIndex == 4) {
                    btnbg4[currentQuestionIndex] = R.drawable.custom_selected_button;
                    btnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                    btnbg3[currentQuestionIndex] = R.drawable.custom_option_button;
                }

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    topicOnePreTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
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
                buttonIndex = 1;
                selectedAnswer = topicOnePreTestOptA.getText().toString();
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
                buttonIndex = 2;
                selectedAnswer = topicOnePreTestOptB.getText().toString();
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
                buttonIndex = 3;
                selectedAnswer = topicOnePreTestOptC.getText().toString();
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
                buttonIndex = 4;
                selectedAnswer = topicOnePreTestOptD.getText().toString();
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

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicOnePreTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicOnePreTestActivity.this, NavigationActivity.class);
            intent.putExtra("loadToTestsFragment", R.id.navigation_tests);
            startActivity(intent);
            finish();
            super.onBackPressed();
        }
    }

    private void NextQuestion() {
        do {
            randomIndex = random.nextInt(questionLength);
        } while (shownQuestionIndices.contains(randomIndex));
        shownQuestionIndices.add(randomIndex);
        if (shownQuestionIndices.size() == questionLength) {
            shownQuestionIndices.clear();
        }

        topicOnePreTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        topicOnePreTestQuestion.setText(question.getQuestion(randomIndex));
        topicOnePreTestOptA.setText(question.getChoice1(randomIndex));
        topicOnePreTestOptB.setText(question.getChoice2(randomIndex));
        topicOnePreTestOptC.setText(question.getChoice3(randomIndex));
        topicOnePreTestOptD.setText(question.getChoice4(randomIndex));
        answer = question.getCorrectAnswer(randomIndex);

        testQuestions.add(question.getQuestion(randomIndex));
        btn1.add(question.getChoice1(randomIndex));
        btn2.add(question.getChoice2(randomIndex));
        btn3.add(question.getChoice3(randomIndex));
        btn4.add(question.getChoice4(randomIndex));
        testSolutions.add(question.getSolution(randomIndex));
        testAnswers.add(question.getSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            topicOnePreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
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

                if (currentQuestionIndex < questionLength) {
                    topicOnePreTestProgress.setProgress(currentQuestionIndex + 1);
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
        topicOnePreTest.setVisibility(View.GONE);
        topicOnePreTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        topicOnePreTestTotalScore = String.valueOf(score);
        topicOnePreTestScoreDisp.setText("Score: " + topicOnePreTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        topicOnePreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        topicOnePreTestTimeDisp.setText("Time: " + topicOnePreTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        //dbHandler.storeTestScore(new TestsModelClass(4276, topicOnePreTestTotalScore, totalSecs));
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