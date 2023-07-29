package com.nomoreft.querymodel.dto;


import com.nomoreft.axonmsa.queries.order.entity.Order;

public record OrderResponse (String orderId, String productId, OrderStatusResponse status) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getOrderId(), order.getProductId(), OrderStatusResponse.from(order.getOrderStatus()));
    }
}
