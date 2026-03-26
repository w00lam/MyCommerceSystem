package com.woolam.commerce;

import java.text.DecimalFormat;
import java.util.List;

public class Product implements Display {
    // 상품 속성
    private final String name;
    private final int price;
    private final String description;
    private final int count;

    // 출력 포맷을 위한 유틸리티 객체
    private final DecimalFormat df = new DecimalFormat("###,###");

    // 생성자
    public Product(String name, int price, String description, int count) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.count = count;
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

    // 상품 정보를 포맷에 맞게 출력
    @Override
    public void display() {
        System.out.printf("선택한 상품: %s | %s | %s | 재고: %d%n%n",
                this.name, df.format(this.price), this.description, this.count);
    }

    // 상품의 하위 항목은 존재하지 않음으로 false 반환
    @Override
    public boolean hasNext() {
        return false;
    }

    // 상품의 하위 항목은 존재하지 않음으로 false 반환
    @Override
    public boolean isEmpty() {
        return false;
    }

    // 상품의 하위 항목이 존재하지 않음으로 null 반환
    @Override
    public List<Display> getNextDisplay() {
        return null;
    }
}
