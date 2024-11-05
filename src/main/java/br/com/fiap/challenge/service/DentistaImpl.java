package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.DentistaDTO;
import br.com.fiap.challenge.entity.DentistaOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.repository.DentistaRepository;
import br.com.fiap.challenge.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DentistaImpl implements DentistaService{

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public DentistaOdontoprev createDentista(DentistaDTO dentista) {
        UsuarioOdontoprev usuario = usuarioRepository.findById(dentista.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return dentistaRepository.save(getDentista(dentista, usuario));
    }

    private DentistaOdontoprev getDentista(DentistaDTO dentista, UsuarioOdontoprev usuario) {

        return DentistaOdontoprev
                .builder()
                .usuarioId(usuario)
                .nomeDentista(dentista.getNomeDentista())
                .emailDentista(dentista.getEmailDentista())
                .telefoneDentista(dentista.getTelefoneDentista())
                .especialidade(dentista.getEspecialidade())
                .build();
    }

    @Override
    public DentistaOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if (dentistaRepository.existsById(id)) {
            return dentistaRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<DentistaOdontoprev> getAllDentista() {
        return dentistaRepository.findAll();
    }

    @Override
    public DentistaOdontoprev updateDentista(Long id, DentistaDTO dentistaDTO) {
        DentistaOdontoprev existingDentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista com id não encontrado: "));

        UsuarioOdontoprev usuario = usuarioRepository.findById(dentistaDTO.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + dentistaDTO.getUsuarioId()));

        existingDentista.setNomeDentista(dentistaDTO.getNomeDentista());
        existingDentista.setEmailDentista(dentistaDTO.getEmailDentista());
        existingDentista.setTelefoneDentista(dentistaDTO.getTelefoneDentista());
        existingDentista.setEspecialidade(dentistaDTO.getEspecialidade());
        existingDentista.setUsuarioId(usuario);

        return dentistaRepository.save(existingDentista);
    }

    @Override
    public void deleteDentista(Long id) {
        dentistaRepository.deleteById(id);
    }
}
