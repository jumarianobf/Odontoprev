package br.com.fiap.challenge.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EnderecoUsuarioDTO {

    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999.")
    private String cepUsuario;

    @NotNull(message = "A cidade é obrigatória.")
    @Size(max = 255, message = "A cidade deve ter no máximo 255 caracteres.")
    private String cidadeUsuario;

    @NotNull(message = "O estado é obrigatório.")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres.")
    private String estadoUsuario;

    @NotNull(message = "O logradouro é obrigatório.")
    @Size(max = 255, message = "O logradouro deve ter no máximo 255 caracteres.")
    private String logradouroUsuario;

    @NotNull(message = "O bairro é obrigatório.")
    @Size(max = 255, message = "O bairro deve ter no máximo 255 caracteres.")
    private String bairroUsuario;
}
