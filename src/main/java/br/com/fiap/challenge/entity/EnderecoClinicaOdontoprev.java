package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_ENDERECO_CLINICA_ODONTOPREV")
public class EnderecoClinicaOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_clinica_id", length = 10)
    private Long enderecoClinicaId;

    @OneToOne
    @JoinColumn(name = "clinica_id", referencedColumnName = "clinica_id")
    private ClinicaOdontoprev clinica;

    @Column(name = "cep_clinica", length = 9)
    private String cepClinica;

    @Column(name = "cidade_clinica", length = 255)
    private String cidadeClinica;

    @Column(name = "estado_clinica", length = 2)
    private String estadoClinica;

    @Column(name = "logradouro_clinica", length = 255)
    private String logradouroClinica;

    @Column(name = "bairro_clinica", length = 255)
    private String bairroClinica;



}
