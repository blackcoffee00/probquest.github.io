package com.example.loginscreen.ui.games;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.loginscreen.R;
import com.example.loginscreen.ui.games.puzzles.PuzzlesActivity;

public class GamesFragment extends Fragment {

    private Button gamePuzzles, gameActivities, gameRollDice, gameHighLow, gameColorRoulette;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games, container, false);

        gamePuzzles = root.findViewById(R.id.gamePuzzles);
        gameActivities = root.findViewById(R.id.gameActivities);
        gameRollDice = root.findViewById(R.id.gameRollDice);
        gameHighLow = root.findViewById(R.id.gameHighLow);
        gameColorRoulette = root.findViewById(R.id.gameColorRoulette);

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

        gameHighLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), HighLowActivity.class);
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

        return root;
    }
}
