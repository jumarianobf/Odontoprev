package br.com.fiap.challenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_USUARIO_ODONTOPREV")
public class UsuarioOdontoprev {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id", length = 10)
    private Long usuarioId;

    @Column(name = "CPF", length = 14, unique = true, nullable = false)
    @NotNull
    private String cpf;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "sobrenome", length = 255, nullable = false)
    private String sobrenome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "genero", length = 1)
    private String genero;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;


}




