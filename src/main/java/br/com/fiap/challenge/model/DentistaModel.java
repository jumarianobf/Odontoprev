package br.com.fiap.challenge.model;

import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
public class DentistaModel extends RepresentationModel<DentistaModel> {

    private final Long usuarioId;
    private final String nomeDentista;
    private final String especialidade;
    private final String telefoneDentista;
    private final String emailDentista;

    @JsonCreator
    public DentistaModel(
            @JsonProperty("usuarioId") Long usuarioId,
            @JsonProperty("nomeDentista") String nomeDentista,
            @JsonProperty("especialidade") String especialidade,
            @JsonProperty("telefoneDentista") String telefoneDentista,
            @JsonProperty("emailDentista") String emailDentista) {
        this.usuarioId = usuarioId;
        this.nomeDentista = nomeDentista;
        this.especialidade = especialidade;
        this.telefoneDentista = telefoneDentista;
        this.emailDentista = emailDentista;
    }



}
