package com.devsuperior.dscommerce.domain.order.entities;

import com.devsuperior.dscommerce.domain.product.entities.Product;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
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
}
