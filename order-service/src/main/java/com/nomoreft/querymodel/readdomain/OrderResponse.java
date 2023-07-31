package com.nomoreft.querymodel.readdomain;


import com.nomoreft.commandmodel.writedomain.Order;

public record OrderResponse (String orderId, java.util.Map<String, Integer> productId, OrderStatusResponse status) {

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getOrderId(), order.getProducts(),
                OrderStatusResponse.from(order.getOrderStatus()));
    }
}
