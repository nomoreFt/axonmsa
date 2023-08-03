package com.nomoreft.command.api.aggregate;

import com.nomoreft.axonmsa.commands.product.CreateProductCommand;
import com.nomoreft.axonmsa.events.product.ProductCreatedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductAggregate {

    @AggregateIdentifier
    String productId;
    String name;
    BigDecimal price;
    Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
                .productId(createProductCommand.productId())
                .name(createProductCommand.name())
                .price(createProductCommand.price())
                .quantity(createProductCommand.quantity())
                .build();

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        this.productId = productCreatedEvent.productId();
        this.name = productCreatedEvent.name();
        this.price = productCreatedEvent.price();
        this.quantity = productCreatedEvent.quantity();
    }




}
