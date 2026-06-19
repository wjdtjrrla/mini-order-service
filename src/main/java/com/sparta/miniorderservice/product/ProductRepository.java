package com.sparta.miniorderservice.product;

//기본 CRUD 메서드를 직접 구현하지 않아도 사용할 수 있고,
//Spring Data JPA가 런타임에 Repository 구현체를 자동으로 생성
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //실행시 내부적으로 아래 쿼리 생성
    //select *
    //from product
    //where visible = true;
    List<Product> findAllByVisible(boolean visible);

    //락
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select p
        from Product p
        where p.id = :id
       """)
    Optional<Product> findByIdForUpdate(Long id);
}

