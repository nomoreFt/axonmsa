package com.nomoreft.command.api.eventhandler;

import com.nomoreft.axonmsa.events.product.ProductCreatedEvent;
import com.nomoreft.command.api.domain.Product;
import com.nomoreft.command.api.domain.ProductJpaRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("product")
public class JpaProductEventsHandler implements ProductEventsHandler{
    private ProductJpaRepository productJpaRepository;

    public JpaProductEventsHandler(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = Product.builder()
                .productId(event.productId())
                .name(event.name())
                .price(event.price())
                .quantity(event.quantity())
                .build();

        productJpaRepository.save(product);
        //throw new Exception("Test");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
