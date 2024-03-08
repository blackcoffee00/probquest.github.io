package com.example.loginscreen;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.loginscreen.databinding.ActivityNavigationBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.loginscreen.databinding.ActivityNavigationBinding;

import java.io.IOException;

public class NavigationActivity extends AppCompatActivity {

    private ActivityNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_study, R.id.navigation_tests, R.id.navigation_games, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_navigation);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("loadToTestsFragment")) {
            int fragmentId = intent.getIntExtra("loadToTestsFragment", R.id.navigation_study);
            navView.setSelectedItemId(fragmentId);
        }
        if (intent != null && intent.hasExtra("loadToProfileFragment")) {
            int fragmentId = intent.getIntExtra("loadToProfileFragment", R.id.navigation_study);
            navView.setSelectedItemId(fragmentId);
        }

    }
}