package br.com.lavajato.veiculo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public VeiculoEntity salvar(VeiculoRequest request) {
        VeiculoEntity entity = new VeiculoEntity();
        entity.setMarca(request.marca());
        entity.setModelo(request.modelo());
        entity.setPlaca(request.placa());
        entity.setCor(request.cor());
        return repository.save(entity);
    }
}