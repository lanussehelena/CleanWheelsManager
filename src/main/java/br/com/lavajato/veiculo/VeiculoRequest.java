package br.com.lavajato.veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VeiculoRequest(
        @NotBlank(message = "Marca é obrigatória!")
        @Size(min = 2, max = 50)
        String marca,

        @NotBlank(message = "Modelo é obrigatório!")
        @Size(min = 2, max = 50)
        String modelo,

        @NotBlank(message = "Placa é obrigatória!")
        @Size(min = 7, max = 10)
        String placa,

        String cor,

        Long clienteId
) {}