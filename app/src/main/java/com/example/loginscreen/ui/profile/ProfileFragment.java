package com.example.loginscreen.ui.profile;

import static com.example.loginscreen.DBHandler.SCORE;
import static com.example.loginscreen.DBHandler.TIME;
import static com.example.loginscreen.DBHandler.TIME_IN_SEC;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Locale;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private TextView profileFName, profileLName, profileSection, tutorialText4, tutProfileFName, tutProfileLName, tutProfileSection;
    private Button tutorialProfile, tutorialCancel4;
    private RelativeLayout tutorialContent4;
    private View maskView4;
    private Handler handler = new Handler();
    private Runnable runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7;
    private MediaPlayer mediaPlayer;
    private ImageView profileImage, tutorialImage4, tutProfileImage;
    private LinearLayout profileEditProfile, tutProfileEditProfile, tutDashboard;
    private CircularProgressBar studyProgressBar, testProgressBar;
    private TextView studyTimePercentage, testScorePercentage, totalStudyTimeValue, testsCompletedValue, totalTestsTimeValue;
    private int studyProgress, testProgress;
    private int timeInSec1, timeInSec2, timeInSec3, timeInSec4, timeInSec5, timeInSec6, score11, score12, score21, score22, score31, score32, score41, score42, score51, score52, score61, score62, time11, time12, time21, time22, time31, time32, time41, time42, time51, time52, time61, time62;
    private Map<String, String> studyTime1, studyTime2, studyTime3, studyTime4, studyTime5, studyTime6, testScore11, testScore12, testScore21, testScore22, testScore31, testScore32, testScore41, testScore42, testScore51, testScore52, testScore61, testScore62;
    private Boolean checkTestCode11, checkTestCode12, checkTestCode21, checkTestCode22, checkTestCode31, checkTestCode32, checkTestCode41, checkTestCode42, checkTestCode51, checkTestCode52, checkTestCode61, checkTestCode62;
    DBHandler dbHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileFName = root.findViewById(R.id.profileFName);
        profileLName = root.findViewById(R.id.profileLName);
        profileSection = root.findViewById(R.id.profileSection);
        profileImage = root.findViewById(R.id.profileImage);
        profileEditProfile = root.findViewById(R.id.profileEditProfile);
        studyProgressBar = root.findViewById(R.id.studyProgressBar);
        testProgressBar = root.findViewById(R.id.testProgressBar);
        studyTimePercentage = root.findViewById(R.id.studyTimePercentage);
        testScorePercentage = root.findViewById(R.id.testScorePercentage);
        totalStudyTimeValue = root.findViewById(R.id.totalStudyTimeValue);
        testsCompletedValue = root.findViewById(R.id.testsCompletedValue);
        totalTestsTimeValue = root.findViewById(R.id.totalTestsTimeValue);

        tutorialText4 = root.findViewById(R.id.tutorialText4);
        tutorialProfile = root.findViewById(R.id.tutorialProfile);
        tutorialCancel4 = root.findViewById(R.id.tutorialCancel4);
        tutorialContent4 = root.findViewById(R.id.tutorialContent4);
        maskView4 = root.findViewById(R.id.maskView4);
        tutorialImage4 = root.findViewById(R.id.tutorialImage4);

        tutProfileEditProfile = root.findViewById(R.id.tutProfileEditProfile);
        tutProfileFName = root.findViewById(R.id.tutProfileFName);
        tutProfileLName = root.findViewById(R.id.tutProfileLName);
        tutProfileSection = root.findViewById(R.id.tutProfileSection);
        tutProfileImage = root.findViewById(R.id.tutProfileImage);
        tutDashboard = root.findViewById(R.id.tutDashboard);

        dbHandler = new DBHandler(requireContext());
        Cursor cursor = dbHandler.getUser(1);

        while (cursor.moveToNext()) {
            profileFName.setText("" + cursor.getString(1));
            profileLName.setText("" + cursor.getString(2));
            profileSection.setText("" + cursor.getString(3));
            byte[] imageByte = cursor.getBlob(4);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            profileImage.setImageBitmap(bitmap);

            tutProfileFName.setText("" + cursor.getString(1));
            tutProfileLName.setText("" + cursor.getString(2));
            tutProfileSection.setText("" + cursor.getString(3));
            tutProfileImage.setImageBitmap(bitmap);
        }
        cursor.close();

        profileEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        int studyTotalTime = 79200;
        int testTotalTime = 1080;
        int testTotalScore = 60;

        studyTime1 = dbHandler.getTopicsId(470);
        studyTime2 = dbHandler.getTopicsId(901);
        studyTime3 = dbHandler.getTopicsId(590);
        studyTime4 = dbHandler.getTopicsId(613);
        studyTime5 = dbHandler.getTopicsId(176);
        studyTime6 = dbHandler.getTopicsId(607);

        if (!studyTime1.isEmpty()) {
            timeInSec1 = Integer.parseInt(studyTime1.get(TIME_IN_SEC));
        }
        if (!studyTime2.isEmpty()) {
            timeInSec2 = Integer.parseInt(studyTime2.get(TIME_IN_SEC));
        }
        if (!studyTime3.isEmpty()) {
            timeInSec3 = Integer.parseInt(studyTime3.get(TIME_IN_SEC));
        }
        if (!studyTime4.isEmpty()) {
            timeInSec4 = Integer.parseInt(studyTime4.get(TIME_IN_SEC));
        }
        if (!studyTime5.isEmpty()) {
            timeInSec5 = Integer.parseInt(studyTime5.get(TIME_IN_SEC));
        }
        if (!studyTime6.isEmpty()) {
            timeInSec6 = Integer.parseInt(studyTime6.get(TIME_IN_SEC));
        }

        checkTestCode11 = dbHandler.checkCode(4276);
        checkTestCode12 = dbHandler.checkCode(6856);
        checkTestCode21 = dbHandler.checkCode(8603);
        checkTestCode22 = dbHandler.checkCode(8970);
        checkTestCode31 = dbHandler.checkCode(7137);
        checkTestCode32 = dbHandler.checkCode(7436);
        checkTestCode41 = dbHandler.checkCode(1067);
        checkTestCode42 = dbHandler.checkCode(3186);
        checkTestCode51 = dbHandler.checkCode(9788);
        checkTestCode52 = dbHandler.checkCode(7260);
        checkTestCode61 = dbHandler.checkCode(3552);
        checkTestCode62 = dbHandler.checkCode(9002);

        Boolean[] testCode = {checkTestCode11, checkTestCode12, checkTestCode21, checkTestCode22, checkTestCode31, checkTestCode32, checkTestCode41, checkTestCode42, checkTestCode51, checkTestCode52, checkTestCode61, checkTestCode62};

        int completeTests = 0;

        for (int i = 0; i < testCode.length; i++) {
            if (testCode[i] == true) {
                completeTests++;
            }
        }

        testScore11 = dbHandler.getScoreTime(4276);
        testScore12 = dbHandler.getScoreTime(6856);
        testScore21 = dbHandler.getScoreTime(8603);
        testScore22 = dbHandler.getScoreTime(8970);
        testScore31 = dbHandler.getScoreTime(7137);
        testScore32 = dbHandler.getScoreTime(7436);
        testScore41 = dbHandler.getScoreTime(1067);
        testScore42 = dbHandler.getScoreTime(3186);
        testScore51 = dbHandler.getScoreTime(9788);
        testScore52 = dbHandler.getScoreTime(7260);
        testScore61 = dbHandler.getScoreTime(3552);
        testScore62 = dbHandler.getScoreTime(9002);

        if (!testScore11.isEmpty()) {
            score11 = Integer.parseInt(testScore11.get(SCORE));
            time11 = Integer.parseInt(testScore11.get(TIME));
        }
        if (!testScore12.isEmpty()) {
            score12 = Integer.parseInt(testScore12.get(SCORE));
            time12 = Integer.parseInt(testScore12.get(TIME));
        }
        if (!testScore21.isEmpty()) {
            score21 = Integer.parseInt(testScore21.get(SCORE));
            time21 = Integer.parseInt(testScore21.get(TIME));
        }
        if (!testScore22.isEmpty()) {
            score22 = Integer.parseInt(testScore22.get(SCORE));
            time22 = Integer.parseInt(testScore22.get(TIME));
        }
        if (!testScore31.isEmpty()) {
            score31 = Integer.parseInt(testScore31.get(SCORE));
            time31 = Integer.parseInt(testScore31.get(TIME));
        }
        if (!testScore32.isEmpty()) {
            score32 = Integer.parseInt(testScore32.get(SCORE));
            time32 = Integer.parseInt(testScore32.get(TIME));
        }
        if (!testScore41.isEmpty()) {
            score41 = Integer.parseInt(testScore41.get(SCORE));
            time41 = Integer.parseInt(testScore41.get(TIME));
        }
        if (!testScore42.isEmpty()) {
            score42 = Integer.parseInt(testScore42.get(SCORE));
            time42 = Integer.parseInt(testScore42.get(TIME));
        }
        if (!testScore51.isEmpty()) {
            score51 = Integer.parseInt(testScore51.get(SCORE));
            time51 = Integer.parseInt(testScore51.get(TIME));
        }
        if (!testScore52.isEmpty()) {
            score52 = Integer.parseInt(testScore52.get(SCORE));
            time52 = Integer.parseInt(testScore52.get(TIME));
        }
        if (!testScore61.isEmpty()) {
            score61 = Integer.parseInt(testScore61.get(SCORE));
            time61 = Integer.parseInt(testScore61.get(TIME));
        }
        if (!testScore62.isEmpty()) {
            score62 = Integer.parseInt(testScore62.get(SCORE));
            time62 = Integer.parseInt(testScore62.get(TIME));
        }

        int totalTimeInSec = timeInSec1 + timeInSec2 + timeInSec3 + timeInSec4 + timeInSec5 + timeInSec6;
        int totalScore = score11 + score12 + score21 + score22 + score31 + score32 + score41 + score42 + score51 + score52 + score61 + score62;
        int totalTestTime = time11 + time12 + time21 + time22 + time31 + time32 + time41 + time42 + time51 + time52 + time61 + time62;

        int studyPercentage = (int) (((float) totalTimeInSec/studyTotalTime)*100);
        int hours = totalTimeInSec / 3600;
        int minutes = (totalTimeInSec % 3600) / 60;
        int seconds = totalTimeInSec % 60;
        String totalTime1 = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);

        int testPercentage = (int) (((float) totalScore/testTotalScore)*100);
        int hrs = totalTestTime / 3600;
        int min = (totalTestTime % 3600) / 60;
        int secs = totalTestTime % 60;
        String totalTime2 = String.format(Locale.getDefault(), "%02d:%02d:%02d", hrs, min, secs);

        testsCompletedValue.setText(completeTests + "/7");
        totalTestsTimeValue.setText(totalTime2);

        studyProgressBar.setProgressMax(100f);
        studyProgressBar.setProgress(studyPercentage);
        studyTimePercentage.setText(String.valueOf(studyPercentage) + "%");
        totalStudyTimeValue.setText(totalTime1);

        testProgressBar.setProgressMax(100f);
        testProgressBar.setProgress(testPercentage);
        testScorePercentage.setText(String.valueOf(testPercentage) + "%");

        tutorialProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right);
                Animation slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
                tutorialContent4.setVisibility(View.VISIBLE);
                maskView4.setVisibility(View.VISIBLE);
                tutorialText4.setAnimation(slideRight);
                tutorialImage4.setAnimation(slideUp);

                tutorialText4.setText("Let's explore the Profile feature, your hub for personalization and progress tracking.");
                tutorialImage4.setImageResource(R.drawable.teacher2);
                profileEditProfile.setEnabled(false);
                tutProfileEditProfile.setVisibility(View.GONE);
                tutDashboard.setVisibility(View.GONE);

                slideUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tutorial_profile);
                        mediaPlayer.start();
                        handler.postDelayed(runnable1, 5500);
                        handler.postDelayed(runnable2, 11800);
                        handler.postDelayed(runnable3, 19500);
                        handler.postDelayed(runnable4, 23000);
                        handler.postDelayed(runnable5, 28000);
                        handler.postDelayed(runnable6, 36500);
                        handler.postDelayed(runnable7, 42600);
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
                tutorialText4.setText("Here, you'll find everything you need to manage your user profile and track your learning journey.");
                tutorialImage4.setImageResource(R.drawable.teacher3);
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                tutorialText4.setText("In the user profile section, you can create and update your profile picture, name, and grade and section.");
                tutorialImage4.setImageResource(R.drawable.teacher7);
                tutProfileEditProfile.setVisibility(View.VISIBLE);
            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {
                tutorialText4.setText("Simply click to edit your profile as needed.");
            }
        };

        runnable4 = new Runnable() {
            @Override
            public void run() {
                tutorialText4.setText("Within the dashboard, you'll find a comprehensive overview of your learning progress.");
                tutProfileEditProfile.setVisibility(View.GONE);
                tutDashboard.setVisibility(View.VISIBLE);
            }
        };

        runnable5 = new Runnable() {
            @Override
            public void run() {
                tutorialText4.setText("Monitor your time spent studying in the Study feature and track your overall score in the Tests feature using circular bars.");
                tutorialImage4.setImageResource(R.drawable.teacher5);
            }
        };

        runnable6 = new Runnable() {
            @Override
            public void run() {
                tutorialText4.setText("With the Profile feature, you're in control of your learning experience every step of the way.");
                tutDashboard.setVisibility(View.GONE);
            }
        };

        runnable7 = new Runnable() {
            @Override
            public void run() {
                tutorialText4.setText("Keep striving for success, and watch your progress soar!");
                tutorialImage4.setImageResource(R.drawable.teacher4);
            }
        };

        tutorialCancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialContent4.setVisibility(View.GONE);
                maskView4.setVisibility(View.GONE);
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

                profileEditProfile.setEnabled(true);
            }
        });

        return root;
    }
}