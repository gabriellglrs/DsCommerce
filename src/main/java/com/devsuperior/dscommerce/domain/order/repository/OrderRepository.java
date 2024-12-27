package com.devsuperior.dscommerce.domain.order.repository;

import com.devsuperior.dscommerce.domain.order.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
