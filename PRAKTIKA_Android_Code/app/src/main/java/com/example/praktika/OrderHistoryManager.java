package com.example.praktika;

import android.content.Context;
import android.content.SharedPreferences;

public class OrderHistoryManager {

    private static final String PREF_NAME = "OrderHistory";

    public static void addOrder(Context context, String username, String orderText) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String key = "history_" + username;
        String oldHistory = prefs.getString(key, "");
        String newHistory = oldHistory + orderText + "\n";

        prefs.edit().putString(key, newHistory).apply();
    }

    public static String getHistory(Context context, String username) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String key = "history_" + username;

        return prefs.getString(key, "");
    }

    public static void clearHistory(Context context, String username) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String key = "history_" + username;

        prefs.edit().remove(key).apply();
    }
}
