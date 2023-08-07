package com.nomoreft.axonmsa.projection;

import com.nomoreft.axonmsa.commands.common.model.CardDetails;
import com.nomoreft.axonmsa.commands.common.model.User;
import com.nomoreft.axonmsa.queries.common.FindUserPaymentDetailsQuery;
import lombok.extern.apachecommons.CommonsLog;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(FindUserPaymentDetailsQuery query) {
        //Ideally Get the details from the DB
        CardDetails cardDetails
                = CardDetails.builder()
                .name("Hyunwoo Kim")
                .validUntilYear(2023)
                .validUntilMonth(01)
                .cardNumber("123456789")
                .build();

        return User.builder()
                .cardDetails(cardDetails)
                .userId(query.userId())
                .firstName("Kim")
                .lastName("Hyunwoo")
                .build();
    }
}
