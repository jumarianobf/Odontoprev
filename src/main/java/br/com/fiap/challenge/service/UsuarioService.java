package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface UsuarioService {
    UsuarioOdontoprev createUsuario (UsuarioOdontoprev usuario);

    UsuarioOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<UsuarioOdontoprev> getAllUsuario() ;

    UsuarioOdontoprev updateUsuario (UsuarioOdontoprev usuario);

    void deleteUsuario (Long id);



}
