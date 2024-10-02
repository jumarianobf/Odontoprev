package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.AtendimentoUsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface AtendimentoUsuarioService {
    AtendimentoUsuarioOdontoprev createAtendimentoUsuario(AtendimentoUsuarioOdontoprev atendimentoUsuarioOdontoprev);

    AtendimentoUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException;;

    List<AtendimentoUsuarioOdontoprev> getAllAtendimentoUsuario();

    AtendimentoUsuarioOdontoprev updateAtendimentoUsuario(AtendimentoUsuarioOdontoprev atendimentoUsuarioOdontoprev);

    void deleteAtendimentoUsuario(Long id);


}
