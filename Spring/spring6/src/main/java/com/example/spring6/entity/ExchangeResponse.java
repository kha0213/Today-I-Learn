package com.example.spring6.entity;

import java.util.List;

public record ExchangeResponse(List<ExchangeRate> rates) {

    static record ExchangeRate(String name, Double rate) {

    }
}
