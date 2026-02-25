package com.example.praktika;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    // UI
    private ImageView imgCoffeeDetail;
    private ImageView btnBack;
    private TextView tvCoffeeName;
    private TextView tvCoffeeDescription;
    private TextView tvCoffeePrice;
    private Button btnAddToCart;

    // поля для текущего кофе (доступны в обработчиках)
    private int coffeeImage = -1;
    private String coffeeName = "";
    private String coffeePrice = "";
    private String coffeeDesc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // findViewById
        imgCoffeeDetail = findViewById(R.id.imgCoffeeDetail);
        btnBack = findViewById(R.id.btnBack);
        tvCoffeeName = findViewById(R.id.tvCoffeeName);
        tvCoffeeDescription = findViewById(R.id.tvCoffeeDescription);
        tvCoffeePrice = findViewById(R.id.tvCoffeePrice);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        // Получаем данные из Intent (адаптер должен их отправлять)
        Intent i = getIntent();
        if (i != null) {
            // используем те же ключи, которые отправляет адаптер: "coffee_image", "coffee_name", "coffee_desc", "coffee_price"
            coffeeImage = i.getIntExtra("coffee_image", -1);
            String tmpName = i.getStringExtra("coffee_name");
            String tmpDesc = i.getStringExtra("coffee_desc");
            String tmpPrice = i.getStringExtra("coffee_price");

            if (tmpName != null) coffeeName = tmpName;
            if (tmpDesc != null) coffeeDesc = tmpDesc;
            if (tmpPrice != null) coffeePrice = tmpPrice;

            // Отображаем в UI (если ресурс картинки валиден)
            if (coffeeImage != -1) {
                imgCoffeeDetail.setImageResource(coffeeImage);
            }
            tvCoffeeName.setText(coffeeName);
            tvCoffeeDescription.setText(coffeeDesc);
            tvCoffeePrice.setText(coffeePrice);
        }

        // Назад
        btnBack.setOnClickListener(v -> finish());

        // Добавить в корзину — используем поля класса, которые уже инициализированы
        btnAddToCart.setOnClickListener(v -> {

            // создаём объект Coffee — проверь, что конструктор Coffee принимает ровно 6 аргументов
            Coffee coffee = new Coffee(
                    coffeeName,
                    coffeePrice,
                    coffeeImage,
                    "none",       // type (можешь заменить)
                    "none",       // strength (можешь заменить)
                    coffeeDesc
            );

            // добавляем в глобальную корзину
            CartManager.addToCart(coffee);

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String username = prefs.getString("name", "guest");

            // сохраняем историю заказа
            OrderHistoryManager.addOrder(
                    DetailsActivity.this,
                    username,
                    coffeeName + " — " + coffeePrice
            );

            // открываем экран корзины
            Intent intent = new Intent(DetailsActivity.this, MyCartActivity.class);
            startActivity(intent);
        });
    }
}
