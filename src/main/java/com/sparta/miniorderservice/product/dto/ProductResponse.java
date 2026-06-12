package com.sparta.miniorderservice.product.dto;

import com.sparta.miniorderservice.product.Product;
import lombok.Getter;

@Getter
public class ProductResponse {

    private Long id;
    private String name;
    private int price;
    private boolean visible;
    private int stock;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.visible = product.isVisible();
        this.stock = product.getStock();
    }
}