package com.sparta.miniorderservice.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // N+1 문제 해결 위한 fetch join.
    // order 조회 이후 product가 필요할 때 조회하는 것이 아닌,
    // order과 product를 한 번에 조회한 이후에 응답
    @Query(
            value = "select o from Order o join fetch o.product",
            countQuery = "select count(o) from Order o"
    )
    Page<Order> findAllWithProduct(Pageable pageable);


    //다른 방법
    /*
    @EntityGraph(attributePaths = "product")
        Page<Order> findAll(Pageable pageable);
    */
}