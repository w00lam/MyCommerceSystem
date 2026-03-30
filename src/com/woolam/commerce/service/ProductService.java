package com.woolam.commerce.service;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.domain.Product;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductService implements Service {
    // 속성
    private final Scanner scanner;

    // 생성자
    public ProductService(Scanner scanner) {
        this.scanner = scanner;
    }

    // 상품 서비스 실행
    @Override
    public ServiceFlag run(ServiceData data) {
        Category selectedCategory = data.getCurrentCategory();
        List<Product> products = selectedCategory.getProducts();

        // 메뉴 출력
        displayMenu(selectedCategory);

        int command = Integer.parseInt(scanner.nextLine());

        if (command == 0) {
            return new ServiceFlag("categoryService", data);
        }

        // 필터 적용
        List<Product> filteredProducts = filterProducts(command, products);

        // 잘못된 입력 방어
        if (filteredProducts == null) {
            System.out.println("잘못된 입력입니다.");
            return new ServiceFlag("productService", data);
        }

        // 필터된 목록 출력
        displayProducts(command, filteredProducts);

        int productCommand = Integer.parseInt(scanner.nextLine());

        if (productCommand == 0) {
            return new ServiceFlag("productService", data);
        }

        Product selectedProduct = filteredProducts.get(productCommand - 1);

        // 상품 상세 출력
        displayDetail(selectedProduct);
        data.setCurrentProduct(selectedProduct);

        return new ServiceFlag("cartService", data);
    }

    // 메뉴 출력
    private void displayMenu(Category category) {
        System.out.println("[ " + category.getLabel() + " 카테고리 ]");
        System.out.println("1. 전체 상품 보기");
        System.out.println("2. 가격대별 필터링 (100만원 이하)");
        System.out.println("3. 가격대별 필터링 (100만원 초과)");
        System.out.println("0. 뒤로가기");
    }

    // 필터 로직
    private List<Product> filterProducts(int command, List<Product> products) {
        return switch (command) {
            case 1 -> products;
            case 2 -> products.stream()
                    .filter(p -> p.getPrice() <= 1_000_000)
                    .collect(Collectors.toList());
            case 3 -> products.stream()
                    .filter(p -> p.getPrice() > 1_000_000)
                    .collect(Collectors.toList());
            default -> null;
        };
    }

    // 상품 목록 출력
    private void displayProducts(int command, List<Product> products) {

        switch (command) {
            case 2:
                System.out.println("[ 100만원 이하 상품 목록 ]");
                break;
            case 3:
                System.out.println("[ 100만원 초과 상품 목록 ]");
                break;
            default:
                System.out.println("[ 전체 상품 목록 ]");
        }

        if (products.isEmpty()) {
            System.out.println("[상품 없음]");
        }

        IntStream.range(0, products.size())
                .forEach(i -> {
                    Product p = products.get(i);
                    System.out.printf("%d. %-15s | %,10d원 | %s | 재고: %d개%n",
                            i + 1,
                            p.getName(),
                            p.getPrice(),
                            p.getDescription(),
                            p.getStock());
                });

        System.out.println("0. 뒤로가기");
    }

    // 상품 상세 정보 출력
    private void displayDetail(Product product) {
        System.out.printf("선택한 상품: %s | %,10d원 | %s | 재고: %d%n",
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getStock());
    }
}
