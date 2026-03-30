package com.woolam.commerce.service;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CategoryService implements Service {
    private final List<Category> categories; // CategoryService에서 가져온 데이터
    private final Scanner scanner;

    public CategoryService(List<Category> categories, Scanner scanner) {
        this.categories = categories;
        this.scanner = scanner;
    }

    @Override
    public ServiceFlag run(ServiceData data) {
        // 카테고리 화면 출력
        displayCategories();

        displayAdmin();

        // 장바구니 상태에 따른 동적 메뉴 출력
        boolean hasItems = !data.getCart().isEmpty();
        if (hasItems) {
            displayOrderMenu();
        }
        int command = Integer.parseInt(scanner.nextLine());


        // 종료
        if (command == 0) return new ServiceFlag("exit", data);

        // 장바구니 관련 메뉴 선택 시
        if (hasItems) {
            if (command == 4) return new ServiceFlag("orderService", data);
            if (command == 5) return new ServiceFlag("orderService", data);
        }

        if (command == 6) return new ServiceFlag("adminService", data);

        // 카테고리 선택 시
        Category selected = getCategory(command - 1);

        if (selected == null) {
            throw new IndexOutOfBoundsException();
        }
        data.setCurrentCategory(selected);
        return new ServiceFlag("productService", data);
    }

    private void displayCategories() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        IntStream.range(0, categories.size())
                .forEach(i -> System.out.printf("%d. %s%n", i + 1, categories.get(i).getLabel()));
        System.out.println("0. 프로그램 종료");
    }

    private void displayOrderMenu() {
        System.out.println("\n[ 주문 관리 ]");
        System.out.println("4. 장바구니 확인    | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. 주문 취소       | 진행중인 주문을 취소합니다.");
    }

    private Category getCategory(int index) {
        if (index < 0 || index >= categories.size()) return null;
        return categories.get(index);
    }

    private void displayAdmin() {
        System.out.println("6. 관리자 모드");
    }
}