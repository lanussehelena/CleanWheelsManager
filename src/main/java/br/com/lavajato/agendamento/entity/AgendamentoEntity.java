package br.com.lavajato.agendamento.entity;

import br.com.lavajato.veiculo.VeiculoEntity;
import br.com.lavajato.servico.ServicoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_AGENDAMENTO")
@Getter @Setter
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "VEICULO_ID", nullable = false)
    private VeiculoEntity veiculo;

    @ManyToMany
    @JoinTable(
            name = "TB_AGENDAMENTO_SERVICO",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<ServicoEntity> servicos;

    public AgendamentoEntity() {}
}
