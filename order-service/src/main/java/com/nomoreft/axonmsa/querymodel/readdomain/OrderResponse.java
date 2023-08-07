package com.nomoreft.axonmsa.querymodel.readdomain;


import com.nomoreft.axonmsa.command.api.domain.Order;

public record OrderResponse (String orderId, java.util.Map<String, Integer> productId, OrderStatusResponse status) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getOrderId(), order.getProducts(),
                OrderStatusResponse.from(order.getOrderStatus()));
    }
}
