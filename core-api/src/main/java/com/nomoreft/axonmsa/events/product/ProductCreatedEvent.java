package com.nomoreft.axonmsa.events.product;

import lombok.Builder;

@Builder
public record ProductCreatedEvent(String productId, String name, java.math.BigDecimal price, Integer quantity) {
}
