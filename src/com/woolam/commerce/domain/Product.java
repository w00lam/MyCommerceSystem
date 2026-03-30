package com.woolam.commerce.domain;

public class Product {
    // 속성
    private final Category category;
    private final String name;
    private int price;
    private String description;
    private int stock;

    // 생성자
    public Product(Category category, String name, int price, String description, int stock) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // Getter
    public Category getCategory() {
        return category;
    }

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

    // Setter
    public void setStock(int stock) {
        this.stock = stock;
    }

    // 가격 수정
    public void updatePrice(int newPrice) {
        int oldPrice = this.price;
        if (newPrice < 0) throw new IllegalArgumentException("가격 오류");
        this.price = newPrice;
        System.out.printf("%s의 가격이 %,d원 → %,d원으로 수정되었습니다.", this.getName(), oldPrice, newPrice);
    }

    // 설명 수정
    public void updateDescription(String newDescription) {
        String oldDescription = this.description;
        this.description = newDescription;
        System.out.printf("%s의 설명이 %s → %s(으)로 수정되었습니다.", this.getName(), oldDescription, newDescription);
    }

    // 재고 수정
    public void updateStock(int newStock) {
        int oldStock = this.stock;
        if (newStock < 0) throw new IllegalArgumentException("재고 오류");
        this.stock = newStock;
        System.out.printf("%s의 재고가 %d → %d개로 수정되었습니다.", this.getStock(), oldStock, newStock);
    }

    // 상품 정보 출력
    public void displayInfo() {
        System.out.printf(
                "현재 상품 정보: %s | %,d | %s | 재고: %d개%n%n",
                this.getName(), this.getPrice(), this.getDescription(), this.getStock()
        );
    }
}
