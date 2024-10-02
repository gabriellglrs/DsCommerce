package com.devsuperior.dscommerce.DTO;

import com.devsuperior.dscommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
        Long id,

        @Size(min = 3, max = 80, message = "nome presisar ter de 3 a 80 caracteres")
        @NotBlank(message = "Campo requerido")
        String name,

        @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
        @NotBlank(message = "Campo requerido")
        String description,

        @Positive(message = "o preco deve ser um valor positivo")
        Double price,

        String imgUrl) {

     public ProductDTO(Product product) {
          this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
     }
}
