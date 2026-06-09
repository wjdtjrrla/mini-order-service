package com.sparta.miniorderservice.product;

//기본 CRUD 메서드를 직접 구현하지 않아도 사용할 수 있고,
//Spring Data JPA가 런타임에 Repository 구현체를 자동으로 생성
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}