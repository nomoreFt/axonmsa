package com.nomoreft.service;

import com.nomoreft.axonmsa.queries.order.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.order.entity.Order;
import com.nomoreft.querymodel.dto.OrderResponse;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderQueryService {

    private final QueryGateway queryGateway;

    public OrderQueryService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public CompletableFuture<List<OrderResponse>> findAllOrders() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(Order.class))
                .thenApply(r -> r.stream()
                        .map(OrderResponse::from).toList());
    }

}
