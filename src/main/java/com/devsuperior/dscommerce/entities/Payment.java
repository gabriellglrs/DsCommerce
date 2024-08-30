package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

     @Serial
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private Instant moment;

     @OneToOne
     @MapsId
     private Order order;

     public Payment(Long id, Instant moment, Order order) {
          this.id = id;
          this.moment = moment;
          this.order = order;
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Payment payment = (Payment) o;
          return Objects.equals(id, payment.id);
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}
