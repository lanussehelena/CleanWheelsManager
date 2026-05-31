package br.com.lavajato.cliente.controller;

import br.com.lavajato.cliente.dto.ClienteResponse;
import br.com.lavajato.cliente.entity.ClienteEntity;
import br.com.lavajato.cliente.dto.ClienteRequest;
import br.com.lavajato.cliente.service.ClienteService;
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
    public ResponseEntity<ClienteResponse> criar(@RequestBody @Valid ClienteRequest request) {
        var response = service.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
