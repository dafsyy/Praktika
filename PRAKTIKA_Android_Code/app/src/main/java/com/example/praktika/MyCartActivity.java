package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    TextView tvTotalPrice;
    Button btnCheckout;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        recyclerCart = findViewById(R.id.recyclerCart);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnBack = findViewById(R.id.btnBack);

        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

        List<Coffee> cartList = CartManager.getCart();

        CartAdapter adapter = new CartAdapter(cartList, this::updateTotal);
        recyclerCart.setAdapter(adapter);

        updateTotal();

        btnCheckout.setOnClickListener(v -> checkout());
        btnBack.setOnClickListener(v -> {
            startActivity(new Intent(this, HomePageActivity.class));
            finish();
        });
    }

    private void updateTotal() {
        double total = CartManager.getTotalPrice();
        tvTotalPrice.setText("Total Price: $" + total);
    }

    private void checkout() {
        List<Coffee> items = CartManager.getCart();
        if (items.isEmpty()) return;

        double total = CartManager.getTotalPrice();

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = prefs.getString("name", "guest");

        OrderManager.addOrder(MyCartActivity.this, username, items, total);

        CartManager.clearCart();

        startActivity(new Intent(MyCartActivity.this, OrderHistoryActivity.class));
        finish();
    }
}
