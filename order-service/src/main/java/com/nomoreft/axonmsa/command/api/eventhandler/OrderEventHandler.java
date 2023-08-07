package com.nomoreft.axonmsa.command.api.eventhandler;

import com.nomoreft.axonmsa.events.*;
import com.nomoreft.axonmsa.queries.FindAllOrderedProductsQuery;
import com.nomoreft.axonmsa.queries.OrderUpdatesQuery;
import com.nomoreft.axonmsa.queries.TotalProductsShippedQuery;
import com.nomoreft.axonmsa.command.api.domain.Order;
import org.reactivestreams.Publisher;

import java.util.List;

public interface OrderEventHandler {
    void on(OrderCreatedEvent event);
}
