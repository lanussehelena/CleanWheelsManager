package br.com.lavajato.cliente.repository;

import br.com.lavajato.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}