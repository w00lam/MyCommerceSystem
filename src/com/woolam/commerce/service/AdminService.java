package com.woolam.commerce.service;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.domain.Product;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class AdminService implements Service {
    private final List<Category> categories;
    private Scanner scanner;

    private static final String ADMIN_PASSWORD = "admin123";

    public AdminService(List<Category> categories, Scanner scanner) {
        this.categories = categories;
        this.scanner = scanner;
    }

    @Override
    public ServiceFlag run(ServiceData data) {

        // 관리자 인증
        if (!authenticate()) {
            System.out.println("3회 실패 메인 메뉴로 돌아갑니다.");
            return new ServiceFlag("categoryService", data);
        }

        // 관리자 메뉴
        while (true) {
            display();
            int command = Integer.parseInt(scanner.nextLine());

            switch (command) {
                case 1 -> addProduct();
                case 2 -> updateProduct();
                case 3 -> deleteProduct(data);
                case 4 -> displayAllProducts();
                case 0 -> {
                    return new ServiceFlag("categoryService", data);
                }

            }
        }
    }

    // 관리자 인증
    private boolean authenticate() {
        final int MAX_ATTEMPTS = 3;

        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            System.out.println("관리자 비밀번호 입력: ");
            String pwd = scanner.nextLine();

            if (ADMIN_PASSWORD.equals(pwd)) {
                return true;
            }
        }
        return false;
    }

    // 관리자 메뉴
    private void display() {
        System.out.println("\n[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }

    // 상품 추가를 위한 카테고리 찾기
    private Category selectCategory() {
        System.out.println("어느 카테고리에 상품을 추가하시겠습니까?");
        IntStream.range(0, categories.size())
                .forEach(i -> System.out.printf("%d. %s%n", i + 1, categories.get(i).getLabel()));

        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= categories.size()) {
            System.out.println("잘못된 선택");
            return null;
        }

        return categories.get(index);
    }

    private void addProduct() {
        Category category = selectCategory();
        if (category == null) return;

        System.out.println("[ " + category.getLabel() + " 카테고리에 상품 추가 ]");
        System.out.print("상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        System.out.print("가격을 입력해주세요: ");
        int price = Integer.parseInt(scanner.nextLine());

        System.out.print("상품 설명을 입력해주세요: ");
        String description = scanner.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.printf("%s | %,d원 | %s | 재고: %d개%n", name, price, description, stock);
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        int command = Integer.parseInt(scanner.nextLine());

        if (command == 1) {
            Product product = new Product(category, name, price, description, stock);
            category.addProduct(product);
            System.out.println("상품이 성공적으로 추가되었습니다!");
        }
    }

    private void updateProduct() {
        System.out.print("수정할 상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        Product product = findProductByName(name);

        if (product == null) {
            System.out.println("상품을 찾을 수 없습니다.");
            return;
        }

        product.displayInfo();

        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명");
        System.out.println("3. 재고수량");
        int command = Integer.parseInt(scanner.nextLine());

        switch (command) {
            case 1 -> {
                System.out.printf("현재 가격: %,d", product.getPrice());
                System.out.print("새로운 가격을 입력해주세요: ");
                product.updatePrice(Integer.parseInt(scanner.nextLine()));
            }
            case 2 -> {
                System.out.println("현재 설명: " + product.getDescription());
                System.out.print("새로운 설명을 입력해주세요: ");
                product.updateDescription(scanner.nextLine());
            }
            case 3 -> {
                System.out.println("현재 재고: " + product.getStock());
                System.out.print("새로운 재고를 입력해주세요: ");
                product.updateStock(Integer.parseInt(scanner.nextLine()));
                scanner.nextLine();
            }
            default -> System.out.println("잘못된 선택");
        }
    }

    private void deleteProduct(ServiceData data) {
        System.out.print("삭제할 상품명을 입력해주세요: ");
        String name = scanner.nextLine();

        Product product = findProductByName(name);

        if (product == null) {
            System.out.println("상품 없음");
            return;
        }

        System.out.print("정말 삭제하시겠습니까? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            product.getCategory().removeProduct(product);
            data.getCart().removeProduct(product);
            System.out.println("삭제 완료");
        }
    }

    // 전체 상품 현황
    private void displayAllProducts() {
        List<Product> products = getAllProducts();

        if (products.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }

        System.out.println("========================== 전체 상품 현황 ==========================");

        for (Product product : products) {
            System.out.printf(
                    "%-15s | %,10d원 | %-15s | 재고: %d%n",
                    product.getName(), product.getPrice(), product.getDescription(), product.getStock()
            );
        }
    }

    private List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();

        for (Category category : categories) {
            allProducts.addAll(category.getProducts());
        }

        return allProducts;
    }

    private Product findProductByName(String name) {
        for (Category category : categories) {
            for (Product product : category.getProducts()) {
                if (product.getName().equalsIgnoreCase(name)) {
                    return product;
                }
            }
        }
        return null;
    }
}