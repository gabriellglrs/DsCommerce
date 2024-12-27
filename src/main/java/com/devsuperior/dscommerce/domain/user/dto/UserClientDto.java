package com.devsuperior.dscommerce.domain.user.dto;

import com.devsuperior.dscommerce.domain.user.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserClientDto {
     private Long id;
     private String name;

     public UserClientDto(User user) {
          id = user.getId();
          name = user.getName();
     }
}
