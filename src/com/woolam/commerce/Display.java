package com.woolam.commerce;

import java.util.List;

public interface Display {
    // 출력 화면
    void display();

    // 하위 목록이 존재하는지
    boolean hasNext();

    boolean isEmpty();

    // 상속관계에 대응하기 위한 와일드카드 사용
    List<? extends Display> getNextDisplay();
}
