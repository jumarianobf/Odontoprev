package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "T_DENTISTA_ODONTOPREV")
public class DentistaOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dentista_id", length = 10)
    private Long dentistaId;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioOdontoprev usuarioId;

    @Column(name = "nome_dentista", length = 100)
    private String nomeDentista;

    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @Column(name = "telefone_dentista", length = 15)
    private String telefoneDentista;


    @Size(max = 100)
    @Column(name = "email_dentista", length = 100, unique = true, nullable = false)
    @NotNull
    @Email
    private String emailDentista;
}
