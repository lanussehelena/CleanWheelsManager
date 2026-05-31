package br.com.lavajato.cliente.dto;

import br.com.lavajato.cliente.entity.ClienteEntity;
import java.util.List;

public record ClienteResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        List<String> placasVeiculos // Mostra as placas dos carros do cliente (opcional)
) {
    public static ClienteResponse fromEntity(ClienteEntity entity) {
        return new ClienteResponse(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getVeiculos() != null ?
                        entity.getVeiculos().stream().map(v -> v.getPlaca()).toList() :
                        List.of()
        );
    }
}