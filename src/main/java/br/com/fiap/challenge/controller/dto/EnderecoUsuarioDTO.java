package br.com.fiap.challenge.controller.dto;

import lombok.Data;

@Data
public class EnderecoUsuarioDTO {

    private Long usuarioId;

    private String cepUsuario;

    private String cidadeUsuario;

    private String estadoUsuario;

    private String logradouroUsuario;

    private String bairroUsuario;
}
