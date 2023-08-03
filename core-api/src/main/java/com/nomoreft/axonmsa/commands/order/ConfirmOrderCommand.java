package com.nomoreft.axonmsa.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ConfirmOrderCommand(@TargetAggregateIdentifier String orderId) {
}
