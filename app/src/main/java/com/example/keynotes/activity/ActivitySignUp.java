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
import com.google.gson.Gson;

public class ActivitySignUp extends AppCompatActivity {

    EditText etEmail, etPassword, etConfirmPassword;
    Button btSignUp;
    TextView tvLogin;
    AppSharedPreferences appSharedPreferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setViews();
        setValues();
        setListeners();
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivitySignUp.this);
    }

    private void setListeners() {
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputValidation();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ActivitySignUp.this, ActivityLogin.class));
            }
        });
    }

    private void userInputValidation() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        if (isEmailValid(email) && isPasswordValid(password) && isConfirmPasswordValid(password, confirmPassword)) {
            user = new User(email,password);
            Gson gson = new Gson();
            appSharedPreferences.setUser(gson.toJson(user));
            appSharedPreferences.setUserSession(true);
            Toast.makeText(ActivitySignUp.this, "Sign Up success", Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(ActivitySignUp.this, ActivityHomePage.class));
        }else {
            Toast.makeText(ActivitySignUp.this, "Sign Up not success", Toast.LENGTH_SHORT).show();
        }



    }

    private boolean isConfirmPasswordValid(String password,String confirmPassword) {
        boolean isTrue = false;
        if (confirmPassword.trim().isEmpty()) {
            etConfirmPassword.setError("Enter password to confirm");
        }else if (!password.trim().equalsIgnoreCase(confirmPassword.trim())) {
            etConfirmPassword.setError("Password doesn't match");
        } else {
            isTrue = true;
        }
        return isTrue;
    }

    private boolean isPasswordValid(String password) {
        boolean isTrue = false;
        if (password.trim().isEmpty()) {
            etPassword.setError("Enter Password");
        } else  if ((password.trim().length() < 4) || (password.trim().length() > 8)) {
            etPassword.setError("Password should be of length 4 to 8 ");
        } else {
            isTrue = true;
        }
        return isTrue;
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

    private void setViews() {
        etEmail = findViewById(R.id.activity_signup_et_email);
        etPassword = findViewById(R.id.activity_signup_et_password);
        etConfirmPassword = findViewById(R.id.activity_signup_et_confirm_password);
        btSignUp = findViewById(R.id.activity_signup_bt_signup);
        tvLogin = findViewById(R.id.activity_signup_tv_login);
    }
}
