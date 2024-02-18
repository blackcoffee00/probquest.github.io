package com.example.loginscreen.ui.games.puzzles;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;
import com.example.loginscreen.ui.games.GameModelClass;

public class EasyPuzzleActivity extends AppCompatActivity {
    private TextView easyQuestion, easySolution, puzzleCount;
    private EditText easyAnswer;
    private Button easySubmit;
    private ImageView easyPuzzleExit, easyPuzzleInstruction, easyBack, easyForward;
    private EasyProblems problem = new EasyProblems();
    private int problemLength = problem.problems.length;
    private int index, solved, addSolved;
    private String answer, solution;
    private Boolean checkGameId;
    private Cursor cursor;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_puzzle);

        easySubmit = findViewById(R.id.easySubmit);
        easyQuestion = findViewById(R.id.easyQuestion);
        easySolution = findViewById(R.id.easySolution);
        easyAnswer = findViewById(R.id.easyAnswer);
        puzzleCount = findViewById(R.id.puzzleCount);
        easyPuzzleExit = findViewById(R.id.easyPuzzleExit);
        easyPuzzleInstruction = findViewById(R.id.easyPuzzleInstruction);
        easyBack = findViewById(R.id.easyBack);
        easyForward = findViewById(R.id.easyForward);

        index = 0;
        solved = 0;
        NewQuestion();

        dbHandler = new DBHandler(this);

        easyBack.setVisibility(View.INVISIBLE);

        easyPuzzleExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EasyPuzzleActivity.this, PuzzlesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        easyPuzzleInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        easyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                NewQuestion();
                if (index == 0) {
                    easyBack.setVisibility(View.INVISIBLE);
                } else if (index < problemLength) {
                    easyForward.setVisibility(View.VISIBLE);
                }
                easyAnswer.setText("");
                easySolution.setText("");
            }
        });

        easyForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                NewQuestion();
                if (index > 0) {
                    easyBack.setVisibility(View.VISIBLE);
                } else if (index == problemLength-1) {
                    easyForward.setVisibility(View.INVISIBLE);
                }
                easyAnswer.setText("");
                easySolution.setText("");
            }
        });

        easySubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String easyEnteredAnswer = easyAnswer.getText().toString();

                if (easyEnteredAnswer.equals(answer)) {
                    solved++;
                    Toast.makeText(EasyPuzzleActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    easySolution.setText(solution);
                } else {
                    Toast.makeText(EasyPuzzleActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                }
/**
                checkGameId = dbHandler.checkGameId(1);
                cursor = dbHandler.getGameScore();

                if (cursor.getCount() == 0) {
                    dbHandler.storeGameScore(new GameModelClass(1, solved));
                } else {
                    cursor.moveToNext();
                    int testType = cursor.getInt(0);
                    if (testType == 1) {
                        addSolved = Integer.parseInt(cursor.getString(1));
                        if (checkGameId) {
                            if (solved > addSolved) {
                                dbHandler.updateGameScore(new GameModelClass(1, solved));
                            }
                        }
                    }
                }
 */
            }
        });
    }

    private void NewQuestion() {
        easyQuestion.setText(problem.getProblem(index));
        answer = problem.getCorrectAnswer(index);
        solution = problem.getSolution(index);
        puzzleCount.setText("Puzzle " + (index + 1));
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Puzzle Game Mechanics").setMessage("");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}