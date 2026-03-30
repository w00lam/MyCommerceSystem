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

    // 상품 추가
    public void addProduct(Product product) {
        validateDuplicate(product.getName());
        products.add(product);
    }

    // 상품 삭제
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // 중복 상품 탐색
    private void validateDuplicate(String name) {
        boolean exists = products.stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));

        if (exists) {
            throw new IllegalArgumentException("이미 존재하는 상품입니다.");
        }
    }
}