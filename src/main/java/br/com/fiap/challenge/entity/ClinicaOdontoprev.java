package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_CLINICA_ODONTOPREV")
public class ClinicaOdontoprev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clinica_id", length = 10)
    private Long clinicaId;

    @OneToOne
    @JoinColumn(name = "dentista_id")
    private DentistaOdontoprev dentistaId;

    @Column(name = "nome_clinica", length = 100)
    private String nomeClinica;

    @Column(name = "telefone_clinica", length = 15)
    private String telefoneClinica;




}
