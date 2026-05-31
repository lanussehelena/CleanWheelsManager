package br.com.lavajato.veiculo.service;

import br.com.lavajato.infra.BusinessException;
import br.com.lavajato.veiculo.dto.VeiculoResponse;
import br.com.lavajato.veiculo.entity.VeiculoEntity;
import br.com.lavajato.veiculo.repository.VeiculoRepository;
import br.com.lavajato.veiculo.dto.VeiculoRequest;
import org.springframework.stereotype.Service;
import br.com.lavajato.cliente.repository.ClienteRepository;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public VeiculoResponse salvar(VeiculoRequest request) {
        // Se o clienteId vier nulo ou não existir, lançamos a nossa exceção
        var cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new BusinessException("Não foi possível cadastrar: Cliente com ID " + request.clienteId() + " não encontrado."));

        VeiculoEntity entity = new VeiculoEntity();
        entity.setMarca(request.marca());
        entity.setModelo(request.modelo());
        entity.setPlaca(request.placa());
        entity.setCor(request.cor());
        entity.setCliente(cliente);

        var salvo = repository.save(entity);
        return VeiculoResponse.fromEntity(salvo);
    }
}