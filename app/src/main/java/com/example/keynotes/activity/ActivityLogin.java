package com.example.keynotes.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keynotes.R;
import com.example.keynotes.utility.AppSharedPreferences;
import com.example.keynotes.utility.User;

public class ActivityLogin extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
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
        listeners();
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivityLogin.this);
        user = appSharedPreferences.getUser();

    }

    private void listeners() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(ActivityLogin.this,ActivitySignUp.class));
            }
        });

    }

    private void inputValidation() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (user != null) {
            if (isEmailValidate(email) && isPasswordValidate(password)) {
                Toast.makeText(ActivityLogin.this,"Successfully Login",Toast.LENGTH_LONG).show();
                appSharedPreferences.setUserSession(true);
                finish();
                startActivity(new Intent(ActivityLogin.this,ActivityHome.class));
            }else {
                Toast.makeText(ActivityLogin.this,"Invalid Input",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(ActivityLogin.this,"You are not Signup yet",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isEmailValidate(String email) {
        boolean isTrue = false;
        if (user != null) {
            if (email.trim().isEmpty()) {
                etEmail.setError("Enter Email");
            } else if (!email.trim().equalsIgnoreCase(user.getEmail())) {
                etEmail.setError("Email doesn't match");
            } else{
                isTrue = true;
            }
        }else {
        }
        return isTrue;
    }

    private boolean isPasswordValidate(String password) {
        boolean isTrue = false;
        if (user != null) {
            if (password.trim().isEmpty()) {
                etPassword.setError("Enter Password");
            }else if (!password.trim().equalsIgnoreCase(user.getPassword())) {
                etPassword.setError("Password doesn't match");
            }else {
                isTrue = true;
            }
        }else {
        }
        return isTrue;
    }


    private void setViews() {
        etEmail = findViewById(R.id.activity_home_et_email);
        etPassword = findViewById(R.id.activity_home_et_password);
        btLogin = findViewById(R.id.activity_home_bt_login);
        tvSignUp = findViewById(R.id.activity_home_signup);
    }
}