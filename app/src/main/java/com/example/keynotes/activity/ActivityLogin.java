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
import com.example.keynotes.util.AppSharedPreferences;
import com.example.keynotes.model.User;

public class ActivityLogin extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btLogin;
    TextView tvSignUp;
    AppSharedPreferences appSharedPreferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
        setValues();
        setListeners();
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivityLogin.this);
    }

    private void setListeners() {
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
            if (loginValidation(email, password)) {
                finish();
                appSharedPreferences.setUserSession(true);
                startActivity(new Intent(ActivityLogin.this,ActivityHomePage.class));
            } else {
                Toast.makeText(ActivityLogin.this,"Email or Password Incorrect"
                , Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(ActivityLogin.this,"Invalid Input",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean loginValidation(String email, String password) {
        user = appSharedPreferences.getUser();
        if (user.getEmail().equalsIgnoreCase(email)
                && user.getPassword().equals(password)) {
            return true;
        }else   {
            return false;
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
