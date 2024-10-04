package br.com.fiap.challenge.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContatoUsuarioDTO {

    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "O e-mail é obrigatório.")
    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres.")
    @Email(message = "O e-mail deve ser válido.")
    private String emailUsuario;

    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    @NotNull(message = "O telefone é obrigatório.")
    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres.")
    private String telefoneUsuario;
}
