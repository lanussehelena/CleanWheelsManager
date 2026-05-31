package br.com.lavajato.agendamento.controller;

import br.com.lavajato.agendamento.Enum.StatusAgendamento;
import br.com.lavajato.agendamento.dto.AgendamentoRequest;
import br.com.lavajato.agendamento.dto.AgendamentoResponse;
import br.com.lavajato.agendamento.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AgendamentoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusAgendamento novoStatus) {

        var response = service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(response);
}
