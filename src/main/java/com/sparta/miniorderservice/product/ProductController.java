package com.sparta.miniorderservice.product;

import com.sparta.miniorderservice.product.dto.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController //JSON 반환, XML 반환, API 서버 개발
//@Controller : HTML 화면 반환, JSP 반환 Thymeleaf 반환
@RequiredArgsConstructor //Lombok 라이브러리에서 제공하는 어노테이션, 필수 인자를 가진 생성자를 자동으로 생성 (final로 표시)
@RequestMapping("/products") //클라이언트가 요청할 주소
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductCreateRequest request) {
        productService.createProduct(request);
    }
}