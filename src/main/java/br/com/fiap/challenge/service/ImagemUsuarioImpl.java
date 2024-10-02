package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import br.com.fiap.challenge.repository.ImagemUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImagemUsuarioImpl implements ImagemUsuarioService {

    @Autowired
    private ImagemUsuarioRepository imagemUsuarioRepository;

    @Override
    public ImagemUsuarioOdontoprev createImagemUsuario(ImagemUsuarioOdontoprev imagemUsuario) {
        return imagemUsuarioRepository.save(imagemUsuario);
    }

    @Override
    public ImagemUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if( imagemUsuarioRepository.existsById(id)) {
            return imagemUsuarioRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<ImagemUsuarioOdontoprev> getAllImagemUsuario() {
        return imagemUsuarioRepository.findAll();
    }

    @Override
    public ImagemUsuarioOdontoprev updateImagemUsuario(ImagemUsuarioOdontoprev imagemUsuario) {
        ImagemUsuarioOdontoprev existingImagemUsuario = imagemUsuarioRepository.findById(Long.valueOf(imagemUsuario.getImagemUsuarioId()))
                .orElseThrow(   () -> new NoSuchElementException("Imagem não encontrado com o id: " + imagemUsuario.getImagemUsuarioId()));

        existingImagemUsuario.setUsuario(imagemUsuario.getUsuario());
        existingImagemUsuario.setImagemUrl(imagemUsuario.getImagemUrl());
        existingImagemUsuario.setDataEnvio(imagemUsuario.getDataEnvio());

        return imagemUsuarioRepository.save(existingImagemUsuario);
    }

    @Override
    public void deleteImagemUsuario(Long id) {
        imagemUsuarioRepository.deleteById(id);
    }
}
