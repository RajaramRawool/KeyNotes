package com.example.keynotes.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;

public class ActivityLogin extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btLogin;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
    }

    private void setViews() {
        etEmail = findViewById(R.id.activity_login_et_email);
        etPassword = findViewById(R.id.activity_login_et_password);
        btLogin = findViewById(R.id.activity_login_bt_login);
        tvSignUp = findViewById(R.id.activity_login_tv_signup);
    }
}
