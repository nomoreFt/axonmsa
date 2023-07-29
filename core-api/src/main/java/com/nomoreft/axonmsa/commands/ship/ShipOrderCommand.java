package com.nomoreft.axonmsa.commands.ship;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public record ShipOrderCommand(@TargetAggregateIdentifier String orderId) {
}