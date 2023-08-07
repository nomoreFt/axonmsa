package com.nomoreft.axonmsa.commands.common.model;

import lombok.Builder;

@Builder
public record User(String userId, String firstName, String lastName, CardDetails cardDetails) {
}
