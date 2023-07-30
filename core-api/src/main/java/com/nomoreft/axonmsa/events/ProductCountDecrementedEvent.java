package com.nomoreft.axonmsa.events;

public record ProductCountDecrementedEvent(String orderId, String productId) {
}
