package com.example.praktika;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CoffeeAdapter coffeeAdapter;
    ArrayList<Coffee> coffeeList = new ArrayList<>();

    Button btnMilk, btnNoMilk, btnStrong, btnMedium, btnLight, btnAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // ------------------------- ПОЛЬЗОВАТЕЛЬ -------------------------------
        TextView welcomeName = findViewById(R.id.text_username);
        ImageButton iconCart = findViewById(R.id.btnCart);
        ImageButton iconProfile = findViewById(R.id.btnProfile);

        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String name = prefs.getString("name", "Гость");
        welcomeName.setText(name);

        iconCart.setOnClickListener(v ->
                startActivity(new Intent(HomePageActivity.this, MyCartActivity.class)));

        iconProfile.setOnClickListener(v ->
                startActivity(new Intent(HomePageActivity.this, ProfileActivity.class)));

        // ------------------------- РЕЦИКЛЕР -------------------------------
        recyclerView = findViewById(R.id.recyclerCoffeeGrid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        loadCoffeeData();
        coffeeAdapter = new CoffeeAdapter(coffeeList, this);
        recyclerView.setAdapter(coffeeAdapter);

        // ------------------------- КАТЕГОРИИ -------------------------------
        btnMilk = findViewById(R.id.btnCategoryMilk);
        btnNoMilk = findViewById(R.id.btnCategoryNoMilk);
        btnStrong = findViewById(R.id.btnCategoryStrong);
        btnMedium = findViewById(R.id.btnCategoryMedium);
        btnLight = findViewById(R.id.btnCategoryLight);
        btnAll = findViewById(R.id.btnCategoryAll);

        btnMilk.setOnClickListener(v -> filterCoffee("milk"));
        btnNoMilk.setOnClickListener(v -> filterCoffee("nomilk"));
        btnStrong.setOnClickListener(v -> filterCoffee("strong"));
        btnMedium.setOnClickListener(v -> filterCoffee("medium"));
        btnLight.setOnClickListener(v -> filterCoffee("light"));
        btnAll.setOnClickListener(v -> showAllCoffee());


        // Нижняя навигация
        ImageButton btnNavHome = findViewById(R.id.btnNavHome);
        ImageButton btnNavOrders = findViewById(R.id.btnNavOrders);

        btnNavHome.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        });

        btnNavOrders.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });

    }

    private void loadCoffeeData() {
        coffeeList.clear();

        coffeeList.add(new Coffee("Американо", "$3.00", R.drawable.americano, "nomilk", "strong", "Классический креппкий черный кофе."));
        coffeeList.add(new Coffee("Капучино", "$3.50", R.drawable.capuccino, "milk", "medium", "Эспрессо с взбитым молоком и легкой пенкой."));
        coffeeList.add(new Coffee("Латте", "$4.00", R.drawable.latte, "milk", "light", "Мягкий кофе с большим количеством молока."));
        coffeeList.add(new Coffee("Эспрессо", "$2.50", R.drawable.espresso, "nomilk", "strong", "Короткий и насыщенный эспрессо"));
        coffeeList.add(new Coffee("Макиато", "$3.20", R.drawable.mocha, "milk", "medium", "Эспрессо с каплей молока и ярким вкусом"));
        coffeeList.add(new Coffee("Флэт Уайт", "$3.80", R.drawable.flatwhite, "milk", "strong", "Плотное молоко и выраженный вкус эспрессо"));
    }

    private void filterCoffee(String category) {
        ArrayList<Coffee> filtered = new ArrayList<>();

        for (Coffee c : coffeeList) {
            if (c.type.equals(category) || c.strength.equals(category)) {
                filtered.add(c);
            }
        }

        coffeeAdapter.updateList(filtered);
    }

    private void showAllCoffee() {
        coffeeAdapter.updateList(coffeeList);
    }
}