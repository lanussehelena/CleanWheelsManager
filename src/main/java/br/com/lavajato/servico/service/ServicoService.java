package br.com.lavajato.servico.service;

import br.com.lavajato.servico.DTO.ServicoRequest;
import br.com.lavajato.servico.DTO.ServicoResponse;
import br.com.lavajato.servico.Entity.ServicoEntity;
import br.com.lavajato.servico.Repository.ServicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public ServicoResponse salvar(ServicoRequest request) {
        ServicoEntity servico = new ServicoEntity();
        servico.setNome(request.nome());
        servico.setPreco(request.preco());
        servico.setDuracaoEstimadaMinutos(request.duracaoEstimadaMinutos());

        var salvo = repository.save(servico);
        return ServicoResponse.fromEntity(salvo);
    }

    public List<ServicoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(ServicoResponse::fromEntity)
                .toList();
    }
}
