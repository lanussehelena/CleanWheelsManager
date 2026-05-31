package br.com.lavajato.agendamento.repository;

import br.com.lavajato.agendamento.entity.AgendamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {


    boolean existsByVeiculoIdAndDataHora(Long veiculoId, LocalDateTime dataHora);

}