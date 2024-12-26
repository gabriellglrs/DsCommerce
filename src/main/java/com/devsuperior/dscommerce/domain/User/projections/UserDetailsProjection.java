package com.devsuperior.dscommerce.domain.User.projections;

public interface UserDetailsProjection {
     String getUsername();
     String getPassword();
     Long getRoleId();
     String getAuthority();
}
