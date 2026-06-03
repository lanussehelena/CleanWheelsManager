package br.com.lavajato.cliente.service;

import br.com.lavajato.cliente.dto.ClienteResponse;
import br.com.lavajato.cliente.entity.ClienteEntity;
import br.com.lavajato.cliente.repository.ClienteRepository;
import br.com.lavajato.cliente.dto.ClienteRequest;

import br.com.lavajato.infra.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteResponse salvar(ClienteRequest request) {
        ClienteEntity entity = new ClienteEntity();
        entity.setNome(request.nome());
        entity.setEmail(request.email());
        entity.setTelefone(request.telefone());

        var salvo = repository.save(entity);
        return ClienteResponse.fromEntity(salvo);
    }

    public List<ClienteResponse> listarTodos() {
        return repository.findAll().stream()
                .map(ClienteResponse::fromEntity)
                .toList();
    }

    public ClienteResponse atualizarParcial(Long id, ClienteRequest request) {
        var cliente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado."));

        if (request.nome() != null) cliente.setNome(request.nome());
        if (request.email() != null) cliente.setEmail(request.email());
        if (request.telefone() != null) cliente.setTelefone(request.telefone());

        var salvo = repository.save(cliente);
        return ClienteResponse.fromEntity(salvo);
    }

    public ClienteResponse buscarPorId(Long id) {
        var cliente = repository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado com o ID: " + id));
        return ClienteResponse.fromEntity(cliente);
    }


    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar: Cliente não encontrado.");
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BusinessException("Este cliente possui veículos vinculados e não pode ser removido.");
        }
    }
}
