package br.com.fiap.challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ImagemUsuarioDTO {

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long usuarioId;

    @NotNull(message = "A URL da imagem não pode ser nula")
    @NotEmpty(message = "A URL da imagem não pode ser vazia")
    @Size(max = 255, message = "O tamanho da URL da imagem deve ser no máximo 255 caracteres")
    private String imagemUrl;

    @NotNull(message = "A data de envio não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataEnvio;

}
