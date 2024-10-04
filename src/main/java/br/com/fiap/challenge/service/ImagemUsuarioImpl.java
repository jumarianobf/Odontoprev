package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ImagemUsuarioDTO;
import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.ImagemUsuarioRepository;
import br.com.fiap.challenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImagemUsuarioImpl implements ImagemUsuarioService {

    @Autowired
    private ImagemUsuarioRepository imagemUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ImagemUsuarioOdontoprev createImagemUsuario(ImagemUsuarioDTO request) {
        UsuarioOdontoprev usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + request.getUsuarioId()));

        return imagemUsuarioRepository.save(getImagemUsuario(request, usuario));
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
    public ImagemUsuarioOdontoprev updateImagemUsuario(Long id, ImagemUsuarioDTO imagemUsuario) {
        ImagemUsuarioOdontoprev existingImagemUsuario = imagemUsuarioRepository.findById(id)
                .orElseThrow(   () -> new NoSuchElementException("Imagem não encontrado"));

        UsuarioOdontoprev usuario = usuarioRepository.findById(imagemUsuario.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + imagemUsuario.getUsuarioId()));


        existingImagemUsuario.setUsuario(usuario);
        existingImagemUsuario.setImagemUrl(imagemUsuario.getImagemUrl());
        existingImagemUsuario.setDataEnvio(imagemUsuario.getDataEnvio());

        return imagemUsuarioRepository.save(existingImagemUsuario);
    }

    @Override
    public void deleteImagemUsuario(Long id) {
        imagemUsuarioRepository.deleteById(id);
    }

    private ImagemUsuarioOdontoprev getImagemUsuario(ImagemUsuarioDTO request, UsuarioOdontoprev usuario) {
        return ImagemUsuarioOdontoprev
                .builder()
                .usuario(usuario)
                .imagemUrl(request.getImagemUrl())
                .dataEnvio(request.getDataEnvio())
                .build();
    }
}
