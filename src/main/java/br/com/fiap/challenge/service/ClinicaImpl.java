package br.com.fiap.challenge.service;

import br.com.fiap.challenge.controller.dto.ClinicaDTO;
import br.com.fiap.challenge.entity.ClinicaOdontoprev;
import br.com.fiap.challenge.entity.DentistaOdontoprev;
import br.com.fiap.challenge.repository.ClinicaRepository;
import br.com.fiap.challenge.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaImpl implements ClinicaService{

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Override
    public ClinicaOdontoprev createClinica(ClinicaDTO clinica) {
        DentistaOdontoprev dentista = dentistaRepository.findById(clinica.getDentistaId())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        return clinicaRepository.save(getClinica(clinica, dentista));
    }

    private ClinicaOdontoprev getClinica(ClinicaDTO clinica, DentistaOdontoprev dentista) {
        return ClinicaOdontoprev
                .builder()
                .dentistaId(dentista)
                .nomeClinica(clinica.getNomeClinica())
                .telefoneClinica(clinica.getTelefoneClinica())
                .build();
    }

    @Override
    public ClinicaOdontoprev getById(Long id) throws ChangeSetPersister.NotFoundException {
        if( clinicaRepository.existsById(id)) {
            return clinicaRepository.findById(id).get();
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<ClinicaOdontoprev> getAllClinica() {
        return clinicaRepository.findAll();
    }

    @Override
    public ClinicaOdontoprev updateClinica(Long id, ClinicaDTO clinica) {
        ClinicaOdontoprev existingClinica = clinicaRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Clinica com id não encontrado: "));

        DentistaOdontoprev existingDentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista com id não encontrado: " + id));

        existingClinica.setNomeClinica(clinica.getNomeClinica());
        existingClinica.setTelefoneClinica(clinica.getTelefoneClinica());
        existingClinica.setDentistaId(existingDentista);

        return clinicaRepository.save(existingClinica);
    }

    @Override
    public void deleteClinica(Long id) {
        clinicaRepository.deleteById(id);
    }
}
