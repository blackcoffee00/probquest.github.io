package com.example.loginscreen.ui.study;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.loginscreen.MainActivity;
import com.example.loginscreen.NavigationActivity;
import com.example.loginscreen.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class StudyFragment extends Fragment {

    private TextView textView2T1, textView2T2, textView2T3, textView2T4, textView2T5, textView2T6;
    private Button questBtn, topic1, topic2, topic3, topic4, topic5, topic6, tutorialStudy;
    private CircularProgressBar progressBarT1, progressBarT2, progressBarT3, progressBarT4, progressBarT5, progressBarT6;
    private TextView progressTextT1, progressTextT2, progressTextT3, progressTextT4, progressTextT5, progressTextT6, tutorialText;
    private RelativeLayout tutorialContent;
    private View maskView;
    private ScrollView scrollView1;
    private Handler handler;
    private Runnable runnable1, runnable2;
    private ImageView tutorialImage, tutorialCancel;
    private MediaPlayer mediaPlayer;
    private int progressT1, progressT2, progressT3, progressT4, progressT5, progressT6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_study, container, false);

        textView2T1 = root.findViewById(R.id.textView2T1);
        textView2T2 = root.findViewById(R.id.textView2T2);
        textView2T3 = root.findViewById(R.id.textView2T3);
        textView2T4 = root.findViewById(R.id.textView2T4);
        textView2T5 = root.findViewById(R.id.textView2T5);
        textView2T6 = root.findViewById(R.id.textView2T6);
        topic1 = root.findViewById(R.id.topic1);
        topic2 = root.findViewById(R.id.topic2);
        topic3 = root.findViewById(R.id.topic3);
        topic4 = root.findViewById(R.id.topic4);
        topic5 = root.findViewById(R.id.topic5);
        topic6 = root.findViewById(R.id.topic6);
        questBtn = root.findViewById(R.id.questBtn);
        tutorialStudy = root.findViewById(R.id.tutorialStudy);
        tutorialContent = root.findViewById(R.id.tutorialContent);
        tutorialText = root.findViewById(R.id.tutorialText);
        tutorialImage = root.findViewById(R.id.tutorialImage);
        tutorialCancel = root.findViewById(R.id.tutorialCancel);
        maskView = root.findViewById(R.id.maskView);
        scrollView1 = root.findViewById(R.id.scrollView1);

        String textView2T1Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of simple events.";
        String textView2T2Format = "<i>Objective</i><br>In this lesson we will learn how to differentiate between simple events and compound events.";
        String textView2T3Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of mutually exclusive events.";
        String textView2T4Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of non - mutually exclusive events.";
        String textView2T5Format = "<i>Objective</i><br>";
        String textView2T6Format = "<i>Objective</i><br>";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView2T1.setText(Html.fromHtml(textView2T1Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView2T1.setText(Html.fromHtml(textView2T1Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView2T2.setText(Html.fromHtml(textView2T2Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView2T2.setText(Html.fromHtml(textView2T2Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView2T3.setText(Html.fromHtml(textView2T3Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView2T3.setText(Html.fromHtml(textView2T3Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView2T4.setText(Html.fromHtml(textView2T4Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView2T4.setText(Html.fromHtml(textView2T4Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView2T5.setText(Html.fromHtml(textView2T5Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView2T5.setText(Html.fromHtml(textView2T5Format));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView2T6.setText(Html.fromHtml(textView2T6Format, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView2T6.setText(Html.fromHtml(textView2T6Format));
        }

        topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TopicOneActivity.class);
                startActivity(intent);
            }
        });

        topic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TopicTwoActivity.class);
                startActivity(intent);
            }
        });

        topic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TopicThreeActivity.class);
                startActivity(intent);
            }
        });

        topic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TopicFourActivity.class);
                startActivity(intent);
            }
        });

        topic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TopicFiveActivity.class);
                startActivity(intent);
            }
        });

        topic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), TopicSixActivity.class);
                startActivity(intent);
            }
        });

        questBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuest();
            }
        });

        handler = new Handler();
        runnable1 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("I'm Ms. Florence, your guide on this journey through the fascinating world of probabilities.");
            }
        };
        runnable2 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("Here, we'll explore the essential concepts and applications of probability theory.");
            }
        };

        tutorialStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right);
                Animation slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
                tutorialContent.setVisibility(View.VISIBLE);
                maskView.setVisibility(View.VISIBLE);
                tutorialText.setAnimation(slideRight);
                tutorialImage.setAnimation(slideUp);

                tutorialText.setText("Hello there, and welcome to our PROBQUEST app!");

                questBtn.setEnabled(false);
                topic1.setEnabled(false);
                topic2.setEnabled(false);
                topic3.setEnabled(false);
                topic4.setEnabled(false);
                topic5.setEnabled(false);
                topic6.setEnabled(false);
                tutorialStudy.setEnabled(false);

                slideUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tutorial_study_1);
                        mediaPlayer.start();
                        handler.postDelayed(runnable1, 3500);
                        handler.postDelayed(runnable2, 10000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        tutorialCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialContent.setVisibility(View.GONE);
                maskView.setVisibility(View.GONE);
                mediaPlayer.stop();
                mediaPlayer.release();
                handler.removeCallbacks(runnable1);
                handler.removeCallbacks(runnable2);

                questBtn.setEnabled(true);
                topic1.setEnabled(true);
                topic2.setEnabled(true);
                topic3.setEnabled(true);
                topic4.setEnabled(true);
                topic5.setEnabled(true);
                topic6.setEnabled(true);
                tutorialStudy.setEnabled(true);
            }
        });

        return root;
    }


    private void showQuest() {
        View view = getLayoutInflater().inflate(R.layout.custom_quest_dialog, null);
        progressBarT1 = view.findViewById(R.id.progressBarT1);
        progressBarT2 = view.findViewById(R.id.progressBarT2);
        progressBarT3 = view.findViewById(R.id.progressBarT3);
        progressBarT4 = view.findViewById(R.id.progressBarT4);
        progressTextT1 = view.findViewById(R.id.progressTextT1);
        progressTextT2 = view.findViewById(R.id.progressTextT2);
        progressTextT3 = view.findViewById(R.id.progressTextT3);
        progressTextT4 = view.findViewById(R.id.progressTextT4);
        progressTextT5 = view.findViewById(R.id.progressTextT5);
        progressTextT6 = view.findViewById(R.id.progressTextT6);

        progressT1 = 0;
        progressT2 = 0;
        progressT3 = 0;
        progressT4 = 0;
        progressT5 = 0;
        progressT6 = 0;

        progressBarT1.setProgressMax(100f);
        progressBarT1.setProgress(progressT1);
        progressBarT2.setProgressMax(100f);
        progressBarT2.setProgress(progressT2);
        progressBarT3.setProgressMax(100f);
        progressBarT3.setProgress(progressT3);
        progressBarT4.setProgressMax(100f);
        progressBarT4.setProgress(progressT4);
        progressBarT5.setProgressMax(100f);
        progressBarT5.setProgress(progressT5);
        progressBarT6.setProgressMax(100f);
        progressBarT6.setProgress(progressT6);

        progressTextT1.setText(String.valueOf(progressT1) + "%");
        progressTextT2.setText(String.valueOf(progressT2) + "%");
        progressTextT3.setText(String.valueOf(progressT3) + "%");
        progressTextT4.setText(String.valueOf(progressT4) + "%");
        progressTextT5.setText(String.valueOf(progressT5) + "%");
        progressTextT6.setText(String.valueOf(progressT6) + "%");

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}