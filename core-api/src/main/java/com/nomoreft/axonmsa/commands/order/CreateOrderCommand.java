package com.nomoreft.axonmsa.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record CreateOrderCommand(@TargetAggregateIdentifier String orderId, String productId) {

}
