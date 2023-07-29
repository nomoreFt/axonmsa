package com.nomoreft.axonmsa.events.order;

public record OrderCreatedEvent(String orderId, String productId) {
}