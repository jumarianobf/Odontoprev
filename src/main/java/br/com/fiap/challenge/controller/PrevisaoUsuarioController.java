package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.PrevisaoUsuarioDTO;
import br.com.fiap.challenge.entity.PrevisaoUsuarioOdontoprev;
import br.com.fiap.challenge.model.PrevisaoUsuarioModel;
import br.com.fiap.challenge.service.PrevisaoUsuarioService;
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
@RequestMapping("api/previsao-usuario")
public class PrevisaoUsuarioController {

    @Autowired
    private PrevisaoUsuarioService previsaoUsuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<PrevisaoUsuarioModel> cadastrarPrevisaoUsuario(
            @RequestBody @Valid PrevisaoUsuarioDTO previsaoUsuarioOdontoprev) {
        try {
            PrevisaoUsuarioOdontoprev previsaoUsuarioSalva = previsaoUsuarioService.createPrevisaoUsuario(previsaoUsuarioOdontoprev);
            PrevisaoUsuarioModel responseModel = toResponseModel(previsaoUsuarioSalva);
            responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).getPrevisaoUsuarioById(previsaoUsuarioSalva.getPrevisaoUsuarioId())).withSelfRel());
            responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).getAllPrevisaoUsuario()).withRel("listar"));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PrevisaoUsuarioModel> getPrevisaoUsuarioById(
            @PathVariable("id") Long id) {
        try {
            PrevisaoUsuarioOdontoprev previsaoUsuario = previsaoUsuarioService.getById(id);
            if (previsaoUsuario == null) {
                return ResponseEntity.notFound().build();
            } else {
                PrevisaoUsuarioModel responseModel = toResponseModel(previsaoUsuario);
                responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).getPrevisaoUsuarioById(id)).withSelfRel());
                responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).getAllPrevisaoUsuario()).withRel("listar"));
                responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).updatePrevisaoUsuario(id, null)).withRel("atualizar"));
                responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).deletePrevisaoUsuario(id)).withRel("deletar"));
                return ResponseEntity.ok(responseModel);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PrevisaoUsuarioModel>> getAllPrevisaoUsuario() {
        List<PrevisaoUsuarioOdontoprev> previsaoUsuarioList = previsaoUsuarioService.getAllPrevisaoUsuario();
        List<PrevisaoUsuarioModel> responseModels = previsaoUsuarioList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(PrevisaoUsuarioController.class).getAllPrevisaoUsuario()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<PrevisaoUsuarioModel> updatePrevisaoUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid PrevisaoUsuarioDTO previsaoUsuarioOdontoprev) {
        PrevisaoUsuarioOdontoprev previsaoUsuarioAtualizada = previsaoUsuarioService.updatePrevisaoUsuario(id, previsaoUsuarioOdontoprev);
        PrevisaoUsuarioModel responseModel = toResponseModel(previsaoUsuarioAtualizada);
        responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).getPrevisaoUsuarioById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(PrevisaoUsuarioController.class).getAllPrevisaoUsuario()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePrevisaoUsuario(@PathVariable("id") Long id) {
        previsaoUsuarioService.deletePrevisaoUsuario(id);
        return new ResponseEntity<>("PrevisaoUsuario deletado com sucesso", HttpStatus.OK);
    }

    private PrevisaoUsuarioModel toResponseModel(PrevisaoUsuarioOdontoprev previsao) {
        return new PrevisaoUsuarioModel(
                previsao.getImagemUsuario().getImagemUsuarioId(),
                previsao.getUsuario().getUsuarioId(),
                previsao.getPrevisaoTexto(),
                previsao.getProbabilidade(),
                previsao.getRecomendacao(),
                previsao.getDataPrevisao()
        );
    }
}
