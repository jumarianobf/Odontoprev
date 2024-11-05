package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class EnderecoUsuarioModel extends RepresentationModel<EnderecoUsuarioModel> {

    private Long usuarioId;
    private String cepUsuario;
    private String cidadeUsuario;
    private String estadoUsuario;
    private String logradouroUsuario;
    private String bairroUsuario;

    @JsonCreator
    public EnderecoUsuarioModel(
            @JsonProperty("usuarioId") Long usuarioId,
            @JsonProperty("cepUsuario") String cepUsuario,
            @JsonProperty("cidadeUsuario") String cidadeUsuario,
            @JsonProperty("estadoUsuario") String estadoUsuario,
            @JsonProperty("logradouroUsuario") String logradouroUsuario,
            @JsonProperty("bairroUsuario") String bairroUsuario) {
        this.usuarioId = usuarioId;
        this.cepUsuario = cepUsuario;
        this.cidadeUsuario = cidadeUsuario;
        this.estadoUsuario = estadoUsuario;
        this.logradouroUsuario = logradouroUsuario;
        this.bairroUsuario = bairroUsuario;
    }
}
