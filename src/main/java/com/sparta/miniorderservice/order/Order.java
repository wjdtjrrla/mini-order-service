package com.sparta.miniorderservice.order;

import com.sparta.miniorderservice.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    // 주문 식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문한 상품
    // 여러 주문은 하나의 상품을 참조할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY) //주문을 조회할 때 상품이 항상 즉시 필요한 것은 아니므로, 필요할 때 가져오도록 설정
    @JoinColumn(name = "product_id")
    private Product product;

    // 주문 당시 상품 가격
    private int orderPrice;

    // 주문 일시
    private LocalDateTime orderedAt;

    // 주문 상태
    @Enumerated(EnumType.STRING) //숫자로 저장되지 않고 문자열로 저장되도록.
    private OrderStatus status;

    // 주문 생성 시 필요한 값은 Product 하나.
    // 가격은 주문 시점의 상품 가격을 복사.
    public Order(Product product) {
        this.product = product;
        this.orderPrice = product.getPrice();
        this.orderedAt = LocalDateTime.now();
        this.status = OrderStatus.ORDERED;
    }

    // 주문 취소
    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }
}