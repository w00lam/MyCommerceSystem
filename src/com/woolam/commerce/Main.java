package com.woolam.commerce;

import com.woolam.commerce.controller.CommerceSystem;
import com.woolam.commerce.domain.*;
import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.service.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 장바구니 생성
        List<CartItem> items = new ArrayList<>();
        Cart cart = new Cart(items);

        // 사용자 생성
        Customer customer = new Customer("사용자", "이메일", "등급", cart);

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
        CartService cartService = new CartService(scanner);
        OrderService orderService = new OrderService(scanner);
        CustomerService customerService = new CustomerService(scanner);

        // 서비스 등록
        Map<String, Service> services = new HashMap<>();
        services.put("categoryService", categoryService);
        services.put("productService", productService);
        services.put("cartService", cartService);
        services.put("orderService", orderService);
        services.put("customerService", customerService);

        ServiceData data = new ServiceData();

        // CommerceSystem 객체 생성
        CommerceSystem commerceSystem = new CommerceSystem(services, customer, data, scanner);

        // 관리시스템 시작
        commerceSystem.start();
    }
}
