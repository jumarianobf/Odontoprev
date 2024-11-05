package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ContatoUsuarioModel extends RepresentationModel<ContatoUsuarioModel> {
    private Long usuarioId;
    private String emailUsuario;
    private String telefoneUsuario;

    @JsonCreator
    public ContatoUsuarioModel(
           @JsonProperty("usuarioId") Long usuarioId,
           @JsonProperty("emailUsuario") String emailUsuario,
           @JsonProperty("telefoneUsuario") String telefoneUsuario) {

        this.usuarioId = usuarioId;
        this.emailUsuario = emailUsuario;
        this.telefoneUsuario = telefoneUsuario;
    }
}
