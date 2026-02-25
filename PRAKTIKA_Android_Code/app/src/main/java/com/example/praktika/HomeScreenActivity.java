package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        boolean isLogged = prefs.getBoolean("isLoggedIn", false);
        boolean isWelcomeShown = prefs.getBoolean("welcomeShown", false);

        new Handler().postDelayed(() -> {

            if (!isWelcomeShown) {
                // показываем welcome первый раз
                prefs.edit().putBoolean("welcomeShown", true).apply();
                startActivity(new Intent(HomeScreenActivity.this, WelcomeActivity.class));
            }
            else if (isLogged) {
                // пользователь уже вошёл
                startActivity(new Intent(HomeScreenActivity.this, HomePageActivity.class));
            } else {
                // пользователь не авторизован
                startActivity(new Intent(HomeScreenActivity.this, SignInActivity.class));
            }

            finish();

        }, 4000); // 4 сек splash
    }
}
