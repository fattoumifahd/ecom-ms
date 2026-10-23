package com.example.identityms.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//@Constraint()
public class StrongPasswordValidation implements ConstraintValidator<StrongPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        // check if the password has at least one uppercase latter, one lowercase latter, special character and has at 8 characters long
        return value.matches("^(?=.*//d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$?^&*+_=()]){8,}$");
    }
}
