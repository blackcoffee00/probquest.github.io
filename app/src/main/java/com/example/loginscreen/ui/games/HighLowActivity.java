package com.example.loginscreen.ui.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class HighLowActivity extends AppCompatActivity {
    private ImageView highLowExit, highLowInstruction, card1, card2, card3, card4, card5, card6, card7, card8, card9;
    private Button higher, lower;
    private TextView guessTextView;
    private int index, points, currentValue;
    private Set<String> usedCardCombinations = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_low);

        highLowExit = findViewById(R.id.highLowExit);
        highLowInstruction = findViewById(R.id.highLowInstruction);
        guessTextView = findViewById(R.id.guessTextView);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        card8 = findViewById(R.id.card8);
        card9 = findViewById(R.id.card9);
        higher = findViewById(R.id.higher);
        lower = findViewById(R.id.lower);

        index = 1;
        points = 0;
        currentValue = 0;
        
        firstCardReveal();

        highLowExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        highLowInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        higher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revealCardHi();
            }
        });

        lower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revealCardLo();
            }
        });
    }

    private void firstCardReveal() {
        int value = randomCardValue();
        String cardType = getRandomCardType();
        String cardTypeValue = cardType + "_" + value;
        usedCardCombinations.add(cardTypeValue);

        int res = getResources().getIdentifier(cardTypeValue, "drawable", "com.example.loginscreen");

        if (index == 1) {
            card1.setImageResource(res);
            currentValue = value;
            index = 2;
        }
    }

    private void revealCardHi() {
        int value;
        String cardType;
        String cardTypeValue;

        do {
            cardType = getRandomCardType();
            value = randomCardValue();
            cardTypeValue = cardType + "_" + value;
        } while (usedCardCombinations.contains(cardTypeValue));

        usedCardCombinations.add(cardTypeValue);

        int res = getResources().getIdentifier(cardTypeValue, "drawable", "com.example.loginscreen");

        setCardImageAtIndex(index, res);

        if (index >= 2 && currentValue < value) {
            points += 5;
            Toast.makeText(HighLowActivity.this, "You have " + points + " points", Toast.LENGTH_SHORT).show();
        } else if (index >= 2 && currentValue > value) {
            points -= 3;
            if (points < 1) {
                points = 0;
                higher.setEnabled(false);
                lower.setEnabled(false);
                Toast.makeText(HighLowActivity.this, "You have " + points + " points.\nGame Over!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HighLowActivity.this, "You guessed wrong. You've lost 3 points. Your current points is "+points, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(HighLowActivity.this, "It's a draw. You gained no points.", Toast.LENGTH_SHORT).show();
        }
        currentValue = value;
        index ++;

        if (index == 10) {
            Toast.makeText(HighLowActivity.this, "You've reached the last card", Toast.LENGTH_SHORT).show();
            higher.setEnabled(false);
            lower.setEnabled(false);
        }
    }

    private void revealCardLo() {
        int value;
        String cardType;
        String cardTypeValue;

        do {
            cardType = getRandomCardType();
            value = randomCardValue();
            cardTypeValue = cardType + "_" + value;
        } while (usedCardCombinations.contains(cardTypeValue));

        usedCardCombinations.add(cardTypeValue);

        int res = getResources().getIdentifier(cardTypeValue, "drawable", "com.example.loginscreen");

        setCardImageAtIndex(index, res);

        if (index >= 2 && currentValue > value) {
            points += 5;
            Toast.makeText(HighLowActivity.this, "You have " + points + " points", Toast.LENGTH_SHORT).show();
        } else if (index >= 2 && currentValue < value) {
            points -= 3;
            if (points < 1) {
                points = 0;
                higher.setEnabled(false);
                lower.setEnabled(false);
                Toast.makeText(HighLowActivity.this, "You have " + points + " points.\nGame Over!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HighLowActivity.this, "You guessed wrong. You've lost 3 points. Your current points is "+points, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(HighLowActivity.this, "It's a draw. You gained no points.", Toast.LENGTH_SHORT).show();
        }
        currentValue = value;
        index ++;

        if (index == 10) {
            Toast.makeText(HighLowActivity.this, "You've reached the last card", Toast.LENGTH_SHORT).show();
            higher.setEnabled(false);
            lower.setEnabled(false);
        }
    }

    private void setCardImageAtIndex(int index, int res) {
        switch (index) {
            case 2:
                card2.setImageResource(res);
                break;
            case 3:
                card3.setImageResource(res);
                break;
            case 4:
                card4.setImageResource(res);
                break;
            case 5:
                card5.setImageResource(res);
                break;
            case 6:
                card6.setImageResource(res);
                break;
            case 7:
                card7.setImageResource(res);
                break;
            case 8:
                card8.setImageResource(res);
                break;
            case 9:
                card9.setImageResource(res);
                break;
        }
    }

    private int randomCardValue() {
        return new Random().nextInt(13) + 1;
    }

    private String getRandomCardType() {
        String[] cardTypes = {"diamonds","clubs","hearts","spades"};
        return cardTypes[new Random().nextInt(cardTypes.length)];
    }

    private void showDialog() {
        String mechanics = getResources().getString(R.string.high_low_mechanics);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("High or Low Game Mechanics");
        builder.setMessage(mechanics);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}