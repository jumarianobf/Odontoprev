package br.com.fiap.challenge.service;

import br.com.fiap.challenge.entity.AtendimentoUsuarioOdontoprev;
import br.com.fiap.challenge.repository.AtendimentoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AtendimentoUsuarioImpl implements AtendimentoUsuarioService {

    @Autowired
    private AtendimentoUsuarioRepository atendimentoUsuarioRepository;
    @Override
    public AtendimentoUsuarioOdontoprev createAtendimentoUsuario(AtendimentoUsuarioOdontoprev atendimentoUsuarioOdontoprev) {
        return atendimentoUsuarioRepository.save(atendimentoUsuarioOdontoprev);
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
    public AtendimentoUsuarioOdontoprev updateAtendimentoUsuario(AtendimentoUsuarioOdontoprev atendimentoUsuarioOdontoprev) {
        AtendimentoUsuarioOdontoprev existingAtendimentoUsuario = atendimentoUsuarioRepository.findById(Long.valueOf(atendimentoUsuarioOdontoprev.getAtendimentoId()))
                .orElseThrow(() -> new NoSuchElementException("Atendimento não encontrado com o id: " + atendimentoUsuarioOdontoprev.getAtendimentoId()));

        existingAtendimentoUsuario.setUsuario(atendimentoUsuarioOdontoprev.getUsuario());
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
}
