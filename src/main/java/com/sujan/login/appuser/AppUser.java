package com.sujan.login.appuser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//To use the @Data annotation you should add the Lombok dependency.
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class AppUser implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String firstName;
   private String lastName;
   private String email;
   private String password;
   @Enumerated(EnumType.STRING)
   private AppUserRole appUserRole;
   private Boolean isLocked = true;
   private Boolean isEnabled = false;

   public AppUser(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.appUserRole = appUserRole;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
      return Collections.singletonList(authority);
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public String getUsername() {
      return email;
   }

   @Override
   public boolean isAccountNonExpired() {
      return false;
   }

   @Override
   public boolean isAccountNonLocked() {
      return !isLocked;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return false;
   }

   @Override
   public boolean isEnabled() {
      return isEnabled;
   }

}