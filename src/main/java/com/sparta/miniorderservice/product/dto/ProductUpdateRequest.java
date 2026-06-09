package com.sparta.miniorderservice.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor //파라미터가 없는 디폴트 생성자를 생성
public class ProductUpdateRequest {

    // 수정할 상품명
    private String name;

    // 수정할 상품 가격
    private int price;
}