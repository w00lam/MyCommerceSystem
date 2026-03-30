package com.woolam.commerce.service;

import com.woolam.commerce.domain.Cart;
import com.woolam.commerce.domain.CartItem;
import com.woolam.commerce.domain.CustomerGrade;
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

        long totalPrice = cart.getTotalPrice();

        System.out.printf("\n[ 총 주문 금액 ]\n%,d원\n", totalPrice);
        System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");

        int command = Integer.parseInt(scanner.nextLine());

        if (command == 1) {

            // 고객 등급 선택
            CustomerGrade grade = selectCustomerGrade();

            // 할인 계산
            long finalPrice = grade.applyDiscount(totalPrice);
            long discountAmount = grade.calculateDiscountAmount(totalPrice);

            System.out.println("\n주문이 완료되었습니다!");
            System.out.printf("할인 전 금액: %,d원%n", totalPrice);
            System.out.printf("%s 등급 할인(%.0f%%): -%,d원%n",
                    grade.name(),
                    grade.getDiscountRate() * 100,
                    discountAmount);
            System.out.printf("최종 결제 금액: %,d원%n", finalPrice);

            // 재고 차감
            for (CartItem item : cart.getItems()) {
                item.updateQuantity();
            }

            cart.clear();
        } else {
            System.out.println("기능 추가 예정");
        }

        return new ServiceFlag("categoryService", data);
    }

    // 고객 등급 선택
    private CustomerGrade selectCustomerGrade() {
        System.out.println("\n고객 등급을 입력해주세요.");
        System.out.println("1. BRONZE   :  0% 할인");
        System.out.println("2. SILVER   :  5% 할인");
        System.out.println("3. GOLD     : 10% 할인");
        System.out.println("4. PLATINUM : 15% 할인");

        int input = Integer.parseInt(scanner.nextLine());

        return CustomerGrade.from(input);
    }
}
