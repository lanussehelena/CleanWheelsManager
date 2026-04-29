package br.com.lavajato.agendamento.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoRequest(
        @NotNull Long veiculoId,
        @NotNull List<Long> servicosIds,
        @NotNull @Future LocalDateTime dataHora
) {}