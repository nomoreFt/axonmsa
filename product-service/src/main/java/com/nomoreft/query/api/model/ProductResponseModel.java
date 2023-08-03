package com.nomoreft.query.api.model;

import com.nomoreft.command.api.domain.Product;

import java.math.BigDecimal;

public record ProductResponseModel(String name, BigDecimal price, Integer quantity) {

    //product entity to response model
    public static ProductResponseModel fromProduct(Product product) {
        return new ProductResponseModel(product.getName(), product.getPrice(), product.getQuantity());
    }

}
