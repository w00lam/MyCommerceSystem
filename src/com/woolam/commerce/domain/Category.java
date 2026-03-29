package com.woolam.commerce.domain;

import java.util.List;

public class Category {
    // 속성
    private final String label;
    private List<Product> products;

    // 생성자
    public Category(String label, List<Product> products) {
        this.label = label;
        this.products = products;
    }

    // Getter
    public String getLabel() {
        return label;
    }
    public List<Product> getProducts() {
        return products;
    }
}