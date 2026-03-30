package com.woolam.commerce.domain;

import java.util.function.Function;

public enum CustomerGrade {
    BRONZE(1, 0.0, price -> price),
    SILVER(2, 0.05, price -> (long)(price * (1 - 0.05))),
    GOLD(3, 0.10, price -> (long)(price * (1 - 0.10))),
    PLATINUM(4, 0.15, price -> (long)(price * (1 - 0.15)));

    private final int menuNumber;
    private final double discountRate;
    private final Function<Long, Long> discountPolicy;

    CustomerGrade(int menuNumber, double discountRate,
                  Function<Long, Long> discountPolicy) {
        this.menuNumber = menuNumber;
        this.discountRate = discountRate;
        this.discountPolicy = discountPolicy;
    }

    public long applyDiscount(long price) {
        return discountPolicy.apply(price);
    }

    public long calculateDiscountAmount(long price) {
        return price - applyDiscount(price);
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public static CustomerGrade from(int input) {
        for (CustomerGrade grade : values()) {
            if (grade.menuNumber == input) {
                return grade;
            }
        }
        throw new IllegalArgumentException("잘못된 등급 입력");
    }
}