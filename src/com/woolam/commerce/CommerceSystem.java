package com.woolam.commerce;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CommerceSystem {
    // 커머스 시스템 속성
    List<Product> products;
    Scanner scanner;

    // 출력 포맷을 위한 유틸리티 객체
    AtomicInteger index = new AtomicInteger(1);
    DecimalFormat df = new DecimalFormat("###,###");

    public CommerceSystem(List<Product> products, Scanner scanner) {
        this.products = products;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            // 포맷에 맞게 상품 리스트 출력
            products.stream()
                    .map(p -> String.format("%d. %-15s | %10s원 | %s",
                            index.getAndIncrement(), p.name, df.format(p.price), p.description))
                    .forEach(System.out::println);
            System.out.printf("%d. %-13s | %s%n", 0, "종료", "프로그램 종료");

            int command = scanner.nextInt();

            if (command == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                System.exit(0);
            }
        }
    }
}
