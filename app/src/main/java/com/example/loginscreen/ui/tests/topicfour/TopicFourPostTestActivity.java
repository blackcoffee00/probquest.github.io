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
import com.example.loginscreen.ui.tests.TestsModelClass;
import com.example.loginscreen.ui.tests.ViewResultAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TopicFourPostTestActivity extends AppCompatActivity {
    private LinearLayout t4PostTestEnterPasscode, t4PostTestInstruction, t4PostTestLayout;
    private RelativeLayout t4PostTestScore, t4PostTestHeader;
    private ListView t4PostTestListView;
    private EditText t4PostTestPasscode;
    private Button t4PostTestBackCode, t4PostTestSendCode, t4PostTestInsBack, t4PostTestInsStart, t4PostTestOptA, t4PostTestOptB, t4PostTestOptC, t4PostTestOptD, t4PostTestNext, t4PostTestResult;
    private TextView t4PostTestItem, t4PostTestTimer, t4PostTestQuestion, t4PostTestScoreDisp, t4PostTestTimeDisp, t4PostTestResultScore;
    private ProgressBar t4PostTestProgress;
    private ImageView t4PostTestResultExit;
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
    private TopicFourQuestion question = new TopicFourQuestion();
    private int questionLength = question.questions1.length;
    private String answer, selectedAnswer, t4PostTestTotalScore, t4PostTestTotalTime;
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
        setContentView(R.layout.activity_topic_four_post_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t4PostTestEnterPasscode = findViewById(R.id.t4PostTestEnterPasscode);
        t4PostTestInstruction = findViewById(R.id.t4PostTestInstruction);
        t4PostTestLayout = findViewById(R.id.t4PostTestLayout);
        t4PostTestScore = findViewById(R.id.t4PostTestScore);
        t4PostTestHeader = findViewById(R.id.t4PostTestHeader);
        t4PostTestListView = findViewById(R.id.t4PostTestListView);
        t4PostTestPasscode = findViewById(R.id.t4PostTestPasscode);
        t4PostTestBackCode = findViewById(R.id.t4PostTestBackCode);
        t4PostTestSendCode = findViewById(R.id.t4PostTestSendCode);
        t4PostTestInsBack = findViewById(R.id.t4PostTestInsBack);
        t4PostTestInsStart = findViewById(R.id.t4PostTestInsStart);
        t4PostTestOptA = findViewById(R.id.t4PostTestOptA);
        t4PostTestOptB = findViewById(R.id.t4PostTestOptB);
        t4PostTestOptC = findViewById(R.id.t4PostTestOptC);
        t4PostTestOptD = findViewById(R.id.t4PostTestOptD);
        t4PostTestNext = findViewById(R.id.t4PostTestNext);
        t4PostTestResult = findViewById(R.id.t4PostTestResult);
        t4PostTestItem = findViewById(R.id.t4PostTestItem);
        t4PostTestTimer = findViewById(R.id.t4PostTestTimer);
        t4PostTestQuestion = findViewById(R.id.t4PostTestQuestion);
        t4PostTestScoreDisp = findViewById(R.id.t4PostTestScoreDisp);
        t4PostTestTimeDisp = findViewById(R.id.t4PostTestTimeDisp);
        t4PostTestResultScore = findViewById(R.id.t4PostTestResultScore);
        t4PostTestProgress = findViewById(R.id.t4PostTestProgress);
        t4PostTestResultExit = findViewById(R.id.t4PostTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicFourPostTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t4PostTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(3186);
        if (checkTestCode == true) {
            t4PostTestEnterPasscode.setVisibility(View.GONE);
            t4PostTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(3186);
            if (!scoreAndTime.isEmpty()) {
                t4PostTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                t4PostTestTimeDisp.setText("Time: " + scoreAndTime.get(TIME));
            }
        } else {
            t4PostTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t4PostTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t4PostTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t4PostTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicFourPostTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t4PostTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicFourPostTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(3186)) {
                            t4PostTestEnterPasscode.setVisibility(View.GONE);
                            t4PostTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicFourPostTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t4PostTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t4PostTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4PostTestInstruction.setVisibility(View.GONE);
                t4PostTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t4PostTestProgress.setMax(questionLength);
                t4PostTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t4PostTestNext.setOnClickListener(new View.OnClickListener() {
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
                    t4PostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t4PostTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t4PostTestScore.setVisibility(View.GONE);
                t4PostTestListView.setVisibility(View.VISIBLE);
                t4PostTestHeader.setVisibility(View.VISIBLE);

                t4PostTestResultScore.setText("Score " + t4PostTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t4PostTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.GONE);
                    ansVis.add(View.GONE);
                }

                checkTestCode = dbHandler.checkCode(3186);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(3186);
                    if (!scoreAndTime.isEmpty()) {
                        t4PostTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t4PostTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t4PostTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t4PostTestOptA.getText().toString();
                t4PostTestNext.setVisibility(View.VISIBLE);
                t4PostTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PostTestOptA.setTextColor(getResources().getColor(R.color.white));
                t4PostTestOptB.setBackgroundTintList(null);
                t4PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptC.setBackgroundTintList(null);
                t4PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptD.setBackgroundTintList(null);
                t4PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t4PostTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t4PostTestOptB.getText().toString();
                t4PostTestNext.setVisibility(View.VISIBLE);
                t4PostTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PostTestOptB.setTextColor(getResources().getColor(R.color.white));
                t4PostTestOptA.setBackgroundTintList(null);
                t4PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptC.setBackgroundTintList(null);
                t4PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptD.setBackgroundTintList(null);
                t4PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t4PostTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t4PostTestOptC.getText().toString();
                t4PostTestNext.setVisibility(View.VISIBLE);
                t4PostTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PostTestOptC.setTextColor(getResources().getColor(R.color.white));
                t4PostTestOptA.setBackgroundTintList(null);
                t4PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptB.setBackgroundTintList(null);
                t4PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptD.setBackgroundTintList(null);
                t4PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t4PostTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t4PostTestOptD.getText().toString();
                t4PostTestNext.setVisibility(View.VISIBLE);
                t4PostTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t4PostTestOptD.setTextColor(getResources().getColor(R.color.white));
                t4PostTestOptA.setBackgroundTintList(null);
                t4PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptC.setBackgroundTintList(null);
                t4PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t4PostTestOptB.setBackgroundTintList(null);
                t4PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t4PostTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicFourPostTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicFourPostTestActivity.this, NavigationActivity.class);
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

        t4PostTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t4PostTestQuestion.setText(question.getPostTestQuestion(randomIndex));
        t4PostTestOptA.setText(question.getPostTestChoice1(randomIndex));
        t4PostTestOptB.setText(question.getPostTestChoice2(randomIndex));
        t4PostTestOptC.setText(question.getPostTestChoice3(randomIndex));
        t4PostTestOptD.setText(question.getPostTestChoice4(randomIndex));
        answer = question.getPostTestCorrectAnswer(randomIndex);

        testQuestions.add(question.getPostTestQuestion(randomIndex));
        btn1.add(question.getPostTestChoice1(randomIndex));
        btn2.add(question.getPostTestChoice2(randomIndex));
        btn3.add(question.getPostTestChoice3(randomIndex));
        btn4.add(question.getPostTestChoice4(randomIndex));
        testSolutions.add(question.getPreTestSolution(randomIndex));
        testAnswers.add(question.getPreTestSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            t4PostTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t4PostTestNext.setText("Submit");
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
                    t4PostTestProgress.setProgress(currentQuestionIndex + 1);
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
        t4PostTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t4PostTestTimer.setTextColor(Color.RED);
        } else {
            t4PostTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t4PostTestLayout.setVisibility(View.GONE);
        t4PostTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t4PostTestTotalScore = String.valueOf(score);
        t4PostTestScoreDisp.setText("Score: " + t4PostTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t4PostTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t4PostTestTimeDisp.setText("Time: " + t4PostTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(3186, t4PostTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t4PostTestNext.setVisibility(View.INVISIBLE);
        t4PostTestOptA.setBackgroundTintList(null);
        t4PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t4PostTestOptA.setTextColor(getResources().getColor(R.color.black));
        t4PostTestOptB.setBackgroundTintList(null);
        t4PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t4PostTestOptB.setTextColor(getResources().getColor(R.color.black));
        t4PostTestOptC.setBackgroundTintList(null);
        t4PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t4PostTestOptC.setTextColor(getResources().getColor(R.color.black));
        t4PostTestOptD.setBackgroundTintList(null);
        t4PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t4PostTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}