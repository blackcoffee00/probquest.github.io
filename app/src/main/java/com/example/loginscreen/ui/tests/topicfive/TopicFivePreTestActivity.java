package com.example.loginscreen.ui.tests.topicfive;

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

public class TopicFivePreTestActivity extends AppCompatActivity {
    private LinearLayout t5PreTestEnterPasscode, t5PreTestInstruction, t5PreTestLayout;
    private RelativeLayout t5PreTestScore, t5PreTestHeader;
    private ListView t5PreTestListView;
    private EditText t5PreTestPasscode;
    private Button t5PreTestBackCode, t5PreTestSendCode, t5PreTestInsBack, t5PreTestInsStart, t5PreTestOptA, t5PreTestOptB, t5PreTestOptC, t5PreTestOptD, t5PreTestNext, t5PreTestResult;
    private TextView t5PreTestItem, t5PreTestTimer, t5PreTestQuestion, t5PreTestScoreDisp, t5PreTestTimeDisp, t5PreTestResultScore;
    private ProgressBar t5PreTestProgress;
    private ImageView t5PreTestResultExit, t5ImagePreTest;
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
    private TopicFiveQuestion question = new TopicFiveQuestion();
    private int questionLength = question.questions1.length;
    private String answer, selectedAnswer, t5PreTestTotalScore, t5PreTestTotalTime;
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
        setContentView(R.layout.activity_topic_five_pre_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t5PreTestEnterPasscode = findViewById(R.id.t5PreTestEnterPasscode);
        t5PreTestInstruction = findViewById(R.id.t5PreTestInstruction);
        t5PreTestLayout = findViewById(R.id.t5PreTestLayout);
        t5PreTestScore = findViewById(R.id.t5PreTestScore);
        t5PreTestHeader = findViewById(R.id.t5PreTestHeader);
        t5PreTestListView = findViewById(R.id.t5PreTestListView);
        t5PreTestPasscode = findViewById(R.id.t5PreTestPasscode);
        t5PreTestBackCode = findViewById(R.id.t5PreTestBackCode);
        t5PreTestSendCode = findViewById(R.id.t5PreTestSendCode);
        t5PreTestInsBack = findViewById(R.id.t5PreTestInsBack);
        t5PreTestInsStart = findViewById(R.id.t5PreTestInsStart);
        t5PreTestOptA = findViewById(R.id.t5PreTestOptA);
        t5PreTestOptB = findViewById(R.id.t5PreTestOptB);
        t5PreTestOptC = findViewById(R.id.t5PreTestOptC);
        t5PreTestOptD = findViewById(R.id.t5PreTestOptD);
        t5PreTestNext = findViewById(R.id.t5PreTestNext);
        t5PreTestResult = findViewById(R.id.t5PreTestResult);
        t5PreTestItem = findViewById(R.id.t5PreTestItem);
        t5PreTestTimer = findViewById(R.id.t5PreTestTimer);
        t5PreTestQuestion = findViewById(R.id.t5PreTestQuestion);
        t5ImagePreTest = findViewById(R.id.t5ImagePreTest);
        t5PreTestScoreDisp = findViewById(R.id.t5PreTestScoreDisp);
        t5PreTestTimeDisp = findViewById(R.id.t5PreTestTimeDisp);
        t5PreTestResultScore = findViewById(R.id.t5PreTestResultScore);
        t5PreTestProgress = findViewById(R.id.t5PreTestProgress);
        t5PreTestResultExit = findViewById(R.id.t5PreTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicFivePreTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t5PreTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(9788);
        if (checkTestCode == true) {
            t5PreTestEnterPasscode.setVisibility(View.GONE);
            t5PreTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(9788);
            if (!scoreAndTime.isEmpty()) {
                t5PreTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t5PreTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t5PreTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t5PreTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t5PreTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t5PreTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicFivePreTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t5PreTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicFivePreTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(9788)) {
                            t5PreTestEnterPasscode.setVisibility(View.GONE);
                            t5PreTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicFivePreTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t5PreTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t5PreTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5PreTestInstruction.setVisibility(View.GONE);
                t5PreTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t5PreTestProgress.setMax(questionLength);
                t5PreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t5PreTestNext.setOnClickListener(new View.OnClickListener() {
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
                    t5PreTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t5PreTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5PreTestScore.setVisibility(View.GONE);
                t5PreTestListView.setVisibility(View.VISIBLE);
                t5PreTestHeader.setVisibility(View.VISIBLE);

                t5PreTestResultScore.setText("Score " + t5PreTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t5PreTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.GONE);
                    ansVis.add(View.VISIBLE);
                }

                checkTestCode = dbHandler.checkCode(9788);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(9788);
                    if (!scoreAndTime.isEmpty()) {
                        t5PreTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t5PreTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t5PreTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t5PreTestOptA.getText().toString();
                t5PreTestNext.setVisibility(View.VISIBLE);
                t5PreTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PreTestOptA.setTextColor(getResources().getColor(R.color.white));
                t5PreTestOptB.setBackgroundTintList(null);
                t5PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptC.setBackgroundTintList(null);
                t5PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptD.setBackgroundTintList(null);
                t5PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t5PreTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t5PreTestOptB.getText().toString();
                t5PreTestNext.setVisibility(View.VISIBLE);
                t5PreTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PreTestOptB.setTextColor(getResources().getColor(R.color.white));
                t5PreTestOptA.setBackgroundTintList(null);
                t5PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptC.setBackgroundTintList(null);
                t5PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptD.setBackgroundTintList(null);
                t5PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t5PreTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t5PreTestOptC.getText().toString();
                t5PreTestNext.setVisibility(View.VISIBLE);
                t5PreTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PreTestOptC.setTextColor(getResources().getColor(R.color.white));
                t5PreTestOptA.setBackgroundTintList(null);
                t5PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptB.setBackgroundTintList(null);
                t5PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptD.setBackgroundTintList(null);
                t5PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t5PreTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t5PreTestOptD.getText().toString();
                t5PreTestNext.setVisibility(View.VISIBLE);
                t5PreTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PreTestOptD.setTextColor(getResources().getColor(R.color.white));
                t5PreTestOptA.setBackgroundTintList(null);
                t5PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptC.setBackgroundTintList(null);
                t5PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t5PreTestOptB.setBackgroundTintList(null);
                t5PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t5PreTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicFivePreTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicFivePreTestActivity.this, NavigationActivity.class);
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

        t5PreTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t5PreTestQuestion.setText(question.getPreTestQuestion(randomIndex));
        t5ImagePreTest.setImageResource(getResources().getIdentifier(question.getImgTest(randomIndex), "drawable", getPackageName()));
        t5PreTestOptA.setText(question.getPreTestChoice1(randomIndex));
        t5PreTestOptB.setText(question.getPreTestChoice2(randomIndex));
        t5PreTestOptC.setText(question.getPreTestChoice3(randomIndex));
        t5PreTestOptD.setText(question.getPreTestChoice4(randomIndex));
        answer = question.getPreTestCorrectAnswer(randomIndex);

        testQuestions.add(question.getPreTestQuestion(randomIndex));
        btn1.add(question.getPreTestChoice1(randomIndex));
        btn2.add(question.getPreTestChoice2(randomIndex));
        btn3.add(question.getPreTestChoice3(randomIndex));
        btn4.add(question.getPreTestChoice4(randomIndex));
        testSolutions.add(question.getPreTestSolution(randomIndex));
        testAnswers.add(question.getPreTestSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            t5PreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t5PreTestNext.setText("Submit");
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
                    t5PreTestProgress.setProgress(currentQuestionIndex + 1);
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
        t5PreTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t5PreTestTimer.setTextColor(Color.RED);
        } else {
            t5PreTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t5PreTestLayout.setVisibility(View.GONE);
        t5PreTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t5PreTestTotalScore = String.valueOf(score);
        t5PreTestScoreDisp.setText("Score: " + t5PreTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t5PreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t5PreTestTimeDisp.setText("Time: " + t5PreTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(9788, t5PreTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t5PreTestNext.setVisibility(View.INVISIBLE);
        t5PreTestOptA.setBackgroundTintList(null);
        t5PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t5PreTestOptA.setTextColor(getResources().getColor(R.color.black));
        t5PreTestOptB.setBackgroundTintList(null);
        t5PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t5PreTestOptB.setTextColor(getResources().getColor(R.color.black));
        t5PreTestOptC.setBackgroundTintList(null);
        t5PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t5PreTestOptC.setTextColor(getResources().getColor(R.color.black));
        t5PreTestOptD.setBackgroundTintList(null);
        t5PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t5PreTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}