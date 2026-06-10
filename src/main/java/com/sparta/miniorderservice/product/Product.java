package com.sparta.miniorderservice.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity

//객체 내부 속성에 직접 접근하지 않아 객체의 정보 은닉을 가능하게 해주어 보안을 강화할 수 있고, 코드의 안전성과 유지보수성을 높일 수 있는 장점
@Getter

//파라미터가 없는 디폴트 생성자를 생성
@NoArgsConstructor
/*
@AllArgsConstructor : 모든 필드 값을 파라미터로 받는 생성자를 생성
@RequiredArgsConstructor : final이나 @NonNull으로 선언된 필드만을 파라미터로 받는 생성자를 생성
*/


public class Product {


    @Id // 상품 식별자(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 자동 생성
    private Long id;

    // 상품명
    private String name;

    // 상품 가격
    private int price;

    // 상품 등록일
    private LocalDateTime createdAt;

    // 상품 마지막 수정일
    private LocalDateTime updatedAt;

    // 노출 여부
    private boolean visible;

    // 상품 생성 시 사용자가 입력하는 값은 name, price만
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.visible = true;
    }

    // 상품 정보 수정
    public void update(String name, int price) {
        this.name = name;
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    // 상품 삭제 대신 숨김 처리
    public void hide() {
        this.visible = false;
        this.updatedAt = LocalDateTime.now();
    }

    // 숨겼던 상품 다시 노출
    public void show(){
        this.visible = true;
        this.updatedAt = LocalDateTime.now();
    }
}