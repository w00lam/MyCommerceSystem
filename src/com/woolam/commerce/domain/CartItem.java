package com.woolam.commerce.domain;

public class CartItem {
    private final Product product; // 원본 상품 객체
    private int quantity;          // 내가 담은 수량

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // 수량 추가
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    // Getter
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // 주문할 상품 출력
    public void display() {
        System.out.printf("%-15s | %,10d원 | %-15s | 수량: %d개%n",
                this.getProduct().getName(), this.getProduct().getPrice(), this.getProduct().getDescription(), this.getQuantity());
    }

    // 재고 업데이트
    public void updateQuantity() {
        Product product = this.getProduct();
        int stock = product.getStock();
        product.setStock(stock - this.getQuantity());
        System.out.printf("%s 재고가 %d개 → %d개로 업데이트 되었습니다.%n", product.getName(), stock, product.getStock());
    }

    public boolean equals(Product product) {
        return product.getName().equals(this.getProduct().getName());
    }

    // 계산 편의 메서드
    public long getSubTotal() {
        return (long) product.getPrice() * quantity;
    }
}
