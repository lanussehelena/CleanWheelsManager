package br.com.lavajato.servico.DTO;

import br.com.lavajato.servico.Entity.ServicoEntity;

import java.math.BigDecimal;

public record ServicoResponse(
        Long id,
        String nome,
        BigDecimal preco
) {
    public static ServicoResponse fromEntity(ServicoEntity entity) {
        return new ServicoResponse(entity.getId(), entity.getNome(), entity.getPreco());
    }
}
