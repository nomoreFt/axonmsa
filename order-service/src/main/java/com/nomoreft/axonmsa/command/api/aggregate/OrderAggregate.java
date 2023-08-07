package com.nomoreft.axonmsa.command.api.aggregate;

import com.nomoreft.axonmsa.commands.order.CreateOrderCommand;
import com.nomoreft.axonmsa.events.OrderCreatedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private OrderStatus orderStatus;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder().build();
        BeanUtils.copyProperties(command,orderCreatedEvent);
        apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.orderId();
        this.productId = event.productId();
        this.userId = event.userId();
        this.addressId = event.addressId();
        this.quantity = event.quantity();
        this.orderStatus = event.orderStatus();
    }
}
