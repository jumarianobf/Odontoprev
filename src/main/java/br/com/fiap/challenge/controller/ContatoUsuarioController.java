package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.ContatoUsuarioDTO;
import br.com.fiap.challenge.entity.ContatoUsuarioOdontoprev;
import br.com.fiap.challenge.model.ContatoUsuarioModel;
import br.com.fiap.challenge.service.ContatoUsuarioService;
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
@RequestMapping("api/contato-usuario")
public class ContatoUsuarioController {

    @Autowired
    private ContatoUsuarioService contatoUsuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<ContatoUsuarioModel> createContatoUsuario(
            @Valid @RequestBody ContatoUsuarioDTO contatoUsuario) {
        try {
            ContatoUsuarioOdontoprev savedContato = contatoUsuarioService.createContatoUsuario(contatoUsuario);
            ContatoUsuarioModel responseModel = toResponseModel(savedContato);
            responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).getContatoUsuarioById(savedContato.getContatoUsuarioId())).withSelfRel());
            responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).getAllContatoUsuario()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ContatoUsuarioModel> getContatoUsuarioById(
            @PathVariable("id") Long id) {
        try {
            ContatoUsuarioOdontoprev contato = contatoUsuarioService.getById(id);
            if (contato == null) {
                return ResponseEntity.notFound().build();
            }
            ContatoUsuarioModel responseModel = toResponseModel(contato);
            responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).getContatoUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).getAllContatoUsuario()).withRel("listar"));
            responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).updateContatoUsuario(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).deleteContatoUsuario(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContatoUsuarioModel>> getAllContatoUsuario() {
        List<ContatoUsuarioOdontoprev> contatos = contatoUsuarioService.getAllContatoUsuario();
        List<ContatoUsuarioModel> responseModels = contatos.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(ContatoUsuarioController.class).getAllContatoUsuario()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContatoUsuarioModel> updateContatoUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid ContatoUsuarioDTO contatoUsuario) {
        ContatoUsuarioOdontoprev updatedContato = contatoUsuarioService.updateContatoUsuario(id, contatoUsuario);
        ContatoUsuarioModel responseModel = toResponseModel(updatedContato);
        responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).getContatoUsuarioById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(ContatoUsuarioController.class).getAllContatoUsuario()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteContatoUsuario(@PathVariable("id") Long id) {
        contatoUsuarioService.deleteContatoUsuario(id);
        return new ResponseEntity<>("Contato usuário excluído com sucesso", HttpStatus.OK);
    }

    private ContatoUsuarioModel toResponseModel(ContatoUsuarioOdontoprev contato) {
        return new ContatoUsuarioModel(
                contato.getUsuario().getUsuarioId(),
                contato.getEmailUsuario(),
                contato.getTelefoneUsuario()
        );
    }
}
