package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.AtendimentoUsuarioDTO;
import br.com.fiap.challenge.entity.AtendimentoUsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface AtendimentoUsuarioService {
    AtendimentoUsuarioOdontoprev createAtendimentoUsuario(AtendimentoUsuarioDTO atendimentoUsuarioOdontoprev);

    AtendimentoUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException;;

    List<AtendimentoUsuarioOdontoprev> getAllAtendimentoUsuario();

    AtendimentoUsuarioOdontoprev updateAtendimentoUsuario(Long id, AtendimentoUsuarioDTO atendimentoUsuarioOdontoprev);

    void deleteAtendimentoUsuario(Long id);


}
