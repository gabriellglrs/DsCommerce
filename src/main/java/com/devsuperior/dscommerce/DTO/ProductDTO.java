package com.devsuperior.dscommerce.DTO;

import com.devsuperior.dscommerce.entities.Product;

public record ProductDTO(Long id, String name, String description, Double price, String imgUrl) {

     public ProductDTO(Product product) {
          this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
     }


}
