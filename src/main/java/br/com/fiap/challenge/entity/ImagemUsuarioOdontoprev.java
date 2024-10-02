package br.com.fiap.challenge.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_IMAGEM_USUARIO_ODONTOPREV")
public class ImagemUsuarioOdontoprev {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagem_usuario_id", length = 10, nullable = false)
    private Long imagemUsuarioId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioOdontoprev usuario;

    @NotNull
    @Column(name = "imagem_url", length = 255, nullable = false)
    private String imagemUrl;

    @NotNull
    @Column(name = "data_envio", insertable = false, updatable = false)
    private LocalDateTime dataEnvio;
}

