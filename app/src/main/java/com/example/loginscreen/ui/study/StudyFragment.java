package com.example.loginscreen.ui.study;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.loginscreen.R;
import com.example.loginscreen.CardViewAdapter;

import java.util.Arrays;

public class StudyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_study, container, false);

        ListView listViewStudy = root.findViewById(R.id.listViewStudy);

        String[] topicTitles = getResources().getStringArray(R.array.topic_titles);
        int[] icon = new int[5];
        Arrays.fill(icon, R.drawable.dice);

        CardViewAdapter cardViewAdapter = new CardViewAdapter(requireActivity(), topicTitles, icon);
        listViewStudy.setAdapter(cardViewAdapter);

        listViewStudy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                if (position == 0) {
                    Intent intent = new Intent(requireActivity(), TopicOneActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(requireActivity(), TopicTwoActivity.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(requireActivity(), TopicThreeActivity.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(requireActivity(), TopicFourActivity.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(requireActivity(), TopicFiveActivity.class);
                    startActivity(intent);
                }

            }
        });

        return root;
    }
}