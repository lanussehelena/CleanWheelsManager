package br.com.lavajato.agendamento.dto;

import br.com.lavajato.agendamento.entity.AgendamentoEntity;
import br.com.lavajato.servico.Entity.ServicoEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoResponse(
        Long id,
        LocalDateTime dataHora,
        String placaVeiculo,
        String nomeCliente,
        BigDecimal valorTotal,
        String status,
        List<String> servicos
) {
    public static AgendamentoResponse fromEntity(AgendamentoEntity entity) {
        return new AgendamentoResponse(
                entity.getId(),
                entity.getDataHora(),
                entity.getVeiculo().getPlaca(),
                entity.getVeiculo().getCliente().getNome(),
                entity.getValorTotal(),
                entity.getStatus().toString(),
                entity.getServicos().stream().map(s -> s.getNome()).toList()
        );
    }
}