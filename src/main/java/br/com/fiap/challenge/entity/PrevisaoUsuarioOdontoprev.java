package br.com.fiap.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_PREVISAO_USUARIO_ODONTOPREV")
@Builder
public class PrevisaoUsuarioOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "previsao_usuario_id", length = 10, nullable = false)
    private Long previsaoUsuarioId;

    @ManyToOne
    @JoinColumn(name = "imagem_usuario_id")
    private ImagemUsuarioOdontoprev imagemUsuario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @NotNull
    private UsuarioOdontoprev usuario;

    @Column(name = "previsao_texto", length = 255)
    private String previsaoTexto;

    @Column(name = "probabilidade")
    private Float probabilidade;

    @Column(name = "recomendacao", length = 255)
    private String recomendacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_previsao", insertable = false, updatable = false)
    private LocalDate dataPrevisao;
}

