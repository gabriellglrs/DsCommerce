package com.devsuperior.dscommerce.domain.product.controllers;

import com.devsuperior.dscommerce.domain.product.DTO.ProductDTO;
import com.devsuperior.dscommerce.domain.product.services.ProductService;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

     private final ProductService service;

     public ProductController(ProductService service) {
          this.service = service;
     }

     // POST - Create (Criar um novo produto)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     @PostMapping
     public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
          ProductDTO newDto = service.create(dto);
          return ResponseEntity
                  .created(ServletUriComponentsBuilder
                          .fromCurrentRequest()
                          .path("/{id}")
                          .buildAndExpand(newDto.getId())
                          .toUri())
                  .body(newDto);
     }

     // GET READ = (Buscar um produto por ID)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
     @GetMapping(value = "/{id}")
     public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
          ProductDTO dto = service.findById(id);
          return ResponseEntity.ok(dto);
     }

     // GET READ = (Buscar todos os produtos com paginação)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
     @GetMapping
     public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam(name = "name", defaultValue = "") String name, Pageable pageable) {
          Page<ProductDTO> dtoList = service.findAll(name, pageable);
          return ResponseEntity.ok(dtoList);
     }

     // PUT UPDATE = (Buscar todos os produtos com paginação)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     @PutMapping(value = "/{id}")
     public ResponseEntity<ProductDTO> update(@PathVariable Long id,@Valid @RequestBody ProductDTO dto) {
          ProductDTO updatedDto = service.update(id, dto);
          return ResponseEntity.ok(updatedDto);
     }

     // DELETE EXCLUIR = (Excluir um produto por ID)
     @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
     @DeleteMapping(value = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id) {
          service.delete(id);
          return ResponseEntity.noContent().build();
     }
}
