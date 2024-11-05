package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.ClinicaDTO;
import br.com.fiap.challenge.entity.ClinicaOdontoprev;
import br.com.fiap.challenge.model.ClinicaModel;
import br.com.fiap.challenge.service.ClinicaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/clinica")
public class ClinicaController {

    @Autowired
    private ClinicaService clinicaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClinicaModel> createClinica(@RequestBody @Valid ClinicaDTO clinicaDTO) {
        try {
            ClinicaOdontoprev savedClinica = clinicaService.createClinica(clinicaDTO);
            ClinicaModel responseModel = toResponseModel(savedClinica);
            responseModel.add(linkTo(methodOn(ClinicaController.class).getClinicaById(savedClinica.getClinicaId())).withSelfRel());
            responseModel.add(linkTo(methodOn(ClinicaController.class).getAllClinica()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ClinicaModel> getClinicaById(@PathVariable("id") Long id) {
        try {
            ClinicaOdontoprev clinica = clinicaService.getById(id);
            if (clinica == null) {
                return ResponseEntity.notFound().build();
            }
            ClinicaModel responseModel = toResponseModel(clinica);
            responseModel.add(linkTo(methodOn(ClinicaController.class).getClinicaById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(ClinicaController.class).getAllClinica()).withRel("listar"));
            responseModel.add(linkTo(methodOn(ClinicaController.class).updateClinica(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(ClinicaController.class).deleteClinica(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClinicaModel>> getAllClinica() {
        List<ClinicaOdontoprev> clinicas = clinicaService.getAllClinica();
        List<ClinicaModel> responseModels = clinicas.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(ClinicaController.class).getAllClinica()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClinicaModel> updateClinica(
            @PathVariable("id") Long id,
            @RequestBody @Valid ClinicaDTO clinicaDTO) {
        ClinicaOdontoprev updatedClinica = clinicaService.updateClinica(id, clinicaDTO);
        ClinicaModel responseModel = toResponseModel(updatedClinica);
        responseModel.add(linkTo(methodOn(ClinicaController.class).getClinicaById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(ClinicaController.class).getAllClinica()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClinica(@PathVariable("id") Long id) {
        clinicaService.deleteClinica(id);
        return new ResponseEntity<>("Clínica deletada com sucesso", HttpStatus.OK);
    }

    private ClinicaModel toResponseModel(ClinicaOdontoprev clinica) {
        return new ClinicaModel(
                clinica.getClinicaId(),
                clinica.getNomeClinica(),
                clinica.getTelefoneClinica()
        );
    }
}
