package br.com.lavajato.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
    @NotBlank(message = "Nome é obrigatório!")
    String nome,

    @Email(message = "E-mail inválido!")
    @NotBlank(message = "E-mail é Obrigatório!")
    String email
) {}
