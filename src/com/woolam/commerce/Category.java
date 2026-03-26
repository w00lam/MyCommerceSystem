package com.woolam.commerce;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Category {
    // 카테고리 속성
    private final String category;
    private final List<Product> products;

    // 출력 포맷을 위한 유틸리티 객체
    DecimalFormat df = new DecimalFormat("###,###");
    static AtomicInteger index = new AtomicInteger(1);

    // 생성자
    public Category(String category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    // 카테고리 이름 반환
    public String getCategory() {
        return category;
    }

    // 상품 리스트 반환
    public List<Product> getProducts() {
        return products;
    }

    // 카테고리 목록을 출력
    public static void getCategoryList(List<Category> categories) {
        index.set(1);
        categories.stream()
                .map(c -> String.format("%d. %s", index.getAndIncrement(), c.getCategory()))
                .forEach(System.out::println);
    }

    // 해당 카테고리의 상품들을 포맷에 맞춰 출력
    public void getProductList(List<Product> products) {
        index.set(1);
        System.out.println("[ " + this.getCategory() + " 카테고리 ]");
        products.stream()
                .map(p -> String.format("%d. %-15s | %10s원 | %s",
                        index.getAndIncrement(), p.getName(), df.format(p.getPrice()), p.getDescription()))
                .forEach(System.out::println);
    }
}