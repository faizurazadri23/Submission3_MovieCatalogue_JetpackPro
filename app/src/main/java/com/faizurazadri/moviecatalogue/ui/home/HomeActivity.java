package com.faizurazadri.moviecatalogue.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.faizurazadri.moviecatalogue.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityHomeBinding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());


        activityHomeBinding.viewPagerMovie.setAdapter(sectionsPagerAdapter);
        activityHomeBinding.tabsMovie.setupWithViewPager(activityHomeBinding.viewPagerMovie);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}