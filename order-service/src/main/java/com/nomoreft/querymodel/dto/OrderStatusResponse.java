package com.nomoreft.querymodel.dto;

import com.nomoreft.axonmsa.queries.entity.OrderStatus;

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
