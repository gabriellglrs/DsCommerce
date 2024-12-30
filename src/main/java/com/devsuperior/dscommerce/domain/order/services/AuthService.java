package com.devsuperior.dscommerce.domain.order.services;

import com.devsuperior.dscommerce.domain.user.entities.User;
import com.devsuperior.dscommerce.domain.user.services.UserService;
import com.devsuperior.dscommerce.exceptions.ForbbidenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

     private final UserService userService;

     @Autowired
     public AuthService(UserService userService) {
          this.userService = userService;
     }

     public void validationSelfOfAdmin(Long userId) {
          User me = userService.authenticated();
          if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
               throw new ForbbidenException("Acesso Negado! - Usuario: " + me.getName() + " | Email: " + me.getEmail());
          }
     }
}
