package com.devsuperior.dscommerce.domain.category.controllers;

import com.devsuperior.dscommerce.domain.category.dto.CategoryDto;
import com.devsuperior.dscommerce.domain.category.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

     private  final CategoryService categoryService;

     @Autowired
     public CategoryController(CategoryService categoryService) {
          this.categoryService = categoryService;
     }

     @GetMapping
     public ResponseEntity<List<CategoryDto>> findAll() {
          List<CategoryDto> list = categoryService.findAll();
          return ResponseEntity.ok(list);
     }
}
