package com.nomoreft.axonmsa.commands.common;


import com.nomoreft.axonmsa.commands.common.model.CardDetails;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record ValidatePaymentCommand(@TargetAggregateIdentifier String paymentId, String orderId, CardDetails cardDetails) {
}

