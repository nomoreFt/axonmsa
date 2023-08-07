package com.nomoreft.axonmsa.events;

import lombok.Builder;

@Builder
public record OrderCreatedEvent(String orderId,String productId,
        String userId,
        String addressId,
        Integer quantity,
        OrderStatus orderStatus) {
}