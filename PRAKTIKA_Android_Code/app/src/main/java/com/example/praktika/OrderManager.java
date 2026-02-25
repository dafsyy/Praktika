package com.example.praktika;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private static final String PREFS = "OrderPrefs";

    // ---------------- Сохранение заказа ----------------
    public static void addOrder(Context ctx, String username, List<Coffee> items, double total) {

        SharedPreferences prefs = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        String old = prefs.getString(username, "");

        // формируем строку:
        // дата;название1|название2|название3;сумма
        StringBuilder sb = new StringBuilder();
        long date = System.currentTimeMillis();

        List<String> names = new ArrayList<>();
        for (Coffee c : items) {
            names.add(c.getName());  // только имя!
        }

        sb.append(date).append(";")
                .append(String.join("|", names)).append(";")
                .append(total);

        String result = old.isEmpty() ? sb.toString() : old + "\n" + sb.toString();

        prefs.edit().putString(username, result).apply();
    }


    // ------------- Получение истории строкой -------------
    public static String getHistory(Context ctx, String username) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return prefs.getString(username, "");
    }


    // ------------- Очистка истории --------------
    public static void clearHistory(Context ctx, String username) {
        SharedPreferences prefs = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs.edit().remove(username).apply();
    }


    // ------------ Превращаем историю в список моделей ------------
    public static List<OrderModel> parseHistory(String history) {

        List<OrderModel> list = new ArrayList<>();
        if (history == null || history.isEmpty()) return list;

        String[] rows = history.split("\n");

        for (String r : rows) {
            String[] parts = r.split(";");

            if (parts.length != 3) continue;

            String date = parts[0];
            String namesStr = parts[1];
            String totalStr = parts[2];

            double total = Double.parseDouble(totalStr);

            List<Coffee> coffeeItems = new ArrayList<>();
            for (String name : namesStr.split("\\|")) {
                coffeeItems.add(
                        new Coffee(name, "0", 0, "", "", "")  // цена НЕ НУЖНА в истории
                );
            }

            list.add(new OrderModel(date, coffeeItems, total));
        }

        return list;
    }

}
