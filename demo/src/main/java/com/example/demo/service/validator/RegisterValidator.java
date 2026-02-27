package com.example.demo.service.validator;

import com.example.demo.domain.dto.RegisterDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<RegisterCheck,RegisterDTO> {
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
        return valid;
    }
}
