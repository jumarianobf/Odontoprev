package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@Data
public class ClinicaModel extends RepresentationModel<ClinicaModel> {

    private final Long dentistaId;
    private final String nomeClinica;
    private final String telefoneClinica;

    @JsonCreator
    public ClinicaModel(
            @JsonProperty("dentistaId") Long dentistaId,
            @JsonProperty("nomeClinica") String nomeClinica,
            @JsonProperty("telefoneClinica") String telefoneClinica) {
        this.dentistaId = dentistaId;
        this.nomeClinica = nomeClinica;
        this.telefoneClinica = telefoneClinica;
    }
}
