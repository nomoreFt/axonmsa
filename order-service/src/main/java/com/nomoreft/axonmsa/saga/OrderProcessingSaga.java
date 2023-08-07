package com.nomoreft.axonmsa.saga;

import com.nomoreft.axonmsa.commands.common.ShipOrderCommand;
import com.nomoreft.axonmsa.commands.common.ValidatePaymentCommand;
import com.nomoreft.axonmsa.commands.common.model.User;
import com.nomoreft.axonmsa.events.OrderCreatedEvent;
import com.nomoreft.axonmsa.events.common.PaymentProcessedEvent;
import com.nomoreft.axonmsa.queries.common.FindUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for Order Id : {}", event.orderId());

        FindUserPaymentDetailsQuery findUserPaymentDetailsQuery
                = new FindUserPaymentDetailsQuery(event.orderId());

        User user = null;
        try {
             user = queryGateway.query(findUserPaymentDetailsQuery,
                    ResponseTypes.instanceOf(User.class)).join();
        } catch (Exception e) {
            log.error(e.getMessage());
            //Start the Compensating transaction
        }

        ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder()
                .cardDetails(user.cardDetails())
                .orderId(event.orderId())
                .paymentId(UUID.randomUUID().toString())
                .build();

        commandGateway.sendAndWait(validatePaymentCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event) {
        ShipOrderCommand.builder().build();
    }
}
