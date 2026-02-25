package com.example.praktika;


public class Coffee {
    String name;
    String price;
    int image;
    String type;      // milk / nomilk
    String strength;  // strong / medium / light
    String description; // new

    public Coffee(String name, String price, int image, String type, String strength, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.type = type;
        this.strength = strength;
        this.description = description;
    }

    // getters (если понадобятся)
    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImage() { return image; }
    public String getType() { return type; }
    public String getStrength() { return strength; }
    public String getDescription() { return description; }
}

