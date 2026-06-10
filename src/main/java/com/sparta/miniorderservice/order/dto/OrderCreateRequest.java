package com.sparta.miniorderservice.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCreateRequest {

    // 주문할 상품 ID
    private Long productId;
}