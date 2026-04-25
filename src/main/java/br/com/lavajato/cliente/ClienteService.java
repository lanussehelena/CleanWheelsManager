package br.com.lavajato.cliente;

import org.springframework.stereotype.Service;

import java.util.List;

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
