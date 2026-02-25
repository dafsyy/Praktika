package com.example.praktika;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static final List<Coffee> cartList = new ArrayList<>();

    public static void addToCart(Coffee coffee) {
        cartList.add(coffee);
    }

    public static List<Coffee> getCart() {
        return cartList;
    }

    public static void removeAt(int index) {
        if (index >= 0 && index < cartList.size()) {
            cartList.remove(index);
        }
    }

    public static void clearCart() {
        cartList.clear();
    }

    // Всё основано на Coffee.price → String
    public static double getTotalPrice() {
        double total = 0;

        for (Coffee c : cartList) {
            try {
                String p = c.price
                        .replace("$", "")
                        .replace("₽", "")
                        .trim();

                total += Double.parseDouble(p);
            } catch (Exception ignored) {}
        }
        return total;
    }
}
