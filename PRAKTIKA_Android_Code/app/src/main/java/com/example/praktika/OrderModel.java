package com.example.praktika;

import java.util.List;

public class OrderModel {

    String date;
    List<Coffee> items;
    double total;

    public OrderModel(String date, List<Coffee> items, double total) {
        this.date = date;
        this.items = items;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public List<Coffee> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }
}
