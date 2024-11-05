package br.com.fiap.challenge.controller.dto;

import br.com.fiap.challenge.entity.ClinicaOdontoprev;
import br.com.fiap.challenge.entity.DentistaOdontoprev;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AtendimentoUsuarioDTO  {

    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long usuarioId;

    @NotNull(message = "O nome do dentista não pode ser nulo")
    @Size(max = 100, message = "O nome do dentista deve ter no máximo 100 caracteres")
    private DentistaOdontoprev dentistaId;

    @NotNull(message = "O nome da clínica não pode ser nulo")
    @Size(max = 100, message = "O nome da clínica deve ter no máximo 100 caracteres")
    private ClinicaOdontoprev clinicaId;

    @NotNull(message = "A data do atendimento não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataAtendimento;

    @Size(max = 500, message = "A descrição do procedimento deve ter no máximo 500 caracteres")
    private String descricaoProcedimento;

    @DecimalMin(value = "0.0", inclusive = false, message = "O custo deve ser maior que zero")
    @Digits(integer = 10, fraction = 2, message = "O custo deve ter no máximo 10 dígitos e 2 casas decimais")
    private BigDecimal custo;

    @NotNull(message = "A data de registro não pode ser nula")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataRegistro;
}
