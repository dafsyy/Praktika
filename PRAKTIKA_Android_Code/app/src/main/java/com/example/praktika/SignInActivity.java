package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText editEmail, editPassword;
    Button btnLogin;
    TextView textRegister;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);
        textRegister = findViewById(R.id.text_register);

        btnLogin.setOnClickListener(v -> login());
        textRegister.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));
    }

    private void login() {

        String email = editEmail.getText().toString().trim();
        String pass = editPassword.getText().toString().trim();

        String savedEmail = prefs.getString("email", "");
        String savedPass = prefs.getString("password", "");

        resetColors();

        if (!email.equals(savedEmail)) {
            setError(editEmail, "Почта не найдена");
            return;
        }

        if (!pass.equals(savedPass)) {
            setError(editPassword, "Неверный пароль");
            return;
        }

        prefs.edit().putBoolean("isLoggedIn", true).apply();

        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }

    private void resetColors() {
        editEmail.setBackgroundColor(Color.TRANSPARENT);
        editPassword.setBackgroundColor(Color.TRANSPARENT);
    }

    private void setError(EditText e, String m) {
        e.setBackgroundColor(Color.parseColor("#FFCDD2"));
        e.setError(m);
    }
}
