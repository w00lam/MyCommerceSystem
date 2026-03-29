package com.woolam.commerce;

import com.woolam.commerce.dto.ServiceResponse;
import com.woolam.commerce.service.Service;

import java.util.Map;


public class CommerceSystem {
    // 속성
    private final Map<String, Service> services;
    private String nextService = "categoryService";
    private Object data = null;


    // 생성자
    public CommerceSystem(Map<String, Service> services) {
        this.services = services;
    }

    // 커머스 관리 메뉴
    public void start() {
        while (nextService != null && !nextService.equals("exit")) {
            Service currentService = services.get(nextService);

            if (currentService != null) {
                ServiceResponse response = currentService.run(data);

                nextService = response.getNextService();
                data = response.getData();
            } else {
                break;
            }
        }
        System.out.println("커머스 플랫폼을 종료합니다.");
    }
}