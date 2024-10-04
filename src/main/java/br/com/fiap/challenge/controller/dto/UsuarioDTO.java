package br.com.fiap.challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    @NotNull(message = "CPF é obrigatório.")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres.")
    private String cpf;

    @NotNull(message = "Nome é obrigatório.")
    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nome;

    @NotNull(message = "Sobrenome é obrigatório.")
    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    @Size(max = 255, message = "O sobrenome deve ter no máximo 255 caracteres.")
    private String sobrenome;

    @Past(message = "A data de nascimento deve ser uma data passada.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @Size(max = 1, message = "O gênero deve ter no máximo 1 caractere.")
    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    private String genero;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataCadastro;
}
