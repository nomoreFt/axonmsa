package com.nomoreft.axonmsa.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record AddProductCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
