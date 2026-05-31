package br.com.lavajato.cliente.service;

import br.com.lavajato.cliente.dto.ClienteResponse;
import br.com.lavajato.cliente.entity.ClienteEntity;
import br.com.lavajato.cliente.repository.ClienteRepository;
import br.com.lavajato.cliente.dto.ClienteRequest;

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
        entity.setTelefone(request.telefone()); // <--- SEGURANÇA: Confira se essa linha existe!

        var salvo = repository.save(entity);
        return ClienteResponse.fromEntity(salvo);
    }

    public List<ClienteResponse> listarTodos() {
        return repository.findAll().stream()
                .map(ClienteResponse::fromEntity)
                .toList();
    }
}
