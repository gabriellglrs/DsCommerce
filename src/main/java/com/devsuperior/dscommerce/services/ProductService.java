package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.DTO.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

     @Autowired
     private ProductRepository repository;

     @Transactional(readOnly = true)
     public ProductDTO findById(Long id) {
          Product product = repository.findById(id).get();
          return new ProductDTO(product);
     }

     @Transactional(readOnly = true)
     public Page<ProductDTO> findAll(Pageable pageable) {
          Page<Product> result = repository.findAll(pageable);
          return result.map(ProductDTO::new);
     }

     @Transactional
     public ProductDTO insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setImgUrl(dto.getImgUrl());

        product = repository.save(product);

        return new ProductDTO(product);
     }

}
