package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
public class ImagemUsuarioModel extends RepresentationModel<ImagemUsuarioModel> {

    private final Long usuarioId;
    private final String imagem;
    private final LocalDate dataEnvio;

    @JsonCreator
    public ImagemUsuarioModel(
            @JsonProperty("usuarioId") Long usuarioId,
            @JsonProperty("imagem") String imagem,
            @JsonProperty("dataEnvio") LocalDate dataEnvio){
        this.usuarioId = usuarioId;
        this.imagem = imagem;
        this.dataEnvio = dataEnvio;
    }

}
