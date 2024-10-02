package br.com.fiap.challenge.controller.dto;

import lombok.Data;

@Data
public class ContatoUsuarioDTO {
    private Long usuarioId;

    private String emailUsuario;

    private String telefoneUsuario;
}
