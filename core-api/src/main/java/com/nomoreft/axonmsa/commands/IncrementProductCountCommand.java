package com.nomoreft.axonmsa.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record IncrementProductCountCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
