package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

     @Serial
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  Long id;

     private Instant moment;
     private OrderStatus status;

     @ManyToOne
     @JoinColumn(name = "client_id")
     private User client;

     @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
     private Payment payment;

     @OneToMany(mappedBy = "id.order")
     private Set<OrderItem> items = new HashSet<>();

     public Order(Long id, Instant moment, OrderStatus status, User client, Payment payment) {
          this.id = id;
          this.moment = moment;
          this.status = status;
          this.client = client;
          this.payment = payment;
     }

     public List<Product> getProducts() {
          return items.stream().map(OrderItem::getProduct).toList();
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Order order = (Order) o;
          return Objects.equals(id, order.id);
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}
