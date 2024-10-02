package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.PrevisaoUsuarioOdontoprev;
import br.com.fiap.challenge.repository.PrevisaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrevisaoUsuarioImpl implements PrevisaoUsuarioService {

    @Autowired
    private PrevisaoUsuarioRepository previsaoUsuarioRepository;

    @Override
    public PrevisaoUsuarioOdontoprev createPrevisaoUsuario(PrevisaoUsuarioOdontoprev previsaoUsuario) {
        return previsaoUsuarioRepository.save(previsaoUsuario);
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
    public PrevisaoUsuarioOdontoprev updatePrevisaoUsuario(PrevisaoUsuarioOdontoprev previsaoUsuario) {
        PrevisaoUsuarioOdontoprev existingPrevisaoUsuario = previsaoUsuarioRepository.findById(Long.valueOf(previsaoUsuario.getPrevisaoUsuarioId()))
                        .orElseThrow( () -> new RuntimeException("PrevisaoUsuario não encontrado" + previsaoUsuario.getPrevisaoUsuarioId()));

        existingPrevisaoUsuario.setUsuario(previsaoUsuario.getUsuario());
        existingPrevisaoUsuario.setDataPrevisao(previsaoUsuario.getDataPrevisao());
        existingPrevisaoUsuario.setPrevisaoTexto(previsaoUsuario.getPrevisaoTexto());
        existingPrevisaoUsuario.setImagemUsuario(previsaoUsuario.getImagemUsuario());
        existingPrevisaoUsuario.setProbabilidade(previsaoUsuario.getProbabilidade());
        existingPrevisaoUsuario.setRecomendacao(previsaoUsuario.getRecomendacao());

        return previsaoUsuarioRepository.save(existingPrevisaoUsuario);
    }

    @Override
    public void deletePrevisaoUsuario(Long id) {
        previsaoUsuarioRepository.deleteById(id);
    }
}
