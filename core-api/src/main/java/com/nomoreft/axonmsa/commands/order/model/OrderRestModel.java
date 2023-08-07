package com.nomoreft.axonmsa.commands.order.model;

public record OrderRestModel (String addressId, String productId, Integer quantity, OrderStatus orderStatus){

}
