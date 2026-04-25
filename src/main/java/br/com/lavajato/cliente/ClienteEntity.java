package br.com.lavajato.cliente;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data

public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    public  ClienteEntity() {}
}
