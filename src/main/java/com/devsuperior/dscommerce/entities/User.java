package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

     @Serial
     private static final long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String name;

     @Column(unique = true)
     private String email;
     private String phone;
     private LocalDate birthDate;
     private String password;

     @OneToMany(mappedBy = "client")
     private List<Order> orders = new ArrayList<>();

     public User(Long id, String name, String email, String phone, LocalDate birthDate, String password) {
          this.id = id;
          this.name = name;
          this.email = email;
          this.phone = phone;
          this.birthDate = birthDate;
          this.password = password;
     }
}