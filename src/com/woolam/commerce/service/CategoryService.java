package com.woolam.commerce.service;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.dto.ServiceResponse;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CategoryService implements Service {
    // 속성
    private List<Category> categories;
    private final Scanner scanner;

    // 생성자
    public CategoryService(List<Category> categories, Scanner scanner) {
        this.categories = categories;
        this.scanner = scanner;
    }

    // 카테고리 서비스 실행
    @Override
    public ServiceResponse run(Object inputData) {
        display();
        int command = scanner.nextInt();

        if (command == 0) {
            return new ServiceResponse("exit", null);
        }

        try {
            Category selectedCategory = categories.get(command - 1);
            return new ServiceResponse("productService", selectedCategory);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n잘못된 번호입니다. 다시 선택해주세요.");
            return new ServiceResponse("categoryService", null);
        }
    }

    // 카테고리 리스트 출력
    private void display() {
        if (categories.isEmpty()) {
            System.out.println("[상품 준비중]");
        } else {
            System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
            IntStream.range(0, categories.size())
                    .forEach(i -> {
                        System.out.printf("%d. %s%n", i + 1, categories.get(i).getLabel());
                    });
            System.out.printf("%d. %-6s | %s%n", 0, "종료", "프로그램 종료");
        }
    }
}
