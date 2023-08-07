package com.nomoreft.axonmsa.commands.common.model;

import lombok.Builder;

@Builder
public record CardDetails (String name, String cardNumber, Integer validUntilMonth, Integer validUntilYear,
                           Integer cvv){
}
