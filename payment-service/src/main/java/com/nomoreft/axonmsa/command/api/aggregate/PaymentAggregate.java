package com.nomoreft.axonmsa.command.api.aggregate;

import com.nomoreft.axonmsa.commands.common.ValidatePaymentCommand;
import com.nomoreft.axonmsa.events.common.PaymentProcessedEvent;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private String paymentId;
    private String orderId;
    private String paymentStatus;

    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand command) {
        //Validate the PaymentDetails
        //Publish the Payment Processed event
        log.info("Executing ValidatePaymentCommand for " +
                        "Order Id {} and Payment Id: {}",
                command.orderId(),
                command.paymentId());

        PaymentProcessedEvent paymentProcessedEvent = PaymentProcessedEvent.builder()
                .paymentId(command.paymentId())
                .orderId(command.orderId())
                .build();


        AggregateLifecycle.apply(paymentProcessedEvent);

        log.info("PaymentProcessedEvent Applied");
    }

    @EventSourcingHandler
    public void on(PaymentProcessedEvent event) {
        this.paymentId = event.paymentId();
        this.orderId = event.orderId();
    }
}
