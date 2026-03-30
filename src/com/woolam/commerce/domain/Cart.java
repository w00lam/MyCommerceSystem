package com.woolam.commerce.domain;

import java.util.List;

public class Cart {
    private final List<CartItem> items;

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    // 상품 추가 (이미 있으면 수량만 +1)
    public void addItem(Product product) {
        for (CartItem item : items) {
            if (item.equals(product)) {
                item.addQuantity(1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    public void display() {
        System.out.println("\n[ 장바구니 내역 ]");

        if (items.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        for (CartItem item : items) {
            item.display();
        }

        System.out.printf("\n[ 총 금액 ]\n%,d원%n", getTotalPrice());
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public long getTotalPrice() {
        return items.stream().mapToLong(CartItem::getSubTotal).sum();
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public boolean removeByProductName(String keyword) {
        return items.removeIf(item ->
                item.getProduct().getName().contains(keyword)
        );
    }
}