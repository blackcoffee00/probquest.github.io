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
import com.example.loginscreen.ui.tests.TestsModelClass;
import com.example.loginscreen.ui.tests.topictwo.ViewResultAdapterT2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicTwoPostTestActivity extends AppCompatActivity {
    private LinearLayout t2PostTestEnterPasscode, t2PostTestInstruction, t2PostTestLayout;
    private RelativeLayout t2PostTestScore, t2PostTestHeader;
    private ListView t2PostTestListView;
    private EditText t2PostTestPasscode;
    private Button t2PostTestBackCode, t2PostTestSendCode, t2PostTestInsBack, t2PostTestInsStart, t2PostTestOptA, t2PostTestOptB, t2PostTestNext, t2PostTestResult;
    private TextView t2Instruction, t2PostTestItem, t2PostTestTimer, t2PostTestQuestion, t2PostTestScoreDisp, t2PostTestTimeDisp, t2PostTestResultScore;
    private ProgressBar t2PostTestProgress;
    private ImageView t2PostTestResultExit;
    private Integer passcode;
    private boolean isTestOngoing = false;
    private int currentQuestionIndex, score, randomIndex, markImage, btnStyle1, btnStyle2;
    private long startTime, timeLeftInMillis;
    private List<String> testQuestions = new ArrayList<>();
    private int[] mark, btnbg1, btnbg2, storeMark, storeBtnbg1, storeBtnbg2;
    private TopicTwoPostTestQuestion question = new TopicTwoPostTestQuestion();
    private int questionLength = question.questions.length;
    private String answer, selectedAnswer, t2PostTestTotalScore, t2PostTestTotalTime;
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
        setContentView(R.layout.activity_topic_two_post_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t2PostTestEnterPasscode = findViewById(R.id.t2PostTestEnterPasscode);
        t2PostTestInstruction = findViewById(R.id.t2PostTestInstruction);
        t2PostTestLayout = findViewById(R.id.t2PostTestLayout);
        t2PostTestScore = findViewById(R.id.t2PostTestScore);
        t2PostTestHeader = findViewById(R.id.t2PostTestHeader);
        t2PostTestListView = findViewById(R.id.t2PostTestListView);
        t2PostTestPasscode = findViewById(R.id.t2PostTestPasscode);
        t2PostTestBackCode = findViewById(R.id.t2PostTestBackCode);
        t2PostTestSendCode = findViewById(R.id.t2PostTestSendCode);
        t2PostTestInsBack = findViewById(R.id.t2PostTestInsBack);
        t2PostTestInsStart = findViewById(R.id.t2PostTestInsStart);
        t2PostTestOptA = findViewById(R.id.t2PostTestOptA);
        t2PostTestOptB = findViewById(R.id.t2PostTestOptB);
        t2PostTestNext = findViewById(R.id.t2PostTestNext);
        t2PostTestResult = findViewById(R.id.t2PostTestResult);
        t2PostTestItem = findViewById(R.id.t2PostTestItem);
        t2PostTestTimer = findViewById(R.id.t2PostTestTimer);
        t2PostTestQuestion = findViewById(R.id.t2PostTestQuestion);
        t2PostTestScoreDisp = findViewById(R.id.t2PostTestScoreDisp);
        t2PostTestTimeDisp = findViewById(R.id.t2PostTestTimeDisp);
        t2PostTestResultScore = findViewById(R.id.t2PostTestResultScore);
        t2PostTestProgress = findViewById(R.id.t2PostTestProgress);
        t2PostTestResultExit = findViewById(R.id.t2PostTestResultExit);
        t2Instruction = findViewById(R.id.t2Instruction);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        storeMark = new int[questionLength];
        storeBtnbg1 = new int[questionLength];
        storeBtnbg2 = new int[questionLength];

        ViewResultAdapterT2 viewResultAdapter = new ViewResultAdapterT2(TopicTwoPostTestActivity.this, testQuestions, mark, btnbg1, btnbg2);
        t2PostTestListView.setAdapter(viewResultAdapter);

        String t2InstructionFormat = "Read and understand each event carefully and tell whether it is a simple or a compound event.<br><br>Choose <b>S</b> if it is a simple event and <b>C</b> if it is a compound event and and click next to proceed to the next item.<br><br>You have a 1 minute and 30 seconds time limit per question.<br><br>Your score will display after answering all 10 questions. Good luck!";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            t2Instruction.setText(Html.fromHtml(t2InstructionFormat, Html.FROM_HTML_MODE_COMPACT));
        } else {
            t2Instruction.setText(Html.fromHtml(t2InstructionFormat));
        }

        checkTestCode = dbHandler.checkCode(8970);
        if (checkTestCode == true) {
            t2PostTestEnterPasscode.setVisibility(View.GONE);
            t2PostTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(8970);
            if (!scoreAndTime.isEmpty()) {
                t2PostTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t2PostTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t2PostTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t2PostTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t2PostTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t2PostTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicTwoPostTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t2PostTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicTwoPostTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(8970)) {
                            t2PostTestEnterPasscode.setVisibility(View.GONE);
                            t2PostTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicTwoPostTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t2PostTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t2PostTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t2PostTestInstruction.setVisibility(View.GONE);
                t2PostTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t2PostTestProgress.setMax(questionLength);
                t2PostTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t2PostTestNext.setOnClickListener(new View.OnClickListener() {
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

                String t22Questions = question.getQuestion(randomIndex);
                markImage = storeMark[currentQuestionIndex];
                btnStyle1 = storeBtnbg1[currentQuestionIndex];
                btnStyle2 = storeBtnbg2[currentQuestionIndex];

                dbHandler.storeT22Result(new T2TestResultModelClass(t22Questions, markImage, btnStyle1, btnStyle2));

                currentQuestionIndex++;

                if (currentQuestionIndex < questionLength) {
                    t2PostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t2PostTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t2PostTestScore.setVisibility(View.GONE);
                t2PostTestListView.setVisibility(View.VISIBLE);
                t2PostTestHeader.setVisibility(View.VISIBLE);
                t2PostTestResultScore.setText("Score " + t2PostTestTotalScore + "/" + questionLength);
                ((ViewResultAdapterT2) t2PostTestListView.getAdapter()).notifyDataSetChanged();

                int markCheck = R.drawable.round_check_24;
                int markWrong = R.drawable.round_clear_24;
                int btnSlc = R.drawable.custom_selected_button;
                int btnOpt = R.drawable.custom_option_button;

                for (int i = 0; i < questionLength; i++) {
                    T2TestResultModelClass testResult = dbHandler.getT22Result(i+1);
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
                        btnbg2[i] = R.drawable.custom_selected_button;
                    }
                }

                checkTestCode = dbHandler.checkCode(8970);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(8970);
                    if (!scoreAndTime.isEmpty()) {
                        t2PostTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t2PostTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t2PostTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = "S";
                t2PostTestNext.setVisibility(View.VISIBLE);
                t2PostTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t2PostTestOptA.setTextColor(getResources().getColor(R.color.white));
                t2PostTestOptB.setBackgroundTintList(null);
                t2PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t2PostTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t2PostTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = "C";
                t2PostTestNext.setVisibility(View.VISIBLE);
                t2PostTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t2PostTestOptB.setTextColor(getResources().getColor(R.color.white));
                t2PostTestOptA.setBackgroundTintList(null);
                t2PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t2PostTestOptA.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicTwoPostTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicTwoPostTestActivity.this, NavigationActivity.class);
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

        t2PostTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t2PostTestQuestion.setText(question.getQuestion(randomIndex));
        answer = question.getCorrectAnswer(randomIndex);

        if ((currentQuestionIndex + 1) < questionLength) {
            t2PostTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t2PostTestNext.setText("Submit");
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
                    t2PostTestProgress.setProgress(currentQuestionIndex + 1);
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
        t2PostTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t2PostTestTimer.setTextColor(Color.RED);
        } else {
            t2PostTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t2PostTestLayout.setVisibility(View.GONE);
        t2PostTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t2PostTestTotalScore = String.valueOf(score);
        t2PostTestScoreDisp.setText("Score: " + t2PostTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t2PostTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t2PostTestTimeDisp.setText("Time: " + t2PostTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(8970, t2PostTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t2PostTestNext.setVisibility(View.INVISIBLE);
        t2PostTestOptA.setBackgroundTintList(null);
        t2PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t2PostTestOptA.setTextColor(getResources().getColor(R.color.black));
        t2PostTestOptB.setBackgroundTintList(null);
        t2PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t2PostTestOptB.setTextColor(getResources().getColor(R.color.black));
    }
}