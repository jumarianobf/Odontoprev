package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ClinicaDTO;
import br.com.fiap.challenge.entity.ClinicaOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ClinicaService {
    ClinicaOdontoprev createClinica (ClinicaDTO clinica);

    ClinicaOdontoprev getById (Long id) throws ChangeSetPersister.NotFoundException;

    List<ClinicaOdontoprev> getAllClinica() ;

    ClinicaOdontoprev updateClinica (Long id, ClinicaDTO clinica);

    void deleteClinica (Long id);
}
