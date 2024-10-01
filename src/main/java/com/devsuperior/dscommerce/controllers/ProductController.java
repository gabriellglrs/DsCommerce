package com.devsuperior.dscommerce.controllers;

import com.devsuperior.dscommerce.DTO.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

     @Autowired
     private ProductService service;

     // POST - Create (Criar um novo produto)
     @PostMapping
     public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
          ProductDTO newDto = service.create(dto);
          return ResponseEntity
                  .created(ServletUriComponentsBuilder
                          .fromCurrentRequest()
                          .path("/{id}")
                          .buildAndExpand(newDto.id())
                          .toUri())
                  .body(newDto);
     }

     // GET READ = (Buscar um produto por ID)
     @GetMapping(value = "/{id}")
     public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
          ProductDTO dto = service.findById(id);
         return ResponseEntity.ok(dto);
     }

     // GET READ = (Buscar todos os produtos com paginação)
     @GetMapping
     public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
          Page<ProductDTO> dtoList = service.findAll(pageable);
          return ResponseEntity.ok(dtoList);
     }

     // PUT UPDATE = (Buscar todos os produtos com paginação)
     @PutMapping(value = "/{id}")
     public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
          ProductDTO updatedDto = service.update(id, dto);
          return ResponseEntity.ok(updatedDto);
     }

     // DELETE EXCLUIR = (Excluir um produto por ID)
     @DeleteMapping(value = "/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id) {
          service.delete(id);
          return ResponseEntity.noContent().build();
     }
}
