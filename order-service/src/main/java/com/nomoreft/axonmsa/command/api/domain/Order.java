package com.nomoreft.axonmsa.command.api.domain;

import com.nomoreft.axonmsa.commands.order.model.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    private String orderId;
    private String productId;
    private String userId;
    private String addressId;
    private Integer quantity;
    private OrderStatus orderStatus;


}
