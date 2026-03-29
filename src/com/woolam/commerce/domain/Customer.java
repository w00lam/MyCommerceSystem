package com.woolam.commerce.domain;

public class Customer {
    // 고객 속성
    private final String name;
    private final String email;
    private String grade;

    // 생성자
    public Customer(String name, String email, String grade) {
        this.name = name;
        this.email = email;
        this.grade = grade;
    }
}
