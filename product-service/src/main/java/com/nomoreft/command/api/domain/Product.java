package com.nomoreft.command.api.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "create")
@EqualsAndHashCode(of = {"productId"})
@DynamicUpdate
public class Product {

        @Id
        private String productId;
        private String name;
        private BigDecimal price;
        private Integer quantity;

}
