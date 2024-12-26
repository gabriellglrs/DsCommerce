package com.devsuperior.dscommerce.domain.product.DTO;


import com.devsuperior.dscommerce.domain.product.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
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

        public ProductDTO(Product product) {
                id = product.getId();
                name = product.getName();
                description = product.getDescription();
                price = product.getPrice();
                imgUrl = product.getImgUrl();
        }
}
