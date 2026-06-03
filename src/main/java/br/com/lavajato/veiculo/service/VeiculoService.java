package br.com.lavajato.veiculo.service;

import br.com.lavajato.infra.BusinessException;
import br.com.lavajato.veiculo.dto.VeiculoResponse;
import br.com.lavajato.veiculo.entity.VeiculoEntity;
import br.com.lavajato.veiculo.repository.VeiculoRepository;
import br.com.lavajato.veiculo.dto.VeiculoRequest;
import org.springframework.stereotype.Service;
import br.com.lavajato.cliente.repository.ClienteRepository;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;
    private final ClienteRepository clienteRepository;

    public VeiculoService(VeiculoRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public VeiculoResponse salvar(VeiculoRequest request) {
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

    public List<VeiculoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(VeiculoResponse::fromEntity)
                .toList();
    }

    public VeiculoResponse buscarPorId(Long id) {
        var veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado."));
        return VeiculoResponse.fromEntity(veiculo);
    }

    public VeiculoResponse atualizar(Long id, VeiculoRequest request) {
        var veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado."));

        if (request.cor() != null) veiculo.setCor(request.cor());
        if (request.modelo() != null) veiculo.setModelo(request.modelo());
        if (request.marca() != null) veiculo.setMarca(request.marca());
        // Placa e ClienteId geralmente não mudam após o cadastro

        var salvo = repository.save(veiculo);
        return VeiculoResponse.fromEntity(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não foi possível excluir: Veículo não encontrado.");
        }
        repository.deleteById(id);
    }
}