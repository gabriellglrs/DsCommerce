package com.devsuperior.dscommerce.DTO;

import com.devsuperior.dscommerce.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductDTO {
     private Long id;
     private String name;
     private String description;
     private Double price;
     private String imgUrl;

     public ProductDTO(Product product) {
          id = product.getId();
          name = product.getName();
          description = product.getDescription();
          price = product.getPrice();
          imgUrl = product.getImgUrl();
     }
}
