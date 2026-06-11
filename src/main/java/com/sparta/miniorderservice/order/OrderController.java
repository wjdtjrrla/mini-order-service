package com.sparta.miniorderservice.order;

import com.sparta.miniorderservice.order.dto.OrderCreateRequest;
import com.sparta.miniorderservice.order.dto.OrderListResponse;
import com.sparta.miniorderservice.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    // 등록된 상품 ID를 이용한 주문 생성
    @PostMapping
    //RequestBody : HTTP Body 안에 있는 JSON 데이터를 객체로 변환
    public void createOrder(@RequestBody OrderCreateRequest request) {
        orderService.createOrder(request);
    }

    // 주문 단건 조회
    @GetMapping("/{orderId}")
    //PathVariable : URL에 있는 경로의 일부를 가져오는 것.
    public OrderResponse getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    //주문 목록 조회
    /*@GetMapping
    public List<OrderListResponse> getOrders(){
        return orderService.getOrders();
    }
    */

    //주문 목록 페이지네이션
    @GetMapping
    public Page<OrderListResponse> getOrders(Pageable pageable) {
        return orderService.getOrders(pageable);
    }
}