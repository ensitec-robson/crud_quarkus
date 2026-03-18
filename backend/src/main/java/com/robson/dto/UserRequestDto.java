package com.robson.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDto {

    @NotBlank(message = "O nome é obrigatório")
    public String name;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "o email deve ser válido")
    public String email;
}