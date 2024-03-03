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
import com.example.loginscreen.ui.tests.TestsModelClass;
import com.example.loginscreen.ui.tests.ViewResultAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicSixPostTestActivity extends AppCompatActivity {
    private LinearLayout t6PostTestEnterPasscode, t6PostTestInstruction, t6PostTestLayout;
    private RelativeLayout t6PostTestScore, t6PostTestHeader;
    private ListView t6PostTestListView;
    private EditText t6PostTestPasscode;
    private Button t6PostTestBackCode, t6PostTestSendCode, t6PostTestInsBack, t6PostTestInsStart, t6PostTestOptA, t6PostTestOptB, t6PostTestOptC, t6PostTestOptD, t6PostTestNext, t6PostTestResult;
    private TextView t6PostTestItem, t6PostTestTimer, t6PostTestQuestion, t6PostTestScoreDisp, t6PostTestTimeDisp, t6PostTestResultScore;
    private ProgressBar t6PostTestProgress;
    private ImageView t6PostTestResultExit;
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
    private TopicSixQuestion question = new TopicSixQuestion();
    private int questionLength = question.questions1.length;
    private String answer, selectedAnswer, t6PostTestTotalScore, t6PostTestTotalTime;
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
        setContentView(R.layout.activity_topic_six_post_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t6PostTestEnterPasscode = findViewById(R.id.t6PostTestEnterPasscode);
        t6PostTestInstruction = findViewById(R.id.t6PostTestInstruction);
        t6PostTestLayout = findViewById(R.id.t6PostTestLayout);
        t6PostTestScore = findViewById(R.id.t6PostTestScore);
        t6PostTestHeader = findViewById(R.id.t6PostTestHeader);
        t6PostTestListView = findViewById(R.id.t6PostTestListView);
        t6PostTestPasscode = findViewById(R.id.t6PostTestPasscode);
        t6PostTestBackCode = findViewById(R.id.t6PostTestBackCode);
        t6PostTestSendCode = findViewById(R.id.t6PostTestSendCode);
        t6PostTestInsBack = findViewById(R.id.t6PostTestInsBack);
        t6PostTestInsStart = findViewById(R.id.t6PostTestInsStart);
        t6PostTestOptA = findViewById(R.id.t6PostTestOptA);
        t6PostTestOptB = findViewById(R.id.t6PostTestOptB);
        t6PostTestOptC = findViewById(R.id.t6PostTestOptC);
        t6PostTestOptD = findViewById(R.id.t6PostTestOptD);
        t6PostTestNext = findViewById(R.id.t6PostTestNext);
        t6PostTestResult = findViewById(R.id.t6PostTestResult);
        t6PostTestItem = findViewById(R.id.t6PostTestItem);
        t6PostTestTimer = findViewById(R.id.t6PostTestTimer);
        t6PostTestQuestion = findViewById(R.id.t6PostTestQuestion);
        t6PostTestScoreDisp = findViewById(R.id.t6PostTestScoreDisp);
        t6PostTestTimeDisp = findViewById(R.id.t6PostTestTimeDisp);
        t6PostTestResultScore = findViewById(R.id.t6PostTestResultScore);
        t6PostTestProgress = findViewById(R.id.t6PostTestProgress);
        t6PostTestResultExit = findViewById(R.id.t6PostTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicSixPostTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t6PostTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(9002);
        if (checkTestCode == true) {
            t6PostTestEnterPasscode.setVisibility(View.GONE);
            t6PostTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(9002);
            if (!scoreAndTime.isEmpty()) {
                t6PostTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t6PostTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t6PostTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t6PostTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t6PostTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t6PostTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicSixPostTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t6PostTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicSixPostTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(9002)) {
                            t6PostTestEnterPasscode.setVisibility(View.GONE);
                            t6PostTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicSixPostTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t6PostTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t6PostTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t6PostTestInstruction.setVisibility(View.GONE);
                t6PostTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t6PostTestProgress.setMax(questionLength);
                t6PostTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t6PostTestNext.setOnClickListener(new View.OnClickListener() {
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
                    t6PostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t6PostTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t6PostTestScore.setVisibility(View.GONE);
                t6PostTestListView.setVisibility(View.VISIBLE);
                t6PostTestHeader.setVisibility(View.VISIBLE);

                t6PostTestResultScore.setText("Score " + t6PostTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t6PostTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.GONE);
                    ansVis.add(View.GONE);
                }

                checkTestCode = dbHandler.checkCode(9002);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(9002);
                    if (!scoreAndTime.isEmpty()) {
                        t6PostTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t6PostTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t6PostTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t6PostTestOptA.getText().toString();
                t6PostTestNext.setVisibility(View.VISIBLE);
                t6PostTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PostTestOptA.setTextColor(getResources().getColor(R.color.white));
                t6PostTestOptB.setBackgroundTintList(null);
                t6PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptC.setBackgroundTintList(null);
                t6PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptD.setBackgroundTintList(null);
                t6PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t6PostTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t6PostTestOptB.getText().toString();
                t6PostTestNext.setVisibility(View.VISIBLE);
                t6PostTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PostTestOptB.setTextColor(getResources().getColor(R.color.white));
                t6PostTestOptA.setBackgroundTintList(null);
                t6PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptC.setBackgroundTintList(null);
                t6PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptD.setBackgroundTintList(null);
                t6PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t6PostTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t6PostTestOptC.getText().toString();
                t6PostTestNext.setVisibility(View.VISIBLE);
                t6PostTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PostTestOptC.setTextColor(getResources().getColor(R.color.white));
                t6PostTestOptA.setBackgroundTintList(null);
                t6PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptB.setBackgroundTintList(null);
                t6PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptD.setBackgroundTintList(null);
                t6PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t6PostTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t6PostTestOptD.getText().toString();
                t6PostTestNext.setVisibility(View.VISIBLE);
                t6PostTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t6PostTestOptD.setTextColor(getResources().getColor(R.color.white));
                t6PostTestOptA.setBackgroundTintList(null);
                t6PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptC.setBackgroundTintList(null);
                t6PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t6PostTestOptB.setBackgroundTintList(null);
                t6PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t6PostTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicSixPostTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicSixPostTestActivity.this, NavigationActivity.class);
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

        t6PostTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t6PostTestQuestion.setText(question.getPostTestQuestion(randomIndex));
        t6PostTestOptA.setText(question.getPostTestChoice1(randomIndex));
        t6PostTestOptB.setText(question.getPostTestChoice2(randomIndex));
        t6PostTestOptC.setText(question.getPostTestChoice3(randomIndex));
        t6PostTestOptD.setText(question.getPostTestChoice4(randomIndex));
        answer = question.getPostTestCorrectAnswer(randomIndex);

        testQuestions.add(question.getPostTestQuestion(randomIndex));
        btn1.add(question.getPostTestChoice1(randomIndex));
        btn2.add(question.getPostTestChoice2(randomIndex));
        btn3.add(question.getPostTestChoice3(randomIndex));
        btn4.add(question.getPostTestChoice4(randomIndex));
        testSolutions.add(question.getPreTestSolution(randomIndex));
        testAnswers.add(question.getPreTestSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            t6PostTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t6PostTestNext.setText("Submit");
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
                    t6PostTestProgress.setProgress(currentQuestionIndex + 1);
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
        t6PostTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t6PostTestTimer.setTextColor(Color.RED);
        } else {
            t6PostTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t6PostTestLayout.setVisibility(View.GONE);
        t6PostTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t6PostTestTotalScore = String.valueOf(score);
        t6PostTestScoreDisp.setText("Score: " + t6PostTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t6PostTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t6PostTestTimeDisp.setText("Time: " + t6PostTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(9002, t6PostTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t6PostTestNext.setVisibility(View.INVISIBLE);
        t6PostTestOptA.setBackgroundTintList(null);
        t6PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t6PostTestOptA.setTextColor(getResources().getColor(R.color.black));
        t6PostTestOptB.setBackgroundTintList(null);
        t6PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t6PostTestOptB.setTextColor(getResources().getColor(R.color.black));
        t6PostTestOptC.setBackgroundTintList(null);
        t6PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t6PostTestOptC.setTextColor(getResources().getColor(R.color.black));
        t6PostTestOptD.setBackgroundTintList(null);
        t6PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t6PostTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}