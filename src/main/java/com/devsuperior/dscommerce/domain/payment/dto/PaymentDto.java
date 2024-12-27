package com.devsuperior.dscommerce.domain.payment.dto;

import com.devsuperior.dscommerce.domain.payment.entities.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Getter
public class PaymentDto {
     private Long id;
     private Instant moment;

     public PaymentDto(Payment payment) {
          id = payment.getId();
          moment = payment.getMoment();
     }
}
