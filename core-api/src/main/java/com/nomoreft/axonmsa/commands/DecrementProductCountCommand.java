package com.nomoreft.axonmsa.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DecrementProductCountCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
