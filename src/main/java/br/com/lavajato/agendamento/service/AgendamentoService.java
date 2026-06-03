package br.com.lavajato.agendamento.service;

import br.com.lavajato.agendamento.Enum.StatusAgendamento;
import br.com.lavajato.agendamento.dto.AgendamentoRequest;
import br.com.lavajato.agendamento.dto.AgendamentoResponse;
import br.com.lavajato.agendamento.entity.AgendamentoEntity;
import br.com.lavajato.agendamento.repository.AgendamentoRepository;
import br.com.lavajato.servico.Entity.ServicoEntity;
import br.com.lavajato.servico.Repository.ServicoRepository;
import br.com.lavajato.veiculo.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final VeiculoRepository veiculoRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository repository,
                              VeiculoRepository veiculoRepository,
                              ServicoRepository servicoRepository) {
        this.repository = repository;
        this.veiculoRepository = veiculoRepository;
        this.servicoRepository = servicoRepository;
    }

    public AgendamentoResponse atualizarStatus(Long id, StatusAgendamento novoStatus) {
        var agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado."));

        if (agendamento.getStatus() == StatusAgendamento.CONCLUIDO ||
                agendamento.getStatus() == StatusAgendamento.CANCELADO) {
            throw new RuntimeException("Não é possível alterar o status de um agendamento finalizado.");
        }

        agendamento.setStatus(novoStatus);
        var salvo = repository.save(agendamento);
        return AgendamentoResponse.fromEntity(salvo);
    }

    public AgendamentoResponse agendar(AgendamentoRequest request) {
        var veiculo = veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        var servicos = servicoRepository.findAllById(request.servicosIds());

        BigDecimal total = servicos.stream()
                .map(ServicoEntity::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        AgendamentoEntity entity = new AgendamentoEntity();
        entity.setVeiculo(veiculo);
        entity.setServicos(servicos);
        entity.setDataHora(request.dataHora());
        entity.setValorTotal(total);
        entity.setStatus(StatusAgendamento.PENDENTE);

        var salvo = repository.save(entity);


        return AgendamentoResponse.fromEntity(salvo);
    }

    public List<AgendamentoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(AgendamentoResponse::fromEntity)
                .toList();
    }


    public AgendamentoResponse buscarPorId(Long id) {
        var agendamento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com o ID: " + id));
        return AgendamentoResponse.fromEntity(agendamento);
    }


    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não foi possível excluir: Agendamento não encontrado.");
        }
        repository.deleteById(id);
    }
}