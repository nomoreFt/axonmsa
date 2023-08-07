package com.nomoreft.axonmsa.command.api.controller;

import com.nomoreft.axonmsa.commands.order.*;
import com.nomoreft.axonmsa.commands.order.model.OrderRestModel;
import com.nomoreft.axonmsa.commands.order.model.OrderStatus;
import com.nomoreft.axonmsa.service.OrderQueryService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway, OrderQueryService orderQueryService) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderRestModel orderRestModel) {
        String orderId = UUID.randomUUID().toString();
        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .orderId(orderId)
                .addressId(orderRestModel.addressId())
                .productId(orderRestModel.productId())
                .quantity(orderRestModel.quantity())
                .orderStatus(OrderStatus.CREATED)
                .build();

        commandGateway.sendAndWait(createOrderCommand);

        return "Order Created";
    }



}
