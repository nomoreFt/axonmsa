package com.nomoreft.axonmsa.queries.common;

import lombok.Builder;

@Builder
public record FindUserPaymentDetailsQuery(String userId) {
}
