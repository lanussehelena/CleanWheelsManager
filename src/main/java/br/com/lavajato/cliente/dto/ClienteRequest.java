package br.com.lavajato.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
    @NotBlank(message = "Nome é obrigatório!")
    @Size(min = 3, max = 100)
    String nome,

    @Email(message = "E-mail inválido!")
    @NotBlank(message = "E-mail é Obrigatório!")
    String email,

    String telefone
) {}
