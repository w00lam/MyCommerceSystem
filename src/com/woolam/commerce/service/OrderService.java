package com.woolam.commerce.service;

import com.woolam.commerce.domain.Cart;
import com.woolam.commerce.domain.CartItem;
import com.woolam.commerce.domain.Product;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.Scanner;

public class OrderService implements Service {
    private final Scanner scanner;

    public OrderService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public ServiceFlag run(ServiceData data) {
        Cart cart = data.getCart();

        System.out.println("\n아래와 같이 주문 하시겠습니까?\n\n[ 장바구니 내역 ]");
        for (CartItem item : cart.getItems()) {
            item.display();
        }
        System.out.printf("\n[ 총 주문 금액 ] %,d원\n", cart.getTotalPrice());
        System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");

        int command = scanner.nextInt();
        if (command == 1) {
            System.out.printf("주문이 완료되었습니다! 총 금액: %,d원%n", cart.getTotalPrice());
            // 재고 차감
            for (CartItem item : cart.getItems()) {
                item.updateQuantity();
                cart.clear(); // 장바구니 비우기
            }
        } else {
            // 주문취소 기능 추가 예정
            // 감소된 재고 다시 증가 시켜주기 (결제 객체에 있는 주문 수량 가져오기)
            System.out.println("기능 추가 예정");
        }
        return new ServiceFlag("categoryService", data);
    }
}
