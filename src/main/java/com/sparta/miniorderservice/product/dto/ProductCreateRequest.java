package com.sparta.miniorderservice.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    // 상품명
    private String name;

    // 상품 가격
    private int price;

    //재고 개수
    private int stock;
}