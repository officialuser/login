package com.sujan.login.registration;

import com.sujan.login.appuser.AppUser;
import com.sujan.login.appuser.AppUserRole;
import com.sujan.login.appuser.AppUserService;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

   private EmailValidator emailValidator;
   private AppUserService appUserService;


   public String register(RegistrationRequest request) {

      boolean isValidEmail = emailValidator.test(request.getEmail());

      if (!isValidEmail) {
         throw new IllegalStateException("Email is not valid");
      }
      return appUserService.signUpUser(
            new AppUser(request.getFirstName(),
                  request.getLastName(),
                  request.getEmail(),
                  request.getPassword(),
                  AppUserRole.USER));
   }

}
