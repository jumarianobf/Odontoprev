package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AtendimentoUsuarioModel extends RepresentationModel<AtendimentoUsuarioModel> {

    private final Long usuarioId;
    private final Long dentistaId;
    private final Long clinicaId;
    private final LocalDate dataAtendimento;
    private final String descricaoProcedimento;
    private final BigDecimal custo;
    private final LocalDate dataRegistro;

    @JsonCreator
    public AtendimentoUsuarioModel(
            @JsonProperty("usuarioId") Long usuarioId,
            @JsonProperty("dentistaId") Long dentistaId,
            @JsonProperty("clinicaId") Long clinicaId,
            @JsonProperty("dataAtendimento") LocalDate dataAtendimento,
            @JsonProperty("descricaoProcedimento") String descricaoProcedimento,
            @JsonProperty("custo") BigDecimal custo,
            @JsonProperty("dataRegistro") LocalDate dataRegistro
    ) {
        this.usuarioId = usuarioId;
        this.dentistaId = dentistaId;
        this.clinicaId = clinicaId;
        this.dataAtendimento = dataAtendimento;
        this.descricaoProcedimento = descricaoProcedimento;
        this.custo = custo;
        this.dataRegistro = dataRegistro;
    }
}
