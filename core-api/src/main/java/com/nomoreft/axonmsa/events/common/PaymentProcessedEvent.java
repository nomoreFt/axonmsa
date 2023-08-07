package com.nomoreft.axonmsa.events.common;

import lombok.Builder;

@Builder
public record PaymentProcessedEvent(String paymentId, String orderId) {
}
