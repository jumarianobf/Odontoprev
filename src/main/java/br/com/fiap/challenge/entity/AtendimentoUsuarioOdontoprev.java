package br.com.fiap.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "T_ATENDIMENTO_USUARIO_ODONTOPREV")
public class AtendimentoUsuarioOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atendimento_id", length = 10, nullable = false)
    private Long atendimentoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioOdontoprev usuario;

    @ManyToOne
    @JoinColumn(name = "dentista_id", nullable = false)
    private DentistaOdontoprev dentista;

    @ManyToOne
    @JoinColumn(name = "clinica_id", referencedColumnName = "clinica_id")
    private ClinicaOdontoprev clinica;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDate dataAtendimento;

    @Lob
    @Column(name = "descricao_procedimento")
    private String descricaoProcedimento;

    @Column(name = "custo", precision = 10, scale = 2)
    private BigDecimal custo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_registro", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDate dataRegistro;

}
