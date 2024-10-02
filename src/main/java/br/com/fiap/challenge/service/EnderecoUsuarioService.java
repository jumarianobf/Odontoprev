package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ContatoUsuarioDTO;
import br.com.fiap.challenge.controller.dto.EnderecoUsuarioDTO;
import br.com.fiap.challenge.entity.EnderecoUsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface EnderecoUsuarioService {

    EnderecoUsuarioOdontoprev createEnderecoUsuario(EnderecoUsuarioDTO enderecoUsuario);

    EnderecoUsuarioOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<EnderecoUsuarioOdontoprev> getAllEnderecoUsuario();

    EnderecoUsuarioOdontoprev updateEnderecoUsuario (Long id, EnderecoUsuarioDTO enderecoUsuario);

    void deleteEnderecoUsuario (Long id);


}
