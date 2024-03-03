package com.example.loginscreen.ui.games.puzzles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class PuzzlesActivity extends AppCompatActivity {
    private Button easyPuzzles, mediumPuzzles, hardPuzzles, easySubmit, mediumSubmit, hardSubmit, scoreTryAgain, scoreExit;
    private TextView easyTimer, easyQuestion, easySolvedProblems, mediumTimer, mediumQuestion, mediumSolvedProblems, hardTimer, hardQuestion, hardSolvedProblems, scoreHeader, scoreTextView;
    private ImageView puzzlesExit, puzzleMusic, puzzleInstruction, easyExit, easyImage, mediumExit, mediumImage, hardExit, hardImage;
    private EditText easyInput, mediumInput, hardInput;
    private MediaPlayer puzzleBgMusic, buttonSound, correctSound, wrongSound, timerSound;
    private RelativeLayout homeLayout, easyLayout, mediumLayout, hardLayout, scoreLayout;
    private boolean isGameOngoing = false;
    private boolean musicPlaying = true;
    private boolean timerRunning, easySelected, mediumSelected, hardSelected;
    private int currentProblemIndex, solved, randomIndex;
    private static final long COUNTDOWN_IN_MILLIS = 91000;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private EasyProblems easyProblems = new EasyProblems();
    private int easyProblemLength = easyProblems.problems.length;
    private MediumProblems mediumProblems = new MediumProblems();
    private int mediumProblemLength = mediumProblems.problems.length;
    private HardProblems hardProblems = new HardProblems();
    private int hardProblemLength = hardProblems.problems.length;
    private String easyAnswer, mediumAnswer, hardAnswer;
    private Set<Integer> shownProblemIndices = new HashSet<>();
    Random random = new Random();
    private Cursor cursor;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzles);

        easyPuzzles = findViewById(R.id.easyPuzzles);
        mediumPuzzles = findViewById(R.id.mediumPuzzles);
        hardPuzzles = findViewById(R.id.hardPuzzles);
        puzzlesExit = findViewById(R.id.puzzlesExit);
        puzzleMusic = findViewById(R.id.puzzleMusic);
        puzzleInstruction = findViewById(R.id.puzzleInstruction);
        homeLayout = findViewById(R.id.homeLayout);
        easyLayout = findViewById(R.id.easyLayout);
        easyExit = findViewById(R.id.easyExit);
        easySolvedProblems = findViewById(R.id.easySolvedProblems);
        easyTimer = findViewById(R.id.easyTimer);
        easyQuestion = findViewById(R.id.easyQuestion);
        easyImage = findViewById(R.id.easyImage);
        easyInput = findViewById(R.id.easyInput);
        easySubmit = findViewById(R.id.easySubmit);
        mediumLayout = findViewById(R.id.mediumLayout);
        mediumExit = findViewById(R.id.mediumExit);
        mediumSolvedProblems = findViewById(R.id.mediumSolvedProblems);
        mediumTimer = findViewById(R.id.mediumTimer);
        mediumQuestion = findViewById(R.id.mediumQuestion);
        mediumImage = findViewById(R.id.mediumImage);
        mediumInput = findViewById(R.id.mediumInput);
        mediumSubmit = findViewById(R.id.mediumSubmit);
        hardLayout = findViewById(R.id.hardLayout);
        hardExit = findViewById(R.id.hardExit);
        hardSolvedProblems = findViewById(R.id.hardSolvedProblems);
        hardTimer = findViewById(R.id.hardTimer);
        hardQuestion = findViewById(R.id.hardQuestion);
        hardImage = findViewById(R.id.hardImage);
        hardInput = findViewById(R.id.hardInput);
        hardSubmit = findViewById(R.id.hardSubmit);
        scoreLayout = findViewById(R.id.scoreLayout);
        scoreHeader = findViewById(R.id.scoreHeader);
        scoreTextView = findViewById(R.id.scoreTextView);
        scoreTryAgain = findViewById(R.id.scoreTryAgain);
        scoreExit = findViewById(R.id.scoreExit);

        buttonSound = MediaPlayer.create(PuzzlesActivity.this, R.raw.button_sound);
        puzzleBgMusic = MediaPlayer.create(PuzzlesActivity.this, R.raw.puzzle_music);
        puzzleBgMusic.setLooping(true);
        puzzleBgMusic.start();

        dbHandler = new DBHandler(this);
/**
        cursor = dbHandler.getGameScore();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int testType = cursor.getInt(0);
                if (testType == 1) {
                    easySolved.setText("Solved " + cursor.getString(1) + " / 10");
                }
            }
        }
 */

        puzzlesExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        puzzleMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 buttonSound.start();
                if (musicPlaying) {
                    puzzleMusic.setImageResource(R.drawable.baseline_volume_off_24);
                    musicPlaying = false;
                    puzzleBgMusic.pause();
                } else {
                    musicPlaying = true;
                    puzzleMusic.setImageResource(R.drawable.baseline_volume_up_24);
                    puzzleBgMusic.start();
                }
            }
        });

        puzzleInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                showDialog();
            }
        });

        easyPuzzles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                homeLayout.setVisibility(View.GONE);
                easyLayout.setVisibility(View.VISIBLE);
                
                isGameOngoing = true;
                easySelected = true;
                mediumSelected = false;
                hardSelected = false;
                solved = 0;
                currentProblemIndex = 0;

                easyNextQuestion();
            }
        });

        easyExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                onBackPressed();
            }
        });

        easySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                String easyEnteredAnswer = easyInput.getText().toString();
                isGameOngoing = false;

                if (easyEnteredAnswer.equals(easyAnswer)) {
                    solved++;
                    showCorrectDialog();
                } else {
                    showWrongDialog();
                }

                countDownTimer.cancel();
            }
        });

        mediumPuzzles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                homeLayout.setVisibility(View.GONE);
                mediumLayout.setVisibility(View.VISIBLE);

                isGameOngoing = true;
                mediumSelected = true;
                easySelected = false;
                hardSelected = false;
                solved = 0;
                currentProblemIndex = 0;

                mediumNextQuestion();
            }
        });

        mediumExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                onBackPressed();
            }
        });

        mediumSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                String mediumEnteredAnswer = mediumInput.getText().toString();
                isGameOngoing = false;

                if (mediumEnteredAnswer.equals(mediumAnswer)) {
                    solved++;
                    showCorrectDialog();
                } else {
                    showWrongDialog();
                }

                countDownTimer.cancel();
            }
        });

        hardPuzzles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                homeLayout.setVisibility(View.GONE);
                hardLayout.setVisibility(View.VISIBLE);

                isGameOngoing = true;
                easySelected = false;
                mediumSelected = false;
                hardSelected = true;
                solved = 0;
                currentProblemIndex = 0;

                hardNextQuestion();
            }
        });

        hardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                onBackPressed();
            }
        });

        hardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                String hardEnteredAnswer = hardInput.getText().toString();
                isGameOngoing = false;

                if (hardEnteredAnswer.equals(hardAnswer)) {
                    solved++;
                    showCorrectDialog();
                } else {
                    showWrongDialog();
                }

                countDownTimer.cancel();
            }
        });

        scoreTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                scoreLayout.setVisibility(View.GONE);
                if (easySelected) {
                    easyLayout.setVisibility(View.VISIBLE);
                    isGameOngoing = true;
                    easySelected = true;
                    mediumSelected = false;
                    hardSelected = false;
                    solved = 0;
                    currentProblemIndex = 0;
                    easyInput.setText("");
                    easyNextQuestion();
                } else if (mediumSelected) {
                    mediumLayout.setVisibility(View.VISIBLE);
                    isGameOngoing = true;
                    easySelected = false;
                    mediumSelected = true;
                    hardSelected = false;
                    solved = 0;
                    currentProblemIndex = 0;
                    mediumInput.setText("");
                    mediumNextQuestion();
                } else if (hardSelected) {
                    hardLayout.setVisibility(View.VISIBLE);
                    isGameOngoing = true;
                    easySelected = false;
                    mediumSelected = false;
                    hardSelected = true;
                    solved = 0;
                    currentProblemIndex = 0;
                    hardInput.setText("");
                    hardNextQuestion();
                }
            }
        });

        scoreExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                homeLayout.setVisibility(View.VISIBLE);
                easyLayout.setVisibility(View.GONE);
                mediumLayout.setVisibility(View.GONE);
                hardLayout.setVisibility(View.GONE);
                scoreLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (isGameOngoing) {
            showExitDialog();
        } else {
            if (puzzleBgMusic != null) {
                puzzleBgMusic.stop();
                puzzleBgMusic.release();
                puzzleBgMusic = null;
            }
            if (buttonSound != null) {
                buttonSound.release();
                buttonSound = null;
            }
            if (correctSound != null) {
                correctSound.release();
                correctSound = null;
            }
            if (wrongSound != null) {
                wrongSound.release();
                wrongSound = null;
            }
            if (timerSound != null) {
                timerSound.release();
                timerSound = null;
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timerSound != null) {
            timerSound.release();
            timerSound = null;
        }
    }

    private void showExitDialog() {
        View view = getLayoutInflater().inflate(R.layout.custom_exit_dialog, null);
        Button yesButton = view.findViewById(R.id.yesButton);
        Button noButton = view.findViewById(R.id.noButton);

        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setView(view).setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        countDownTimer.cancel();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                homeLayout.setVisibility(View.VISIBLE);
                shownProblemIndices.clear();
                isGameOngoing = false;

                if (easySelected) {
                    easyLayout.setVisibility(View.GONE);
                    easyInput.setText("");
                    easySelected = false;
                } else if (mediumSelected) {
                    mediumLayout.setVisibility(View.GONE);
                    mediumInput.setText("");
                    mediumSelected = false;
                } else if (hardSelected) {
                    hardLayout.setVisibility(View.GONE);
                    hardInput.setText("");
                    hardSelected = false;
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                if (isGameOngoing) {
                    startCountDown();
                } else {
                    isGameOngoing = true;
                    currentProblemIndex++;

                    if (easySelected) {
                        if (currentProblemIndex < easyProblemLength) {
                            easyNextQuestion();
                            easySolvedProblems.setText("Solved: " + solved + "/" + easyProblemLength);
                            easyInput.setText("");
                        } else {
                            countDownTimer.cancel();
                            showScore();
                        }
                    } else if (mediumSelected) {
                        if (currentProblemIndex < mediumProblemLength) {
                            mediumNextQuestion();
                            mediumSolvedProblems.setText("Solved: " + solved + "/" + mediumProblemLength);
                            mediumInput.setText("");
                        } else {
                            countDownTimer.cancel();
                            showScore();
                        }
                    } else if (hardSelected) {
                        if (currentProblemIndex < hardProblemLength) {
                            hardNextQuestion();
                            hardSolvedProblems.setText("Solved: " + solved + "/" + hardProblemLength);
                            hardInput.setText("");
                        } else {
                            countDownTimer.cancel();
                            showScore();
                        }
                    }
                }
            }
        });
    }

    private void easyNextQuestion() {
        do {
            randomIndex = random.nextInt(easyProblemLength);
        } while (shownProblemIndices.contains(randomIndex));

        shownProblemIndices.add(randomIndex);

        if (shownProblemIndices.size() == easyProblemLength) {
            shownProblemIndices.clear();
        }

        easySolvedProblems.setText("Solved: " + solved + "/" + easyProblemLength);
        
        easyQuestion.setText(easyProblems.getProblem(randomIndex));
        easyImage.setImageResource(getResources().getIdentifier(easyProblems.getImageName(randomIndex), "drawable", getPackageName()));
        easyAnswer = easyProblems.getCorrectAnswer(randomIndex);

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    private void mediumNextQuestion() {
        do {
            randomIndex = random.nextInt(mediumProblemLength);
        } while (shownProblemIndices.contains(randomIndex));

        shownProblemIndices.add(randomIndex);

        if (shownProblemIndices.size() == mediumProblemLength) {
            shownProblemIndices.clear();
        }

        mediumSolvedProblems.setText("Solved: " + solved + "/" + mediumProblemLength);

        mediumQuestion.setText(mediumProblems.getProblem(randomIndex));
        mediumImage.setImageResource(getResources().getIdentifier(mediumProblems.getImageName(randomIndex), "drawable", getPackageName()));
        mediumAnswer = mediumProblems.getCorrectAnswer(randomIndex);

        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
    }

    private void hardNextQuestion() {
        do {
            randomIndex = random.nextInt(hardProblemLength);
        } while (shownProblemIndices.contains(randomIndex));

        shownProblemIndices.add(randomIndex);

        if (shownProblemIndices.size() == hardProblemLength) {
            shownProblemIndices.clear();
        }

        hardSolvedProblems.setText("Solved: " + solved + "/" + hardProblemLength);

        hardQuestion.setText(hardProblems.getProblem(randomIndex));
        hardImage.setImageResource(getResources().getIdentifier(hardProblems.getImageName(randomIndex), "drawable", getPackageName()));
        hardAnswer = hardProblems.getCorrectAnswer(randomIndex);

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
                showWrongDialog();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis/1000) /60;
        int seconds = (int) (timeLeftInMillis/1000) %60;

        String timerFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        easyTimer.setText(timerFormatted);
        mediumTimer.setText(timerFormatted);
        hardTimer.setText(timerFormatted);

        if (timeLeftInMillis < 11000) {
            easyTimer.setTextColor(Color.RED);
            mediumTimer.setTextColor(Color.RED);
            hardTimer.setTextColor(Color.RED);

            if (timerSound == null) {
                timerSound = MediaPlayer.create(PuzzlesActivity.this, R.raw.timer_sound);
                timerSound.start();
            }
        } else if (timeLeftInMillis == 0000) {
            timerSound.stop();
        }else {
            easyTimer.setTextColor(Color.WHITE);
            mediumTimer.setTextColor(Color.WHITE);
            hardTimer.setTextColor(Color.WHITE);
        }
        if (timeLeftInMillis == 0) {
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showCorrectDialog() {
        View view = getLayoutInflater().inflate(R.layout.custom_response, null);
        LottieAnimationView happyEmoji = view.findViewById(R.id.responseImage);
        TextView correctText = view.findViewById(R.id.responseText);
        Button tryAgainButton = view.findViewById(R.id.tryAgainButton);
        Button nextButton = view.findViewById(R.id.nextButton);
        Button exitButton = view.findViewById(R.id.exitButton);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        happyEmoji.setAnimation(R.raw.happy_emoji);
        correctText.setText("CORRECT!");
        correctText.setTextColor(getResources().getColor(R.color.correct));
        tryAgainButton.setVisibility(View.GONE);

        correctSound = MediaPlayer.create(PuzzlesActivity.this, R.raw.correct_sound);
        correctSound.start();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                isGameOngoing = true;
                currentProblemIndex++;

                if (easySelected) {
                    if (currentProblemIndex < easyProblemLength) {
                        easyNextQuestion();
                        easySolvedProblems.setText("Solved: " + solved + "/" + easyProblemLength);
                        easyInput.setText("");
                    } else {
                        countDownTimer.cancel();
                        showScore();
                    }
                } else if (mediumSelected) {
                    if (currentProblemIndex < mediumProblemLength) {
                        mediumNextQuestion();
                        mediumSolvedProblems.setText("Solved: " + solved + "/" + mediumProblemLength);
                        mediumInput.setText("");
                    } else {
                        countDownTimer.cancel();
                        showScore();
                    }
                } else if (hardSelected) {
                    if (currentProblemIndex < hardProblemLength) {
                        hardNextQuestion();
                        hardSolvedProblems.setText("Solved: " + solved + "/" + hardProblemLength);
                        hardInput.setText("");
                    } else {
                        countDownTimer.cancel();
                        showScore();
                    }
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                showExitDialog();
            }
        });
    }

    private void showWrongDialog() {
        View view = getLayoutInflater().inflate(R.layout.custom_response, null);
        LottieAnimationView sadEmoji = view.findViewById(R.id.responseImage);
        TextView wrongText = view.findViewById(R.id.responseText);
        Button tryAgainButton = view.findViewById(R.id.tryAgainButton);
        Button nextButton = view.findViewById(R.id.nextButton);
        Button exitButton = view.findViewById(R.id.exitButton);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        sadEmoji.setAnimation(R.raw.sad_emoji);
        wrongText.setText("WRONG!");
        wrongText.setTextColor(getResources().getColor(R.color.wrong));
        tryAgainButton.setVisibility(View.VISIBLE);

        wrongSound = MediaPlayer.create(PuzzlesActivity.this, R.raw.wrong_sound);
        wrongSound.start();

        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                isGameOngoing = true;
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                startCountDown();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                isGameOngoing = true;
                currentProblemIndex++;

                if (easySelected) {
                    if (currentProblemIndex < easyProblemLength) {
                        easyNextQuestion();
                        easySolvedProblems.setText("Solved: " + solved + "/" + easyProblemLength);
                        easyInput.setText("");
                    } else {
                        countDownTimer.cancel();
                        showScore();
                    }
                } else if (mediumSelected) {
                    if (currentProblemIndex < mediumProblemLength) {
                        mediumNextQuestion();
                        mediumSolvedProblems.setText("Solved: " + solved + "/" + mediumProblemLength);
                        mediumInput.setText("");
                    } else {
                        countDownTimer.cancel();
                        showScore();
                    }
                } else if (hardSelected) {
                    if (currentProblemIndex < hardProblemLength) {
                        hardNextQuestion();
                        hardSolvedProblems.setText("Solved: " + solved + "/" + hardProblemLength);
                        hardInput.setText("");
                    } else {
                        countDownTimer.cancel();
                        showScore();
                    }
                }
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSound.start();
                alertDialog.dismiss();
                showExitDialog();
            }
        });
    }

    private void showScore() {
        easyLayout.setVisibility(View.GONE);
        mediumLayout.setVisibility(View.GONE);
        hardLayout.setVisibility(View.GONE);
        scoreLayout.setVisibility(View.VISIBLE);
        isGameOngoing = false;
        if (easySelected) {
            scoreHeader.setText("Easy Level Completed!");
            scoreTextView.setText("Your score is " + solved + " out of " + easyProblemLength);
        } else if (mediumSelected) {
            scoreHeader.setText("Medium Level Completed!");
            scoreTextView.setText("Your score is " + solved + " out of " + mediumProblemLength);
        } else if (hardSelected) {
            scoreHeader.setText("Hard Level Completed!");
            scoreTextView.setText("Your score is " + solved + " out of " + hardProblemLength);
        }
    }

    private void showDialog() {
        String mechanics = getResources().getString(R.string.puzzle_mechanics);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Probability Puzzle Game Mechanics");
        builder.setMessage(mechanics);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}