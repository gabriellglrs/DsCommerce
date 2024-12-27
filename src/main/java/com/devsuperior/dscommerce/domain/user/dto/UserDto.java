package com.devsuperior.dscommerce.domain.user.dto;

import com.devsuperior.dscommerce.domain.user.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDto {
     private Long id;
     private String name;
     private String email;
     private String phone;
     private LocalDate birthDate;

     private List<String> roles = new ArrayList<>();

     public UserDto(User user) {
          id = user.getId();
          name = user.getName();
          email = user.getEmail();
          phone = user.getPhone();
          birthDate = user.getBirthDate();
          for (GrantedAuthority role : user.getRoles()) {
               roles.add(role.getAuthority());
          }
     }
}
