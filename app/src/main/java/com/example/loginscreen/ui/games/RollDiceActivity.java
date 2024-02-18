package com.example.loginscreen.ui.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.util.Random;

public class RollDiceActivity extends AppCompatActivity {
    private ImageView rollDiceInstruction, rollDiceExit, dice1, dice2;
    private TextView sumTextView, rollDiceHighScore, rollDiceScore;
    private Button rollDiceRoll, lessThan7, equalTo7, greaterThan7,rollDiceRestart;
    private int animationCounter, index, result, score, highScore;
    private static final int WIN_LESS_THAN_7 = 1;
    private static final int WIN_GREATER_THAN_7 = 2;
    private static final int WIN_EQUAL_TO_7 = 3;
    private static final int LOSE = 4;
    private Boolean checkGameId;
    private Cursor cursor;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        rollDiceInstruction = findViewById(R.id.rollDiceInstruction);
        rollDiceRoll = findViewById(R.id.rollDiceRoll);
        rollDiceRestart = findViewById(R.id.rollDiceRestart);
        rollDiceExit = findViewById(R.id.rollDiceExit);
        lessThan7 = findViewById(R.id.lessThan7);
        equalTo7 = findViewById(R.id.equalTo7);
        greaterThan7 = findViewById(R.id.greaterThan7);
        sumTextView = findViewById(R.id.sumTextView);
        rollDiceHighScore = findViewById(R.id.rollDiceHighScore);
        rollDiceScore = findViewById(R.id.rollDiceScore);
        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);

        animationCounter = 0;
        index = 0;
        result = 0;
        score = 0;

        dbHandler = new DBHandler(this);

        cursor = dbHandler.getGameScore();
        while (cursor.moveToNext()) {
            int gameType = cursor.getInt(0);
            if (gameType == 1) {
                rollDiceHighScore.setText("High Score: " + cursor.getString(1));
            }
        }

        rollDiceExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rollDiceInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        lessThan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 1;
                rollDiceRoll.setVisibility(View.VISIBLE);
                lessThan7.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                lessThan7.setTextColor(getResources().getColor(R.color.purple));
                equalTo7.setBackgroundResource(R.drawable.custom_option_button);
                equalTo7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                equalTo7.setTextColor(getResources().getColor(R.color.white));
                greaterThan7.setBackgroundResource(R.drawable.custom_option_button);
                greaterThan7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                greaterThan7.setTextColor(getResources().getColor(R.color.white));
            }
        });

        equalTo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 2;
                rollDiceRoll.setVisibility(View.VISIBLE);
                equalTo7.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                equalTo7.setTextColor(getResources().getColor(R.color.purple));
                lessThan7.setBackgroundResource(R.drawable.custom_option_button);
                lessThan7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                lessThan7.setTextColor(getResources().getColor(R.color.white));
                greaterThan7.setBackgroundResource(R.drawable.custom_option_button);
                greaterThan7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                greaterThan7.setTextColor(getResources().getColor(R.color.white));
            }
        });

        greaterThan7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 3;
                rollDiceRoll.setVisibility(View.VISIBLE);
                greaterThan7.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                greaterThan7.setTextColor(getResources().getColor(R.color.purple));
                lessThan7.setBackgroundResource(R.drawable.custom_option_button);
                lessThan7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                lessThan7.setTextColor(getResources().getColor(R.color.white));
                equalTo7.setBackgroundResource(R.drawable.custom_option_button);
                equalTo7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                equalTo7.setTextColor(getResources().getColor(R.color.white));
            }
        });

        rollDiceRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0) {
                    rollDice();
                    lessThan7.setEnabled(false);
                    equalTo7.setEnabled(false);
                    greaterThan7.setEnabled(false);
                    rollDiceRoll.setVisibility(View.INVISIBLE);
                    rollDiceRestart.setVisibility(View.VISIBLE);
                }
            }
        });

        rollDiceRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDiceRoll.setEnabled(true);
                lessThan7.setEnabled(true);
                equalTo7.setEnabled(true);
                greaterThan7.setEnabled(true);
                rollDiceRestart.setVisibility(View.INVISIBLE);
                sumTextView.setText("Sum:");

                lessThan7.setBackgroundResource(R.drawable.custom_option_button);
                lessThan7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                lessThan7.setTextColor(getResources().getColor(R.color.white));
                equalTo7.setBackgroundResource(R.drawable.custom_option_button);
                equalTo7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                equalTo7.setTextColor(getResources().getColor(R.color.white));
                greaterThan7.setBackgroundResource(R.drawable.custom_option_button);
                greaterThan7.setBackgroundTintList(getResources().getColorStateList(R.color.purple));
                greaterThan7.setTextColor(getResources().getColor(R.color.white));

            }
        });

    }

    private void rollDice() {
        final Animation anim = AnimationUtils.loadAnimation(RollDiceActivity.this, R.anim.roll_button);
        dice1.startAnimation(anim);
        dice2.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationCounter++;
                if (animationCounter == 1) {
                    rollAndSetDiceValues();
                    animationCounter = 0;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void rollAndSetDiceValues() {
        int value1 = randomDiceValue();
        int value2 = randomDiceValue();
        int res1 = getResources().getIdentifier("dice_" + value1, "drawable", "com.example.loginscreen");
        int res2 = getResources().getIdentifier("dice_" + value2, "drawable", "com.example.loginscreen");

        dice1.setImageResource(res1);
        dice2.setImageResource(res2);

        int sum = value1 + value2;
        sumTextView.setText("Sum: " + sum);

        if (index == 1 && sum < 7) {
            score += 2;
            result = WIN_LESS_THAN_7;
        } else if (index == 2 && sum == 7) {
            score += 4;
            result = WIN_EQUAL_TO_7;
        } else if (index == 3 && sum > 7) {
            score += 2;
            result = WIN_GREATER_THAN_7;
        } else {
            score -= 1;
            if (score < 1) {
                score = 0;
            } else {
                result = LOSE;
            }
        }

        rollDiceScore.setText("Score: "+score);

        checkGameId = dbHandler.checkGameId(1);
        cursor = dbHandler.getGameScore();

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int gameType = cursor.getInt(0);
                if (gameType == 1) {
                    highScore = Integer.parseInt(cursor.getString(1));
                    if (score > highScore) {
                        dbHandler.updateGameScore(new GameModelClass(1, score));
                        rollDiceHighScore.setText("High Score: " + score);
                        Toast.makeText(RollDiceActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            dbHandler.storeGameScore(new GameModelClass(1, score));
            rollDiceHighScore.setText("High Score: " + score);
            Toast.makeText(RollDiceActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private int randomDiceValue() {
        return new Random().nextInt(6) + 1;
    }

    private void showDialog() {
        String mechanics = getResources().getString(R.string.roll_dice_mechanics);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Roll Dice Game Mechanics");
        builder.setMessage(mechanics);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}