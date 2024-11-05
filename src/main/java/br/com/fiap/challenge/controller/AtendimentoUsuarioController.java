package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.AtendimentoUsuarioDTO;
import br.com.fiap.challenge.entity.AtendimentoUsuarioOdontoprev;
import br.com.fiap.challenge.model.AtendimentoUsuarioModel;
import br.com.fiap.challenge.service.AtendimentoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/atendimento-usuario")
public class AtendimentoUsuarioController {

    @Autowired
    private AtendimentoUsuarioService atendimentoUsuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<AtendimentoUsuarioModel> createAtendimentoUsuario(
            @Valid @RequestBody AtendimentoUsuarioDTO atendimentoUsuario) {
        try {
            AtendimentoUsuarioOdontoprev savedAtendimento = atendimentoUsuarioService.createAtendimentoUsuario(atendimentoUsuario);
            AtendimentoUsuarioModel responseModel = toResponseModel(savedAtendimento);
            responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAtendimentoUsuarioById(savedAtendimento.getAtendimentoId())).withSelfRel());
            responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAllAtendimentoUsuario()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

    @GetMapping("{id}")
    public ResponseEntity<AtendimentoUsuarioModel> getAtendimentoUsuarioById(
            @PathVariable("id") Long id) {
        try {
            AtendimentoUsuarioOdontoprev atendimento = atendimentoUsuarioService.getById(id);
            if (atendimento == null) {
                return ResponseEntity.notFound().build();
            }
            AtendimentoUsuarioModel responseModel = toResponseModel(atendimento);
            responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAtendimentoUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAllAtendimentoUsuario()).withRel("listar"));
            responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).updateAtendimentoUsuario(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).deleteAtendimentoUsuario(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AtendimentoUsuarioModel>> getAllAtendimentoUsuario() {
        List<AtendimentoUsuarioOdontoprev> atendimentos = atendimentoUsuarioService.getAllAtendimentoUsuario();
        List<AtendimentoUsuarioModel> responseModels = atendimentos.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAllAtendimentoUsuario()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<AtendimentoUsuarioModel> updateAtendimentoUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid AtendimentoUsuarioDTO atendimentoUsuario) {
        AtendimentoUsuarioOdontoprev updatedAtendimento = atendimentoUsuarioService.updateAtendimentoUsuario(id, atendimentoUsuario);
        AtendimentoUsuarioModel responseModel = toResponseModel(updatedAtendimento);
        responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAtendimentoUsuarioById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(AtendimentoUsuarioController.class).getAllAtendimentoUsuario()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAtendimentoUsuario(@PathVariable("id") Long id) {
        atendimentoUsuarioService.deleteAtendimentoUsuario(id);
        return new ResponseEntity<>("Atendimento usuário excluído com sucesso", HttpStatus.OK);
    }

    private AtendimentoUsuarioModel toResponseModel(AtendimentoUsuarioOdontoprev atendimento) {
        return new AtendimentoUsuarioModel(
                atendimento.getUsuario().getUsuarioId(),
                atendimento.getDentista().getDentistaId(),
                atendimento.getClinica() != null ? atendimento.getClinica().getClinicaId() : null,
                atendimento.getDataAtendimento(),
                atendimento.getDescricaoProcedimento(),
                atendimento.getCusto(),
                atendimento.getDataRegistro()
        );
    }
}
