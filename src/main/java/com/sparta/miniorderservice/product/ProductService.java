package com.sparta.miniorderservice.product;

import com.sparta.miniorderservice.product.dto.ProductCreateRequest;
import com.sparta.miniorderservice.product.dto.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.sparta.miniorderservice.product.dto.ProductResponse;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //Lombok 라이브러리에서 제공하는 어노테이션, 필수 인자를 가진 생성자를 자동으로 생성 (final로 표시)
@Transactional
public class ProductService {

    // Product 저장/조회 역할
    private final ProductRepository productRepository;

    // 상품 등록
    // 반환할 필요가 없으면 void
    public void createProduct(ProductCreateRequest request) {
        Product product = new Product(request.getName(), request.getPrice());

        productRepository.save(product);
    }

    //상품 조회
    //결과를 반환해줘야 하는 조회는 void가 아님.
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        return new ProductResponse(product);
    }

    //상품 업데이트
    //dirty checking이 있어서 save() 실행해주지 않아도 변경 이력 반영
    public void updateProduct(Long productId, ProductUpdateRequest request) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.update(request.getName(), request.getPrice());
    }

    //상품 비활성화(숨김 처리)
    public void hideProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        product.hide();
    }

    //상품 활성화(숨겼던 상품 노출)
    public void showProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));

        product.show();
    }

    //상품 삭제
    public void deleteProduct(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다"));

        productRepository.delete(product);
    }
}