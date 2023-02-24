package com.example.mvcseminar.repository;

import com.example.mvcseminar.entity.RentOrder;
import com.example.mvcseminar.exception.OrderException;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class OrderRepository {

    public void save(RentOrder rentOrder) {
        if (Objects.requireNonNull(rentOrder).getName().contains("error")) {
            throw new OrderException("error 예약!!");
        }
    }
}
