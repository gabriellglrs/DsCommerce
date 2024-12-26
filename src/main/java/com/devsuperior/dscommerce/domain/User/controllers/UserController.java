package com.devsuperior.dscommerce.domain.User.controllers;

import com.devsuperior.dscommerce.domain.User.dto.UserDto;
import com.devsuperior.dscommerce.domain.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

     private UserService userService;

     @Autowired
     public UserController(UserService userService) {
          this.userService = userService;
     }

     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
     @GetMapping(value = "/me")
     public ResponseEntity<UserDto> FindMe() {
          UserDto userDto = userService.getMe();
          return ResponseEntity.ok(userDto);
     }
}
