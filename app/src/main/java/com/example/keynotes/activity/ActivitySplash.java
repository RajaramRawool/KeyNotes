package com.example.keynotes.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;
import com.example.keynotes.util.AppSharedPreferences;

public class ActivitySplash extends AppCompatActivity {
//    Instance Variable
    AppSharedPreferences appSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setValues();


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);

                    runOnUiThread( new Runnable() {
                        @Override
                        public void run() {
                            checkSession();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivitySplash.this);
    }

    private void checkSession() {
        finish();
        if (appSharedPreferences.getUserSession()) {
            startActivity(new Intent(ActivitySplash.this, ActivityHomePage.class));
        }else {
            startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
        }
    }
}
