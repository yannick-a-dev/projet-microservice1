package com.micro_service_1.micro_service_1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro_service_1.micro_service_1.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
