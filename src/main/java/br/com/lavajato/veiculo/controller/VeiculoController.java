package br.com.lavajato.veiculo.controller;

import br.com.lavajato.veiculo.dto.VeiculoResponse;
import br.com.lavajato.veiculo.entity.VeiculoEntity;
import br.com.lavajato.veiculo.dto.VeiculoRequest;
import br.com.lavajato.veiculo.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> create(@Valid @RequestBody VeiculoRequest request) {
        var veiculoSalvo = service.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }
}