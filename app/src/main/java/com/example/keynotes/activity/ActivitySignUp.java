package com.example.keynotes.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;

public class ActivitySignUp extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirmPassword;
    Button btSignUp;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();



    }

    private void setViews() {
        etEmail = findViewById(R.id.activity_signup_et_email);
        etPassword = findViewById(R.id.activity_signup_et_password);
        etConfirmPassword = findViewById(R.id.activity_signup_et_confirm_password);
        btSignUp = findViewById(R.id.activity_signup_bt_signup);
        tvLogin = findViewById(R.id.activity_signup_tv_login);
    }
}
