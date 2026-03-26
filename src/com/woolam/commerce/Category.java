package com.woolam.commerce;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.IntStream;

public class Category implements Display {
    // 카테고리 속성
    private final String category;
    private final List<Product> products;

    // 출력 포맷을 위한 유틸리티 객체
    private final DecimalFormat df = new DecimalFormat("###,###");

    // 생성자
    public Category(String category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    // 카테고리 이름 반환
    public String getCategory() {
        return category;
    }

    // 해당 카테고리의 상품들을 포맷에 맞춰 출력
    @Override
    public void display() {
        System.out.println("[ " + this.category + " 카테고리 ]");
        IntStream.range(0, this.products.size())
                .forEach(i -> {
                    System.out.printf("%d. %-15s | %10s원 | %s%n",
                            i + 1,
                            products.get(i).getName(),
                            df.format(products.get(i).getPrice()),
                            products.get(i).getDescription());
                });
        System.out.println("0. 뒤로가기");
    }

    // 카테고리의 하위 항목이 존재하는지
    @Override
    public boolean hasNext() {
        return !products.isEmpty();
    }

    // 상품 리스트가 비여있는지
    @Override
    public boolean isEmpty() {
        return products.isEmpty();
    }

    // 카테고리를 선택하면 상품 리스트를 보여줘야함
    @Override
    public List<Product> getNextDisplay() {
        return this.products;
    }
}