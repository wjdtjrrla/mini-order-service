package com.sparta.miniorderservice.order.dto;

import com.sparta.miniorderservice.order.Order;
import com.sparta.miniorderservice.order.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponse {

    private Long orderId;
    private Long productId;
    private String productName;
    private int orderPrice;
    private OrderStatus status;
    private LocalDateTime orderedAt;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.productId = order.getProduct().getId();
        this.productName = order.getProduct().getName();
        this.orderPrice = order.getOrderPrice();
        this.status = order.getStatus();
        this.orderedAt = order.getOrderedAt();
    }
}