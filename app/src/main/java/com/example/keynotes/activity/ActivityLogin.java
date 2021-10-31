package com.example.keynotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;

public class ActivityLogin extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btLogin;
    TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
        setListeners();
    }

    private void setListeners() {
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,ActivitySignUp.class));
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputValidation();
            }
        });

    }

    private void userInputValidation() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (isEmailValid(email) && isPasswordValid(password)) {
            finish();
            startActivity(new Intent(ActivityLogin.this,ActivityHomePage.class));
        }else {
            Toast.makeText(ActivityLogin.this,"Invalid Input",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isEmailValid(String email) {
        boolean isTrue = false;
        if (email.trim().isEmpty()) {
            etEmail.setError("Enter Email");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
            etEmail.setError("Enter Valid Email");
        } else {
            isTrue = true;
        }
        return isTrue;
    }

    private boolean isPasswordValid(String password) {
        boolean isTrue = false;
        if (password.trim().isEmpty()) {
            etPassword.setError("Enter Password");
        } else if (password.trim().length() < 4 || password.trim().length() > 8) {
            etPassword.setError("Password length should be between 4 to 8");
        } else {
            isTrue = true;
        }
        return isTrue;
    }


    private void setViews() {
        etEmail = findViewById(R.id.activity_login_et_email);
        etPassword = findViewById(R.id.activity_login_et_password);
        btLogin = findViewById(R.id.activity_login_bt_login);
        tvSignUp = findViewById(R.id.activity_login_tv_signup);
    }
}
