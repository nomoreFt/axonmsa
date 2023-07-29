package com.nomoreft.querymodel;

import com.nomoreft.axonmsa.events.order.OrderCreatedEvent;
import com.nomoreft.axonmsa.events.ship.OrderShippedEvent;
import com.nomoreft.axonmsa.queries.order.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.order.entity.Order;

import java.util.List;

public interface OrderEventHandler {
    void on(OrderCreatedEvent event);
    void on(OrderShippedEvent event);

    List<Order> handle(FindAllOrderedProductsQuery query);
}
