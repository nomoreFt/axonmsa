package com.nomoreft.querymodel;

import com.nomoreft.axonmsa.events.*;
import com.nomoreft.axonmsa.queries.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.OrderUpdatesQuery;
import com.nomoreft.axonmsa.queries.TotalProductsShippedQuery;
import com.nomoreft.axonmsa.queries.entity.Order;
import org.reactivestreams.Publisher;

import java.util.List;

public interface OrderEventHandler {
    void on(OrderCreatedEvent event);

    void on(ProductAddedEvent event);

    void on(ProductCountIncrementedEvent event);

    void on(ProductCountDecrementedEvent event);

    void on(ProductRemovedEvent event);

    void on(OrderShippedEvent event);
    List<Order> handle(FindAllOrderedProductsQuery query);

    Publisher<Order> handleStreaming(FindAllOrderedProductsQuery query);
    Integer handle(TotalProductsShippedQuery query);

    Order handle(OrderUpdatesQuery query);

    void reset(List<Order> orderList);
}
