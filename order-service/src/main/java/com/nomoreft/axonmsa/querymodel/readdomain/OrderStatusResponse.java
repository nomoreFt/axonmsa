package com.nomoreft.axonmsa.querymodel.readdomain;

import com.nomoreft.axonmsa.commands.order.model.OrderStatus;

public enum OrderStatusResponse {
    CREATED, CONFIRMED, SHIPPED, UNKNOWN;

    static OrderStatusResponse from(OrderStatus status) {
        switch (status) {
            case CREATED:
                return CREATED;
            case CONFIRMED:
                return CONFIRMED;
            case SHIPPED:
                return SHIPPED;
            default:
                return UNKNOWN;
        }
    }
}
