package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.DTO.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.exceptions.DatabaseException;
import com.devsuperior.dscommerce.exceptions.ResourceNotFoundException;
import com.devsuperior.dscommerce.repositories.ProductRepository;
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
     public ProductDTO create(ProductDTO dto) {
          Product product = new Product();
          copyDtoToEntity(dto, product);
          product = repository.save(product);
          return new ProductDTO(product);
     }

     // Read (Buscar um produto por ID)
     @Transactional(readOnly = true)
     public ProductDTO findById(Long id) {
          Product product = repository.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
          return new ProductDTO(product);
     }

     // Read (Buscar todos os produtos com paginação)
     @Transactional(readOnly = true)
     public Page<ProductDTO> findAll(Pageable pageable) {
          Page<Product> result = repository.findAll(pageable);
          return result.map(ProductDTO::new);
     }


     // Update (Atualizar um produto existente)
     @Transactional
     public ProductDTO update(Long id, ProductDTO dto) {
          try {
               Product product = repository.getReferenceById(id);
               copyDtoToEntity(dto, product);
               product = repository.save(product);
               return new ProductDTO(product);
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
     private void copyDtoToEntity(ProductDTO dto, Product product) {
          product.setName(dto.name());
          product.setDescription(dto.description());
          product.setPrice(dto.price());
          product.setImgUrl(dto.imgUrl());
     }
}
