package com.sparta.miniorderservice.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    // 주문할 상품 ID
    private Long productId;

    // 테스트 코드에서 직접 요청 객체를 만들기 위한 생성자
    public OrderCreateRequest(Long productId) {
        this.productId = productId;
    }
}