package com.example.loginscreen.ui.study;

import static com.example.loginscreen.DBHandler.TIME_IN_SEC;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Html;
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

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Map;

public class StudyFragment extends Fragment {
    private TextView greetings, textView2T1, textView2T2, textView2T3, textView2T4, textView2T5, textView2T6, tutContinue;
    private Button questBtn, topic1, topic2, topic3, topic4, topic5, topic6, tutorialStudy, tutorialCancel, tutQuestBtn;
    private CircularProgressBar progressBarT1, progressBarT2, progressBarT3, progressBarT4, progressBarT5, progressBarT6;
    private TextView progressTextT1, progressTextT2, progressTextT3, progressTextT4, progressTextT5, progressTextT6, tutorialText;
    private RelativeLayout tutorialContent, tutorialQuest;
    private View maskView;
    private ScrollView scrollView1;
    private Handler handler;
    private Runnable runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7, runnable8, runnable9, runnable10, runnable11, runnable12;
    private ImageView tutorialImage;
    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3;

    private boolean beforePlayer2 = true;
    private int progressT1, progressT2, progressT3, progressT4, progressT5, progressT6;
    private Map<String, String> studyTime1, studyTime2, studyTime3, studyTime4, studyTime5, studyTime6;
    DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_study, container, false);

        greetings = root.findViewById(R.id.greetings);
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
        tutorialQuest = root.findViewById(R.id.tutorialQuest);
        tutQuestBtn = root.findViewById(R.id.tutQuestBtn);
        tutContinue = root.findViewById(R.id.tutContinue);
        maskView = root.findViewById(R.id.maskView);
        scrollView1 = root.findViewById(R.id.scrollView1);

        dbHandler = new DBHandler(requireContext());

        Cursor cursor = dbHandler.getUser(1);
        if (cursor.moveToNext()) {
            greetings.setText("Hi, " + cursor.getString(1) + "!");
        }

        String textView2T1Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of simple events.";
        String textView2T2Format = "<i>Objective</i><br>In this lesson we will learn how to differentiate between simple events and compound events.";
        String textView2T3Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of mutually exclusive events.";
        String textView2T4Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of non-mutually exclusive events.";
        String textView2T5Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of independent events.";
        String textView2T6Format = "<i>Objective</i><br>In this lesson we will learn how to find the probability of dependent events.";

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
                tutorialText.setText("I'm Ms. Florence, your guide through its exciting features.");
            }
        };
        runnable2 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("First up, we have the Study feature. Here, you'll find your probability quests and lessons.");
                tutorialImage.setImageResource(R.drawable.teacher3);
            }
        };
        runnable3 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("Click on the Quest button to embark on your quests.");
                tutorialImage.setImageResource(R.drawable.teacher7);
            }
        };
        runnable4 = new Runnable() {
            @Override
            public void run() {
                tutorialQuest.setVisibility(View.VISIBLE);
            }
        };
        tutQuestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialQuest.setVisibility(View.GONE);
                showQuest();
                tutContinue.setVisibility(View.VISIBLE);
                tutorialText.setText("");
            }
        });
        tutContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (beforePlayer2 == true) {
                    tutContinue.setVisibility(View.GONE);
                    mediaPlayer2 = MediaPlayer.create(requireContext(), R.raw.tutorial_study_2);
                    mediaPlayer2.start();
                    tutorialText.setText("You're tasked with completing each lesson within a specific time frame.");
                    tutorialImage.setImageResource(R.drawable.teacher6);
                    handler.postDelayed(runnable5, 4500);
                    handler.postDelayed(runnable6, 10500);
                    beforePlayer2 = false;
                } else {
                    scrollView1.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });

                    tutContinue.setVisibility(View.GONE);
                    mediaPlayer3 = MediaPlayer.create(requireContext(), R.raw.tutorial_study_3);
                    mediaPlayer3.start();
                    tutorialText.setText("Below the Probability Quest, you'll find our six lessons: Simple Events, Compound Events,");
                    tutorialImage.setImageResource(R.drawable.teacher7);
                    handler.postDelayed(runnable7, 6000);
                    handler.postDelayed(runnable8, 13500);
                    handler.postDelayed(runnable9, 22000);
                    handler.postDelayed(runnable10, 27000);
                }
            }
        });
        runnable5 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("Keep an eye on the circular bar to track your progress as you delve deeper into each lesson.");
            }
        };
        runnable6 = new Runnable() {
            @Override
            public void run() {
                tutContinue.setVisibility(View.VISIBLE);
            }
        };
        runnable7 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("Mutually Exclusive Events, Non-Mutually Exclusive Events, Independent Events, and Dependent Events.");
            }
        };
        runnable8 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("Each lesson features interactive content, including readings, video lectures, and engaging activities.");
                tutorialImage.setImageResource(R.drawable.teacher3);
            }
        };
        runnable9 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("Simply click on the Start Lesson button to embark on your journey.");
            }
        };
        runnable10 = new Runnable() {
            @Override
            public void run() {
                tutorialText.setText("So, what are you waiting for? Dive into PROBQUEST today and level up your understanding of probability.");
                tutorialImage.setImageResource(R.drawable.teacher4);
            }
        };

        Button[] buttons = {questBtn, topic1, topic2, topic3, topic4, topic5, topic6, tutorialStudy};

        tutorialStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right);
                Animation slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
                tutorialContent.setVisibility(View.VISIBLE);
                maskView.setVisibility(View.VISIBLE);
                tutorialText.setAnimation(slideRight);
                tutorialImage.setAnimation(slideUp);
                beforePlayer2 = true;

                tutorialText.setText("Hello there, and welcome to the PROBQUEST app!");
                tutorialImage.setImageResource(R.drawable.teacher1);

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(false);
                }

                scrollView1.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });

                slideUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mediaPlayer1 = MediaPlayer.create(requireContext(), R.raw.tutorial_study_1);
                        mediaPlayer1.start();
                        handler.postDelayed(runnable1, 3400);
                        handler.postDelayed(runnable2, 7500);
                        handler.postDelayed(runnable3, 14500);
                        handler.postDelayed(runnable4, 18500);
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
                if (mediaPlayer1 != null) {
                    mediaPlayer1.stop();
                    mediaPlayer1.release();
                    mediaPlayer1 = null;
                }
                if (mediaPlayer2 != null) {
                    mediaPlayer2.stop();
                    mediaPlayer2.release();
                    mediaPlayer2 = null;
                }
                if (mediaPlayer3 != null) {
                    mediaPlayer3.stop();
                    mediaPlayer3.release();
                    mediaPlayer3 = null;
                }
                handler.removeCallbacks(runnable1);
                handler.removeCallbacks(runnable2);
                handler.removeCallbacks(runnable3);
                handler.removeCallbacks(runnable4);
                handler.removeCallbacks(runnable5);
                handler.removeCallbacks(runnable6);
                handler.removeCallbacks(runnable7);
                handler.removeCallbacks(runnable8);
                handler.removeCallbacks(runnable9);
                handler.removeCallbacks(runnable10);

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(true);
                }
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
        progressBarT5 = view.findViewById(R.id.progressBarT5);
        progressBarT6 = view.findViewById(R.id.progressBarT6);

        progressTextT1 = view.findViewById(R.id.progressTextT1);
        progressTextT2 = view.findViewById(R.id.progressTextT2);
        progressTextT3 = view.findViewById(R.id.progressTextT3);
        progressTextT4 = view.findViewById(R.id.progressTextT4);
        progressTextT5 = view.findViewById(R.id.progressTextT5);
        progressTextT6 = view.findViewById(R.id.progressTextT6);

        int time = 14400;

        studyTime1 = dbHandler.getTopicsId(470);
        studyTime2 = dbHandler.getTopicsId(901);
        studyTime3 = dbHandler.getTopicsId(590);
        studyTime4 = dbHandler.getTopicsId(613);
        studyTime5 = dbHandler.getTopicsId(176);
        studyTime6 = dbHandler.getTopicsId(607);

        if (!studyTime1.isEmpty()) {
            int timeInSec1 = Integer.parseInt(studyTime1.get(TIME_IN_SEC));
            progressT1 = (int) (((float) timeInSec1/time)*100);
        }
        if (!studyTime2.isEmpty()) {
            int timeInSec2 = Integer.parseInt(studyTime2.get(TIME_IN_SEC));
            progressT2 = (int) (((float) timeInSec2/time)*100);
        }
        if (!studyTime3.isEmpty()) {
            int timeInSec3 = Integer.parseInt(studyTime3.get(TIME_IN_SEC));
            progressT3 = (int) (((float) timeInSec3/10800)*100);
        }
        if (!studyTime4.isEmpty()) {
            int timeInSec4 = Integer.parseInt(studyTime4.get(TIME_IN_SEC));
            progressT4 = (int) (((float) timeInSec4/10800)*100);
        }
        if (!studyTime5.isEmpty()) {
            int timeInSec5 = Integer.parseInt(studyTime5.get(TIME_IN_SEC));
            progressT5 = (int) (((float) timeInSec5/time)*100);
        }
        if (!studyTime6.isEmpty()) {
            int timeInSec6 = Integer.parseInt(studyTime6.get(TIME_IN_SEC));
            progressT6 = (int) (((float) timeInSec6/time)*100);
        }

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