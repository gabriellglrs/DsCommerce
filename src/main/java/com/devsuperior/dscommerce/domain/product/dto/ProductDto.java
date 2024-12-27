package com.devsuperior.dscommerce.domain.product.dto;


import com.devsuperior.dscommerce.domain.category.dto.CategoryDto;
import com.devsuperior.dscommerce.domain.product.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ProductDto {
        private Long id;

        @Size(min = 3, max = 80, message = "nome presisar ter de 3 a 80 caracteres")
        @NotBlank(message = "Campo requerido")
        private String name;
        @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
        @NotBlank(message = "Campo requerido")
        private String description;
        @Positive(message = "o preco deve ser um valor positivo")
        private Double price;
        private String imgUrl;

        @NotEmpty(message = "Deve ter pelo Menos uma categoria")
        private List<CategoryDto>  categories = new ArrayList<>();

        public ProductDto(Product product) {
                id = product.getId();
                name = product.getName();
                description = product.getDescription();
                price = product.getPrice();
                imgUrl = product.getImgUrl();
                categories = product.getCategories().stream().map(CategoryDto::new).toList();
        }
}
