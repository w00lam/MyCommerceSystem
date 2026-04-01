package com.woolam.commerce.domain;

import java.util.List;

/**
 * 커머스 시스템의 장바구니를 나타내는 클래스
 *
 * <p>장바구니에 담긴 상품을 관리하며
 * 상품 추가와 제거, 총 금액 등의 비즈니스 로직을 관리한다.</p>
 *
 * @author woolam (contact : woolam@example.com)
 * @since 2026-03-31
 */
public class Cart {
    private final List<CartItem> items;

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    /**
     * 장바구니에 상품을 추가해주는 함수
     *
     * <p>상품이 주어졌을때 장바구니에 상품을 추가하며
     * 이미 추가된 상품이면 수량만 늘려준다.</p>
     *
     * @param product 추가할 상품
     *
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
    public void addItem(Product product) {
        for (CartItem item : items) {
            if (item.equals(product)) {
                item.addQuantity(1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }


    /**
     * 장바구니 정보를 보여주는 함수
     *
     * <p>장바구니 리스트를 보여주고 총 금액을 출력하며
     * 장바구니가 비어있을경우 메시지를 출력해준다.</p>
     *
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
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

    /**
     * 장바구니가 비어있는지 확인하는 함수
     *
     * @return boolean
     *
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * 장바구니를 비우는 함수
     *
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
    public void clear() {
        items.clear();
    }

    /**
     * 장바구니에 담긴 상품의 총 금액을 반환해주는 함수
     *
     * @return 상품의 총 금액
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
    public long getTotalPrice() {
        return items.stream().mapToLong(CartItem::getSubTotal).sum();
    }

    /**
     * 장바구니에 담긴 상품을 제거하는 함수
     *
     * @param product 제거할 상품
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    /**
     * 이름이 일치하는 상품을 반환하는 함수
     *
     * @param keyword 찾고 싶은 상품
     * @return item 이름이 일치하는 상품
     *
     * @author woolam (contact : woolam@example.com)
     * @since 2026-03-31
     */
    public boolean removeByProductName(String keyword) {
        return items.removeIf(item ->
                item.getProduct().getName().contains(keyword)
        );
    }
}