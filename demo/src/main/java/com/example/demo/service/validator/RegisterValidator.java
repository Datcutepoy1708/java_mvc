package com.example.demo.service.validator;

import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.RegisterDTO;
import com.example.demo.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class RegisterValidator implements ConstraintValidator<RegisterCheck,RegisterDTO> {
    
    private final UserService userService;
    public RegisterValidator(UserService userService){
      this.userService=userService;
    }

    @Override
    public boolean isValid(RegisterDTO user,ConstraintValidatorContext context){
        boolean valid=true;
        if(user.getPassword() == null || user.getConfirmPassword() == null) {
        return false;
        }
        if(!user.getPassword().equals(user.getConfirmPassword())) {
          context.buildConstraintViolationWithTemplate("Passwords must match")
            .addPropertyNode("confirmPassword")
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
          valid=false;  
        }
        //check email
        if(this.userService.checkEmailExist(user.getEmail())){
          context.buildConstraintViolationWithTemplate("The email already exists")
          .addPropertyNode("email")
          .addConstraintViolation()
          .disableDefaultConstraintViolation();
          valid=false;
        }

        return valid;
    }
}
