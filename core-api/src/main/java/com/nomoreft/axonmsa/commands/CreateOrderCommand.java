package com.nomoreft.axonmsa.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record CreateOrderCommand(@TargetAggregateIdentifier String orderId) {

}
