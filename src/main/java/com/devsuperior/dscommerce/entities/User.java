package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
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

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          User user = (User) o;
          return Objects.equals(id, user.id);
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}