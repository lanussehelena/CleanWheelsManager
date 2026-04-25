package br.com.lavajato.cliente;

import jakarta.persistence.*;

@Entity
@Table

public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    public  ClienteEntity() {}
}
