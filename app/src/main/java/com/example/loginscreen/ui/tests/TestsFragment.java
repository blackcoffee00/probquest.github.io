package com.example.loginscreen.ui.tests;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;
import com.example.loginscreen.ui.tests.summative.SummativeTestActivity;
import com.example.loginscreen.ui.tests.topicfive.TopicFivePostTestActivity;
import com.example.loginscreen.ui.tests.topicfive.TopicFivePreTestActivity;
import com.example.loginscreen.ui.tests.topicfour.TopicFourPostTestActivity;
import com.example.loginscreen.ui.tests.topicfour.TopicFourPreTestActivity;
import com.example.loginscreen.ui.tests.topicone.TopicOnePostTestActivity;
import com.example.loginscreen.ui.tests.topicone.TopicOnePreTestActivity;
import com.example.loginscreen.ui.tests.topicthree.TopicThreePostTestActivity;
import com.example.loginscreen.ui.tests.topicthree.TopicThreePreTestActivity;
import com.example.loginscreen.ui.tests.topictwo.TopicTwoPostTestActivity;
import com.example.loginscreen.ui.tests.topictwo.TopicTwoPreTestActivity;

import java.util.List;
import java.util.Locale;

public class TestsFragment extends Fragment {

    private CardView topicOnePreTest, topicOnePostTest, topicTwoPreTest, topicTwoPostTest, topicThreePreTest, topicThreePostTest, topicFourPreTest, topicFourPostTest, topicFivePreTest, topicFivePostTest, summativeTest;
    private TextView topicOnePreTestScore, topicOnePreTestTime, topicOnePostTestScore, topicOnePostTestTime, topicTwoPreTestScore, topicTwoPreTestTime, topicTwoPostTestScore, topicTwoPostTestTime, topicThreePreTestScore, topicThreePreTestTime, topicThreePostTestScore, topicThreePostTestTime, topicFourPreTestScore, topicFourPreTestTime, topicFourPostTestScore, topicFourPostTestTime, topicFivePreTestScore, topicFivePreTestTime, topicFivePostTestScore, topicFivePostTestTime, summativeTestScore, summativeTestTime;
    private Cursor cursor;
    TestsDBHandler testsdbHandler;

    private int userInput;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tests, container, false);

        testsdbHandler = new TestsDBHandler(requireContext());

        topicOnePreTest = root.findViewById(R.id.topicOnePreTest);
        topicTwoPreTest = root.findViewById(R.id.topicTwoPreTest);
        topicThreePreTest = root.findViewById(R.id.topicThreePreTest);
        topicFourPreTest = root.findViewById(R.id.topicFourPreTest);
        topicFivePreTest = root.findViewById(R.id.topicFivePreTest);
        topicOnePostTest = root.findViewById(R.id.topicOnePostTest);
        topicTwoPostTest = root.findViewById(R.id.topicTwoPostTest);
        topicThreePostTest = root.findViewById(R.id.topicThreePostTest);
        topicFourPostTest = root.findViewById(R.id.topicFourPostTest);
        topicFivePostTest = root.findViewById(R.id.topicFivePostTest);
        summativeTest = root.findViewById(R.id.summativeTest);

        topicOnePreTestScore = root.findViewById(R.id.topicOnePreTestScore);
        topicTwoPreTestScore = root.findViewById(R.id.topicTwoPreTestScore);
        topicThreePreTestScore = root.findViewById(R.id.topicThreePreTestScore);
        topicFourPreTestScore = root.findViewById(R.id.topicFourPreTestScore);
        topicFivePreTestScore = root.findViewById(R.id.topicFivePreTestScore);
        topicOnePostTestScore = root.findViewById(R.id.topicOnePostTestScore);
        topicTwoPostTestScore = root.findViewById(R.id.topicTwoPostTestScore);
        topicThreePostTestScore = root.findViewById(R.id.topicThreePostTestScore);
        topicFourPostTestScore = root.findViewById(R.id.topicFourPostTestScore);
        topicFivePostTestScore = root.findViewById(R.id.topicFivePostTestScore);
        summativeTestScore = root.findViewById(R.id.summativeTestScore);

        topicOnePreTestTime = root.findViewById(R.id.topicOnePreTestTime);
        topicTwoPreTestTime = root.findViewById(R.id.topicTwoPreTestTime);
        topicThreePreTestTime = root.findViewById(R.id.topicThreePreTestTime);
        topicFourPreTestTime = root.findViewById(R.id.topicFourPreTestTime);
        topicFivePreTestTime = root.findViewById(R.id.topicFivePreTestTime);
        topicOnePostTestTime = root.findViewById(R.id.topicOnePostTestTime);
        topicTwoPostTestTime = root.findViewById(R.id.topicTwoPostTestTime);
        topicThreePostTestTime = root.findViewById(R.id.topicThreePostTestTime);
        topicFourPostTestTime = root.findViewById(R.id.topicFourPostTestTime);
        topicFivePostTestTime = root.findViewById(R.id.topicFivePostTestTime);
        summativeTestTime = root.findViewById(R.id.summativeTestTime);

        topicOnePreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog11();
            }
        });

        topicTwoPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog21();
            }
        });

        topicThreePreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog31();
            }
        });

        topicFourPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog41();
            }
        });

        topicFivePreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog51();
            }
        });

        topicOnePostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog12();
            }
        });

        topicTwoPostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog22();
            }
        });

        topicThreePostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog32();
            }
        });

        topicFourPostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog42();
            }
        });

        topicFivePostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog52();
            }
        });

        summativeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog6();
            }
        });

        cursor = testsdbHandler.getScoreTime();

        switch (cursor.getCount()) {
            case 1:
                if (cursor.moveToFirst()) {
                    topicOnePreTestScore.setText("Score: " + cursor.getString(1));
                    topicOnePreTestTime.setText("Time: " + cursor.getString(2));
                }
                break;
            case 2:
                if (cursor.moveToFirst()) {
                    topicOnePostTestScore.setText("Score: " + cursor.getString(1));
                    topicOnePostTestTime.setText("Time: " + cursor.getString(2));
                }
                break;
            case 3:
                if (cursor.moveToFirst()) {
                    topicTwoPreTestScore.setText("Score: " + cursor.getString(1));
                    topicTwoPreTestTime.setText("Time: " + cursor.getString(2));
                }
                break;
            case 4:
                if (cursor.moveToFirst()) {
                    topicTwoPostTestScore.setText("Score: " + cursor.getString(1));
                    topicTwoPostTestTime.setText("Time: " + cursor.getString(2));
                }
                break;
            default:
                // Handle other cases or provide a default behavior
                break;
        }

        /**
        if (cursor.getCount() == 1) {
            while (cursor.moveToNext()) {
                topicOnePreTestScore.setText("Score: " + cursor.getString(1));
                topicOnePreTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 2) {
            while (cursor.moveToNext()) {
                topicOnePostTestScore.setText("Score: " + cursor.getString(1));
                topicOnePostTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 3) {
            while (cursor.moveToNext()) {
                topicTwoPreTestScore.setText("Score: " + cursor.getString(1));
                topicTwoPreTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 4) {
            while (cursor.moveToNext()) {
                topicTwoPostTestScore.setText("Score: " + cursor.getString(1));
                topicTwoPostTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 5) {
            while (cursor.moveToNext()) {
                topicThreePreTestScore.setText("Score: " + cursor.getString(1));
                topicThreePreTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 6) {
            while (cursor.moveToNext()) {
                topicThreePostTestScore.setText("Score: " + cursor.getString(1));
                topicThreePostTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 7) {
            while (cursor.moveToNext()) {
                topicFourPreTestScore.setText("Score: " + cursor.getString(1));
                topicFourPreTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 8) {
            while (cursor.moveToNext()) {
                topicFourPostTestScore.setText("Score: " + cursor.getString(1));
                topicFourPostTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 9) {
            while (cursor.moveToNext()) {
                topicFivePreTestScore.setText("Score: " + cursor.getString(1));
                topicFourPreTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 10) {
            while (cursor.moveToNext()) {
                topicFivePostTestScore.setText("Score: " + cursor.getString(1));
                topicFivePostTestTime.setText("Time: " + cursor.getString(2));
            }
        } else if (cursor.getCount() == 11) {
            while (cursor.moveToNext()) {
                summativeTestScore.setText("Score: " + cursor.getString(1));
                summativeTestTime.setText("Time: " + cursor.getString(2));
            }
        }
        */

        return root;
    }

    private void showAlertDialog11() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);
        EditText editTextCode = view.findViewById(R.id.editTextCode);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);
                if (userInput == 4276) {
                    Intent intent = new Intent(requireContext(), TopicOnePreTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog12() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);
        EditText editTextCode = view.findViewById(R.id.editTextCode);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);
        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);
                if (userInput == 6856) {
                    Intent intent = new Intent(requireContext(), TopicOnePostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog21() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 8603) {
                    Intent intent = new Intent(requireContext(), TopicTwoPreTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog22() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 8970) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog31() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 7137) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog32() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 7436) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog41() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 0067) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog42() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 3186) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog51() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 9788) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog52() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 7260) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlertDialog6() {
        View view = getLayoutInflater().inflate(R.layout.custom_code_dialog, null);

        EditText editTextCode = view.findViewById(R.id.editTextCode);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String userInputString = editTextCode.getText().toString();
                userInput = userInputString.isEmpty() ? 0 : Integer.parseInt(userInputString);

                if (userInput == 3552) {
                    Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(requireContext(), "Incorrect passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}