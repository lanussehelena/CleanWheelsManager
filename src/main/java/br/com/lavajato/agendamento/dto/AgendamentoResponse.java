package br.com.lavajato.agendamento.dto;

import br.com.lavajato.agendamento.Enum.StatusAgendamento;
import br.com.lavajato.agendamento.entity.AgendamentoEntity;

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

    public AgendamentoResponse atualizarStatus(Long id, StatusAgendamento novoStatus) {
        var agendamento = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Agendamento não encontrado."));


        if (agendamento.getStatus() == StatusAgendamento.CONCLUIDO ||
                agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new BusinessException("Não é possível alterar o status de um agendamento já finalizado ou cancelado.");
        }

        agendamento.setStatus(novoStatus);
        var salvo = repository.save(agendamento);

        return AgendamentoResponse.fromEntity(salvo);
}