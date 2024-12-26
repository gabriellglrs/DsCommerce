package com.devsuperior.dscommerce.domain.User.service;

import com.devsuperior.dscommerce.domain.User.dto.UserDto;
import com.devsuperior.dscommerce.domain.User.entities.Role;
import com.devsuperior.dscommerce.domain.User.entities.User;
import com.devsuperior.dscommerce.domain.User.enums.RoleType;
import com.devsuperior.dscommerce.domain.User.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.domain.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
     private UserRepository repository;

     @Autowired
     public UserService(UserRepository repository) {
          this.repository = repository;
     }

     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

          if (result.size() == 0) {
               throw new com.devsuperior.dscommerce.exceptions.UsernameNotFoundException("User nao existe");
          }

          User user = new User();
          user.setEmail(username);
          user.setPassword(result.get(0).getPassword());

          for (UserDetailsProjection projection : result) {
               user.addRole(new Role(RoleType.valueOf(projection.getAuthority()), projection.getRoleId()));
          }
          return user;
     }

     protected User authenticated() {
          try {
               Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
               Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
               String username = jwtPrincipal.getClaim("username");
               return repository.findByEmail(username).get();
          } catch (Exception e) {
               throw new UsernameNotFoundException("Email not found");
          }
     }

     @Transactional(readOnly = true)
     public UserDto getMe() {
          User user = authenticated();
          return new UserDto(user);
     }
}
