package br.com.lavajato.cliente;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")


public class ClienteController {
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClienteEntity> create(@Valid @RequestBody ClienteRequest request) {
        var clienteSalvo = service.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }
}
