package com.nomoreft.axonmsa.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddProductCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
