package com.nomoreft.commandmodel;

import com.nomoreft.axonmsa.commands.CreateOrderCommand;
import com.nomoreft.axonmsa.commands.ShipOrderCommand;
import com.nomoreft.axonmsa.events.OrderCreatedEvent;
import com.nomoreft.axonmsa.events.OrderShippedEvent;
import com.nomoreft.commandmodel.aggregations.OrderAggregate;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class OrderAggregateTest {

    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    //orderCreateTest

    @Test
    void test1() {
        String orderId = UUID.randomUUID().toString();
        String productId = "Deluxe Chair";
        fixture.givenNoPriorActivity()
                .when(new CreateOrderCommand(orderId))
                .expectEvents(new OrderCreatedEvent(orderId, productId));
    }

    //orderShipTest

    @Test
    void test2() {
        String orderId = UUID.randomUUID().toString();
        String productId = "Deluxe Chair";
        fixture.given(new OrderCreatedEvent(orderId, productId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));
    }
}