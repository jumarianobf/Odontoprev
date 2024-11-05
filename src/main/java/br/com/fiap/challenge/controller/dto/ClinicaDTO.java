package br.com.fiap.challenge.controller.dto;

import br.com.fiap.challenge.entity.DentistaOdontoprev;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClinicaDTO {

    @NotNull(message = "O ID do dentista é obrigatório")
    private Long dentistaId;

    @NotNull(message = "Nome da CLinica é obrigatório.")
    @NotEmpty(message = "Não pode ser vazio (string vazia)")
    private String nomeClinica;


    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres.")
    private String telefoneClinica;
}
