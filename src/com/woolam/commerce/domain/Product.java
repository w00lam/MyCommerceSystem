package com.woolam.commerce.domain;

public class Product {
    // 속성
    private final String name;
    private int price;
    private final String description;
    private int stock;

    // 생성자
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStock() {
        return stock;
    }
}
