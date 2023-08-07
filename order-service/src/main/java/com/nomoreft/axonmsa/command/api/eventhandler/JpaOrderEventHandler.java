package com.nomoreft.axonmsa.command.api.eventhandler;

import com.nomoreft.axonmsa.command.api.domain.JpaOrderRepository;
import com.nomoreft.axonmsa.command.api.domain.Order;
import com.nomoreft.axonmsa.command.api.domain.OrderRepository;
import com.nomoreft.axonmsa.events.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class JpaOrderEventHandler implements OrderEventHandler{

    private JpaOrderRepository orderRepository;

    public JpaOrderEventHandler(JpaOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Order order = Order.builder().build();
        BeanUtils.copyProperties(event, order);

        orderRepository.save(order);

    }
}
