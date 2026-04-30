package br.com.lavajato.cliente.service;

import br.com.lavajato.cliente.entity.ClienteEntity;
import br.com.lavajato.cliente.repository.ClienteRepository;
import br.com.lavajato.cliente.dto.ClienteRequest;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteEntity salvar(ClienteRequest request) {
        ClienteEntity entity = new ClienteEntity();
        entity.setNome(request.nome());
        entity.setEmail(request.email());
        return repository.save(entity);
    }
}
