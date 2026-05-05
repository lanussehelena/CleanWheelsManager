package br.com.lavajato.veiculo.entity;

import br.com.lavajato.cliente.entity.ClienteEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class VeiculoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, unique = true, length = 10)
    private String placa;

    @Column(length = 20)
    private String cor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    public VeiculoEntity() {}

}