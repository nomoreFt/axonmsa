package com.nomoreft.axonmsa.commands.common;

import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
public record ShipOrderCommand(@TargetAggregateIdentifier String shipmentId, String orderId) {
}
