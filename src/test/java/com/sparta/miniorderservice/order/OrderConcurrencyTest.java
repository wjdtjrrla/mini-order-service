package com.sparta.miniorderservice.order;

import com.sparta.miniorderservice.order.dto.OrderCreateRequest;
import com.sparta.miniorderservice.product.Product;
import com.sparta.miniorderservice.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//동시 실행 도구 추가
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//실제 스프링 서버를 띄워줌
@SpringBootTest
class OrderConcurrencyTest  {

    //테스트 코드에서 사용하기 위해.
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // CountDownLatch의 await()는 현재 스레드를 대기 상태로 만든다.
    // 대기 중 다른 스레드가 interrupt()를 호출하면 InterruptedException이 발생
    @Test
    void 동시주문테스트() throws InterruptedException{

        Product product = new Product(
                "동시성테스트상품",
                1000,
                1
        );

        Product savedProduct =
                productRepository.save(product);

        //동시에 두 명 작업 수행하는 Count
        int threadCount = 2;

        //동시 작업
        ExecutorService executorService =
                Executors.newFixedThreadPool(threadCount);

        //작업 2개 끝날 때까지 대기
        CountDownLatch latch =
                new CountDownLatch(threadCount);

        //동시에 주문 요청
        for (int i = 0; i < threadCount; i++) {

            executorService.submit(() -> {

                try {

                    OrderCreateRequest request =
                            new OrderCreateRequest(savedProduct.getId());

                    orderService.createOrder(request);

                } catch (Exception e) {

                    System.out.println(
                            "주문 실패 : " + e.getMessage()
                    );

                } finally {

                    latch.countDown();

                }

            }
            );
        }
        // 두 주문 요청이 모두 끝날 때까지 대기
        latch.await();

        Product resultProduct =
                productRepository.findById(savedProduct.getId())
                        .orElseThrow();

        long orderCount = orderRepository.count();

        System.out.println("최종 재고 : " + resultProduct.getStock());
        System.out.println("주문 개수 : " + orderCount);
    }
}