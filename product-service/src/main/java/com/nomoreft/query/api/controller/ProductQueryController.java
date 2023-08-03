package com.nomoreft.query.api.controller;

import com.nomoreft.axonmsa.queries.product.FindAllProductsQuery;
import com.nomoreft.query.api.model.ProductResponseModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/all")
    public CompletableFuture<List<ProductResponseModel>> getAllProducts() {
        CompletableFuture<List<ProductResponseModel>> result = queryGateway.query(new FindAllProductsQuery(),
                ResponseTypes.multipleInstancesOf(ProductResponseModel.class));
        return result;
    }
}
