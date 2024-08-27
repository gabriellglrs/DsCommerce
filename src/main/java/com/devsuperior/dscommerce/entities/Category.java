package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {
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

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Category category = (Category) o;
          return Objects.equals(id, category.id);
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}
