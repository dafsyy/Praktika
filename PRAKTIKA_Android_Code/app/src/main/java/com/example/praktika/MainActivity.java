package com.example.praktika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 3000; // 3 секунды загрузки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // это splash-экран

        new Handler().postDelayed(() -> {

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

            boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

            Intent intent;

            if (isLoggedIn) {
                // Пользователь авторизован → на главную
                intent = new Intent(MainActivity.this, HomePageActivity.class);
            } else {
                // Не авторизован → на SignIn (не на регистрацию!)
                intent = new Intent(MainActivity.this, SignInActivity.class);
            }

            startActivity(intent);
            finish();

        }, SPLASH_TIME);
    }
}
