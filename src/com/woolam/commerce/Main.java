package com.woolam.commerce;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 상품 객체를 담을 리스트
        List<Product> products = new ArrayList<>();
        // 출력 포맷을 위한 유틸리티 객체
        AtomicInteger index = new AtomicInteger(1);
        DecimalFormat df = new DecimalFormat("###,###");

        // 상품 생성
        products.add(new Product("Galaxy S25", 1200000, "최신 스마트폰", 50));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 50));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 50));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50));

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            // 포맷에 맞게 상품 리스트 출력
            products.stream()
                    .map(p -> String.format("%d. %-15s | %10s원 | %s",
                            index.getAndIncrement(), p.name, df.format(p.price), p.description))
                    .forEach(System.out::println);
            System.out.printf("%d. %-13s | %s%n", 0, "종료", "프로그램 종료");

            int command = sc.nextInt();

            if (command == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                System.exit(0);
            }
        }
    }
}
