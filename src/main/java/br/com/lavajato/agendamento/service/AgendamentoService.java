package br.com.lavajato.agendamento.service;

import br.com.lavajato.agendamento.dto.AgendamentoRequest;
import br.com.lavajato.veiculo.VeiculoRepository;
import br.com.lavajato.servico.ServicoRepository;
import org.springframework.stereotype.Service;
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

    public AgendamentoEntity agendar(AgendamentoRequest request) {
        var veiculo = veiculoRepository.findById(request.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        var servicos = servicoRepository.findAllById(request.servicosIds());
        if (servicos.isEmpty()) {
            throw new RuntimeException("Selecione pelo menos um serviço válido");
        }

        AgendamentoEntity agendamento = new AgendamentoEntity();
        agendamento.setVeiculo(veiculo);
        agendamento.setServicos(servicos);
        agendamento.setDataHora(request.dataHora());

        return repository.save(agendamento);
    }
}
