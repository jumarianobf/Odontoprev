package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
public class PrevisaoUsuarioModel extends RepresentationModel<PrevisaoUsuarioModel> {
    private final Long usuarioId;
    private final Long imagemUsuarioId;
    private final String previsaoTexto;
    private final Float probabilidade;
    private final String recomendacao;
    private final LocalDate dataPrevisao;

    @JsonCreator
    public PrevisaoUsuarioModel(
            @JsonProperty("usuarioId") Long usuarioId,
            @JsonProperty("imagemUsuarioId") Long imagemUsuarioId,
            @JsonProperty("previsaoTexto") String previsaoTexto,
            @JsonProperty("probabilidade") Float probabilidade,
            @JsonProperty("recomendacao") String recomendacao,
            @JsonProperty("dataPrevisao") LocalDate dataPrevisao) {
        this.usuarioId = usuarioId;
        this.imagemUsuarioId = imagemUsuarioId;
        this.previsaoTexto = previsaoTexto;
        this.probabilidade = probabilidade;
        this.recomendacao = recomendacao;
        this.dataPrevisao = dataPrevisao;
    }
}

