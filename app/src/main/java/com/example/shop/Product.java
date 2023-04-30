package com.example.shop;

public class Product {
    private String id;
    private String name;
    private String description;
    private String price;
    private float rating;
    private int imageResource;
    private int cartedCount;

    public Product(String name, String description, String price, float rating, int imageResource, int cartedCount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageResource = imageResource;
        this.cartedCount = cartedCount;
    }

    public Product() {
    }

    public String _getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPrice() {
        return price;
    }
    public float getRating() {
        return rating;
    }
    public int getImageResource() {
        return imageResource;
    }
    public int getCartedCount() {
        return cartedCount;
    }

    public void setId(String id) {
        this.id = id;
    }
}
