package com.example.keynotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;
import com.example.keynotes.utility.AppSharedPreferences;

public class ActivityHome extends AppCompatActivity {
    AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setValues();
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivityHome.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_sign_out:
                appSharedPreferences.setUserSession(false);
                finish();
                startActivity(new Intent(ActivityHome.this,ActivityLogin.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
