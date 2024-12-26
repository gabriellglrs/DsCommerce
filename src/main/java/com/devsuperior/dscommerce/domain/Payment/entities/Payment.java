package com.devsuperior.dscommerce.domain.Payment.entities;

import com.devsuperior.dscommerce.domain.order.entities.Order;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class Payment {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @Column(columnDefinition = "DATETIME")
     private Instant moment;

     @OneToOne
     @MapsId
     private Order order;

     public Payment(Long id, Instant moment, Order order) {
          this.id = id;
          this.moment = moment;
          this.order = order;
     }
}
