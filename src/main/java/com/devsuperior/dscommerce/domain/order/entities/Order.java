package com.devsuperior.dscommerce.domain.order.entities;

import com.devsuperior.dscommerce.domain.order.enums.OrderStatus;
import com.devsuperior.dscommerce.domain.product.entities.Product;
import com.devsuperior.dscommerce.domain.payment.entities.Payment;
import com.devsuperior.dscommerce.domain.user.entities.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

     @Serial
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private  Long id;

     @Column(columnDefinition = "DATETIME")
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
}
