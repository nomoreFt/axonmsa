package com.nomoreft.commandmodel.aggregations;

import com.nomoreft.axonmsa.commands.CreateOrderCommand;
import com.nomoreft.axonmsa.commands.ShipOrderCommand;
import com.nomoreft.axonmsa.events.OrderCreatedEvent;
import com.nomoreft.axonmsa.events.OrderShippedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        apply(new OrderCreatedEvent(command.orderId()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.orderId();
        this.orderConfirmed = false;
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (orderConfirmed) {
            return;
        }

        apply(new OrderShippedEvent(command.orderId()));
    }
}
