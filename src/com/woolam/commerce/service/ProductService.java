package com.woolam.commerce.service;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.domain.Product;
import com.woolam.commerce.dto.ServiceResponse;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ProductService implements Service {
    // 속성
    private final Scanner scanner;

    // 출력 포맷을 위한 유틸리티 객체
    private final DecimalFormat df = new DecimalFormat("###,###");

    // 생성자
    public ProductService(Scanner scanner) {
        this.scanner = scanner;
    }

    // 상품 서비스 실행
    @Override
    public ServiceResponse run(Object inputData) {
        // 전달받은 데이터 가공
        Category selectedCategory = (Category) inputData;
        List<Product> products = selectedCategory.getProducts();

        System.out.println("\n[ " + selectedCategory.getLabel() + " 카테고리 ]");
        display(products);
        int command = scanner.nextInt();

        if (command == 0) {
            return new ServiceResponse("categoryService", null);
        }
        try {
            Product selectedProduct = products.get(command - 1);
            printDetail(selectedProduct);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n잘못된 번호입니다. 다시 선택해주세요.");
        }

        return new ServiceResponse("productService", selectedCategory);
    }

    // 상품 리스트 화면 출력
    private void display(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("[상품 준비중]");
        }
        IntStream.range(0, products.size())
                .forEach(i -> {
                    System.out.printf("%d. %-15s | %10s원 | %s%n",
                            i + 1,
                            products.get(i).getName(),
                            df.format(products.get(i).getPrice()),
                            products.get(i).getDescription());
                });
        System.out.println("0. 뒤로가기");
    }

    // 상품 상세 정보 출력
    private void printDetail(Product product) {
        System.out.printf("선택한 상품: %s | %s원 | %s | 재고: %d%n",
                product.getName(),
                df.format(product.getPrice()),
                product.getDescription(),
                product.getStock());
    }
}
