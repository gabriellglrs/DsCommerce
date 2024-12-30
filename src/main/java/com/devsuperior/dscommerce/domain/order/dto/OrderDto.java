package com.devsuperior.dscommerce.domain.order.dto;

import com.devsuperior.dscommerce.domain.order.entities.Order;
import com.devsuperior.dscommerce.domain.order.entities.OrderItem;
import com.devsuperior.dscommerce.domain.order.enums.OrderStatus;
import com.devsuperior.dscommerce.domain.payment.dto.PaymentDto;
import com.devsuperior.dscommerce.domain.user.dto.UserClientDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class OrderDto {
     private Long id;
     private Instant moment;
     private OrderStatus status;

     private UserClientDto client;
     private PaymentDto payment;
     @NotEmpty(message = "Deve ter pelo menos 1 item")
     private List<OrderItemDto> items = new ArrayList<>();

     public OrderDto(Order order) {
          id = order.getId();
          moment = order.getMoment();
          status = order.getStatus();
          client = new UserClientDto(order.getClient());
          payment = (order.getPayment() == null) ? null : new PaymentDto(order.getPayment());
          for (OrderItem item : order.getItems()) {
               OrderItemDto itemDto = new OrderItemDto(item);
               items.add(itemDto);
          }
     }

     public Double getTotal () {
          double sum = 0.0;
          for (OrderItemDto item : items) {
               sum += item.getSubTotal();
          }
          return sum;
     }
}
