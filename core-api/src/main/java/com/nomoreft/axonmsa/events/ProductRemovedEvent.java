package com.nomoreft.axonmsa.events;

public record ProductRemovedEvent(String orderId, String productId) {
}
