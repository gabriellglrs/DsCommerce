package com.devsuperior.dscommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

     @Serial
     private static final long serialVersionUID = 1L;

     @EmbeddedId
     private OrderItemPK id = new OrderItemPK();
     private Integer quantity;
     private Double price;

     public OrderItem(Order order, Product product, Integer quantity, Double price) {
          id.setOrder(order);
          id.setProduct(product);
          this.quantity = quantity;
          this.price = price;
     }

     public void setOrder(Order order) {
          id.setOrder(order);
     }

     public Order getOrder() {
          return id.getOrder();
     }

     public void setProduct(Product product) {
          id.setProduct(product);
     }

     public Product getProduct() {
          return id.getProduct();
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          OrderItem orderItem = (OrderItem) o;
          return Objects.equals(id, orderItem.id);
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}
