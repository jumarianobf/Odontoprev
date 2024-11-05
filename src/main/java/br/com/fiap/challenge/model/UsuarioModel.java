package br.com.fiap.challenge.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

    private final String cpf;
    private final String nome;
    private final String sobrenome;
    private final LocalDate dataNascimento;
    private final String genero;
    private final LocalDate dataCadastro;

    @JsonCreator
    public UsuarioModel (
            @JsonProperty("cpf") String cpf,
            @JsonProperty("nome") String nome,
            @JsonProperty("sobrenome") String sobrenome,
            @JsonProperty("dataNascimento") LocalDate dataNascimento,
            @JsonProperty("genero") String genero,
            @JsonProperty("dataCadastro") LocalDate dataCadastro
    ) {
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.dataCadastro = dataCadastro;
    }

}