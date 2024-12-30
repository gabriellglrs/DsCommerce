package com.devsuperior.dscommerce.domain.order.dto;

import com.devsuperior.dscommerce.domain.order.entities.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderItemDto {
     private Long productId;
     private String name;
     private Double price;
     private Integer quantity;
     private String imgUrl;

     public OrderItemDto(OrderItem orderItem) {
          productId = orderItem.getProduct().getId();
          name = orderItem.getProduct().getName();
          price = orderItem.getPrice();
          quantity = orderItem.getQuantity();
          imgUrl = orderItem.getProduct().getImgUrl();
     }

     public Double getSubTotal() {
          return price * quantity;
     }

}
