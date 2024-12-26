package com.devsuperior.dscommerce.domain.product.repositories;

import com.devsuperior.dscommerce.domain.product.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

     @Query("select obj from Product obj where upper(obj.name) like upper(concat('%', :name, '%'))")
     Page<Product> searchByName(String name, Pageable pageable);
}
