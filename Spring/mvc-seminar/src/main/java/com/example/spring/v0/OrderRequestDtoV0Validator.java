package com.example.spring.v0;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderRequestDtoV0Validator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RentOrderRequestV0Dto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RentOrderRequestV0Dto dto = (RentOrderRequestV0Dto) target;
        if (dto.getStartDateTime().isAfter(dto.getEndDateTime())) {
            errors.rejectValue("startDateTime", "wrongValue", "시작일이 종료일보다 늦습니다.");
        }
    }
}
