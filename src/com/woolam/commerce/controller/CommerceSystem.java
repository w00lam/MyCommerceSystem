package com.woolam.commerce.controller;

import com.woolam.commerce.domain.Customer;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;
import com.woolam.commerce.service.Service;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class CommerceSystem {
    // 속성
    private final Map<String, Service> services;
    private Customer customer;
    private ServiceData data;
    private final Scanner scanner;
    private String nextService = "categoryService";


    // 생성자
    public CommerceSystem(Map<String, Service> services, Customer customer, ServiceData data, Scanner scanner) {
        this.services = services;
        this.customer = customer;
        this.data = data;
        this.scanner = scanner;
    }

    // 커머스 관리 메뉴
    public void start() {
        data.setCart(customer.getCart());

        while (nextService != null && !nextService.equals("exit")) {
            try {
                Service currentService = services.get(nextService);

                if (currentService != null) {
                    ServiceFlag flag = currentService.run(data);

                    nextService = flag.getNextService();
                    data = flag.getData();
                } else {
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("잘못된 번호입니다. 다시 선택해주세요.\n");
            } catch (InputMismatchException e) {
                System.out.println("올바른 형식의 숫자만 입력해주세요.\n");
                scanner.nextLine();
            }
        }

        System.out.println("커머스 플랫폼을 종료합니다.");
        scanner.close();
    }
}