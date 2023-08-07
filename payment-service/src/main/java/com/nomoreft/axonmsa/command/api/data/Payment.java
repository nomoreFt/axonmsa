package com.nomoreft.axonmsa.command.api.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Payment {
    @Id
    private String paymentId;
    private String orderId;
    private Date timeStamp;
    private String paymentStatus;

}
