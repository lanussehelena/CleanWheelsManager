package br.com.lavajato.veiculo.dto;

import br.com.lavajato.veiculo.entity.VeiculoEntity; // <--- O IMPORT QUE RESOLVE TUDO!

public record VeiculoResponse(
        Long id,
        String marca,
        String modelo,
        String placa,
        String nomeDono
) {
    public static VeiculoResponse fromEntity(VeiculoEntity entity) {
        String dono = "Sem dono";
        if (entity.getCliente() != null) {
            dono = entity.getCliente().getNome();
        }

        return new VeiculoResponse(
                entity.getId(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getPlaca(),
                dono
        );
    }
}
