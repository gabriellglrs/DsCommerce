package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     @Column(columnDefinition = "TEXT")
     private String description;

     private Double price;
     private String imgUrl;

     @ManyToMany
     @JoinTable(name = "tb_product_category",
             joinColumns = @JoinColumn(name = "product_id"),
             inverseJoinColumns = @JoinColumn(name = "category_id"))
     private Set<Category> categories = new HashSet<>();

     @OneToMany(mappedBy = "id.product")
     private Set<OrderItem> items = new HashSet<>();

     public Product(Long id, String name, String description, Double price, String imgUrl) {
          this.id = id;
          this.name = name;
          this.description = description;
          this.price = price;
          this.imgUrl = imgUrl;
     }

     public List<Order> getOrders() {
          return  items.stream().map(OrderItem::getOrder).toList();
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Product product = (Product) o;
          return Objects.equals(id, product.id);
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}
