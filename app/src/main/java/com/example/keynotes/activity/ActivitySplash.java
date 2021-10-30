package com.example.keynotes.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;
import com.example.keynotes.utility.AppSharedPreferences;

public class ActivitySplash extends AppCompatActivity {

    ImageView ivLogo;
    AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setViews();
        setValues();
        setTimer();
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivitySplash.this);
    }

    private void setTimer() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);
                    activityLaunch();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void activityLaunch() {

        finish();
        if (appSharedPreferences.getUserSession()) {
            startActivity(new Intent(ActivitySplash.this, ActivityHome.class));
        }else {
            startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
        }
    }


    private void setViews() {
        ivLogo = findViewById(R.id.iv_logo);
    }
}
