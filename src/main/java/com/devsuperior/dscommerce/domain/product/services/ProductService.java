package com.devsuperior.dscommerce.domain.product.services;

import com.devsuperior.dscommerce.domain.product.dto.ProductDto;
import com.devsuperior.dscommerce.domain.product.dto.ProductMinDto;
import com.devsuperior.dscommerce.domain.product.entities.Product;
import com.devsuperior.dscommerce.exceptions.DatabaseException;
import com.devsuperior.dscommerce.exceptions.ResourceNotFoundException;
import com.devsuperior.dscommerce.domain.product.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

     private final ProductRepository repository;

     public ProductService(ProductRepository repository) {
          this.repository = repository;
     }

     // Create (Inserir um novo produto)
     @Transactional
     public ProductDto create(ProductDto dto) {
          Product product = new Product();
          copyDtoToEntity(dto, product);
          product = repository.save(product);
          return new ProductDto(product);
     }

     // Read (Buscar um produto por ID)
     @Transactional(readOnly = true)
     public ProductDto findById(Long id) {
          Product product = repository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
          return new ProductDto(product);
     }

     // Read (Buscar todos os produtos com paginação)
     @Transactional(readOnly = true)
     public Page<ProductMinDto> findAll(String name, Pageable pageable) {
          Page<Product> result = repository.searchByName(name, pageable);
          return result.map(ProductMinDto::new);
     }


     // Update (Atualizar um produto existente)
     @Transactional
     public ProductDto update(Long id, ProductDto dto) {
          try {
               Product product = repository.getReferenceById(id);
               copyDtoToEntity(dto, product);
               product = repository.save(product);
               return new ProductDto(product);
          } catch (EntityNotFoundException e) {
               throw new ResourceNotFoundException("ID não encontrado: " + id);
          }
     }

     // Delete (Remover um produto por ID)
     @Transactional(propagation = Propagation.SUPPORTS)
     public void delete(Long id) {
          if (!repository.existsById(id)) {
               throw new ResourceNotFoundException("ID não encontrado: " + id);
          }
          try {
               repository.deleteById(id);
          } catch (DataIntegrityViolationException e) {
               throw new DatabaseException("Violação de integridade");
          }
     }

     // Método utilitário para copiar os dados do DTO para a entidade
     private void copyDtoToEntity(ProductDto dto, Product product) {
          product.setName(dto.getName());
          product.setDescription(dto.getDescription());
          product.setPrice(dto.getPrice());
          product.setImgUrl(dto.getImgUrl());
     }
}
