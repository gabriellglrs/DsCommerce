package com.devsuperior.dscommerce.domain.category.entities;

import com.devsuperior.dscommerce.domain.product.entities.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

     @Serial
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     @ManyToMany(mappedBy = "categories")
     private Set<Product> products = new HashSet<>();

     public Category(Long id, String name) {
          this.id = id;
          this.name = name;
     }
}
