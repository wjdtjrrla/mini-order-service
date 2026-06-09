package com.sparta.miniorderservice.product;

import com.sparta.miniorderservice.product.dto.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sparta.miniorderservice.product.dto.ProductResponse;

@Service
@RequiredArgsConstructor //Lombok 라이브러리에서 제공하는 어노테이션, 필수 인자를 가진 생성자를 자동으로 생성 (final로 표시)
public class ProductService {

    // Product 저장/조회 역할
    private final ProductRepository productRepository;

    // 상품 등록
    public void createProduct(ProductCreateRequest request) {
        Product product = new Product(request.getName(), request.getPrice());

        productRepository.save(product);
    }

    //상품 조회
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        return new ProductResponse(product);
    }
}