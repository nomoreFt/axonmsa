package com.nomoreft.controller;

import com.nomoreft.axonmsa.commands.order.CreateOrderCommand;
import com.nomoreft.axonmsa.commands.ship.ShipOrderCommand;
import com.nomoreft.querymodel.dto.OrderResponse;
import com.nomoreft.service.OrderQueryService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
public class OrderController {

    private final CommandGateway commandGateway;
    private final OrderQueryService orderQueryService;

    public OrderController(CommandGateway commandGateway, OrderQueryService orderQueryService) {
        this.commandGateway = commandGateway;
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/ship-order")
    public CompletableFuture<Void> shipOrder() {
        String orderId = UUID.randomUUID()
                .toString();
        return commandGateway.send(new CreateOrderCommand(orderId,"testProduct"))
                .thenCompose(result -> commandGateway.send(new ShipOrderCommand(orderId)));
    }

    @GetMapping("/all-orders")
    public CompletableFuture<List<OrderResponse>> findAllOrders() {
        return orderQueryService.findAllOrders();
    }

}
