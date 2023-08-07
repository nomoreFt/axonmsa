package com.nomoreft.axonmsa.command.api.events;

import com.nomoreft.axonmsa.command.api.data.Payment;
import com.nomoreft.axonmsa.command.api.data.PaymentRepository;
import com.nomoreft.axonmsa.events.common.PaymentProcessedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentEventHandler {

    private PaymentRepository repository;

    public PaymentEventHandler(PaymentRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) {
        Payment completed = Payment.builder()
                .paymentId(event.paymentId())
                .orderId(event.orderId())
                .paymentStatus("COMPLETED")
                .timeStamp(new Date())
                .build();

        repository.save(completed);
    }
}
