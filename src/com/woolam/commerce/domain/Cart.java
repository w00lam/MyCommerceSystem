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

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    public long getTotalPrice() {
        return items.stream().mapToLong(CartItem::getSubTotal).sum();
    }
}