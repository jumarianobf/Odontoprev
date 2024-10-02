package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "T_CONTATO_USUARIO_ODONTOPREV")
public class ContatoUsuarioOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_usuario_id", length = 10)
    private Long contatoUsuarioId;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioOdontoprev usuario;

    @Size(max = 100)
    @Column(name = "email_usuario", length = 100, unique = true, nullable = false)
    @NotNull
    @Email
    private String emailUsuario;

    @Column(name = "telefone_usuario", length = 15)
    private String telefoneUsuario;


}

