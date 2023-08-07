package com.nomoreft.axonmsa.commands.order;

import com.nomoreft.axonmsa.commands.order.model.OrderStatus;
import lombok.Builder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;


@Builder
public record CreateOrderCommand(@TargetAggregateIdentifier String orderId,String productId, String userId,
                                 String addressId, Integer quantity, OrderStatus orderStatus) {

}
