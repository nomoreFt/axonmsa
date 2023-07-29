package com.nomoreft.axonmsa.queries.order.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@EqualsAndHashCode
public class Order {

    private final String orderId;
    private final String productId;
    private OrderStatus orderStatus;

    public Order(String orderId,String productId) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderStatus = OrderStatus.CREATED;
    }

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        this.orderStatus = OrderStatus.SHIPPED;
    }


}
