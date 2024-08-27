package com.devsuperior.dscommerce.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderItemPK {

     @ManyToOne
     @JoinColumn(name = "order_id")
     private Order order;

     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product;

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          OrderItemPK that = (OrderItemPK) o;
          return Objects.equals(order, that.order) && Objects.equals(product, that.product);
     }

     @Override
     public int hashCode() {
          return Objects.hash(order, product);
     }
}
