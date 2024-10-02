package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ContatoUsuarioDTO;
import br.com.fiap.challenge.entity.ContatoUsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ContatoUsuarioService {

    ContatoUsuarioOdontoprev createContatoUsuario(ContatoUsuarioDTO contatoUsuario);

    ContatoUsuarioOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<ContatoUsuarioOdontoprev> getAllContatoUsuario();

    ContatoUsuarioOdontoprev updateContatoUsuario(Long id, ContatoUsuarioDTO contatoUsuario);

    void deleteContatoUsuario (Long id);


}
