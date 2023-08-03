package com.nomoreft.query.api.projection;

import com.nomoreft.axonmsa.queries.product.FindAllProductsQuery;
import com.nomoreft.command.api.domain.Product;
import com.nomoreft.command.api.domain.ProductJpaRepository;
import com.nomoreft.command.api.model.ProductRestModel;
import com.nomoreft.query.api.model.ProductResponseModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductProjection {

    private ProductJpaRepository productJpaRepository;

    public ProductProjection(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @QueryHandler
    public List<ProductResponseModel> handle(FindAllProductsQuery findAllProductsQuery) {
        List<Product> products =
                productJpaRepository.findAll();

        return products.stream()
                .map(ProductResponseModel::fromProduct)
                .toList();
    }
}
