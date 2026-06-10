package com.sparta.miniorderservice.order;

import com.sparta.miniorderservice.order.dto.OrderCreateRequest;
import com.sparta.miniorderservice.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 등록된 상품 ID를 이용한 주문 생성
    @PostMapping
    public void createOrder(@RequestBody OrderCreateRequest request) {
        orderService.createOrder(request);
    }

    // 주문 단건 조회
    @GetMapping("/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }
}