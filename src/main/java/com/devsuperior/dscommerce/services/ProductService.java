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


     private ProductRepository repository;

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
          Product product = repository.findById(id).get();
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
          Product product = repository.getReferenceById(id);
          copyDtoToEntity(dto, product);
          product = repository.save(product);
          return new ProductDTO(product);
     }

     // Delete (Remover um produto por ID)
     @Transactional
     public void delete(Long id) {
          repository.deleteById(id);
     }

     // Método utilitário para copiar os dados do DTO para a entidade
     private void copyDtoToEntity(ProductDTO dto, Product product) {
          product.setName(dto.name());
          product.setDescription(dto.description());
          product.setPrice(dto.price());
          product.setImgUrl(dto.imgUrl());
     }
}
