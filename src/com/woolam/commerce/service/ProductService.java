package com.woolam.commerce.service;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.domain.Product;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.List;
import java.util.Scanner;
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

        System.out.println("[ " + selectedCategory.getLabel() + " 카테고리 ]");
        display(products);

        int command = Integer.parseInt(scanner.nextLine());

        if (command == 0) {
            return new ServiceFlag("categoryService", data);
        } else {
            Product selectedProduct = products.get(command - 1);
            displayDetail(selectedProduct);
            data.setCurrentProduct(selectedProduct);

            return new ServiceFlag("cartService", data);
        }
    }

    // 상품 리스트 화면 출력
    private void display(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("[상품 준비중]");
        }
        IntStream.range(0, products.size())
                .forEach(i -> {
                    System.out.printf("%d. %-15s | %,10d원 | %s%n",
                            i + 1,
                            products.get(i).getName(),
                            products.get(i).getPrice(),
                            products.get(i).getDescription());
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
