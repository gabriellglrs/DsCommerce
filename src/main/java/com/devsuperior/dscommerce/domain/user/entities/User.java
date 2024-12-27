package com.devsuperior.dscommerce.domain.user.entities;

import com.devsuperior.dscommerce.domain.order.entities.Order;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

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

     @ManyToMany
     @JoinTable(
             name = "tb_user_role", // Nome da tabela de junção
             joinColumns = @JoinColumn(name = "user_id"), // Coluna que referencia user
             inverseJoinColumns = @JoinColumn(name = "role_id") // Coluna que referencia role
     )
     private List<Role> roles = new ArrayList<>();

     public void addRole(Role role) {
          roles.add(role);
     }

     public boolean hasRole(String roleName) {
          for (Role role : roles) {
               if (role.getAuthority().equals(roleName)) {
                    return true;
               }
          }
          return false;
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return roles;
     }

     @Override
     public String getUsername() {
          return email;
     }

     @Override
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }
}
