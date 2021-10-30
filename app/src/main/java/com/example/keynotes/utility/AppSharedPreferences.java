package com.example.keynotes.utility;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import static com.example.keynotes.utility.AppConstants.APP_NAME;
import static com.example.keynotes.utility.AppConstants.IS_LOGGED_IN;
import static com.example.keynotes.utility.AppConstants.USER;

public class AppSharedPreferences {
    SharedPreferences sharedPreferences;

    public AppSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_NAME, Context.MODE_PRIVATE);
    }

    public void setUserSession(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    public void setUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.USER,new Gson().toJson(user));
        editor.apply();
    }


    public boolean getUserSession() {

        return sharedPreferences.getBoolean(IS_LOGGED_IN,false);
    }

    public User getUser(){
      String userJson = sharedPreferences.getString(USER,null);
      User user = null;
      if (!userJson.trim().isEmpty() ) {
          user = new Gson().fromJson(userJson,User.class);
      }
        return user;
    }
}


