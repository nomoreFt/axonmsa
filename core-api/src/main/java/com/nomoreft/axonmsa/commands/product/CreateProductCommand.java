package com.nomoreft.axonmsa.commands.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Builder
public record CreateProductCommand(@TargetAggregateIdentifier String productId, String name, BigDecimal price,
                                   Integer quantity) {
}
