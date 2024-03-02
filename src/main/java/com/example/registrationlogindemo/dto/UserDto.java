package com.example.registrationlogindemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String firstName;
    @NotEmpty(message = "Este campo no puede estar vacio")
    private String lastName;
    @NotEmpty(message = "El email no puede estar vacio")
    @Email
    private String email;
    @NotEmpty(message = "La contraseña no puede estar vacia")
    private String password;
}
