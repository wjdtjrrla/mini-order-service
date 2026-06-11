package com.sparta.miniorderservice.order.dto;

import com.sparta.miniorderservice.order.Order;
import com.sparta.miniorderservice.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderListResponse {

    private Long orderId;
    private String productName;
    private int orderPrice;
    private OrderStatus status;

    public OrderListResponse(Order order){
        this.orderId = order.getId();
        this.productName = order.getProduct().getName();
        this.orderPrice = order.getOrderPrice();
        this.status = order.getStatus();
    }
}
