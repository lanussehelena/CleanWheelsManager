package br.com.lavajato.servico.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record ServicoRequest(
        @NotBlank(message = "O nome do serviço é obrigatório")
        String nome,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @Positive(message = "A duração deve ser positiva")
        Integer duracaoEstimadaMinutos
) {}
