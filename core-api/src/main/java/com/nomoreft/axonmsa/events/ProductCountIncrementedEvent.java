package com.nomoreft.axonmsa.events;

public record ProductCountIncrementedEvent(String orderId, String productId) {
}
