package com.woolam.commerce;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CommerceSystem {
    // 커머스 시스템 속성
    Scanner scanner;
    List<Category> categories;

    // 출력 포맷을 위한 유틸리티 객체
    AtomicInteger index = new AtomicInteger(1);

    // 생성자
    public CommerceSystem(List<Category> categories, Scanner scanner) {
        this.categories = categories;
        this.scanner = scanner;
    }

    // 커머스 관리 메뉴
    public void start() {
        while (true) {
            index.set(1);
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            // 포맷에 맞게 메뉴 출력
            categories.stream()
                    .map(c -> String.format("%d. %s", index.getAndIncrement(), c.getCategory()))
                    .forEach(System.out::println);

            System.out.printf("%d. %-6s | %s%n", 0, "종료", "프로그램 종료");
            int command = scanner.nextInt();

            switch (command) {
                // 전자 제품 카테고리
                case 1: {
                    Category category = categories.getFirst();
                    List<Product> electronics = category.getProducts();
                    if (electronics.isEmpty()) {
                        System.out.println("해당 카테고리에 상품이 없습니다.\n");
                    } else {
                        category.getProductList(electronics);
                        System.out.println("0. 뒤로가기");
                        int number = scanner.nextInt();

                        if (number == 0) {
                            break;
                        } else if (1 <= number && number <= electronics.size()) {
                            Product product = category.getProducts().get(number - 1);
                            product.getProductInfo();
                        } else {
                            System.out.println("없는 상품 번호입니다.\n");
                            continue;
                        }
                    }
                    break;
                }
                // 의류 카테고리
                case 2: {
                    Category category = categories.get(1);
                    List<Product> clothes = category.getProducts();
                    if (clothes.isEmpty()) {
                        System.out.println("해당 카테고리에 상품이 없습니다.\n");
                    } else {
                        category.getProductList(clothes);
                        System.out.println("0. 뒤로가기");
                        int number = scanner.nextInt();

                        if (number == 0) {
                            break;
                        } else if (1 <= number && number <= clothes.size()) {
                            Product product = clothes.get(number - 1);
                            product.getProductInfo();
                        } else {
                            System.out.println("다시 입력해 주세요.\n");
                        }
                    }
                }
                // 식품 카테고리
                case 3: {
                    Category category = categories.get(2);
                    List<Product> groceries = category.getProducts();
                    if (groceries.isEmpty()) {
                        System.out.println("해당 카테고리에 상품이 없습니다.\n");
                    } else {
                        category.getProductList(groceries);
                        System.out.println("0. 뒤로가기");
                        int number = scanner.nextInt();

                        if (number == 0) {
                            break;
                        } else if (1 <= number && number <= groceries.size()) {
                            Product product = groceries.get(number - 1);
                            product.getProductInfo();
                        } else {
                            System.out.println("다시 입력해 주세요.\n");
                        }
                    }
                }
                case 0: {
                    break;
                }
            }

            if (command == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        }
    }
}
