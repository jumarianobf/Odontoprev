package br.com.fiap.challenge.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrevisaoUsuarioDTO {


    @NotNull(message = "O usuário é obrigatório.")
    private Long usuarioId;

    @NotNull(message = "A imagem do usuário é obrigatória.")
    private Long imagemUsuarioId;

    @Size(max = 255, message = "A previsão de texto deve ter no máximo 255 caracteres.")
    private String previsaoTexto;

    @DecimalMin(value = "0.0", inclusive = true, message = "A probabilidade deve ser no mínimo 0.")
    @DecimalMax(value = "1.0", inclusive = true, message = "A probabilidade deve ser no máximo 1.")
    private Float probabilidade;

    @Size(max = 255, message = "A recomendação deve ter no máximo 255 caracteres.")
    private String recomendacao;

    @NotNull(message = "A data de envio não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataPrevisao;
}
