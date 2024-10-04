package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.PrevisaoUsuarioDTO;
import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import br.com.fiap.challenge.entity.PrevisaoUsuarioOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.ImagemUsuarioRepository;
import br.com.fiap.challenge.repository.PrevisaoUsuarioRepository;
import br.com.fiap.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PrevisaoUsuarioImpl implements PrevisaoUsuarioService {

    @Autowired
    private PrevisaoUsuarioRepository previsaoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagemUsuarioRepository imagemUsuarioRepository;

    @Override
    public PrevisaoUsuarioOdontoprev createPrevisaoUsuario(PrevisaoUsuarioDTO request) {
        UsuarioOdontoprev usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ImagemUsuarioOdontoprev imagem = imagemUsuarioRepository.findById(request.getImagemUsuarioId()).orElseThrow(() -> new RuntimeException("Imagem não encontrada"));
        return previsaoUsuarioRepository.save(getPrevisaoUsuario(request, usuario, imagem));
    }

    @Override
    public PrevisaoUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if( previsaoUsuarioRepository.existsById(id)) {
            return previsaoUsuarioRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<PrevisaoUsuarioOdontoprev> getAllPrevisaoUsuario() {
        return previsaoUsuarioRepository.findAll();
    }

    @Override
    public PrevisaoUsuarioOdontoprev updatePrevisaoUsuario(Long id, PrevisaoUsuarioDTO previsaoUsuario) {
        PrevisaoUsuarioOdontoprev existingPrevisaoUsuario = previsaoUsuarioRepository.findById(id)
                        .orElseThrow( () -> new RuntimeException("PrevisaoUsuario não encontrado"));

        UsuarioOdontoprev usuario = usuarioRepository.findById(previsaoUsuario.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + previsaoUsuario.getUsuarioId()));

        ImagemUsuarioOdontoprev imagem = imagemUsuarioRepository.findById(previsaoUsuario.getImagemUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Imagem não encontrada com ID: " + previsaoUsuario.getImagemUsuarioId()));


        existingPrevisaoUsuario.setUsuario(usuario);
        existingPrevisaoUsuario.setImagemUsuario(imagem);
        existingPrevisaoUsuario.setDataPrevisao(previsaoUsuario.getDataPrevisao());
        existingPrevisaoUsuario.setPrevisaoTexto(previsaoUsuario.getPrevisaoTexto());
        existingPrevisaoUsuario.setProbabilidade(previsaoUsuario.getProbabilidade());
        existingPrevisaoUsuario.setRecomendacao(previsaoUsuario.getRecomendacao());

        return previsaoUsuarioRepository.save(existingPrevisaoUsuario);
    }

    @Override
    public void deletePrevisaoUsuario(Long id) {
        previsaoUsuarioRepository.deleteById(id);
    }

    private PrevisaoUsuarioOdontoprev getPrevisaoUsuario(PrevisaoUsuarioDTO request, UsuarioOdontoprev usuario, ImagemUsuarioOdontoprev imagem) {
        return PrevisaoUsuarioOdontoprev
                .builder()
                .usuario(usuario)
                .dataPrevisao(request.getDataPrevisao())
                .previsaoTexto(request.getPrevisaoTexto())
                .imagemUsuario(imagem)
                .probabilidade(request.getProbabilidade())
                .recomendacao(request.getRecomendacao())
                .build();
    }
}
