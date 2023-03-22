package com.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null &&
                value.toString().matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
    }
}
