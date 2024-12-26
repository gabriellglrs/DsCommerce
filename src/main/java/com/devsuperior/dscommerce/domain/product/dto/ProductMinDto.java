package com.devsuperior.dscommerce.domain.product.dto;

import com.devsuperior.dscommerce.domain.product.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductMinDto {
     private Long id;
     private String name;
     private Double price;
     private String imgUrl;

     public ProductMinDto(Product product) {
          id = product.getId();
          name = product.getName();
          price = product.getPrice();
          imgUrl = product.getImgUrl();
     }
}
