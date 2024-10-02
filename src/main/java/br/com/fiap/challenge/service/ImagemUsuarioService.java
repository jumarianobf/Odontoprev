package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ImagemUsuarioService {

    ImagemUsuarioOdontoprev createImagemUsuario (ImagemUsuarioOdontoprev imagemUsuario);

    ImagemUsuarioOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<ImagemUsuarioOdontoprev> getAllImagemUsuario();

    ImagemUsuarioOdontoprev updateImagemUsuario (ImagemUsuarioOdontoprev imagemUsuario);

    void deleteImagemUsuario (Long id);

}
