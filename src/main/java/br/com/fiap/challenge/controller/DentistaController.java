package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.DentistaDTO;
import br.com.fiap.challenge.entity.DentistaOdontoprev;
import br.com.fiap.challenge.model.DentistaModel;
import br.com.fiap.challenge.service.DentistaService;
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
@RequestMapping("api/dentista")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<DentistaModel> createDentista(@RequestBody @Valid DentistaDTO dentistaDTO) {
        try {
            DentistaOdontoprev savedDentista = dentistaService.createDentista(dentistaDTO);
            DentistaModel responseModel = toResponseModel(savedDentista);
            responseModel.add(linkTo(methodOn(DentistaController.class).getDentistaById(savedDentista.getDentistaId())).withSelfRel());
            responseModel.add(linkTo(methodOn(DentistaController.class).getAllDentista()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DentistaModel> getDentistaById(@PathVariable("id") Long id) {
        try {
            DentistaOdontoprev dentista = dentistaService.getById(id);
            if (dentista == null) {
                return ResponseEntity.notFound().build();
            }
            DentistaModel responseModel = toResponseModel(dentista);
            responseModel.add(linkTo(methodOn(DentistaController.class).getDentistaById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(DentistaController.class).getAllDentista()).withRel("listar"));
            responseModel.add(linkTo(methodOn(DentistaController.class).updateDentista(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(DentistaController.class).deleteDentista(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DentistaModel>> getAllDentista() {
        List<DentistaOdontoprev> dentistas = dentistaService.getAllDentista();
        List<DentistaModel> responseModels = dentistas.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(DentistaController.class).getAllDentista()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<DentistaModel> updateDentista(
            @PathVariable("id") Long id,
            @RequestBody @Valid DentistaDTO dentistaDTO) {
        DentistaOdontoprev updatedDentista = dentistaService.updateDentista(id, dentistaDTO);
        DentistaModel responseModel = toResponseModel(updatedDentista);
        responseModel.add(linkTo(methodOn(DentistaController.class).getDentistaById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(DentistaController.class).getAllDentista()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDentista(@PathVariable("id") Long id) {
        dentistaService.deleteDentista(id);
        return new ResponseEntity<>("Dentista deletado com sucesso", HttpStatus.OK);
    }

    private DentistaModel toResponseModel(DentistaOdontoprev dentista) {
        return new DentistaModel(
                dentista.getDentistaId(),
                dentista.getNomeDentista(),
                dentista.getEspecialidade(),
                dentista.getTelefoneDentista(),
                dentista.getEmailDentista()
        );
    }
}
