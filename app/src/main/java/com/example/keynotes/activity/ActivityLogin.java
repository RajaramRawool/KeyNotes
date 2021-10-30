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

public class ActivityLogin extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    Button btLogin;
    TextView tvSignUp;
    AppSharedPreferences appSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
        listeners();
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
        if (isEmailValidate(email) && isPasswordValidate(password)) {
            Toast.makeText(ActivityLogin.this,"Successfully Login",Toast.LENGTH_LONG).show();

            appSharedPreferences = new AppSharedPreferences(ActivityLogin.this);
            appSharedPreferences.setUserSession(true);
            finish();
            startActivity(new Intent(ActivityLogin.this,ActivityHome.class));
        }else {
            Toast.makeText(ActivityLogin.this,"Invalid Input",Toast.LENGTH_LONG).show();
        }

    }

    private boolean isEmailValidate(String email) {
        if (email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter Valid Email ");
            return false;
        }else {
            return true;
        }
    }

    private boolean isPasswordValidate(String password) {
        boolean isTrue = false;
        if (password.trim().isEmpty()) {
            etPassword.setError("Enter Password");
        }else if (password.trim().length() < 4) {
            etPassword.setError("Minimum length is 4");
        }else {
            isTrue = true;
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