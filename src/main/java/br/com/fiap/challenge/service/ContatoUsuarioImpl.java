package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ContatoUsuarioDTO;
import br.com.fiap.challenge.entity.ContatoUsuarioOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.ContatoUsuarioRepository;
import br.com.fiap.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContatoUsuarioImpl implements ContatoUsuarioService {

    @Autowired
    private ContatoUsuarioRepository contatoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ContatoUsuarioOdontoprev createContatoUsuario(ContatoUsuarioDTO request) {
        // Busca o usuário pelo ID fornecido no objeto contatoUsuario
        UsuarioOdontoprev usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return contatoUsuarioRepository.save(getContatoUsuario(request, usuario));
    }

    @Override
    public ContatoUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if(contatoUsuarioRepository.existsById(id)) {
            return contatoUsuarioRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<ContatoUsuarioOdontoprev> getAllContatoUsuario() {
        return contatoUsuarioRepository.findAll();
    }

    @Override
    public ContatoUsuarioOdontoprev updateContatoUsuario(Long id, ContatoUsuarioDTO contatoUsuario) {
        // Busca o contato existente pelo ID
        var existingContatoUsuario = contatoUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contato não encontrado"));

        // Busca o usuário pelo ID fornecido no DTO
        UsuarioOdontoprev usuario = usuarioRepository.findById(contatoUsuario.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + contatoUsuario.getUsuarioId()));

        // Atualiza os campos do contato existente
        existingContatoUsuario.setUsuario(usuario); // Aqui passamos o objeto UsuarioOdontoprev
        existingContatoUsuario.setEmailUsuario(contatoUsuario.getEmailUsuario());
        existingContatoUsuario.setTelefoneUsuario(contatoUsuario.getTelefoneUsuario());

        return contatoUsuarioRepository.save(existingContatoUsuario);
    }

    @Override
    public void deleteContatoUsuario(Long id) {
        contatoUsuarioRepository.deleteById(id);
    }


    private ContatoUsuarioOdontoprev getContatoUsuario(ContatoUsuarioDTO request, UsuarioOdontoprev usuario) {

        return ContatoUsuarioOdontoprev
                .builder()
                .usuario(usuario)
                .emailUsuario(request.getEmailUsuario())
                .telefoneUsuario(request.getTelefoneUsuario())
                .build();
    }
}


