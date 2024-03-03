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

public class TopicFivePostTestActivity extends AppCompatActivity {
    private LinearLayout t5PostTestEnterPasscode, t5PostTestInstruction, t5PostTestLayout;
    private RelativeLayout t5PostTestScore, t5PostTestHeader;
    private ListView t5PostTestListView;
    private EditText t5PostTestPasscode;
    private Button t5PostTestBackCode, t5PostTestSendCode, t5PostTestInsBack, t5PostTestInsStart, t5PostTestOptA, t5PostTestOptB, t5PostTestOptC, t5PostTestOptD, t5PostTestNext, t5PostTestResult;
    private TextView t5PostTestItem, t5PostTestTimer, t5PostTestQuestion, t5PostTestScoreDisp, t5PostTestTimeDisp, t5PostTestResultScore;
    private ProgressBar t5PostTestProgress;
    private ImageView t5PostTestResultExit, t5ImagePostTest;
    private Integer passcode;
    private boolean isTestOngoing = false;
    private int currentQuestionIndex, score, randomIndex, buttonIndex;
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
    private String answer, selectedAnswer, t5PostTestTotalScore, t5PostTestTotalTime;
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
        setContentView(R.layout.activity_topic_five_post_test);

        random = new Random();
        dbHandler = new DBHandler(this);

        t5PostTestEnterPasscode = findViewById(R.id.t5PostTestEnterPasscode);
        t5PostTestInstruction = findViewById(R.id.t5PostTestInstruction);
        t5PostTestLayout = findViewById(R.id.t5PostTestLayout);
        t5PostTestScore = findViewById(R.id.t5PostTestScore);
        t5PostTestHeader = findViewById(R.id.t5PostTestHeader);
        t5PostTestListView = findViewById(R.id.t5PostTestListView);
        t5PostTestPasscode = findViewById(R.id.t5PostTestPasscode);
        t5PostTestBackCode = findViewById(R.id.t5PostTestBackCode);
        t5PostTestSendCode = findViewById(R.id.t5PostTestSendCode);
        t5PostTestInsBack = findViewById(R.id.t5PostTestInsBack);
        t5PostTestInsStart = findViewById(R.id.t5PostTestInsStart);
        t5PostTestOptA = findViewById(R.id.t5PostTestOptA);
        t5PostTestOptB = findViewById(R.id.t5PostTestOptB);
        t5PostTestOptC = findViewById(R.id.t5PostTestOptC);
        t5PostTestOptD = findViewById(R.id.t5PostTestOptD);
        t5PostTestNext = findViewById(R.id.t5PostTestNext);
        t5PostTestResult = findViewById(R.id.t5PostTestResult);
        t5PostTestItem = findViewById(R.id.t5PostTestItem);
        t5PostTestTimer = findViewById(R.id.t5PostTestTimer);
        t5PostTestQuestion = findViewById(R.id.t5PostTestQuestion);
        t5ImagePostTest = findViewById(R.id.t5ImagePostTest);
        t5PostTestScoreDisp = findViewById(R.id.t5PostTestScoreDisp);
        t5PostTestTimeDisp = findViewById(R.id.t5PostTestTimeDisp);
        t5PostTestResultScore = findViewById(R.id.t5PostTestResultScore);
        t5PostTestProgress = findViewById(R.id.t5PostTestProgress);
        t5PostTestResultExit = findViewById(R.id.t5PostTestResultExit);

        mark = new int[questionLength];
        btnbg1 = new int[questionLength];
        btnbg2 = new int[questionLength];
        btnbg3 = new int[questionLength];
        btnbg4 = new int[questionLength];

        ViewResultAdapter viewResultAdapter = new ViewResultAdapter(TopicFivePostTestActivity.this, testQuestions, btn1, btn2, btn3, btn4, testSolutions, testAnswers, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4);
        t5PostTestListView.setAdapter(viewResultAdapter);

        checkTestCode = dbHandler.checkCode(7260);
        if (checkTestCode == true) {
            t5PostTestEnterPasscode.setVisibility(View.GONE);
            t5PostTestScore.setVisibility(View.VISIBLE);

            scoreAndTime = dbHandler.getScoreTime(7260);
            if (!scoreAndTime.isEmpty()) {
                t5PostTestScoreDisp.setText("Score: " + scoreAndTime.get(SCORE) + "/" + questionLength);
                int totalSeconds = Integer.parseInt(scoreAndTime.get(TIME));
                int min = (totalSeconds % 3600) / 60;
                int secs = totalSeconds % 60;
                String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
                t5PostTestTimeDisp.setText("Time: " + totalTime);
            }
        } else {
            t5PostTestEnterPasscode.setVisibility(View.VISIBLE);
        }

        t5PostTestBackCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t5PostTestSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (t5PostTestPasscode.getText().toString().isEmpty()) {
                    Toast.makeText(TopicFivePostTestActivity.this, "Require passcode", Toast.LENGTH_SHORT).show();
                } else {
                    passcode = Integer.valueOf(t5PostTestPasscode.getText().toString());
                    checkTestCode = dbHandler.checkCode(passcode);

                    if (checkTestCode == true) {
                        Toast.makeText(TopicFivePostTestActivity.this, "You have already taken this test", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passcode.equals(7260)) {
                            t5PostTestEnterPasscode.setVisibility(View.GONE);
                            t5PostTestInstruction.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(TopicFivePostTestActivity.this, "Incorrect passcode", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        t5PostTestInsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t5PostTestInsStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5PostTestInstruction.setVisibility(View.GONE);
                t5PostTestLayout.setVisibility(View.VISIBLE);

                isTestOngoing = true;

                score = 0;
                currentQuestionIndex = 0;

                t5PostTestProgress.setMax(questionLength);
                t5PostTestProgress.setProgress(currentQuestionIndex + 1);

                startTime = System.currentTimeMillis();
                NextQuestion();
            }
        });

        t5PostTestNext.setOnClickListener(new View.OnClickListener() {
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
                    t5PostTestProgress.setProgress(currentQuestionIndex + 1);
                    NextQuestion();
                    resetButtonStyle();
                } else {
                    countDownTimer.cancel();
                    showScore();
                }
            }
        });

        t5PostTestResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t5PostTestScore.setVisibility(View.GONE);
                t5PostTestListView.setVisibility(View.VISIBLE);
                t5PostTestHeader.setVisibility(View.VISIBLE);

                t5PostTestResultScore.setText("Score " + t5PostTestTotalScore + "/" + questionLength);
                ((ViewResultAdapter) t5PostTestListView.getAdapter()).notifyDataSetChanged();

                for (int i = 0; i < questionLength; i++) {
                    solVis.add(View.GONE);
                    ansVis.add(View.VISIBLE);
                }

                checkTestCode = dbHandler.checkCode(7260);
                if (checkTestCode == true) {
                    scoreAndTime = dbHandler.getScoreTime(7260);
                    if (!scoreAndTime.isEmpty()) {
                        t5PostTestResultScore.setText("Score " + scoreAndTime.get(SCORE) + "/" + questionLength);
                    }
                }
            }
        });

        t5PostTestResultExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        t5PostTestOptA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 1;
                selectedAnswer = t5PostTestOptA.getText().toString();
                t5PostTestNext.setVisibility(View.VISIBLE);
                t5PostTestOptA.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PostTestOptA.setTextColor(getResources().getColor(R.color.white));
                t5PostTestOptB.setBackgroundTintList(null);
                t5PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptC.setBackgroundTintList(null);
                t5PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptD.setBackgroundTintList(null);
                t5PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t5PostTestOptB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 2;
                selectedAnswer = t5PostTestOptB.getText().toString();
                t5PostTestNext.setVisibility(View.VISIBLE);
                t5PostTestOptB.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PostTestOptB.setTextColor(getResources().getColor(R.color.white));
                t5PostTestOptA.setBackgroundTintList(null);
                t5PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptC.setBackgroundTintList(null);
                t5PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptD.setBackgroundTintList(null);
                t5PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t5PostTestOptC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 3;
                selectedAnswer = t5PostTestOptC.getText().toString();
                t5PostTestNext.setVisibility(View.VISIBLE);
                t5PostTestOptC.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PostTestOptC.setTextColor(getResources().getColor(R.color.white));
                t5PostTestOptA.setBackgroundTintList(null);
                t5PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptB.setBackgroundTintList(null);
                t5PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptB.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptD.setBackgroundTintList(null);
                t5PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptD.setTextColor(getResources().getColor(R.color.black));
            }
        });

        t5PostTestOptD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonIndex = 4;
                selectedAnswer = t5PostTestOptD.getText().toString();
                t5PostTestNext.setVisibility(View.VISIBLE);
                t5PostTestOptD.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                t5PostTestOptD.setTextColor(getResources().getColor(R.color.white));
                t5PostTestOptA.setBackgroundTintList(null);
                t5PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptA.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptC.setBackgroundTintList(null);
                t5PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptC.setTextColor(getResources().getColor(R.color.black));
                t5PostTestOptB.setBackgroundTintList(null);
                t5PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
                t5PostTestOptB.setTextColor(getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (isTestOngoing) {
            Toast.makeText(TopicFivePostTestActivity.this, "Can't go back. Test is ongoing.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(TopicFivePostTestActivity.this, NavigationActivity.class);
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

        t5PostTestItem.setText((currentQuestionIndex + 1) + " of " + questionLength + " Questions");

        t5PostTestQuestion.setText(question.getPostTestQuestion(randomIndex));
        t5ImagePostTest.setImageResource(getResources().getIdentifier(question.getImgTest(randomIndex), "drawable", getPackageName()));
        t5PostTestOptA.setText(question.getPostTestChoice1(randomIndex));
        t5PostTestOptB.setText(question.getPostTestChoice2(randomIndex));
        t5PostTestOptC.setText(question.getPostTestChoice3(randomIndex));
        t5PostTestOptD.setText(question.getPostTestChoice4(randomIndex));
        answer = question.getPostTestCorrectAnswer(randomIndex);

        testQuestions.add(question.getPostTestQuestion(randomIndex));
        btn1.add(question.getPostTestChoice1(randomIndex));
        btn2.add(question.getPostTestChoice2(randomIndex));
        btn3.add(question.getPostTestChoice3(randomIndex));
        btn4.add(question.getPostTestChoice4(randomIndex));
        testSolutions.add(question.getPreTestSolution(randomIndex));
        testAnswers.add(question.getPostTestSolAnswer(randomIndex));

        if ((currentQuestionIndex + 1) < questionLength) {
            t5PostTestNext.setText("Next");
        } else if ((currentQuestionIndex + 1) == questionLength) {
            t5PostTestNext.setText("Submit");
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
                    t5PostTestProgress.setProgress(currentQuestionIndex + 1);
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
        t5PostTestTimer.setText(timerFormatted);

        if (timeLeftInMillis < 10000) {
            t5PostTestTimer.setTextColor(Color.RED);
        } else {
            t5PostTestTimer.setTextColor(Color.WHITE);
        }

        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showScore() {
        t5PostTestLayout.setVisibility(View.GONE);
        t5PostTestScore.setVisibility(View.VISIBLE);

        isTestOngoing = false;

        t5PostTestTotalScore = String.valueOf(score);
        t5PostTestScoreDisp.setText("Score: " + t5PostTestTotalScore + "/" + questionLength);

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        int totalSeconds = (int) (timeDiff / 1000);

        int min = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        t5PostTestTotalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
        t5PostTestTimeDisp.setText("Time: " + t5PostTestTotalTime);

        String totalSecs = String.valueOf(totalSeconds);

        dbHandler.storeTestScore(new TestsModelClass(7260, t5PostTestTotalScore, totalSecs));
    }

    private void resetButtonStyle() {
        t5PostTestNext.setVisibility(View.INVISIBLE);
        t5PostTestOptA.setBackgroundTintList(null);
        t5PostTestOptA.setBackgroundResource(R.drawable.custom_option_button);
        t5PostTestOptA.setTextColor(getResources().getColor(R.color.black));
        t5PostTestOptB.setBackgroundTintList(null);
        t5PostTestOptB.setBackgroundResource(R.drawable.custom_option_button);
        t5PostTestOptB.setTextColor(getResources().getColor(R.color.black));
        t5PostTestOptC.setBackgroundTintList(null);
        t5PostTestOptC.setBackgroundResource(R.drawable.custom_option_button);
        t5PostTestOptC.setTextColor(getResources().getColor(R.color.black));
        t5PostTestOptD.setBackgroundTintList(null);
        t5PostTestOptD.setBackgroundResource(R.drawable.custom_option_button);
        t5PostTestOptD.setTextColor(getResources().getColor(R.color.black));
    }
}