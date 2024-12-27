package com.devsuperior.dscommerce.domain.order.services;

import com.devsuperior.dscommerce.domain.order.dto.OrderDto;
import com.devsuperior.dscommerce.domain.order.entities.Order;
import com.devsuperior.dscommerce.domain.order.repository.OrderRepository;
import com.devsuperior.dscommerce.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
     private final OrderRepository orderRepository;

     @Autowired
     public OrderService(OrderRepository orderRepository) {
          this.orderRepository = orderRepository;
     }

     // Read (Buscar um produto por ID)
     @Transactional(readOnly = true)
     public OrderDto findById(Long id) {
          Order order = orderRepository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
          return new OrderDto(order);
     }
}
