package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.UsuarioDTO;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.model.UsuarioModel; // Certifique-se de ter essa classe modelo
import br.com.fiap.challenge.service.UsuarioService;
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
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioModel> createUsuario(@RequestBody @Valid UsuarioOdontoprev usuarioOdontoprev) {
        try {
            UsuarioOdontoprev usuario = usuarioService.createUsuario(usuarioOdontoprev);
            UsuarioModel responseModel = toResponseModel(usuario);
            responseModel.add(linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getUsuarioId())).withSelfRel());
            responseModel.add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable("id") Long id) {
        try {
            UsuarioOdontoprev usuario = usuarioService.getById(id);
            if (usuario == null) {
                return ResponseEntity.notFound().build();
            }
            UsuarioModel responseModel = toResponseModel(usuario);
            responseModel.add(linkTo(methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel());
            responseModel.add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withRel("listar"));
            responseModel.add(linkTo(methodOn(UsuarioController.class).updateUsuario(id, null)).withRel("atualizar"));
            responseModel.add(linkTo(methodOn(UsuarioController.class).deleteUsuario(id)).withRel("deletar"));
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> getAllUsuario() {
        List<UsuarioOdontoprev> usuarioList = usuarioService.getAllUsuario();
        List<UsuarioModel> responseModels = usuarioList.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioModel> updateUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid UsuarioOdontoprev usuarioOdontoprev) {
        UsuarioOdontoprev updatedUsuario = usuarioService.updateUsuario(id, usuarioOdontoprev);
        UsuarioModel responseModel = toResponseModel(updatedUsuario);
        responseModel.add(linkTo(methodOn(UsuarioController.class).getUsuarioById(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(UsuarioController.class).getAllUsuario()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    private UsuarioModel toResponseModel(UsuarioOdontoprev usuario) {
        return new UsuarioModel(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getSobrenome(),
                usuario.getDataNascimento(),
                usuario.getGenero(),
                usuario.getDataCadastro()
        );
    }
}
