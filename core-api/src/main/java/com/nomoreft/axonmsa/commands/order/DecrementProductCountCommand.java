package com.nomoreft.axonmsa.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DecrementProductCountCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
