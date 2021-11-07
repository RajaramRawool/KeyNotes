package com.example.keynotes.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static com.example.keynotes.util.AppConstants.APP_NAME;
import static com.example.keynotes.util.AppConstants.IS_LOGGED_IN;
import static com.example.keynotes.util.AppConstants.USER;

public class AppSharedPreferences {
    SharedPreferences sharedPreferences;
    User user;

    public AppSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE);
    }

    public void setUserSession(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN,isLoggedIn);
        editor.apply();
    }

    public boolean getUserSession() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }

    public void setUser(String user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER,user);
        editor.apply();
    }

    public User getUser() {
        String jsonUser = sharedPreferences.getString(USER,null);
        User user = null;
        if (  jsonUser != null && !jsonUser.isEmpty()  ) {
            Gson gson = new Gson();
            user = gson.fromJson(jsonUser, User.class);
        }
        return user;

    }

}
