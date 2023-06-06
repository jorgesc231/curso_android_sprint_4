package com.example.about_me;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;
import static com.example.about_me.ProjectFragment.ARG_PARAM1;
import static com.example.about_me.ProjectFragment.ARG_PARAM2;
import static com.example.about_me.ProjectFragment.ARG_PARAM3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.about_me.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        setSupportActionBar(binding.Toolbar);

        binding.fabContact.setOnClickListener(v -> navigateToContactActivity());
        binding.btChangeMode.setOnClickListener(v -> changeVisualMode());
        initProjectList();
    }

    private void navigateToContactActivity () {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    private void changeVisualMode() {

        if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES || AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
        }
    }

    private void initProjectList() {
        initProjectFragment("Snake", R.drawable.snake, R.id.snakeFragmentContainer, "https://github.com/jorgesc231/snake");
        initProjectFragment("Timberman", R.drawable.timberman, R.id.timbermanFragmentContainer, "https://github.com/jorgesc231/timberman");
        initProjectFragment("Gif2Webp", R.drawable.gif2webp, R.id.gifFragmentContainer, "https://github.com/jorgesc231/gif2webp_for_android");
        initProjectFragment("JWT con Flutter", R.drawable.jwt_flutter, R.id.jwtFragmentContainer, "https://github.com/jorgesc231/api_jwt_client");
        initProjectFragment("Game of Life", R.drawable.game_of_life, R.id.gameFragmentContainer, "https://github.com/jorgesc231/game_of_life_sdl");
    }

    private void initProjectFragment(String name, int picture_id, int container_id, String url) {
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM1, name);
        bundle.putInt(ARG_PARAM2, picture_id);
        bundle.putString(ARG_PARAM3, url);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.add(container_id, fragment);
        fragmentTransaction.commit();
    }
}