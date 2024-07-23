package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PlayerDTO(
    Long id,

    @NotBlank(message = "O campo nome não pode estar vazio") 
    String name,

    @NotBlank(message = "O campo email não pode estar vazio") 
    @Email (message = "O email tem que ser valido")
    String email,

    String phoneNumber,

    String codename,

    String group
) {
    
}
