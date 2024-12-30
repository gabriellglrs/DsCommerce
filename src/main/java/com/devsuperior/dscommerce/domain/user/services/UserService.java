package com.devsuperior.dscommerce.domain.user.services;

import com.devsuperior.dscommerce.domain.user.dto.UserDto;
import com.devsuperior.dscommerce.domain.user.entities.Role;
import com.devsuperior.dscommerce.domain.user.entities.User;
import com.devsuperior.dscommerce.domain.user.enums.RoleType;
import com.devsuperior.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dscommerce.domain.user.repository.UserRepository;
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

          if (result.isEmpty()) {
               throw new UsernameNotFoundException("User nao existe");
          }

          User user = new User();
          user.setEmail(username);
          user.setPassword(result.get(0).getPassword());

          for (UserDetailsProjection projection : result) {
               user.addRole(new Role(RoleType.valueOf(projection.getAuthority()), projection.getRoleId()));
          }
          return user;
     }

     public User authenticated() {
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
