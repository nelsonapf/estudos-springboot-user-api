package com.nelson.estudos.java.user_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Nome obrigatorio!")
    private String nome;
    @NotBlank(message = "CPF obrigatorio!")
    private String cpf;
    private String endereco;
    @NotBlank(message = "E-mail obrigatorio!")
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;
}
