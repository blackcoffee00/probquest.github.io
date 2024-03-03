package com.example.loginscreen.ui.games;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.loginscreen.R;
import com.example.loginscreen.ui.games.puzzles.PuzzlesActivity;

public class GamesFragment extends Fragment {
    private Button gamePuzzles, gameActivities, gameRollDice, gameColorRoulette, tutorialGames, tutorialCancel3;
    private TextView tutorialText3;
    private RelativeLayout tutorialContent3;
    private View maskView3;
    private Handler handler = new Handler();
    private Runnable runnable1, runnable2, runnable3, runnable4, runnable5, runnable6, runnable7;
    private ImageView tutorialImage3;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games, container, false);

        gamePuzzles = root.findViewById(R.id.gamePuzzles);
        gameActivities = root.findViewById(R.id.gameActivities);
        gameRollDice = root.findViewById(R.id.gameRollDice);
        gameColorRoulette = root.findViewById(R.id.gameColorRoulette);

        tutorialGames = root.findViewById(R.id.tutorialGames);
        tutorialCancel3 = root.findViewById(R.id.tutorialCancel3);
        tutorialText3 = root.findViewById(R.id.tutorialText3);
        tutorialContent3 = root.findViewById(R.id.tutorialContent3);
        maskView3 = root.findViewById(R.id.maskView3);
        tutorialImage3 = root.findViewById(R.id.tutorialImage3);

        gamePuzzles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), PuzzlesActivity.class);
                startActivity(intent);
            }
        });

        gameActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ActivitiesActivity.class);
                startActivity(intent);
            }
        });

        gameRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), RollDiceActivity.class);
                startActivity(intent);
            }
        });

        gameColorRoulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ColorRouletteActivity.class);
                startActivity(intent);
            }
        });

        Button[] buttons = {gamePuzzles, gameActivities, gameRollDice, gameColorRoulette, tutorialGames};

        tutorialGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slideRight = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_right);
                Animation slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
                tutorialContent3.setVisibility(View.VISIBLE);
                maskView3.setVisibility(View.VISIBLE);
                tutorialText3.setAnimation(slideRight);
                tutorialImage3.setAnimation(slideUp);

                tutorialText3.setText("Welcome to the Games feature, where learning meets fun in four exciting ways!");
                tutorialImage3.setImageResource(R.drawable.teacher1);

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(false);
                }

                slideUp.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.tutorial_games);
                        mediaPlayer.start();
                        handler.postDelayed(runnable1, 6500);
                        handler.postDelayed(runnable2, 14500);
                        handler.postDelayed(runnable3, 22000);
                        handler.postDelayed(runnable4, 29500);
                        handler.postDelayed(runnable5, 37500);
                        handler.postDelayed(runnable6, 42500);
                        handler.postDelayed(runnable7, 49500);
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
                tutorialText3.setText("First off, we have Worksheets, providing printable worksheets that you can save as PDFs for class activities.");
                tutorialImage3.setImageResource(R.drawable.teacher7);
            }
        };

        runnable2 = new Runnable() {
            @Override
            public void run() {
                tutorialText3.setText("Then, there's Puzzles, a probability quiz ranging from easy to hard levels, sure to challenge and entertain.");
            }
        };

        runnable3 = new Runnable() {
            @Override
            public void run() {
                tutorialText3.setText("For a dose of excitement, try Roll Dice, where you'll roll two dice and predict the total sum of their roll.");
            }
        };

        runnable4 = new Runnable() {
            @Override
            public void run() {
                tutorialText3.setText("And last but not least, we have Color Roulette, where you'll spin a wheel and predict the color it will land on.");
            }
        };

        runnable5 = new Runnable() {
            @Override
            public void run() {
                tutorialText3.setText("Simply click on the Play button of your chosen game to start playing the game.");
                tutorialImage3.setImageResource(R.drawable.teacher6);
            }
        };

        runnable6 = new Runnable() {
            @Override
            public void run() {
                tutorialText3.setText("With these games, you'll not only build your probability skills but also have a blast while doing so.");
            }
        };

        runnable7 = new Runnable() {
            @Override
            public void run() {
                tutorialText3.setText("Have fun and enjoy the games!");
                tutorialImage3.setImageResource(R.drawable.teacher1);
            }
        };

        tutorialCancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tutorialContent3.setVisibility(View.GONE);
                maskView3.setVisibility(View.GONE);
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

                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(true);
                }
            }
        });

        return root;
    }
}
