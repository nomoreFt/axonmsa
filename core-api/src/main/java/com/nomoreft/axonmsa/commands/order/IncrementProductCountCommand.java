package com.nomoreft.axonmsa.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record IncrementProductCountCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
