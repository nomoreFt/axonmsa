package com.nomoreft.command.api.eventhandler;


import com.nomoreft.axonmsa.events.product.ProductCreatedEvent;

public interface ProductEventsHandler {

    public void on(ProductCreatedEvent event) throws Exception;

}
