package com.nomoreft.axonmsa.command.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends OrderRepository, JpaRepository<Order,String> {
}
