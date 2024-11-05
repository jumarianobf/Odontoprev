package br.com.fiap.challenge.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DentistaDTO {
    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "O nome do dentista é obrigatório.")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
    private String nomeDentista;

    @NotNull(message = "A especialidade do dentista é obrigatória.")
    @Size(max = 100, message = "A especialidade deve ter no máximo 100 caracteres.")
    private String especialidade;

    @NotNull(message = "O telefone é obrigatório.")
    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres.")
    private String telefoneDentista;

    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
    @Email(message = "O e-mail deve ser válido.")
    private String emailDentista;

}
