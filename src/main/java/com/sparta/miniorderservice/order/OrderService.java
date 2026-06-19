package com.sparta.miniorderservice.order;

import com.sparta.miniorderservice.order.dto.OrderCreateRequest;
import com.sparta.miniorderservice.order.dto.OrderListResponse;
import com.sparta.miniorderservice.order.dto.OrderResponse;
import com.sparta.miniorderservice.product.Product;
import com.sparta.miniorderservice.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    public void createOrder(OrderCreateRequest request) {
        Product product = productRepository.findByIdForUpdate(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.decreaseStock();
        Order order = new Order(product);

        orderRepository.save(order);
    }

    // 주문 단건 조회
    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));

        return new OrderResponse(order);
    }

    // 주문 목록 조회
    /*
    @Transactional(readOnly = true)
    public List<OrderListResponse> getOrders() {
        //N+1 문제 우려
        return orderRepository.findAll()
                .stream()
                .map(OrderListResponse::new)
                .toList();
    }
    */

    //주문 목록 페이지네이션
    @Transactional(readOnly = true)
    public Page<OrderListResponse> getOrders(Pageable pageable) {
        /*
        return orderRepository.findAll(pageable)
                .map(OrderListResponse::new);
         */

        //N+1 문제 해결 위한
        return orderRepository.findAllWithProduct(pageable)
                .map(OrderListResponse::new);
    }
}