package com.example.loginscreen.ui.tests;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.loginscreen.R;
import com.example.loginscreen.DBHandler;
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

public class TestsFragment extends Fragment {

    private CardView topicOnePreTest, topicOnePostTest, topicTwoPreTest, topicTwoPostTest, topicThreePreTest, topicThreePostTest, topicFourPreTest, topicFourPostTest, topicFivePreTest, topicFivePostTest, summativeTest;
    private TextView topicOnePreTestScore, topicOnePreTestTime, topicOnePostTestScore, topicOnePostTestTime, topicTwoPreTestScore, topicTwoPreTestTime, topicTwoPostTestScore, topicTwoPostTestTime, topicThreePreTestScore, topicThreePreTestTime, topicThreePostTestScore, topicThreePostTestTime, topicFourPreTestScore, topicFourPreTestTime, topicFourPostTestScore, topicFourPostTestTime, topicFivePreTestScore, topicFivePreTestTime, topicFivePostTestScore, topicFivePostTestTime, summativeTestScore, summativeTestTime;
    private Cursor cursor;
    DBHandler dbHandler;

    private int userInput;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tests, container, false);

        dbHandler = new DBHandler(requireContext());

        topicOnePreTest = root.findViewById(R.id.topicOnePreTest);
        topicTwoPreTest = root.findViewById(R.id.topicTwoPreTest);
        topicThreePreTest = root.findViewById(R.id.topicThreePreTest);
        topicFourPreTest = root.findViewById(R.id.topicFourPreTest);
        topicOnePostTest = root.findViewById(R.id.topicOnePostTest);
        topicTwoPostTest = root.findViewById(R.id.topicTwoPostTest);
        topicThreePostTest = root.findViewById(R.id.topicThreePostTest);
        topicFourPostTest = root.findViewById(R.id.topicFourPostTest);
        summativeTest = root.findViewById(R.id.summativeTest);

        topicOnePreTestScore = root.findViewById(R.id.topicOnePreTestScore);
        topicTwoPreTestScore = root.findViewById(R.id.topicTwoPreTestScore);
        topicThreePreTestScore = root.findViewById(R.id.topicThreePreTestScore);
        topicFourPreTestScore = root.findViewById(R.id.topicFourPreTestScore);
        topicOnePostTestScore = root.findViewById(R.id.topicOnePostTestScore);
        topicTwoPostTestScore = root.findViewById(R.id.topicTwoPostTestScore);
        topicThreePostTestScore = root.findViewById(R.id.topicThreePostTestScore);
        topicFourPostTestScore = root.findViewById(R.id.topicFourPostTestScore);
        summativeTestScore = root.findViewById(R.id.summativeTestScore);

        topicOnePreTestTime = root.findViewById(R.id.topicOnePreTestTime);
        topicTwoPreTestTime = root.findViewById(R.id.topicTwoPreTestTime);
        topicThreePreTestTime = root.findViewById(R.id.topicThreePreTestTime);
        topicFourPreTestTime = root.findViewById(R.id.topicFourPreTestTime);
        topicOnePostTestTime = root.findViewById(R.id.topicOnePostTestTime);
        topicTwoPostTestTime = root.findViewById(R.id.topicTwoPostTestTime);
        topicThreePostTestTime = root.findViewById(R.id.topicThreePostTestTime);
        topicFourPostTestTime = root.findViewById(R.id.topicFourPostTestTime);
        summativeTestTime = root.findViewById(R.id.summativeTestTime);

        topicOnePreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicOnePreTestActivity.class);
                startActivity(intent);
            }
        });

        topicTwoPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicTwoPreTestActivity.class);
                startActivity(intent);
            }
        });

        topicThreePreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicThreePreTestActivity.class);
                startActivity(intent);
            }
        });

        topicFourPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicFourPreTestActivity.class);
                startActivity(intent);
            }
        });

        topicOnePostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicOnePostTestActivity.class);
                startActivity(intent);
            }
        });

        topicTwoPostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicTwoPostTestActivity.class);
                startActivity(intent);
            }
        });

        topicThreePostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicThreePostTestActivity.class);
                startActivity(intent);
            }
        });

        topicFourPostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicFourPostTestActivity.class);
                startActivity(intent);
            }
        });

        summativeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), SummativeTestActivity.class);
                startActivity(intent);
            }
        });

        cursor = dbHandler.getScoreTime();
        while (cursor.moveToNext()) {
            int testType = cursor.getInt(0);
            if (testType == 1) {
                topicOnePreTestScore.setText("Score: " + cursor.getString(2));
                topicOnePreTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 2) {
                topicOnePostTestScore.setText("Score: " + cursor.getString(2));
                topicOnePostTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 3) {
                topicTwoPreTestScore.setText("Score: " + cursor.getString(2));
                topicTwoPreTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 4) {
                topicTwoPostTestScore.setText("Score: " + cursor.getString(2));
                topicTwoPostTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 5) {
                topicThreePreTestScore.setText("Score: " + cursor.getString(2));
                topicThreePreTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 6) {
                topicThreePostTestScore.setText("Score: " + cursor.getString(2));
                topicThreePostTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 7) {
                topicFourPreTestScore.setText("Score: " + cursor.getString(2));
                topicFourPreTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 8) {
                topicFourPostTestScore.setText("Score: " + cursor.getString(2));
                topicFourPostTestTime.setText("Time: " + cursor.getString(3));
            } else if (testType == 11) {
                summativeTestScore.setText("Score: " + cursor.getString(2));
                summativeTestTime.setText("Time: " + cursor.getString(3));
            }
        }

        return root;
    }
}