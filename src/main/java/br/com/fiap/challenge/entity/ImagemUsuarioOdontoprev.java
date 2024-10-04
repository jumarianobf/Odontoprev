package br.com.fiap.challenge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_IMAGEM_USUARIO_ODONTOPREV")
@Builder
public class ImagemUsuarioOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagem_usuario_id", nullable = false)
    private Long imagemUsuarioId;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioOdontoprev usuario;

    @NotNull
    @Column(name = "imagem_url", length = 255, nullable = false)
    private String imagemUrl;

    @NotNull
    @Column(name = "data_envio", nullable = false)
    private LocalDate dataEnvio;

}
