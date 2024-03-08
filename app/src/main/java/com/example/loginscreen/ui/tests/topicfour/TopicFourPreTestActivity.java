package com.example.loginscreen.ui.tests.topicfour;

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

public class TopicFourPreTestActivity extends AppCompatActivity {
    private LinearLayout t4PreTestEnterPasscode, t4PreTestInstruction, t4PreTestLayout;
    private RelativeLayout t4PreTestScore, t4PreTestHeader;
    private ListView t4PreTestListView;
    private EditText t4PreTestPasscode;
    private Button t4PreTestBackCode, t4PreTestSendCode, t4PreTestInsBack, t4PreTestInsStart, t4PreTestOptA, t4PreTestOptB, t4PreTestOptC, t4PreTestOptD, t4PreTestNext, t4PreTestResult;
    private TextView t4PreTestItem, t4PreTestTimer, t4PreTestQuestion, t4PreTestScoreDisp, t4PreTestTimeDisp, t4PreTestResultScore;
    private ProgressBar t4PreTestProgress;
    private ImageView t4PreTestResultExit;
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
    private TopicFourQuestion question = new TopicFourQuestion();
    private int questionLength = question.questions1.length;
    private String answer, selectedAnswer, t4PreTestTotalScore, t4PreTestTotalTime;
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
        setContentView(R.layout.activity_topic_four_pre_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t4PreTestEnterPasscode = findViewById(R.id.t4PreTestEnterPasscode);
        t4PreTestInstruction = findViewById(R.id.t4PreTestInstruction);
        t4PreTestLayout = findViewById(R.id.t4PreTestLayout);
        t4PreTestScore = findViewById(R.id.t4PreTestScore);
        t4PreTestHeader = findViewById(R.id.t4PreTestHeader);
        t4PreTestListView = findViewById(R.id.t4PreTestListView);
        t4PreTestPasscode = findViewById(R.id.t4PreTestPasscode);
        t4PreTestBackCode = findViewById(R.id.t4PreTestBackCode);
        t4PreTestSendCode = findViewById(R.id.t4PreTestSendCode);
        t4PreTestInsBack = findViewById(R.id.t4PreTestInsBack);
        t4PreTestInsStart = findViewById(R.id.t4PreTestInsStart);
        t4PreTestOptA = findViewById(R.id.t4PreTestOptA);
        t4PreTestOptB = findViewById(R.id.t4PreTestOptB);
        t4PreTestOptC = findViewById(R.id.t4PreTestOptC);
        t4PreTestOptD = findViewById(R.id.t4PreTestOptD);
        t4PreTestNext = findViewById(R.id.t4PreTestNext);
        t4PreTestResult = findViewById(R.id.t4PreTestResult);
        t4PreTestItem = findViewById(R.id.t4PreTestItem);
        t4PreTestTimer = findViewById(R.id.t4PreTestTimer);
        t4PreTestQuestion = findViewById(R.id.t4PreTestQuestion);
        t4PreTestScoreDisp = findViewById(R.id.t4PreTestScoreDisp);
        t4PreTestTimeDisp = findViewById(R.id.t4PreTestTimeDisp);
        t4PreTestResultScore = findViewById(R.id.t4PreTestResultScore);
        t4PreTestProgress = findViewById(R.id.t4PreTestProgress);
        t4PreTestResultExit = findViewById(R.id.t4PreTestResultExit);

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

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicFourPreTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t4PreTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(1067);
        if (checkTestCode == true) {
            t4PreTestEnterPasscode.setVisibility(View.GONE);
            t4PreTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(1067);
            if (!scoreAndTime.isEmpty()) {
                t4PreTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t4PreTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t4PreTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t4PreTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t4PreTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4PreTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicFourPreTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t4PreTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicFourPreTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(1067)) {
                            t4PreTestEnterPasscode.setVisibility(View.GONE);
                            t4PreTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicFourPreTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t4PreTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t4PreTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4PreTestInstruction.setVisibility(View.GONE);
                t4PreTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t4PreTestProgress.setMax(questionLength);
                t4PreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t4PreTestNext.setOnClickListener(new View.OnClickListener() {
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

                String t41Questions = question.getPreTestQuestion(randomIndex);
                String t41Btn1 = question.getPreTestChoice1(randomIndex);
                String t41Btn2 = question.getPreTestChoice2(randomIndex);
                String t41Btn3 = question.getPreTestChoice3(randomIndex);
                String t41Btn4 = question.getPreTestChoice4(randomIndex);
                String t41Solutions = question.getPreTestSolution(randomIndex);
                String t41Answers = question.getPreTestSolAnswer(randomIndex);

                markImage = storeMark[currentQuestionIndex];
                btnStyle1 = storeBtnbg1[currentQuestionIndex];
                btnStyle2 = storeBtnbg2[currentQuestionIndex];
                btnStyle3 = storeBtnbg3[currentQuestionIndex];
                btnStyle4 = storeBtnbg4[currentQuestionIndex];

                dbHandler.storeT41Result(new TestResultModelClass(t41Questions, t41Btn1, t41Btn2, t41Btn3, t41Btn4, t41Solutions, t41Answers,
                        solStyle, ansStyle, markImage, btnStyle1, btnStyle2, btnStyle3, btnStyle4));

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    t4PreTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t4PreTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4PreTestScore.setVisibility(View.GONE);
                t4PreTestListView.setVisibility(View.VISIBLE);
                t4PreTestHeader.setVisibility(View.VISIBLE);
                t4PreTestResultScore.setText("Score " + t4PreTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t4PreTestListView.getAdapter()).notifyDataSetChanged();

                int markCheck = R.drawable.round_check_24;
                int markWrong = R.drawable.round_clear_24;
                int btnSlc = R.drawable.custom_selected_button;
                int btnOpt = R.drawable.custom_option_button;

                for (int i = 0; i < questionLength; i++) {
                    TestResultModelClass testResult = dbHandler.getT41Result(i+1);
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

                checkTestCode = dbHandler.checkCode(1067);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(1067);
                    if (!scoreAndTime.isEmpty()) {
                        t4PreTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t4PreTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t4PreTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t4PreTestOptA.getText().toString();
                t4PreTestNext.setVisibility(View.VISIBLE);
                t4PreTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PreTestOptA.setTextColor(getResources().getColor(R.color.white));
                t4PreTestOptB.setBackgroundTintList(null);
                t4PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptC.setBackgroundTintList(null);
                t4PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptD.setBackgroundTintList(null);
                t4PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t4PreTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t4PreTestOptB.getText().toString();
                t4PreTestNext.setVisibility(View.VISIBLE);
                t4PreTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PreTestOptB.setTextColor(getResources().getColor(R.color.white));
                t4PreTestOptA.setBackgroundTintList(null);
                t4PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptC.setBackgroundTintList(null);
                t4PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptD.setBackgroundTintList(null);
                t4PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t4PreTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t4PreTestOptC.getText().toString();
                t4PreTestNext.setVisibility(View.VISIBLE);
                t4PreTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PreTestOptC.setTextColor(getResources().getColor(R.color.white));
                t4PreTestOptA.setBackgroundTintList(null);
                t4PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptB.setBackgroundTintList(null);
                t4PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptB.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptD.setBackgroundTintList(null);
                t4PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t4PreTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t4PreTestOptD.getText().toString();
                t4PreTestNext.setVisibility(View.VISIBLE);
                t4PreTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PreTestOptD.setTextColor(getResources().getColor(R.color.white));
                t4PreTestOptA.setBackgroundTintList(null);
                t4PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptA.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptC.setBackgroundTintList(null);
                t4PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptC.setTextColor(getResources().getColor(R.color.black));
                t4PreTestOptB.setBackgroundTintList(null);
                t4PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t4PreTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicFourPreTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicFourPreTestActivity.this, NavigationActivity.class);
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

        t4PreTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t4PreTestQuestion.setText(question.getPreTestQuestion(randomIndex));
        t4PreTestOptA.setText(question.getPreTestChoice1(randomIndex));
        t4PreTestOptB.setText(question.getPreTestChoice2(randomIndex));
        t4PreTestOptC.setText(question.getPreTestChoice3(randomIndex));
        t4PreTestOptD.setText(question.getPreTestChoice4(randomIndex));
        answer = question.getPreTestCorrectAnswer(randomIndex);

        if ((currentQuestionIndex + 1) < questionLength) {
            t4PreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t4PreTestNext.setText("Submit");
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
                    t4PreTestProgress.setProgress(currentQuestionIndex + 1);
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
        t4PreTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t4PreTestTimer.setTextColor(Color.RED);
        } else {
            t4PreTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t4PreTestLayout.setVisibility(View.GONE);
        t4PreTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t4PreTestTotalScore = String.valueOf(score);
        t4PreTestScoreDisp.setText("Score: " + t4PreTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t4PreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t4PreTestTimeDisp.setText("Time: " + t4PreTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(1067, t4PreTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t4PreTestNext.setVisibility(View.INVISIBLE);
        t4PreTestOptA.setBackgroundTintList(null);
        t4PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t4PreTestOptA.setTextColor(getResources().getColor(R.color.black));
        t4PreTestOptB.setBackgroundTintList(null);
        t4PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t4PreTestOptB.setTextColor(getResources().getColor(R.color.black));
        t4PreTestOptC.setBackgroundTintList(null);
        t4PreTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t4PreTestOptC.setTextColor(getResources().getColor(R.color.black));
        t4PreTestOptD.setBackgroundTintList(null);
        t4PreTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t4PreTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}