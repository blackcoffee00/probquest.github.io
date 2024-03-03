package com.example.loginscreen.ui.tests.topicthree;

import static com.example.loginscreen.DBHandler.SCORE;
import static com.example.loginscreen.DBHandler.TIME;

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

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.NavigationActivity;
import com.example.loginscreen.R;
import com.example.loginscreen.ui.tests.TestsModelClass;
import com.example.loginscreen.ui.tests.ViewResultAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicThreePreTestActivity extends AppCompatActivity {
    private LinearLayout t3PreTestEnterPasscode, t3PreTestInstruction, t3PreTestLayout;
    private RelativeLayout t3PreTestScore, t3PreTestHeader;
    private ListView t3PreTestListView;
    private EditText t3PreTestPasscode;
    private Button t3PreTestBackCode, t3PreTestSendCode, t3PreTestInsBack, t3PreTestInsStart, t3PreTestOptA, t3PreTestOptB, t3PreTestOptC, t3PreTestOptD, t3PreTestNext, t3PreTestResult;
    private TextView t3PreTestItem, t3PreTestTimer, t3PreTestQuestion, t3PreTestScoreDisp, t3PreTestTimeDisp, t3PreTestResultScore;
    private ProgressBar t3PreTestProgress;
    private ImageView t3PreTestResultExit;
    private Integer passcode;
    private boolean isTestOngoing = false;
    private int currentQuestionIndex, score, buttonIndex;
    private long startTime, timeLeftInMillis;
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
    private TopicThreeQuestion question = new TopicThreeQuestion();
    private int questionLength = question.questions1.length;
    private String answer, selectedAnswer, t3PreTestTotalScore, t3PreTestTotalTime;
    private CountDownTimer countDownTimer;
    private static final long COUNTDOWN_IN_MILLIS = 91000;
    private Set<Integer> shownQuestionIndices = new HashSet<>();
    private Random random;
    private Boolean checkTestCode;
    private Map<String, String> scoreAndTime;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_three_pre_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t3PreTestEnterPasscode = findViewById(R.id.t3PreTestEnterPasscode);
        t3PreTestInstruction = findViewById(R.id.t3PreTestInstruction);
        t3PreTestLayout = findViewById(R.id.t3PreTestLayout);
        t3PreTestScore = findViewById(R.id.t3PreTestScore);
        t3PreTestHeader = findViewById(R.id.t3PreTestHeader);
        t3PreTestListView = findViewById(R.id.t3PreTestListView);
        t3PreTestPasscode = findViewById(R.id.t3PreTestPasscode);
        t3PreTestBackCode = findViewById(R.id.t3PreTestBackCode);
        t3PreTestSendCode = findViewById(R.id.t3PreTestSendCode);
        t3PreTestInsBack = findViewById(R.id.t3PreTestInsBack);
        t3PreTestInsStart = findViewById(R.id.t3PreTestInsStart);
        t3PreTestOptA = findViewById(R.id.t3PreTestOptA);
        t3PreTestOptB = findViewById(R.id.t3PreTestOptB);
        t3PreTestOptC = findViewById(R.id.t3PreTestOptC);
        t3PreTestOptD = findViewById(R.id.t3PreTestOptD);
        t3PreTestNext = findViewById(R.id.t3PreTestNext);
        t3PreTestResult = findViewById(R.id.t3PreTestResult);
        t3PreTestItem = findViewById(R.id.t3PreTestItem);
        t3PreTestTimer = findViewById(R.id.t3PreTestTimer);
        t3PreTestQuestion = findViewById(R.id.t3PreTestQuestion);
        t3PreTestScoreDisp = findViewById(R.id.t3PreTestScoreDisp);
        t3PreTestTimeDisp = findViewById(R.id.t3PreTestTimeDisp);
        t3PreTestResultScore = findViewById(R.id.t3PreTestResultScore);
        t3PreTestProgress = findViewById(R.id.t3PreTestProgress);
        t3PreTestResultExit = findViewById(R.id.t3PreTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicThreePreTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t3PreTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(7137);
        if (checkTestCode == true) {
            t3PreTestEnterPasscode.setVisibility(View.GONE);
            t3PreTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(7137);
            if (!scoreAndTime.isEmpty()) {
                t3PreTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t3PreTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t3PreTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t3PreTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t3PreTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t3PreTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicThreePreTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t3PreTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicThreePreTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(7137)) {
                            t3PreTestEnterPasscode.setVisibility(View.GONE);
                            t3PreTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicThreePreTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t3PreTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t3PreTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3PreTestInstruction.setVisibility(View.GONE);
                t3PreTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t3PreTestProgress.setMax(questionLength);
                t3PreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t3PreTestNext.setOnClickListener(new View.OnClickListener() {
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
                    t3PreTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t3PreTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3PreTestScore.setVisibility(View.GONE);
                t3PreTestListView.setVisibility(View.VISIBLE);
                t3PreTestHeader.setVisibility(View.VISIBLE);

                t3PreTestResultScore.setText("Score " + t3PreTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t3PreTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.GONE);
                    ansVis.add(View.GONE);
                }

                checkTestCode = dbHandler.checkCode(7137);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(7137);
                    if (!scoreAndTime.isEmpty()) {
                        t3PreTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t3PreTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t3PreTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t3PreTestOptA.getText().toString();
                t3PreTestNext.setVisibility(View.VISIBLE);
                t3PreTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PreTestOptA.setTextColor(getResources().getColor(R.color.white));
                t3PreTestOptB.setBackgroundTintList(null);
                t3PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptC.setBackgroundTintList(null);
                t3PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptD.setBackgroundTintList(null);
                t3PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t3PreTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t3PreTestOptB.getText().toString();
                t3PreTestNext.setVisibility(View.VISIBLE);
                t3PreTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PreTestOptB.setTextColor(getResources().getColor(R.color.white));
                t3PreTestOptA.setBackgroundTintList(null);
                t3PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptC.setBackgroundTintList(null);
                t3PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptD.setBackgroundTintList(null);
                t3PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t3PreTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t3PreTestOptC.getText().toString();
                t3PreTestNext.setVisibility(View.VISIBLE);
                t3PreTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PreTestOptC.setTextColor(getResources().getColor(R.color.white));
                t3PreTestOptA.setBackgroundTintList(null);
                t3PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptB.setBackgroundTintList(null);
                t3PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptD.setBackgroundTintList(null);
                t3PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t3PreTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t3PreTestOptD.getText().toString();
                t3PreTestNext.setVisibility(View.VISIBLE);
                t3PreTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PreTestOptD.setTextColor(getResources().getColor(R.color.white));
                t3PreTestOptA.setBackgroundTintList(null);
                t3PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptC.setBackgroundTintList(null);
                t3PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t3PreTestOptB.setBackgroundTintList(null);
                t3PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t3PreTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicThreePreTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicThreePreTestActivity.this, NavigationActivity.class);
            intent.putExtra("loadToTestsFragment", R.id.navigation_tests);
            startActivity(intent);
            finish();
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

        t3PreTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t3PreTestQuestion.setText(question.getPreTestQuestion(randomIndex));
        t3PreTestOptA.setText(question.getPreTestChoice1(randomIndex));
        t3PreTestOptB.setText(question.getPreTestChoice2(randomIndex));
        t3PreTestOptC.setText(question.getPreTestChoice3(randomIndex));
        t3PreTestOptD.setText(question.getPreTestChoice4(randomIndex));
        answer = question.getPreTestCorrectAnswer(randomIndex);

        testQuestions.add(question.getPreTestQuestion(randomIndex));
        btn1.add(question.getPreTestChoice1(randomIndex));
        btn2.add(question.getPreTestChoice2(randomIndex));
        btn3.add(question.getPreTestChoice3(randomIndex));
        btn4.add(question.getPreTestChoice4(randomIndex));
        testSolutions.add(question.getPreTestSolution(randomIndex));
        testAnswers.add(question.getPreTestSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            t3PreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t3PreTestNext.setText("Submit");
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
                    t3PreTestProgress.setProgress(currentQuestionIndex + 1);
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
        t3PreTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t3PreTestTimer.setTextColor(Color.RED);
        } else {
            t3PreTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t3PreTestLayout.setVisibility(View.GONE);
        t3PreTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t3PreTestTotalScore = String.valueOf(score);
        t3PreTestScoreDisp.setText("Score: " + t3PreTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t3PreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t3PreTestTimeDisp.setText("Time: " + t3PreTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(7137, t3PreTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t3PreTestNext.setVisibility(View.INVISIBLE);
        t3PreTestOptA.setBackgroundTintList(null);
        t3PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t3PreTestOptA.setTextColor(getResources().getColor(R.color.black));
        t3PreTestOptB.setBackgroundTintList(null);
        t3PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t3PreTestOptB.setTextColor(getResources().getColor(R.color.black));
        t3PreTestOptC.setBackgroundTintList(null);
        t3PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t3PreTestOptC.setTextColor(getResources().getColor(R.color.black));
        t3PreTestOptD.setBackgroundTintList(null);
        t3PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t3PreTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}