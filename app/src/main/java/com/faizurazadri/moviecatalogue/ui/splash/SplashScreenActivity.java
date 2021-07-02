package com.faizurazadri.moviecatalogue.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.faizurazadri.moviecatalogue.R;
import com.faizurazadri.moviecatalogue.ui.home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Thread splash = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();
                }
            }
        };
        splash.start();
    }
}