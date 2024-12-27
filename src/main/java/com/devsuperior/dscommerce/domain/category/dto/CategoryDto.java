package com.devsuperior.dscommerce.domain.category.dto;

import com.devsuperior.dscommerce.domain.category.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryDto {
     private Long id;
     private String name;

     public CategoryDto(Category category) {
          id = category.getId();
          name = category.getName();
     }
}
