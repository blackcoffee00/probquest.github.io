package com.example.loginscreen.ui.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.Map;
import java.util.Random;

public class ColorRouletteActivity extends AppCompatActivity {
    private ImageView rouletteInstruction, rouletteExit, wheelImageView;
    private TextView rouletteHighScore, rouletteScore;
    private Button redButton, blueButton, yellowButton, greenButton, rouletteRestart, rouletteSpin;
    private int index, score, degree, result, highScore;
    private boolean isSpinning = false;
    private String[] sectors;
    private int[] sectorsDegrees;
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static final int YELLOW = 3;
    private static final int GREEN = 4;
    private static final int LOSE = 5;
    private static final String PREFS_FILE_NAME = "ColorRouletteScore";
    private static final String KEY_HIGH_SCORE = "g2HighScore";
    private MediaPlayer spinSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_roulette);

        rouletteInstruction = findViewById(R.id.rouletteInstruction);
        rouletteExit = findViewById(R.id.rouletteExit);
        wheelImageView = findViewById(R.id.wheelImageView);
        rouletteHighScore = findViewById(R.id.rouletteHighScore);
        rouletteScore = findViewById(R.id.rouletteScore);
        redButton = findViewById(R.id.redButton);
        blueButton = findViewById(R.id.blueButton);
        yellowButton = findViewById(R.id.yellowButton);
        greenButton = findViewById(R.id.greenButton);
        rouletteRestart = findViewById(R.id.rouletteRestart);
        rouletteSpin = findViewById(R.id.rouletteSpin);

        score = 0;

        loadHighScore();
        rouletteHighScore.setText("High Score: " + highScore);
        
        wheelGenerator();
        rouletteExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rouletteInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        rouletteSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSpinning) {
                    spinSound = MediaPlayer.create(ColorRouletteActivity.this, R.raw.spin_sound);
                    spinSound.start();
                    spinWheel();
                    isSpinning = true;
                    rouletteSpin.setVisibility(View.INVISIBLE);
                    redButton.setEnabled(false);
                    blueButton.setEnabled(false);
                    yellowButton.setEnabled(false);
                    greenButton.setEnabled(false);
                }
            }
        });

        rouletteRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wheelGenerator();
                resetButton();
            }
        });

        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                rouletteSpin.setVisibility(View.VISIBLE);
                redButton.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                redButton.setTextColor(getResources().getColor(R.color.blue));
                blueButton.setBackgroundResource(R.drawable.custom_option_button);
                blueButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                blueButton.setTextColor(getResources().getColor(R.color.white));
                yellowButton.setBackgroundResource(R.drawable.custom_option_button);
                yellowButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                yellowButton.setTextColor(getResources().getColor(R.color.white));
                greenButton.setBackgroundResource(R.drawable.custom_option_button);
                greenButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                greenButton.setTextColor(getResources().getColor(R.color.white));
            }
        });

        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                rouletteSpin.setVisibility(View.VISIBLE);
                blueButton.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                blueButton.setTextColor(getResources().getColor(R.color.blue));
                redButton.setBackgroundResource(R.drawable.custom_option_button);
                redButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                redButton.setTextColor(getResources().getColor(R.color.white));
                yellowButton.setBackgroundResource(R.drawable.custom_option_button);
                yellowButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                yellowButton.setTextColor(getResources().getColor(R.color.white));
                greenButton.setBackgroundResource(R.drawable.custom_option_button);
                greenButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                greenButton.setTextColor(getResources().getColor(R.color.white));
            }
        });

        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 3;
                rouletteSpin.setVisibility(View.VISIBLE);
                yellowButton.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                yellowButton.setTextColor(getResources().getColor(R.color.blue));
                blueButton.setBackgroundResource(R.drawable.custom_option_button);
                blueButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                blueButton.setTextColor(getResources().getColor(R.color.white));
                redButton.setBackgroundResource(R.drawable.custom_option_button);
                redButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                redButton.setTextColor(getResources().getColor(R.color.white));
                greenButton.setBackgroundResource(R.drawable.custom_option_button);
                greenButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                greenButton.setTextColor(getResources().getColor(R.color.white));
            }
        });

        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 4;
                rouletteSpin.setVisibility(View.VISIBLE);
                greenButton.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                greenButton.setTextColor(getResources().getColor(R.color.blue));
                blueButton.setBackgroundResource(R.drawable.custom_option_button);
                blueButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                blueButton.setTextColor(getResources().getColor(R.color.white));
                yellowButton.setBackgroundResource(R.drawable.custom_option_button);
                yellowButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                yellowButton.setTextColor(getResources().getColor(R.color.white));
                redButton.setBackgroundResource(R.drawable.custom_option_button);
                redButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
                redButton.setTextColor(getResources().getColor(R.color.white));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (spinSound != null) {
            spinSound.stop();
            spinSound.release();
            spinSound = null;
        }
        super.onBackPressed();
    }

    private void spinWheel() {
        degree = new Random().nextInt(sectors.length - 1);
        RotateAnimation rotateAnimation = new RotateAnimation(0, (360 * sectors.length) + sectorsDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(2500);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rouletteRestart.setVisibility(View.VISIBLE);
                isSpinning = false;
                points();
                if (spinSound != null) {
                    spinSound.stop();
                    spinSound.release();
                    spinSound = null;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheelImageView.startAnimation(rotateAnimation);
    }

    private void points() {
        String color = sectors[sectors.length - (degree + 1)];
        if (index == 1 && color == "red") {
            score += 2;
            result = RED;
        } else if (index == 2 && color == "blue") {
            score += 2;
            result = BLUE;
        } else if (index == 3 && color == "yellow") {
            score += 2;
            result = YELLOW;
        } else if (index == 4 && color == "green") {
            score += 2;
            result = GREEN;
        } else {
            score--;
            if (score < 1) {
                score = 0;
            } else {
                result = LOSE;
            }
        }

        rouletteScore.setText(String.valueOf("Score: " + score));
        updateHighScore(score);
    }

    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGH_SCORE, 0);
    }

    private void saveHighScore(int score) {
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGH_SCORE, score);
        editor.apply();
    }

    private void updateHighScore(int newScore) {
        if (newScore > highScore) {
            highScore = newScore;
            saveHighScore(highScore);
            rouletteHighScore.setText("High Score: " + highScore);
        }
    }

    private void wheelGenerator() {
        int value = randomWheel();
        int res = getResources().getIdentifier("wheel_" + value, "drawable", "com.example.loginscreen");
        wheelImageView.setImageResource(res);

        if (value == 1) {
            sectors = new String[]{"red", "yellow", "green", "green", "blue", "green", "blue", "green", "yellow", "blue", "blue", "yellow"};
        } else if (value == 2) {
            sectors = new String[]{"yellow", "yellow", "red", "blue", "yellow", "red", "blue", "green", "green", "blue", "red", "yellow"};
        } else if (value == 3) {
            sectors = new String[]{"blue", "red", "green", "yellow", "yellow", "blue", "green", "green", "red", "red", "red", "blue"};
        } else if (value == 4) {
            sectors = new String[]{"green", "blue", "yellow", "blue", "green", "yellow", "yellow", "red", "blue", "green", "red", "blue"};
        }

        sectorsDegrees = new int[sectors.length];
        getDegreesForSectors();
    }

    private void getDegreesForSectors(){
        int sectorDegree = 360/sectors.length;
        for (int i = 0; i < sectors.length; i++) {
            sectorsDegrees[i] = (i + 1) * sectorDegree;
        }
    }

    private int randomWheel() {
        return new Random().nextInt(4)+1;
    }

    private void resetButton() {
        rouletteRestart.setVisibility(View.INVISIBLE);
        rouletteSpin.setVisibility(View.INVISIBLE);
        redButton.setEnabled(true);
        blueButton.setEnabled(true);
        yellowButton.setEnabled(true);
        greenButton.setEnabled(true);
        redButton.setBackgroundResource(R.drawable.custom_option_button);
        redButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        redButton.setTextColor(getResources().getColor(R.color.white));
        blueButton.setBackgroundResource(R.drawable.custom_option_button);
        blueButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        blueButton.setTextColor(getResources().getColor(R.color.white));
        yellowButton.setBackgroundResource(R.drawable.custom_option_button);
        yellowButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        yellowButton.setTextColor(getResources().getColor(R.color.white));
        greenButton.setBackgroundResource(R.drawable.custom_option_button);
        greenButton.setBackgroundTintList(getResources().getColorStateList(R.color.blue));
        greenButton.setTextColor(getResources().getColor(R.color.white));
    }

    private void showDialog() {
        String mechanics = getResources().getString(R.string.color_roulette_mechanics);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Color Roulette Game Mechanics");
        builder.setMessage(mechanics);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}