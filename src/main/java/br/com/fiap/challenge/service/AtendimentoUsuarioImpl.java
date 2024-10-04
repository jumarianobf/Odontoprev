package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.AtendimentoUsuarioDTO;
import br.com.fiap.challenge.entity.AtendimentoUsuarioOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.AtendimentoUsuarioRepository;
import br.com.fiap.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AtendimentoUsuarioImpl implements AtendimentoUsuarioService {

    @Autowired
    private AtendimentoUsuarioRepository atendimentoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public AtendimentoUsuarioOdontoprev createAtendimentoUsuario(AtendimentoUsuarioDTO request) {
        UsuarioOdontoprev usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return atendimentoUsuarioRepository.save(getAtendimentoUsuario(request, usuario));
    }

    @Override
    public AtendimentoUsuarioOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if(atendimentoUsuarioRepository.existsById(id)) {
            return atendimentoUsuarioRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<AtendimentoUsuarioOdontoprev> getAllAtendimentoUsuario() {
        return atendimentoUsuarioRepository.findAll();
    }

    @Override
    public AtendimentoUsuarioOdontoprev updateAtendimentoUsuario(Long id, AtendimentoUsuarioDTO atendimentoUsuarioOdontoprev) {
        AtendimentoUsuarioOdontoprev existingAtendimentoUsuario = atendimentoUsuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Atendimento não encontrado"));

        UsuarioOdontoprev usuario = usuarioRepository.findById(atendimentoUsuarioOdontoprev.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + atendimentoUsuarioOdontoprev.getUsuarioId()));


        existingAtendimentoUsuario.setUsuario(usuario);
        existingAtendimentoUsuario.setDentistaNome(atendimentoUsuarioOdontoprev.getDentistaNome());
        existingAtendimentoUsuario.setClinicaNome(atendimentoUsuarioOdontoprev.getClinicaNome());
        existingAtendimentoUsuario.setDataAtendimento(atendimentoUsuarioOdontoprev.getDataAtendimento());
        existingAtendimentoUsuario.setDescricaoProcedimento(atendimentoUsuarioOdontoprev.getDescricaoProcedimento());
        existingAtendimentoUsuario.setCusto(atendimentoUsuarioOdontoprev.getCusto());

        return atendimentoUsuarioRepository.save(existingAtendimentoUsuario);
    }

    @Override
    public void deleteAtendimentoUsuario(Long id) {
        atendimentoUsuarioRepository.deleteById(id);
    }

    private AtendimentoUsuarioOdontoprev getAtendimentoUsuario(AtendimentoUsuarioDTO request, UsuarioOdontoprev usuario) {

        return AtendimentoUsuarioOdontoprev
                .builder()
                .usuario(usuario)
                .dentistaNome(request.getDentistaNome())
                .clinicaNome(request.getClinicaNome())
                .dataAtendimento(request.getDataAtendimento())
                .descricaoProcedimento(request.getDescricaoProcedimento())
                .custo(request.getCusto())
                .build();
    }
}
