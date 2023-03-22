package com.example.validation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HomeController {
    @GetMapping("/")
    public String home(@Valid RentOrder rentOrder, BindingResult bindingResult) {
        log.info(rentOrder.toString());
        if (bindingResult.hasErrors()) {
            return "error" + bindingResult.getAllErrors().toString();
        }
        return rentOrder.getOrderId().toString();
    }

    @PostMapping("/")
    public String postHome(@Valid @RequestBody RentOrder rentOrder, BindingResult bindingResult) {
        log.info(rentOrder.toString());
        if (bindingResult.hasErrors()) {
            return "error" + bindingResult.getAllErrors().toString();
        }
        return rentOrder.getOrderId().toString();
    }
}
