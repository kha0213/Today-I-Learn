package com.example.mvcseminar.entity;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class User {
    @Positive
    Long userId;
    @NotBlank
    String name;
    @Pattern(regexp = "[MF]")
    Gender gender;
    @Email
    String email;
}
