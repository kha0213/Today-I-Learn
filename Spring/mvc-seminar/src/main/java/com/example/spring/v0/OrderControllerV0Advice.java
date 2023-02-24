package com.example.spring.v0;

import com.example.mvcseminar.exception.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

@ControllerAdvice(assignableTypes = OrderControllerV0.class)
public class OrderControllerV0Advice {
    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleOrderException(OrderException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleOrderException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        return allErrors.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new OrderRequestDtoV0Validator());
    }
}
