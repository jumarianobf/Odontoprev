package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_ENDERECO_USUARIO_ODONTOPREV")
public class EnderecoUsuarioOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_usuario_id", length = 10)
    private Long enderecoUsuarioId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private UsuarioOdontoprev usuario;

    @Column(name = "cep_usuario", length = 9)
    private String cepUsuario;

    @Column(name = "cidade_usuario", length = 255)
    private String cidadeUsuario;

    @Column(name = "estado_usuario", length = 2)
    private String estadoUsuario;

    @Column(name = "logradouro_usuario", length = 255)
    private String logradouroUsuario;

    @Column(name = "bairro_usuario", length = 255)
    private String bairroUsuario;


}

