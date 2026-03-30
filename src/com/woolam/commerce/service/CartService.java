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

        // 상품 담기
        if (selectedProduct != null) {
            displayConfirmMessage(selectedProduct);
            int command = Integer.parseInt(scanner.nextLine());

            if (command == 1) {
                addToCart(data, selectedProduct);
                return new ServiceFlag("categoryService", data);
            } else if (command == 2) {
                data.setCurrentProduct(null);
                return new ServiceFlag("productService", data);
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        // 장바구니 관리 메뉴
        displayCartMenu();
        int command = Integer.parseInt(scanner.nextLine());

        switch (command) {
            case 1: // 장바구니 보기
                data.getCart().display();
                return new ServiceFlag("cartService", data);

            case 2: // 상품 삭제
                System.out.println("삭제할 상품 이름을 입력하세요:");
                String keyword = scanner.nextLine();

                boolean removed = data.getCart().removeByProductName(keyword);

                if (removed) {
                    System.out.println("상품이 장바구니에서 제거되었습니다.");
                } else {
                    System.out.println("일치하는 상품이 없습니다.");
                }

                return new ServiceFlag("cartService", data);

            case 3: // 주문하기
                return new ServiceFlag("orderService", data);

            case 0: // 뒤로가기
                return new ServiceFlag("categoryService", data);

            default:
                throw new IllegalArgumentException("잘못된 입력");
        }


    }

    // 장바구니 메뉴 출력
    private void displayCartMenu() {
        System.out.println("\n[ 장바구니 관리 ]");
        System.out.println("1. 장바구니 확인");
        System.out.println("2. 상품 삭제");
        System.out.println("3. 주문하기");
        System.out.println("0. 뒤로가기");
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
