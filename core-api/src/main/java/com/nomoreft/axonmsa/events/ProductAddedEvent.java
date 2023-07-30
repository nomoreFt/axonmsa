package com.nomoreft.axonmsa.events;

public record ProductAddedEvent(String orderId, String productId) {
}
