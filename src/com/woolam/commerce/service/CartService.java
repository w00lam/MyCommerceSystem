package com.woolam.commerce.service;

import com.woolam.commerce.domain.Product;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.Scanner;

public class CartService implements Service {
    // 속성
    private final Scanner scanner;

    // 생성자
    public CartService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public ServiceFlag run(ServiceData data) {
        Product selectedProduct = data.getCurrentProduct();

        // 1. 상품을 선택하고 들어온 경우 (장바구니 담기 확인 창)
        if (selectedProduct != null) {
            displayConfirmMessage(selectedProduct);
            int command = Integer.parseInt(scanner.nextLine());

            if (command == 1) {
                addToCart(data, selectedProduct);
                return new ServiceFlag("categoryService", data); // 담고 카테고리 리스트로
            } else if (command == 2) {
                data.setCurrentProduct(null);
                return new ServiceFlag("productService", data); // 취소 시 상품 목록으로
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else {
            return new ServiceFlag("orderService", data);
        }


    }

    private void displayConfirmMessage(Product product) {
        System.out.printf("\"%s | %,d원 | %s\"%n",
                product.getName(), product.getPrice(), product.getDescription());
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
    }

    private void addToCart(ServiceData data, Product product) {
        if (product.getStock() <= 0) {
            System.out.println("재고가 부족하여 장바구니에 담을 수 없습니다.");
        } else {
            data.getCart().addItem(product);
            System.out.println(product.getName() + "가 장바구니에 추가되었습니다.\n");
        }
        data.setCurrentProduct(null); // 작업 완료 후 선택 상품 비우기
    }
}
