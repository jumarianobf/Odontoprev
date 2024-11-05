package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.DentistaDTO;
import br.com.fiap.challenge.entity.ClinicaOdontoprev;
import br.com.fiap.challenge.entity.DentistaOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;


public interface DentistaService {
    DentistaOdontoprev createDentista (DentistaDTO dentista);

    DentistaOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<DentistaOdontoprev> getAllDentista() ;

    DentistaOdontoprev updateDentista (Long id, DentistaDTO clinica);

    void deleteDentista (Long id);
}
