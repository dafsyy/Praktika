package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    ImageButton btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(v -> {

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            prefs.edit().putBoolean("isFirstLaunch", false).apply();

            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        });
    }
}
