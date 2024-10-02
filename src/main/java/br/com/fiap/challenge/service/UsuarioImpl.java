package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UsuarioOdontoprev createUsuario(UsuarioOdontoprev usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException  {
        if( usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<UsuarioOdontoprev> getAllUsuario() {
        return usuarioRepository.findAll();
    }


    @Override
    public UsuarioOdontoprev updateUsuario(UsuarioOdontoprev usuario) {
        UsuarioOdontoprev existingUsuario = usuarioRepository.findById(Long.valueOf(usuario.getUsuarioId()))
                        .orElseThrow( () -> new RuntimeException("Usuario com id não encontrado: " + usuario.getUsuarioId()));

        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setCpf(usuario.getCpf());
        existingUsuario.setGenero(usuario.getGenero());
        existingUsuario.setSobrenome(usuario.getSobrenome());
        existingUsuario.setDataCadastro(usuario.getDataCadastro());
        existingUsuario.setDataNascimento(usuario.getDataNascimento());

        return usuarioRepository.save(existingUsuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
