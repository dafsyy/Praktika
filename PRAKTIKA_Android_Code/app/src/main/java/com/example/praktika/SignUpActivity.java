package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    EditText editName, editPhone, editEmail, editPassword, editRepeatPassword;
    Button btnRegister;
    TextView textLogin;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        editEmail = findViewById(R.id.edit_email);
        editPassword = findViewById(R.id.edit_password);
        editRepeatPassword = findViewById(R.id.edit_repeat_password);

        btnRegister = findViewById(R.id.btn_register);
        textLogin = findViewById(R.id.text_login);

        btnRegister.setOnClickListener(v -> registerUser());
        textLogin.setOnClickListener(v -> startActivity(new Intent(this, SignInActivity.class)));
    }

    private void registerUser() {

        String name = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String pass1 = editPassword.getText().toString().trim();
        String pass2 = editRepeatPassword.getText().toString().trim();

        resetColors();

        if (name.isEmpty()) { setError(editName,"Введите имя"); return; }
        if (!email.contains("@") || !email.contains(".")) { setError(editEmail, "Некорректная почта"); return; }
        if (pass1.isEmpty() || pass2.isEmpty()) { setError(editPassword, "Введите пароль"); return; }
        if (!pass1.equals(pass2)) { setError(editRepeatPassword, "Пароли не совпадают"); return; }

        SharedPreferences.Editor ed = prefs.edit();
        ed.putString("name", name);
        ed.putString("phone", phone);
        ed.putString("email", email);
        ed.putString("password", pass1);
        ed.putBoolean("isLoggedIn", true);
        ed.apply();

        Toast.makeText(this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, HomePageActivity.class));
        finish();
    }

    private void resetColors() {
        editName.setBackgroundColor(Color.TRANSPARENT);
        editPhone.setBackgroundColor(Color.TRANSPARENT);
        editEmail.setBackgroundColor(Color.TRANSPARENT);
        editPassword.setBackgroundColor(Color.TRANSPARENT);
        editRepeatPassword.setBackgroundColor(Color.TRANSPARENT);
    }

    private void setError(EditText e, String m){
        e.setBackgroundColor(Color.parseColor("#FFCDD2"));
        e.setError(m);
    }
}
