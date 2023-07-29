package com.nomoreft.querymodel;

import com.nomoreft.axonmsa.events.order.OrderCreatedEvent;
import com.nomoreft.axonmsa.events.ship.OrderShippedEvent;
import com.nomoreft.axonmsa.queries.order.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.order.entity.Order;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
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
        orders.put(orderId, new Order(orderId, event.productId()));
    }

    @EventHandler
    public void on(OrderShippedEvent event) {

    }
    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orders.values());
    }


    /*@EventHandler
    public void on(OrderShippedEvent event) {
        orders.computeIfPresent(event.orderId(), (orderId, order) -> {
            order.setOrderShipped();
            emitter.emitUpdate(order);
            return order;
        });
    }

    private void emitUpdate(Order order) {
        emitter.emit(OrderUpdatesQuery.class, q -> order.getOrderId()
                .equals(q.getOrderId()), order);
    }*/

}
