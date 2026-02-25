package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerOrders;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        recyclerOrders = findViewById(R.id.recyclerOrders);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(v -> finish());

        // имя пользователя
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = prefs.getString("name", "guest");

        // получаем историю в виде строки
        String rawHistory = OrderManager.getHistory(this, username);

        // превращаем в объекты OrderModel
        List<OrderModel> historyList = OrderManager.parseHistory(rawHistory);

        recyclerOrders.setLayoutManager(new LinearLayoutManager(this));
        recyclerOrders.setAdapter(new OrderHistoryAdapter(historyList));

        // ---------- НИЖНЯЯ НАВИГАЦИЯ ----------
        ImageButton btnNavHome = findViewById(R.id.btnNavHome);
        ImageButton btnNavOrders = findViewById(R.id.btnNavOrders);

        btnNavHome.setOnClickListener(v -> {
            Intent intent = new Intent(OrderHistoryActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        });

        btnNavOrders.setOnClickListener(v -> {
            // остаёмся на этой же странице
        });
    }
}
