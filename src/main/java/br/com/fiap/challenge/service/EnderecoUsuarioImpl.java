package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ContatoUsuarioDTO;
import br.com.fiap.challenge.controller.dto.EnderecoUsuarioDTO;
import br.com.fiap.challenge.entity.ContatoUsuarioOdontoprev;
import br.com.fiap.challenge.entity.EnderecoUsuarioOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.EnderecoUsuarioRepository;
import br.com.fiap.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnderecoUsuarioImpl implements EnderecoUsuarioService {

    @Autowired
    private EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public EnderecoUsuarioOdontoprev createEnderecoUsuario(EnderecoUsuarioDTO request) {
        UsuarioOdontoprev usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return enderecoUsuarioRepository.save(getEnderecoUsuario(request, usuario));
    }

    @Override
    public EnderecoUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if( enderecoUsuarioRepository.existsById(id)){
            return enderecoUsuarioRepository.findById(id).get();
        }else{
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<EnderecoUsuarioOdontoprev> getAllEnderecoUsuario() {
        return enderecoUsuarioRepository.findAll();
    }

    @Override
    public EnderecoUsuarioOdontoprev updateEnderecoUsuario(Long id, EnderecoUsuarioDTO enderecoUsuario) {
        EnderecoUsuarioOdontoprev enderecoUsuarioExistente = enderecoUsuarioRepository.findById(id)
                        .orElseThrow(   () -> new NoSuchElementException("Endereco não encontrado com o id: " ));

        UsuarioOdontoprev usuario = usuarioRepository.findById(enderecoUsuario.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + enderecoUsuario.getUsuarioId()));

        enderecoUsuarioExistente.setUsuario(usuario);
        enderecoUsuarioExistente.setCepUsuario(enderecoUsuario.getCepUsuario());
        enderecoUsuarioExistente.setCidadeUsuario(enderecoUsuario.getCidadeUsuario());
        enderecoUsuarioExistente.setEstadoUsuario(enderecoUsuario.getEstadoUsuario());
        enderecoUsuarioExistente.setLogradouroUsuario(enderecoUsuario.getLogradouroUsuario());
        enderecoUsuarioExistente.setBairroUsuario(enderecoUsuario.getBairroUsuario());

        return enderecoUsuarioRepository.save(enderecoUsuarioExistente);
    }

    @Override
    public void deleteEnderecoUsuario(Long id) {
        enderecoUsuarioRepository.deleteById(id);
    }

    private EnderecoUsuarioOdontoprev getEnderecoUsuario(EnderecoUsuarioDTO request, UsuarioOdontoprev usuario) {

        return EnderecoUsuarioOdontoprev
                .builder()
                .usuario(usuario)
                .cepUsuario(request.getCepUsuario())
                .cidadeUsuario(request.getCidadeUsuario())
                .estadoUsuario(request.getEstadoUsuario())
                .logradouroUsuario(request.getLogradouroUsuario())
                .bairroUsuario(request.getBairroUsuario())
                .build();
    }
}
