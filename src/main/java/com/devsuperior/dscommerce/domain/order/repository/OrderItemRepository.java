package com.devsuperior.dscommerce.domain.order.repository;

import com.devsuperior.dscommerce.domain.order.entities.OrderItem;
import com.devsuperior.dscommerce.domain.order.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
