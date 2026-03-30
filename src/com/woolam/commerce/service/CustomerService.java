package com.woolam.commerce.service;

import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

import java.util.Scanner;

public class CustomerService implements Service {
    private final Scanner scanner;

    public CustomerService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public ServiceFlag run(ServiceData data) {
        // 기능 추가 예정
        return null;
    }
}
