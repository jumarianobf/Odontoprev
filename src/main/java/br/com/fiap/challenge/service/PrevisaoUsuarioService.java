package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.PrevisaoUsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface PrevisaoUsuarioService {

    PrevisaoUsuarioOdontoprev createPrevisaoUsuario(PrevisaoUsuarioOdontoprev previsaoUsuario);

    PrevisaoUsuarioOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<PrevisaoUsuarioOdontoprev> getAllPrevisaoUsuario();

    PrevisaoUsuarioOdontoprev updatePrevisaoUsuario (PrevisaoUsuarioOdontoprev previsaoUsuario);

    void deletePrevisaoUsuario (Long id);
}
