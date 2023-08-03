package com.nomoreft.querymodel.projections;

import com.nomoreft.axonmsa.events.*;
import com.nomoreft.axonmsa.queries.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.OrderUpdatesQuery;
import com.nomoreft.axonmsa.queries.TotalProductsShippedQuery;
import com.nomoreft.commandmodel.writedomain.Order;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ProcessingGroup("orders")
public class InMemoryOrderEventHandler implements OrderEventHandler{

    private final Map<String, Order> orders = new HashMap<>();
    private final QueryUpdateEmitter emitter;

    public InMemoryOrderEventHandler(QueryUpdateEmitter emitter) {
        this.emitter = emitter;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        String orderId = event.orderId();
        orders.put(orderId, new Order(orderId));
    }

    @Override
    public void on(ProductAddedEvent event) {
        orders.computeIfPresent(event.orderId(), (orderId, order) -> {
            order.addProduct(event.productId());
           emitUpdate(order);
            return order;
        });
    }

    @Override
    public void on(ProductCountIncrementedEvent event) {

    }

    @Override
    public void on(ProductCountDecrementedEvent event) {

    }

    @Override
    public void on(ProductRemovedEvent event) {

    }

    @EventHandler
    public void on(OrderShippedEvent event) {

    }
    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Publisher<Order> handleStreaming(FindAllOrderedProductsQuery query) {
        return null;
    }

    @Override
    public Integer handle(TotalProductsShippedQuery query) {
        return null;
    }

    @Override
    public Order handle(OrderUpdatesQuery query) {
        return null;
    }

    @Override
    public void reset(List<Order> orderList) {

    }


/*    @EventHandler
    public void on(OrderShippedEvent event) {
        orders.computeIfPresent(event.orderId(), (orderId, order) -> {
            order.setOrderShipped();
            emitUpdate(order);
            return order;
        });
    }*/

    private void emitUpdate(Order order) {
        emitter.emit(OrderUpdatesQuery.class, q -> order.getOrderId()
                .equals(q.orderId()), order);
    }

}
