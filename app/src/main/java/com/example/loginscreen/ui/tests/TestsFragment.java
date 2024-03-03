package com.example.loginscreen.ui.tests;

import static com.example.loginscreen.DBHandler.SCORE;
import static com.example.loginscreen.DBHandler.TIME;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.example.loginscreen.ui.tests.topicsix.TopicSixPostTestActivity;
import com.example.loginscreen.ui.tests.topicsix.TopicSixPreTestActivity;
import com.example.loginscreen.ui.tests.topicthree.TopicThreePostTestActivity;
import com.example.loginscreen.ui.tests.topicthree.TopicThreePreTestActivity;
import com.example.loginscreen.ui.tests.topictwo.TopicTwoPostTestActivity;
import com.example.loginscreen.ui.tests.topictwo.TopicTwoPreTestActivity;

import java.util.Locale;
import java.util.Map;

public class TestsFragment extends Fragment {

    private CardView topicOnePreTest, topicOnePostTest, topicTwoPreTest, topicTwoPostTest, topicThreePreTest, topicThreePostTest, topicFourPreTest, topicFourPostTest, topicFivePreTest, topicFivePostTest, topicSixPreTest, topicSixPostTest, summativeTest;
    private TextView topicOnePreTestScore, topicOnePreTestTime, topicOnePostTestScore, topicOnePostTestTime, topicTwoPreTestScore, topicTwoPreTestTime, topicTwoPostTestScore, topicTwoPostTestTime, topicThreePreTestScore, topicThreePreTestTime, topicThreePostTestScore, topicThreePostTestTime, topicFourPreTestScore, topicFourPreTestTime, topicFourPostTestScore, topicFourPostTestTime, topicFivePreTestScore, topicFivePreTestTime, topicFivePostTestScore, topicFivePostTestTime, topicSixPreTestScore, topicSixPreTestTime, topicSixPostTestScore, topicSixPostTestTime, summativeTestScore, summativeTestTime, tutorialText2;
    private int testCode11, testCode12, testCode21, testCode22, testCode31, testCode32, testCode41, testCode42, testCode51, testCode52, testCode61, testCode62;
    private Map<String, String> scoreAndTime11, scoreAndTime12, scoreAndTime21, scoreAndTime22, scoreAndTime31, scoreAndTime32, scoreAndTime41, scoreAndTime42, scoreAndTime51, scoreAndTime52, scoreAndTime61, scoreAndTime62;
    private ScrollView scrollView2;
    private Button tutorialTest, tutorialCancel2;
    private View maskView2;
    private RelativeLayout tutorialContent2;
    private ImageView tutorialImage2;
    private MediaPlayer mediaPlayer;
    private Runnable runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7;
    private Handler handler = new Handler();;
    DBHandler dbHandler;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_tests, container, false);

        dbHandler = new DBHandler(requireContext());

        topicOnePreTest = root.findViewById(R.id.topicOnePreTest);
        topicTwoPreTest = root.findViewById(R.id.topicTwoPreTest);
        topicThreePreTest = root.findViewById(R.id.topicThreePreTest);
        topicFourPreTest = root.findViewById(R.id.topicFourPreTest);
        topicFivePreTest = root.findViewById(R.id.topicFivePreTest);
        topicSixPreTest = root.findViewById(R.id.topicSixPreTest);

        topicOnePostTest = root.findViewById(R.id.topicOnePostTest);
        topicTwoPostTest = root.findViewById(R.id.topicTwoPostTest);
        topicThreePostTest = root.findViewById(R.id.topicThreePostTest);
        topicFourPostTest = root.findViewById(R.id.topicFourPostTest);
        topicFivePostTest = root.findViewById(R.id.topicFivePostTest);
        topicSixPostTest = root.findViewById(R.id.topicSixPostTest);
        summativeTest = root.findViewById(R.id.summativeTest);

        topicOnePreTestScore = root.findViewById(R.id.topicOnePreTestScore);
        topicTwoPreTestScore = root.findViewById(R.id.topicTwoPreTestScore);
        topicThreePreTestScore = root.findViewById(R.id.topicThreePreTestScore);
        topicFourPreTestScore = root.findViewById(R.id.topicFourPreTestScore);
        topicFivePreTestScore = root.findViewById(R.id.topicFivePreTestScore);
        topicSixPreTestScore = root.findViewById(R.id.topicSixPreTestScore);

        topicOnePostTestScore = root.findViewById(R.id.topicOnePostTestScore);
        topicTwoPostTestScore = root.findViewById(R.id.topicTwoPostTestScore);
        topicThreePostTestScore = root.findViewById(R.id.topicThreePostTestScore);
        topicFourPostTestScore = root.findViewById(R.id.topicFourPostTestScore);
        topicFivePostTestScore = root.findViewById(R.id.topicFivePostTestScore);
        topicSixPostTestScore = root.findViewById(R.id.topicSixPostTestScore);
        summativeTestScore = root.findViewById(R.id.summativeTestScore);

        topicOnePreTestTime = root.findViewById(R.id.topicOnePreTestTime);
        topicTwoPreTestTime = root.findViewById(R.id.topicTwoPreTestTime);
        topicThreePreTestTime = root.findViewById(R.id.topicThreePreTestTime);
        topicFourPreTestTime = root.findViewById(R.id.topicFourPreTestTime);
        topicFivePreTestTime = root.findViewById(R.id.topicFivePreTestTime);
        topicSixPreTestTime = root.findViewById(R.id.topicSixPreTestTime);

        topicOnePostTestTime = root.findViewById(R.id.topicOnePostTestTime);
        topicTwoPostTestTime = root.findViewById(R.id.topicTwoPostTestTime);
        topicThreePostTestTime = root.findViewById(R.id.topicThreePostTestTime);
        topicFourPostTestTime = root.findViewById(R.id.topicFourPostTestTime);
        topicFivePostTestTime = root.findViewById(R.id.topicFivePostTestTime);
        topicSixPostTestTime = root.findViewById(R.id.topicSixPostTestTime);
        summativeTestTime = root.findViewById(R.id.summativeTestTime);

        tutorialTest = root.findViewById(R.id.tutorialTest);
        tutorialCancel2 = root.findViewById(R.id.tutorialCancel2);
        tutorialText2 = root.findViewById(R.id.tutorialText2);
        scrollView2 = root.findViewById(R.id.scrollView2);
        maskView2 = root.findViewById(R.id.maskView2);
        tutorialContent2 = root.findViewById(R.id.tutorialContent2);
        tutorialImage2 = root.findViewById(R.id.tutorialImage2);


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

        topicFivePreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicFivePreTestActivity.class);
                startActivity(intent);
            }
        });

        topicSixPreTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicSixPreTestActivity.class);
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

        topicFivePostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicFivePostTestActivity.class);
                startActivity(intent);
            }
        });

        topicSixPostTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), TopicSixPostTestActivity.class);
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

        scoreAndTime11 = dbHandler.getScoreTime(4276);
        scoreAndTime12 = dbHandler.getScoreTime(6856);
        scoreAndTime21 = dbHandler.getScoreTime(8603);
        scoreAndTime22 = dbHandler.getScoreTime(8970);
        scoreAndTime31 = dbHandler.getScoreTime(7137);
        scoreAndTime32 = dbHandler.getScoreTime(7436);
        scoreAndTime41 = dbHandler.getScoreTime(1067);
        scoreAndTime42 = dbHandler.getScoreTime(3186);
        scoreAndTime51 = dbHandler.getScoreTime(9788);
        scoreAndTime52 = dbHandler.getScoreTime(7260);
        scoreAndTime61 = dbHandler.getScoreTime(3552);
        scoreAndTime62 = dbHandler.getScoreTime(9002);

        if (!scoreAndTime11.isEmpty()) {
            topicOnePreTestScore.setText("Score: " + scoreAndTime11.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime11.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicOnePreTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime12.isEmpty()) {
            topicOnePostTestScore.setText("Score: " + scoreAndTime12.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime12.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicOnePostTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime21.isEmpty()) {
            topicTwoPreTestScore.setText("Score: " + scoreAndTime21.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime21.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicTwoPreTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime22.isEmpty()) {
            topicTwoPostTestScore.setText("Score: " + scoreAndTime22.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime22.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicTwoPostTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime31.isEmpty()) {
            topicThreePreTestScore.setText("Score: " + scoreAndTime31.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime31.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicThreePreTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime32.isEmpty()) {
            topicThreePostTestScore.setText("Score: " + scoreAndTime32.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime32.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicThreePostTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime41.isEmpty()) {
            topicFourPreTestScore.setText("Score: " + scoreAndTime41.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime41.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicFourPreTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime42.isEmpty()) {
            topicFourPostTestScore.setText("Score: " + scoreAndTime42.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime42.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicFourPostTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime51.isEmpty()) {
            topicFivePreTestScore.setText("Score: " + scoreAndTime51.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime51.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicFivePreTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime52.isEmpty()) {
            topicFivePostTestScore.setText("Score: " + scoreAndTime52.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime52.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicFivePostTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime61.isEmpty()) {
            topicSixPreTestScore.setText("Score: " + scoreAndTime61.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime61.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicSixPreTestTime.setText("Time: " + totalTime);
        }
        if (!scoreAndTime62.isEmpty()) {
            topicSixPostTestScore.setText("Score: " + scoreAndTime62.get(SCORE));
            int totalSeconds = Integer.parseInt(scoreAndTime62.get(TIME));
            int min = (totalSeconds % 3600) / 60;
            int secs = totalSeconds % 60;
            String totalTime = String.format(Locale.getDefault(), "%02d:%02d", min, secs);
            topicSixPostTestTime.setText("Time: " + totalTime);
        }

        CardView[] cardViews = {topicOnePreTest, topicOnePostTest, topicTwoPreTest, topicTwoPostTest, topicThreePreTest, topicThreePostTest, topicFourPreTest, topicFourPostTest, topicFivePreTest, topicFivePostTest, topicSixPreTest, topicSixPostTest, summativeTest};

        tutorialTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right);
                Animation slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
                tutorialContent2.setVisibility(View.VISIBLE);
                maskView2.setVisibility(View.VISIBLE);
                tutorialText2.setAnimation(slideRight);
                tutorialImage2.setAnimation(slideUp);

                tutorialText2.setText("Now, let's talk about the Tests feature, where you'll put your newfound knowledge to the test!");
                tutorialImage2.setImageResource(R.drawable.teacher5);
                for (int i = 0; i < cardViews.length; i++) {
                    cardViews[i].setEnabled(false);
                }
                tutorialTest.setEnabled(false);

                slideUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tutorial_tests);
                        mediaPlayer.start();
                        handler.postDelayed(runnable1, 5500);
                        handler.postDelayed(runnable2, 13500);
                        handler.postDelayed(runnable3, 19500);
                        handler.postDelayed(runnable4, 22000);
                        handler.postDelayed(runnable5, 25800);
                        handler.postDelayed(runnable6, 33800);
                        handler.postDelayed(runnable7, 37000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        runnable1 = new Runnable() {
            @Override
            public void run() {
                tutorialText2.setText("Here, you'll find both pre-tests and post-tests for each topic, each consisting of 10 items.");
                tutorialImage2.setImageResource(R.drawable.teacher7);
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                tutorialText2.setText("These tests are designed to assess your understanding and reinforce your learning.");
            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {
                tutorialText2.setText("Remember, before you can access a test, you'll need to enter a passcode provided by your teacher.");
                tutorialImage2.setImageResource(R.drawable.teacher6);
            }
        };

        runnable4 = new Runnable() {
            @Override
            public void run() {

            }
        };

        runnable5 = new Runnable() {
            @Override
            public void run() {
                tutorialText2.setText("Once you've completed the test, your score and the time taken will be displayed, allowing you to track your progress.");
            }
        };

        runnable6 = new Runnable() {
            @Override
            public void run() {
                tutorialText2.setText("Now, it's time to put your skills to the test! Good luck, and may the odds be ever in your favor.");
                tutorialImage2.setImageResource(R.drawable.teacher2);
            }
        };

        runnable7 = new Runnable() {
            @Override
            public void run() {
                tutorialImage2.setImageResource(R.drawable.teacher4);
            }
        };

        tutorialCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialContent2.setVisibility(View.GONE);
                maskView2.setVisibility(View.GONE);
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                handler.removeCallbacks(runnable1);
                handler.removeCallbacks(runnable2);
                handler.removeCallbacks(runnable3);
                handler.removeCallbacks(runnable4);
                handler.removeCallbacks(runnable5);
                handler.removeCallbacks(runnable6);
                handler.removeCallbacks(runnable7);
                for (int i = 0; i < cardViews.length; i++) {
                    cardViews[i].setEnabled(true);
                }
                tutorialTest.setEnabled(true);
            }
        });

        return root;
    }
}