package com.example.loginscreen.ui.tests.topicsix;

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
import com.example.loginscreen.ui.tests.TestResultModelClass;
import com.example.loginscreen.ui.tests.TestsModelClass;
import com.example.loginscreen.ui.tests.ViewResultAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicSixPreTestActivity extends AppCompatActivity {
    private LinearLayout t6PreTestEnterPasscode, t6PreTestInstruction, t6PreTestLayout;
    private RelativeLayout t6PreTestScore, t6PreTestHeader;
    private ListView t6PreTestListView;
    private EditText t6PreTestPasscode;
    private Button t6PreTestBackCode, t6PreTestSendCode, t6PreTestInsBack, t6PreTestInsStart, t6PreTestOptA, t6PreTestOptB, t6PreTestOptC, t6PreTestOptD, t6PreTestNext, t6PreTestResult;
    private TextView t6PreTestItem, t6PreTestTimer, t6PreTestQuestion, t6PreTestScoreDisp, t6PreTestTimeDisp, t6PreTestResultScore;
    private ProgressBar t6PreTestProgress;
    private ImageView t6PreTestResultExit;
    private Integer passcode;
    private boolean isTestOngoing = false;
    private int currentQuestionIndex, score, buttonIndex, randomIndex, markImage, btnStyle1, btnStyle2, btnStyle3, btnStyle4, solStyle = View.GONE, ansStyle = View.GONE;
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
    private int[] mark, btnbg1, btnbg2, btnbg3, btnbg4, storeMark, storeBtnbg1, storeBtnbg2, storeBtnbg3, storeBtnbg4;
    private TopicSixQuestion question = new TopicSixQuestion();
    private int questionLength = question.questions1.length;
    private String answer, selectedAnswer, t6PreTestTotalScore, t6PreTestTotalTime;
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
        setContentView(R.layout.activity_topic_six_pre_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t6PreTestEnterPasscode = findViewById(R.id.t6PreTestEnterPasscode);
        t6PreTestInstruction = findViewById(R.id.t6PreTestInstruction);
        t6PreTestLayout = findViewById(R.id.t6PreTestLayout);
        t6PreTestScore = findViewById(R.id.t6PreTestScore);
        t6PreTestHeader = findViewById(R.id.t6PreTestHeader);
        t6PreTestListView = findViewById(R.id.t6PreTestListView);
        t6PreTestPasscode = findViewById(R.id.t6PreTestPasscode);
        t6PreTestBackCode = findViewById(R.id.t6PreTestBackCode);
        t6PreTestSendCode = findViewById(R.id.t6PreTestSendCode);
        t6PreTestInsBack = findViewById(R.id.t6PreTestInsBack);
        t6PreTestInsStart = findViewById(R.id.t6PreTestInsStart);
        t6PreTestOptA = findViewById(R.id.t6PreTestOptA);
        t6PreTestOptB = findViewById(R.id.t6PreTestOptB);
        t6PreTestOptC = findViewById(R.id.t6PreTestOptC);
        t6PreTestOptD = findViewById(R.id.t6PreTestOptD);
        t6PreTestNext = findViewById(R.id.t6PreTestNext);
        t6PreTestResult = findViewById(R.id.t6PreTestResult);
        t6PreTestItem = findViewById(R.id.t6PreTestItem);
        t6PreTestTimer = findViewById(R.id.t6PreTestTimer);
        t6PreTestQuestion = findViewById(R.id.t6PreTestQuestion);
        t6PreTestScoreDisp = findViewById(R.id.t6PreTestScoreDisp);
        t6PreTestTimeDisp = findViewById(R.id.t6PreTestTimeDisp);
        t6PreTestResultScore = findViewById(R.id.t6PreTestResultScore);
        t6PreTestProgress = findViewById(R.id.t6PreTestProgress);
        t6PreTestResultExit = findViewById(R.id.t6PreTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];
        storeMark = new int[questionLength];
        storeBtnbg1 = new int[questionLength];
        storeBtnbg2 = new int[questionLength];
        storeBtnbg3 = new int[questionLength];
        storeBtnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicSixPreTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t6PreTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(3552);
        if (checkTestCode == true) {
            t6PreTestEnterPasscode.setVisibility(View.GONE);
            t6PreTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(3552);
            if (!scoreAndTime.isEmpty()) {
                t6PreTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t6PreTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t6PreTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t6PreTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t6PreTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t6PreTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicSixPreTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t6PreTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicSixPreTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(3552)) {
                            t6PreTestEnterPasscode.setVisibility(View.GONE);
                            t6PreTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicSixPreTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t6PreTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t6PreTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t6PreTestInstruction.setVisibility(View.GONE);
                t6PreTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t6PreTestProgress.setMax(questionLength);
                t6PreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t6PreTestNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAnswer.equals(answer)) {
                    storeMark[currentQuestionIndex] = R.drawable.round_check_24;
                    score++;
                } else {
                    storeMark[currentQuestionIndex] = R.drawable.round_clear_24;
                }

                if (buttonIndex == 1) {
                    storeBtnbg1[currentQuestionIndex] = R.drawable.custom_selected_button;
                    storeBtnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg3[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg4[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (buttonIndex == 2) {
                    storeBtnbg2[currentQuestionIndex] = R.drawable.custom_selected_button;
                    storeBtnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg3[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg4[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (buttonIndex == 3) {
                    storeBtnbg3[currentQuestionIndex] = R.drawable.custom_selected_button;
                    storeBtnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg4[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (buttonIndex == 4) {
                    storeBtnbg4[currentQuestionIndex] = R.drawable.custom_selected_button;
                    storeBtnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                    storeBtnbg3[currentQuestionIndex] = R.drawable.custom_option_button;
                }

                String t61Questions = question.getPreTestQuestion(randomIndex);
                String t61Btn1 = question.getPreTestChoice1(randomIndex);
                String t61Btn2 = question.getPreTestChoice2(randomIndex);
                String t61Btn3 = question.getPreTestChoice3(randomIndex);
                String t61Btn4 = question.getPreTestChoice4(randomIndex);
                String t61Solutions = question.getPreTestSolution(randomIndex);
                String t61Answers = question.getPreTestSolAnswer(randomIndex);

                markImage = storeMark[currentQuestionIndex];
                btnStyle1 = storeBtnbg1[currentQuestionIndex];
                btnStyle2 = storeBtnbg2[currentQuestionIndex];
                btnStyle3 = storeBtnbg3[currentQuestionIndex];
                btnStyle4 = storeBtnbg4[currentQuestionIndex];

                dbHandler.storeT61Result(new TestResultModelClass(t61Questions, t61Btn1, t61Btn2, t61Btn3, t61Btn4, t61Solutions, t61Answers,
                        solStyle, ansStyle, markImage, btnStyle1, btnStyle2, btnStyle3, btnStyle4));

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    t6PreTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t6PreTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t6PreTestScore.setVisibility(View.GONE);
                t6PreTestListView.setVisibility(View.VISIBLE);
                t6PreTestHeader.setVisibility(View.VISIBLE);

                t6PreTestResultScore.setText("Score " + t6PreTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t6PreTestListView.getAdapter()).notifyDataSetChanged();

                int markCheck = R.drawable.round_check_24;
                int markWrong = R.drawable.round_clear_24;
                int btnSlc = R.drawable.custom_selected_button;
                int btnOpt = R.drawable.custom_option_button;

                for (int i = 0; i < questionLength; i++) {
                    TestResultModelClass testResult = dbHandler.getT61Result(i+1);
                    testQuestions.add(testResult.getTestQuestion());
                    btn1.add(testResult.getBtn1());
                    btn2.add(testResult.getBtn2());
                    btn3.add(testResult.getBtn3());
                    btn4.add(testResult.getBtn4());
                    testSolutions.add(testResult.getTestSolution());
                    testAnswers.add(testResult.getTestAnswer());
                    solVis.add(View.GONE);
                    ansVis.add(View.GONE);

                    if (testResult.getMark() == markCheck) {
                        mark[i] = R.drawable.round_check_24;
                    } else if (testResult.getMark() == markWrong) {
                        mark[i] = R.drawable.round_clear_24;
                    }
                    if (testResult.getBtnbg1() == btnOpt) {
                        btnbg1[i] = R.drawable.custom_option_button;
                    } else if (testResult.getBtnbg1() == btnSlc) {
                        btnbg1[i] =R.drawable.custom_selected_button;
                    }
                    if (testResult.getBtnbg2() == btnOpt) {
                        btnbg2[i] = R.drawable.custom_option_button;
                    } else if (testResult.getBtnbg2() == btnSlc) {
                        btnbg2[i] =R.drawable.custom_selected_button;
                    }
                    if (testResult.getBtnbg3() == btnOpt) {
                        btnbg3[i] = R.drawable.custom_option_button;
                    } else if (testResult.getBtnbg3() == btnSlc) {
                        btnbg3[i] =R.drawable.custom_selected_button;
                    }
                    if (testResult.getBtnbg4() == btnOpt) {
                        btnbg4[i] = R.drawable.custom_option_button;
                    } else if (testResult.getBtnbg4() == btnSlc) {
                        btnbg4[i] =R.drawable.custom_selected_button;
                    }
                }

                checkTestCode = dbHandler.checkCode(3552);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(3552);
                    if (!scoreAndTime.isEmpty()) {
                        t6PreTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t6PreTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t6PreTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t6PreTestOptA.getText().toString();
                t6PreTestNext.setVisibility(View.VISIBLE);
                t6PreTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PreTestOptA.setTextColor(getResources().getColor(R.color.white));
                t6PreTestOptB.setBackgroundTintList(null);
                t6PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptC.setBackgroundTintList(null);
                t6PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptD.setBackgroundTintList(null);
                t6PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t6PreTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t6PreTestOptB.getText().toString();
                t6PreTestNext.setVisibility(View.VISIBLE);
                t6PreTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PreTestOptB.setTextColor(getResources().getColor(R.color.white));
                t6PreTestOptA.setBackgroundTintList(null);
                t6PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptC.setBackgroundTintList(null);
                t6PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptD.setBackgroundTintList(null);
                t6PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t6PreTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t6PreTestOptC.getText().toString();
                t6PreTestNext.setVisibility(View.VISIBLE);
                t6PreTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PreTestOptC.setTextColor(getResources().getColor(R.color.white));
                t6PreTestOptA.setBackgroundTintList(null);
                t6PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptB.setBackgroundTintList(null);
                t6PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptD.setBackgroundTintList(null);
                t6PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t6PreTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t6PreTestOptD.getText().toString();
                t6PreTestNext.setVisibility(View.VISIBLE);
                t6PreTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PreTestOptD.setTextColor(getResources().getColor(R.color.white));
                t6PreTestOptA.setBackgroundTintList(null);
                t6PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptC.setBackgroundTintList(null);
                t6PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t6PreTestOptB.setBackgroundTintList(null);
                t6PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t6PreTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicSixPreTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicSixPreTestActivity.this, NavigationActivity.class);
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

        t6PreTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t6PreTestQuestion.setText(question.getPreTestQuestion(randomIndex));
        t6PreTestOptA.setText(question.getPreTestChoice1(randomIndex));
        t6PreTestOptB.setText(question.getPreTestChoice2(randomIndex));
        t6PreTestOptC.setText(question.getPreTestChoice3(randomIndex));
        t6PreTestOptD.setText(question.getPreTestChoice4(randomIndex));
        answer = question.getPreTestCorrectAnswer(randomIndex);

        if ((currentQuestionIndex + 1) < questionLength) {
            t6PreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t6PreTestNext.setText("Submit");
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
                    t6PreTestProgress.setProgress(currentQuestionIndex + 1);
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
        t6PreTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t6PreTestTimer.setTextColor(Color.RED);
        } else {
            t6PreTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t6PreTestLayout.setVisibility(View.GONE);
        t6PreTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t6PreTestTotalScore = String.valueOf(score);
        t6PreTestScoreDisp.setText("Score: " + t6PreTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t6PreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t6PreTestTimeDisp.setText("Time: " + t6PreTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(3552, t6PreTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t6PreTestNext.setVisibility(View.INVISIBLE);
        t6PreTestOptA.setBackgroundTintList(null);
        t6PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t6PreTestOptA.setTextColor(getResources().getColor(R.color.black));
        t6PreTestOptB.setBackgroundTintList(null);
        t6PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t6PreTestOptB.setTextColor(getResources().getColor(R.color.black));
        t6PreTestOptC.setBackgroundTintList(null);
        t6PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t6PreTestOptC.setTextColor(getResources().getColor(R.color.black));
        t6PreTestOptD.setBackgroundTintList(null);
        t6PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t6PreTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}