package com.devsuperior.dscommerce.domain.order.controllers;

import com.devsuperior.dscommerce.domain.order.dto.OrderDto;
import com.devsuperior.dscommerce.domain.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
     private final OrderService orderService;

     @Autowired
     public OrderController(OrderService orderService) {
          this.orderService = orderService;
     }

     // GET READ = (Buscar um produto por ID)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
     @GetMapping(value = "/{id}")
     public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
          OrderDto dto = orderService.findById(id);
          return ResponseEntity.ok(dto);
     }
}
