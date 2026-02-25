package com.example.praktika;

import android.app.AlertDialog;
import android.content.*;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvPhone, tvEmail, tvAddress;
    ImageView btnEditName, btnEditPhone, btnEditEmail, btnEditAddress, btnBack;
    Button btnLogout, btnDeleteProfile;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        tvName = findViewById(R.id.tvProfileName);
        tvPhone = findViewById(R.id.tvProfilePhone);
        tvEmail = findViewById(R.id.tvProfileEmail);
        tvAddress = findViewById(R.id.tvProfileAddress);

        btnEditName = findViewById(R.id.btnEditName);
        btnEditPhone = findViewById(R.id.btnEditPhone);
        btnEditEmail = findViewById(R.id.btnEditEmail);
        btnEditAddress = findViewById(R.id.btnEditAddress);

        btnLogout = findViewById(R.id.btnLogout);
        btnDeleteProfile = findViewById(R.id.btnDeleteProfile);

        btnBack = findViewById(R.id.btnBack);

        loadData();

        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(this, HomePageActivity.class));
            finish();
        });

        btnEditName.setOnClickListener(v -> editValue("name", tvName));
        btnEditPhone.setOnClickListener(v -> editValue("phone", tvPhone));
        btnEditEmail.setOnClickListener(v -> editValue("email", tvEmail));
        btnEditAddress.setOnClickListener(v -> editValue("address", tvAddress));

        btnLogout.setOnClickListener(v -> logoutUser());
        btnDeleteProfile.setOnClickListener(v -> deleteProfile());
    }

    private void loadData() {
        tvName.setText(prefs.getString("name", ""));
        tvPhone.setText(prefs.getString("phone", ""));
        tvEmail.setText(prefs.getString("email", ""));
        tvAddress.setText(prefs.getString("address", ""));
    }

    private void editValue(String key, TextView target) {
        EditText input = new EditText(this);
        input.setText(target.getText());
        new AlertDialog.Builder(this)
                .setTitle("Изменить")
                .setView(input)
                .setPositiveButton("Сохранить", (d, w) -> {
                    prefs.edit().putString(key, input.getText().toString()).apply();
                    target.setText(input.getText());
                })
                .setNegativeButton("Отмена", null)
                .show();
    }

    private void logoutUser() {
        prefs.edit().putBoolean("isLoggedIn", false).apply();
        Intent i = new Intent(this, SignInActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    private void deleteProfile() {
        prefs.edit().clear().apply();
        Intent i = new Intent(this, SignUpActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = prefs.getString("name", "guest");

        OrderManager.clearHistory(ProfileActivity.this, username);

    }
}
