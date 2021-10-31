package com.example.keynotes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keynotes.R;
import com.example.keynotes.utility.AppSharedPreferences;
import com.example.keynotes.utility.User;

public class ActivitySignUp extends AppCompatActivity {
    EditText etEmail, etPassword, etConfirmPassword;
    Button btSignup;
    TextView tvLogin;
    AppSharedPreferences appSharedPreferences;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setViews();
        setValues();
        listeners();
    }

    private void listeners() {
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();

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

    private void validation() {
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String confirmPassword =  etConfirmPassword.getText().toString();
        if (emailValidation(email) && passwordValidation(password) && (ConfirmPasswordValidation(password,confirmPassword))) {
            Toast.makeText(ActivitySignUp.this,"Login Successful",Toast.LENGTH_LONG).show();
            user = new User(email,password);
            appSharedPreferences.setUser(user);
            appSharedPreferences.setUserSession(true);

            finish();
            startActivity(new Intent(ActivitySignUp.this,ActivityHome.class));

        }else {
            Toast.makeText(ActivitySignUp.this,"Validation Not Successful",Toast.LENGTH_LONG).show();

        }
    }

    private boolean ConfirmPasswordValidation(String password, String confirmPassword) {
        boolean isTrue = false;
        if (confirmPassword.trim().isEmpty()) {
            etConfirmPassword.setError("Enter Password to Confirm");
        }else if(!(confirmPassword.trim().equalsIgnoreCase(password.trim()))){
            etConfirmPassword.setError("Password doesn't match");
        }else {
            isTrue = true;
        }
        return isTrue;
    }

    private boolean passwordValidation(String password) {
        boolean isValidate = false;
        if (password.trim().isEmpty()) {
            etPassword.setError("Enter Valid Password");

        }else if (password.trim().length() < 4) {
            etPassword.setError("Minimum length is 4");
        }else {
            isValidate = true;
        }
        return isValidate;
    }

    private boolean emailValidation(String email) {
        if (email.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter Valid Email");
            return false;
        }else {
            return true;
        }
    }

    private void setValues() {
        appSharedPreferences = new AppSharedPreferences(ActivitySignUp.this);
    }

    private void setViews() {
        etEmail = findViewById(R.id.activity_signup_et_email);
        etPassword = findViewById(R.id.activity_signup_et_password);
        etConfirmPassword = findViewById(R.id.activity_signup_et_confirm_password);
        btSignup = findViewById(R.id.activity_signup_bt_signup);
        tvLogin = findViewById(R.id.activity_signup_tv_login);
    }
}
