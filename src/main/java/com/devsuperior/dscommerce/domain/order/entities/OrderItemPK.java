package com.devsuperior.dscommerce.domain.order.entities;

import com.devsuperior.dscommerce.domain.product.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class OrderItemPK {

     @ManyToOne
     @JoinColumn(name = "order_id")
     private Order order;

     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product;
}
