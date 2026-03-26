package com.woolam.commerce;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CommerceSystem implements Display {
    // 커머스 시스템 속성
    Scanner scanner;
    List<Category> categories;

    // 생성자
    public CommerceSystem(List<Category> categories, Scanner scanner) {
        this.categories = categories;
        this.scanner = scanner;
    }


    // 커머스 관리 메뉴
    public void start() {
        menu(this);
    }

    private void menu(Display currentNode) {
        while (true) {
            // 현재 단계의 display 정보 출력
            currentNode.display();

            // 하위 정보가 없는 경우
            if (!currentNode.hasNext()) {
                return;
            }
            int choice = scanner.nextInt();
            if (choice == 0) {
                if (currentNode instanceof CommerceSystem) {
                    System.out.println("커머스 플랫폼을 종료합니다.");
                    scanner.close();
                    System.exit(0);
                }
                return; // 뒤로가기
            }

            List<? extends Display> children = currentNode.getNextDisplay();
            if (choice > 0 && choice <= children.size()) {
                Display child = children.get(choice - 1);
                if(child.isEmpty()) {   // 카테고리의 상품이 없을때
                    System.out.println("해당 카테고리의 상품이 존재하지 않습니다.\n");
                }else { // 하위 단계로 재귀 호출
                    menu(children.get(choice - 1));
                }

            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        }
    }

    // 포맷에 맞는 메인 화면 출력
    @Override
    public void display() {
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        IntStream.range(0, categories.size())
                .forEach(i -> {
                    System.out.printf("%d. %s%n", i + 1, categories.get(i).getCategory());
                });
        System.out.printf("%d. %-6s | %s%n", 0, "종료", "프로그램 종료");
    }

    // 카테고리 리스트가 존재하는지
    @Override
    public boolean hasNext() {
        return !categories.isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    // 메인 메뉴를 보여준 후 카테고리를 보여줘야함
    @Override
    public List<Category> getNextDisplay() {
        return this.categories;
    }
}