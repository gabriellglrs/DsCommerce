package com.devsuperior.dscommerce.domain.order.services;

import com.devsuperior.dscommerce.domain.order.dto.OrderDto;
import com.devsuperior.dscommerce.domain.order.dto.OrderItemDto;
import com.devsuperior.dscommerce.domain.order.entities.Order;
import com.devsuperior.dscommerce.domain.order.entities.OrderItem;
import com.devsuperior.dscommerce.domain.order.enums.OrderStatus;
import com.devsuperior.dscommerce.domain.order.repository.OrderItemRepository;
import com.devsuperior.dscommerce.domain.order.repository.OrderRepository;
import com.devsuperior.dscommerce.domain.product.entities.Product;
import com.devsuperior.dscommerce.domain.product.repositories.ProductRepository;
import com.devsuperior.dscommerce.domain.user.entities.User;
import com.devsuperior.dscommerce.domain.user.services.UserService;
import com.devsuperior.dscommerce.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {
     private final OrderRepository orderRepository;
     private final UserService userService;
     private final ProductRepository productRepository;
     private final OrderItemRepository orderItemRepository;
     private final AuthService authService;

     @Autowired
     public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository, AuthService authService) {
          this.orderRepository = orderRepository;
          this.userService = userService;
          this.productRepository = productRepository;
          this.orderItemRepository = orderItemRepository;
          this.authService = authService;
     }

     @Transactional
     public OrderDto create(OrderDto dto) {
          Order order = new Order();

          order.setMoment(Instant.now());
          order.setStatus(OrderStatus.WAITING_PAYMENT);

          User user = userService.authenticated();
          order.setClient(user);

          for (OrderItemDto itemDto : dto.getItems()) {
               Product product = productRepository.getReferenceById(itemDto.getProductId());
               OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
               order.getItems().add(item);
          }

          orderRepository.save(order);
          orderItemRepository.saveAll(order.getItems());

          return new OrderDto(order);
     }

     // Read (Buscar um produto por ID)
     @Transactional(readOnly = true)
     public OrderDto findById(Long id) {
          Order order = orderRepository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
          authService.validationSelfOfAdmin(order.getClient().getId());
          return new OrderDto(order);
     }
}
