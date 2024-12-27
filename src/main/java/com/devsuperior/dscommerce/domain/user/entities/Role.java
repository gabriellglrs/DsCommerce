package com.devsuperior.dscommerce.domain.user.entities;

import com.devsuperior.dscommerce.domain.user.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "authority")
@Getter
@Setter
@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Enumerated(EnumType.STRING) // ENUM
     private RoleType authority;

     @ManyToMany(mappedBy = "roles")
     private List<User> user = new ArrayList<>();

     public Role(RoleType authority, Long id) {
          this.authority = authority;
          this.id = id;
     }

     @Override
     public String getAuthority() {
          return authority.name();
     }
}
