package com.example.loginscreen.ui.tests.topictwo;

import static com.example.loginscreen.DBHandler.SCORE;
import static com.example.loginscreen.DBHandler.TIME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
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
import com.example.loginscreen.ui.tests.T2TestResultModelClass;
import com.example.loginscreen.ui.tests.TestResultModelClass;
import com.example.loginscreen.ui.tests.TestsModelClass;
import com.example.loginscreen.ui.tests.topictwo.ViewResultAdapterT2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicTwoPreTestActivity extends AppCompatActivity {
    private LinearLayout t2PreTestEnterPasscode, t2PreTestInstruction, t2PreTestLayout;
    private RelativeLayout t2PreTestScore, t2PreTestHeader;
    private ListView t2PreTestListView;
    private EditText t2PreTestPasscode;
    private Button t2PreTestBackCode, t2PreTestSendCode, t2PreTestInsBack, t2PreTestInsStart, t2PreTestOptA, t2PreTestOptB, t2PreTestNext, t2PreTestResult;
    private TextView t2Instruction, t2PreTestItem, t2PreTestTimer, t2PreTestQuestion, t2PreTestScoreDisp, t2PreTestTimeDisp, t2PreTestResultScore;
    private ProgressBar t2PreTestProgress;
    private ImageView t2PreTestResultExit;
    private Integer passcode;
    private boolean isTestOngoing = false;
    private int currentQuestionIndex, score, randomIndex, markImage, btnStyle1, btnStyle2;
    private long startTime, timeLeftInMillis;
    private List<String> testQuestions = new ArrayList<>();
    private int[] mark, btnbg1, btnbg2, storeMark, storeBtnbg1, storeBtnbg2;
    private TopicTwoPreTestQuestion question = new TopicTwoPreTestQuestion();
    private int questionLength = question.questions.length;
    private String answer, selectedAnswer, t2PreTestTotalScore, t2PreTestTotalTime;
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
        setContentView(R.layout.activity_topic_two_pre_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t2PreTestEnterPasscode = findViewById(R.id.t2PreTestEnterPasscode);
        t2PreTestInstruction = findViewById(R.id.t2PreTestInstruction);
        t2PreTestLayout = findViewById(R.id.t2PreTestLayout);
        t2PreTestScore = findViewById(R.id.t2PreTestScore);
        t2PreTestHeader = findViewById(R.id.t2PreTestHeader);
        t2PreTestListView = findViewById(R.id.t2PreTestListView);
        t2PreTestPasscode = findViewById(R.id.t2PreTestPasscode);
        t2PreTestBackCode = findViewById(R.id.t2PreTestBackCode);
        t2PreTestSendCode = findViewById(R.id.t2PreTestSendCode);
        t2PreTestInsBack = findViewById(R.id.t2PreTestInsBack);
        t2PreTestInsStart = findViewById(R.id.t2PreTestInsStart);
        t2PreTestOptA = findViewById(R.id.t2PreTestOptA);
        t2PreTestOptB = findViewById(R.id.t2PreTestOptB);
        t2PreTestNext = findViewById(R.id.t2PreTestNext);
        t2PreTestResult = findViewById(R.id.t2PreTestResult);
        t2PreTestItem = findViewById(R.id.t2PreTestItem);
        t2PreTestTimer = findViewById(R.id.t2PreTestTimer);
        t2PreTestQuestion = findViewById(R.id.t2PreTestQuestion);
        t2PreTestScoreDisp = findViewById(R.id.t2PreTestScoreDisp);
        t2PreTestTimeDisp = findViewById(R.id.t2PreTestTimeDisp);
        t2PreTestResultScore = findViewById(R.id.t2PreTestResultScore);
        t2PreTestProgress = findViewById(R.id.t2PreTestProgress);
        t2PreTestResultExit = findViewById(R.id.t2PreTestResultExit);
        t2Instruction = findViewById(R.id.t2Instruction);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        storeMark = new int[questionLength];
        storeBtnbg1 = new int[questionLength];
        storeBtnbg2 = new int[questionLength];

        ViewResultAdapterT2 viewResultAdapter = new ViewResultAdapterT2(TopicTwoPreTestActivity.this, testQuestions, mark, btnbg1, btnbg2);
        t2PreTestListView.setAdapter(viewResultAdapter);

        String t2InstructionFormat = "Read and understand each event carefully and tell whether it is a simple or a compound event.<br><br>Choose <b>S</b> if it is a simple event and <b>C</b> if it is a compound event and and click next to proceed to the next item.<br><br>You have a 1 minute and 30 seconds time limit per question.<br><br>Your score will display after answering all 10 questions. Good luck!";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t2Instruction.setText(Html.fromHtml(t2InstructionFormat, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t2Instruction.setText(Html.fromHtml(t2InstructionFormat));
        }

        checkTestCode = dbHandler.checkCode(8603);
        if (checkTestCode == true) {
            t2PreTestEnterPasscode.setVisibility(View.GONE);
            t2PreTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(8603);
            if (!scoreAndTime.isEmpty()) {
                t2PreTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t2PreTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t2PreTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t2PreTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t2PreTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t2PreTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicTwoPreTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t2PreTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicTwoPreTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(8603)) {
                            t2PreTestEnterPasscode.setVisibility(View.GONE);
                            t2PreTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicTwoPreTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t2PreTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t2PreTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t2PreTestInstruction.setVisibility(View.GONE);
                t2PreTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t2PreTestProgress.setMax(questionLength);
                t2PreTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t2PreTestNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedAnswer.equals(answer)) {
                    storeMark[currentQuestionIndex] = R.drawable.round_check_24;
                    score++;
                } else {
                    storeMark[currentQuestionIndex] = R.drawable.round_clear_24;
                }

                if (selectedAnswer.equals("S")) {
                    storeBtnbg1[currentQuestionIndex] = R.drawable.custom_selected_button;
                    storeBtnbg2[currentQuestionIndex] = R.drawable.custom_option_button;
                } else if (selectedAnswer.equals("C")) {
                    storeBtnbg2[currentQuestionIndex] = R.drawable.custom_selected_button;
                    storeBtnbg1[currentQuestionIndex] = R.drawable.custom_option_button;
                }

                String t21Questions = question.getQuestion(randomIndex);
                markImage = storeMark[currentQuestionIndex];
                btnStyle1 = storeBtnbg1[currentQuestionIndex];
                btnStyle2 = storeBtnbg2[currentQuestionIndex];

                dbHandler.storeT21Result(new T2TestResultModelClass(t21Questions, markImage, btnStyle1, btnStyle2));

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    t2PreTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t2PreTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t2PreTestScore.setVisibility(View.GONE);
                t2PreTestListView.setVisibility(View.VISIBLE);
                t2PreTestHeader.setVisibility(View.VISIBLE);
                t2PreTestResultScore.setText("Score " + t2PreTestTotalScore + "/" + questionLength);
                ((ViewResultAdapterT2) t2PreTestListView.getAdapter()).notifyDataSetChanged();

                int markCheck = R.drawable.round_check_24;
                int markWrong = R.drawable.round_clear_24;
                int btnSlc = R.drawable.custom_selected_button;
                int btnOpt = R.drawable.custom_option_button;

                for (int i = 0; i < questionLength; i++) {
                    T2TestResultModelClass testResult = dbHandler.getT21Result(i+1);
                    testQuestions.add(testResult.getT2TestQuestion());

                    if (testResult.getT2Mark() == markCheck) {
                        mark[i] = R.drawable.round_check_24;
                    } else if (testResult.getT2Mark() == markWrong) {
                        mark[i] = R.drawable.round_clear_24;
                    }
                    if (testResult.getT2Btnbg1() == btnOpt) {
                        btnbg1[i] = R.drawable.custom_option_button;
                    } else if (testResult.getT2Btnbg1() == btnSlc) {
                        btnbg1[i] =R.drawable.custom_selected_button;
                    }
                    if (testResult.getT2Btnbg2() == btnOpt) {
                        btnbg2[i] = R.drawable.custom_option_button;
                    } else if (testResult.getT2Btnbg2() == btnSlc) {
                        btnbg2[i] =R.drawable.custom_selected_button;
                    }
                }

                 checkTestCode = dbHandler.checkCode(8603);
                 if (checkTestCode == true) {
                     scoreAndTime = dbHandler.getScoreTime(8603);
                     if (!scoreAndTime.isEmpty()) {
                        t2PreTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                     }
                 }
            }
        });

        t2PreTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t2PreTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = "S";
                t2PreTestNext.setVisibility(View.VISIBLE);
                t2PreTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t2PreTestOptA.setTextColor(getResources().getColor(R.color.white));
                t2PreTestOptB.setBackgroundTintList(null);
                t2PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t2PreTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t2PreTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = "C";
                t2PreTestNext.setVisibility(View.VISIBLE);
                t2PreTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t2PreTestOptB.setTextColor(getResources().getColor(R.color.white));
                t2PreTestOptA.setBackgroundTintList(null);
                t2PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t2PreTestOptA.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicTwoPreTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicTwoPreTestActivity.this, NavigationActivity.class);
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

        t2PreTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t2PreTestQuestion.setText(question.getQuestion(randomIndex));
        answer = question.getCorrectAnswer(randomIndex);

        if ((currentQuestionIndex + 1) < questionLength) {
            t2PreTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t2PreTestNext.setText("Submit");
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
                    t2PreTestProgress.setProgress(currentQuestionIndex + 1);
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
        t2PreTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t2PreTestTimer.setTextColor(Color.RED);
        } else {
            t2PreTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t2PreTestLayout.setVisibility(View.GONE);
        t2PreTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t2PreTestTotalScore = String.valueOf(score);
        t2PreTestScoreDisp.setText("Score: " + t2PreTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t2PreTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t2PreTestTimeDisp.setText("Time: " + t2PreTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(8603, t2PreTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t2PreTestNext.setVisibility(View.INVISIBLE);
        t2PreTestOptA.setBackgroundTintList(null);
        t2PreTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t2PreTestOptA.setTextColor(getResources().getColor(R.color.black));
        t2PreTestOptB.setBackgroundTintList(null);
        t2PreTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t2PreTestOptB.setTextColor(getResources().getColor(R.color.black));
    }
}