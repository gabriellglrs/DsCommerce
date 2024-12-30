package com.devsuperior.dscommerce.domain.order.controllers;

import com.devsuperior.dscommerce.domain.order.dto.OrderDto;
import com.devsuperior.dscommerce.domain.order.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
     private final OrderService orderService;

     @Autowired
     public OrderController(OrderService orderService) {
          this.orderService = orderService;
     }

     @PreAuthorize("hasAnyRole('ROLE_USER')")
     @PostMapping
     public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto dto) {
          orderService.create(dto);
          URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
          return ResponseEntity.created(uri).body(dto);
     }

     // GET READ = (Buscar um produto por ID)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
     @GetMapping(value = "/{id}")
     public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
          OrderDto dto = orderService.findById(id);
          return ResponseEntity.ok(dto);
     }
}
