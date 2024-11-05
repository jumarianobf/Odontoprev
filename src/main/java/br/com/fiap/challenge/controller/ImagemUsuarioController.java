package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.ImagemUsuarioDTO;
import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import br.com.fiap.challenge.model.ImagemUsuarioModel; // Certifique-se de criar essa classe
import br.com.fiap.challenge.service.ImagemUsuarioService;
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
@RequestMapping("api/imagem-usuario")
public class ImagemUsuarioController {

    @Autowired
    private ImagemUsuarioService imagemUsuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ImagemUsuarioModel> cadastrar(
            @RequestBody @Valid ImagemUsuarioDTO imagemUsuario) {
        try {
            ImagemUsuarioOdontoprev savedImagemUsuario = imagemUsuarioService.createImagemUsuario(imagemUsuario);
            ImagemUsuarioModel responseModel = toResponseModel(savedImagemUsuario);
            responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).buscarPorId(savedImagemUsuario.getImagemUsuarioId())).withSelfRel());
            responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).buscarTodos()).withRel("listar"));
            return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ImagemUsuarioModel> buscarPorId(
            @PathVariable("id") Long id) {
        try {
            ImagemUsuarioOdontoprev getIdImagemUsuario = imagemUsuarioService.getById(id);
            if (getIdImagemUsuario == null) {
                return ResponseEntity.notFound().build();
            } else {
                ImagemUsuarioModel responseModel = toResponseModel(getIdImagemUsuario);
                responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).buscarPorId(id)).withSelfRel());
                responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).buscarTodos()).withRel("listar"));
                responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).atualizar(id, null)).withRel("atualizar"));
                responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).deletar(id)).withRel("deletar"));
                return ResponseEntity.ok(responseModel);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ImagemUsuarioModel>> buscarTodos() {
        List<ImagemUsuarioOdontoprev> imagemUsuario = imagemUsuarioService.getAllImagemUsuario();
        List<ImagemUsuarioModel> responseModels = imagemUsuario.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(ImagemUsuarioController.class).buscarTodos()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<ImagemUsuarioModel> atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid ImagemUsuarioDTO imagemUsuario) {
        ImagemUsuarioOdontoprev updatedImagemUsuario = imagemUsuarioService.updateImagemUsuario(id, imagemUsuario);
        ImagemUsuarioModel responseModel = toResponseModel(updatedImagemUsuario);
        responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).buscarPorId(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(ImagemUsuarioController.class).buscarTodos()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        imagemUsuarioService.deleteImagemUsuario(id);
        return new ResponseEntity<>("Imagem de usuário deletada com sucesso", HttpStatus.OK);
    }

    private ImagemUsuarioModel toResponseModel(ImagemUsuarioOdontoprev imagemUsuario) {
        return new ImagemUsuarioModel(
                imagemUsuario.getUsuario().getUsuarioId(),
                imagemUsuario.getImagemUrl(),
                imagemUsuario.getDataEnvio()
        );
    }
}
