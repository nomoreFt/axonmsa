package com.nomoreft.command.api.model;

import java.math.BigDecimal;

public record ProductRestModel(String name, BigDecimal price, Integer quantity) {
}
