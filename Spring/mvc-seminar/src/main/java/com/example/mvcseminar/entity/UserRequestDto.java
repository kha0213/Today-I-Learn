package com.example.mvcseminar.entity;

import com.example.mvcseminar.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @Positive
    Long userId;
    @NotBlank
    String name;
    @Pattern(regexp = "[MF]")
    String gender;

    String loginId;
    @Email
    String email;
}
