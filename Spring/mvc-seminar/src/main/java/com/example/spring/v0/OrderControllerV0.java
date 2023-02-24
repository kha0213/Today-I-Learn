package com.example.spring.v0;

import com.example.mvcseminar.controller.OrderController;
import com.example.mvcseminar.entity.RentOrder;
import com.example.mvcseminar.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.mvcseminar.entity.RentOrder.of;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 implements OrderController {
    private final OrderRepository orderRepository;

    @Override
    public RentOrder saveRentOrder(@Validated(SaveRequest.class) RentOrderRequestV0Dto dto) {
        RentOrder rentOrder = of(dto);
        orderRepository.save(rentOrder);
        return rentOrder;
    }

    @Override
    public RentOrder updateRentOrder(@Validated(UpdateRequest.class) RentOrderRequestV0Dto dto) {
        RentOrder rentOrder = of(dto);
        orderRepository.save(rentOrder);
        return rentOrder;
    }
}
