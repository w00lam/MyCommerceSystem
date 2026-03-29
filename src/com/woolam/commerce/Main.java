package com.woolam.commerce;

import com.woolam.commerce.domain.Category;
import com.woolam.commerce.domain.Product;
import com.woolam.commerce.service.CategoryService;
import com.woolam.commerce.service.ProductService;
import com.woolam.commerce.service.Service;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 상품 생성
        List<Product> products = new ArrayList<>();
        products.add(new Product("Galaxy S25", 1200000, "최신 스마트폰", 50));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 50));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 50));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 50));

        // 카테고리 생성
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("전자제품", products));
        categories.add(new Category("의류", new ArrayList<>()));
        categories.add(new Category("식품", new ArrayList<>()));

        // 서비스 객체 생성
        CategoryService categoryService = new CategoryService(categories, scanner);
        ProductService productService = new ProductService(scanner);

        // 서비스 등록
        Map<String, Service> services = new HashMap<>();
        services.put("categoryService", categoryService);
        services.put("productService", productService);

        // CommerceSystem 객체 생성
        CommerceSystem commerceSystem = new CommerceSystem(services);

        // 관리시스템 시작
        commerceSystem.start();

        // 종료
        scanner.close();
    }
}
