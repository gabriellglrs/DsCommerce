package com.devsuperior.dscommerce.domain.category.services;

import com.devsuperior.dscommerce.domain.category.dto.CategoryDto;
import com.devsuperior.dscommerce.domain.category.entities.Category;
import com.devsuperior.dscommerce.domain.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

     private final CategoryRepository categoryRepository;

     @Autowired
     public CategoryService(CategoryRepository categoryRepository) {
          this.categoryRepository = categoryRepository;
     }

     @Transactional
     public List<CategoryDto> findAll() {
          List<Category> result = categoryRepository.findAll();
          return result.stream().map(CategoryDto::new).toList();
     }
}
