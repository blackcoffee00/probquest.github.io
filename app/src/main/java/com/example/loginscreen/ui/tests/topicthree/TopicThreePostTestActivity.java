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

public class TopicThreePostTestActivity extends AppCompatActivity {
    private LinearLayout t3PostTestEnterPasscode, t3PostTestInstruction, t3PostTestLayout;
    private RelativeLayout t3PostTestScore, t3PostTestHeader;
    private ListView t3PostTestListView;
    private EditText t3PostTestPasscode;
    private Button t3PostTestBackCode, t3PostTestSendCode, t3PostTestInsBack, t3PostTestInsStart, t3PostTestOptA, t3PostTestOptB, t3PostTestOptC, t3PostTestOptD, t3PostTestNext, t3PostTestResult;
    private TextView t3PostTestItem, t3PostTestTimer, t3PostTestQuestion, t3PostTestScoreDisp, t3PostTestTimeDisp, t3PostTestResultScore;
    private ProgressBar t3PostTestProgress;
    private ImageView t3PostTestResultExit;
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
    private String answer, selectedAnswer, t3PostTestTotalScore, t3PostTestTotalTime;
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
        setContentView(R.layout.activity_topic_three_post_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t3PostTestEnterPasscode = findViewById(R.id.t3PostTestEnterPasscode);
        t3PostTestInstruction = findViewById(R.id.t3PostTestInstruction);
        t3PostTestLayout = findViewById(R.id.t3PostTestLayout);
        t3PostTestScore = findViewById(R.id.t3PostTestScore);
        t3PostTestHeader = findViewById(R.id.t3PostTestHeader);
        t3PostTestListView = findViewById(R.id.t3PostTestListView);
        t3PostTestPasscode = findViewById(R.id.t3PostTestPasscode);
        t3PostTestBackCode = findViewById(R.id.t3PostTestBackCode);
        t3PostTestSendCode = findViewById(R.id.t3PostTestSendCode);
        t3PostTestInsBack = findViewById(R.id.t3PostTestInsBack);
        t3PostTestInsStart = findViewById(R.id.t3PostTestInsStart);
        t3PostTestOptA = findViewById(R.id.t3PostTestOptA);
        t3PostTestOptB = findViewById(R.id.t3PostTestOptB);
        t3PostTestOptC = findViewById(R.id.t3PostTestOptC);
        t3PostTestOptD = findViewById(R.id.t3PostTestOptD);
        t3PostTestNext = findViewById(R.id.t3PostTestNext);
        t3PostTestResult = findViewById(R.id.t3PostTestResult);
        t3PostTestItem = findViewById(R.id.t3PostTestItem);
        t3PostTestTimer = findViewById(R.id.t3PostTestTimer);
        t3PostTestQuestion = findViewById(R.id.t3PostTestQuestion);
        t3PostTestScoreDisp = findViewById(R.id.t3PostTestScoreDisp);
        t3PostTestTimeDisp = findViewById(R.id.t3PostTestTimeDisp);
        t3PostTestResultScore = findViewById(R.id.t3PostTestResultScore);
        t3PostTestProgress = findViewById(R.id.t3PostTestProgress);
        t3PostTestResultExit = findViewById(R.id.t3PostTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicThreePostTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t3PostTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(7436);
        if (checkTestCode == true) {
            t3PostTestEnterPasscode.setVisibility(View.GONE);
            t3PostTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(7436);
            if (!scoreAndTime.isEmpty()) {
                t3PostTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t3PostTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t3PostTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t3PostTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t3PostTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t3PostTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicThreePostTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t3PostTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicThreePostTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(7436)) {
                            t3PostTestEnterPasscode.setVisibility(View.GONE);
                            t3PostTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicThreePostTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t3PostTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t3PostTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3PostTestInstruction.setVisibility(View.GONE);
                t3PostTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t3PostTestProgress.setMax(questionLength);
                t3PostTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t3PostTestNext.setOnClickListener(new View.OnClickListener() {
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
                    t3PostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t3PostTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t3PostTestScore.setVisibility(View.GONE);
                t3PostTestListView.setVisibility(View.VISIBLE);
                t3PostTestHeader.setVisibility(View.VISIBLE);

                t3PostTestResultScore.setText("Score " + t3PostTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t3PostTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.GONE);
                    ansVis.add(View.GONE);
                }

                checkTestCode = dbHandler.checkCode(7436);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(7436);
                    if (!scoreAndTime.isEmpty()) {
                        t3PostTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t3PostTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t3PostTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t3PostTestOptA.getText().toString();
                t3PostTestNext.setVisibility(View.VISIBLE);
                t3PostTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PostTestOptA.setTextColor(getResources().getColor(R.color.white));
                t3PostTestOptB.setBackgroundTintList(null);
                t3PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptC.setBackgroundTintList(null);
                t3PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptD.setBackgroundTintList(null);
                t3PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t3PostTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t3PostTestOptB.getText().toString();
                t3PostTestNext.setVisibility(View.VISIBLE);
                t3PostTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PostTestOptB.setTextColor(getResources().getColor(R.color.white));
                t3PostTestOptA.setBackgroundTintList(null);
                t3PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptC.setBackgroundTintList(null);
                t3PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptD.setBackgroundTintList(null);
                t3PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t3PostTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t3PostTestOptC.getText().toString();
                t3PostTestNext.setVisibility(View.VISIBLE);
                t3PostTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PostTestOptC.setTextColor(getResources().getColor(R.color.white));
                t3PostTestOptA.setBackgroundTintList(null);
                t3PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptB.setBackgroundTintList(null);
                t3PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptD.setBackgroundTintList(null);
                t3PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t3PostTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t3PostTestOptD.getText().toString();
                t3PostTestNext.setVisibility(View.VISIBLE);
                t3PostTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t3PostTestOptD.setTextColor(getResources().getColor(R.color.white));
                t3PostTestOptA.setBackgroundTintList(null);
                t3PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptC.setBackgroundTintList(null);
                t3PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t3PostTestOptB.setBackgroundTintList(null);
                t3PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t3PostTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicThreePostTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicThreePostTestActivity.this, NavigationActivity.class);
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

        t3PostTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t3PostTestQuestion.setText(question.getPostTestQuestion(randomIndex));
        t3PostTestOptA.setText(question.getPostTestChoice1(randomIndex));
        t3PostTestOptB.setText(question.getPostTestChoice2(randomIndex));
        t3PostTestOptC.setText(question.getPostTestChoice3(randomIndex));
        t3PostTestOptD.setText(question.getPostTestChoice4(randomIndex));
        answer = question.getPostTestCorrectAnswer(randomIndex);

        testQuestions.add(question.getPostTestQuestion(randomIndex));
        btn1.add(question.getPostTestChoice1(randomIndex));
        btn2.add(question.getPostTestChoice2(randomIndex));
        btn3.add(question.getPostTestChoice3(randomIndex));
        btn4.add(question.getPostTestChoice4(randomIndex));
        testSolutions.add(question.getPreTestSolution(randomIndex));
        testAnswers.add(question.getPreTestSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            t3PostTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t3PostTestNext.setText("Submit");
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
                    t3PostTestProgress.setProgress(currentQuestionIndex + 1);
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
        t3PostTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t3PostTestTimer.setTextColor(Color.RED);
        } else {
            t3PostTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t3PostTestLayout.setVisibility(View.GONE);
        t3PostTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t3PostTestTotalScore = String.valueOf(score);
        t3PostTestScoreDisp.setText("Score: " + t3PostTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t3PostTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t3PostTestTimeDisp.setText("Time: " + t3PostTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(7436, t3PostTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t3PostTestNext.setVisibility(View.INVISIBLE);
        t3PostTestOptA.setBackgroundTintList(null);
        t3PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t3PostTestOptA.setTextColor(getResources().getColor(R.color.black));
        t3PostTestOptB.setBackgroundTintList(null);
        t3PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t3PostTestOptB.setTextColor(getResources().getColor(R.color.black));
        t3PostTestOptC.setBackgroundTintList(null);
        t3PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t3PostTestOptC.setTextColor(getResources().getColor(R.color.black));
        t3PostTestOptD.setBackgroundTintList(null);
        t3PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t3PostTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}