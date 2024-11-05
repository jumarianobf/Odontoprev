package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.EnderecoUsuarioDTO;
import br.com.fiap.challenge.entity.EnderecoUsuarioOdontoprev;
import br.com.fiap.challenge.model.EnderecoUsuarioModel;
import br.com.fiap.challenge.service.EnderecoUsuarioService;
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
@RequestMapping("api/endereco-usuario")
public class EnderecoUsuarioController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<EnderecoUsuarioModel> cadastrarEndereco(@RequestBody @Valid EnderecoUsuarioDTO enderecoUsuario) {
        try {
            EnderecoUsuarioOdontoprev savedEnderecoUsuario = enderecoUsuarioService.createEnderecoUsuario(enderecoUsuario);
            EnderecoUsuarioModel responseModel = toResponseModel(savedEnderecoUsuario);
            responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarPorId(savedEnderecoUsuario.getEnderecoUsuarioId())).withSelfRel());
            responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarTodos()).withRel("listar"));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<EnderecoUsuarioModel> buscarPorId(@PathVariable("id") Long id) {
        try {
            EnderecoUsuarioOdontoprev getIdEnderecoUsuario = enderecoUsuarioService.getById(id);
            if (getIdEnderecoUsuario == null) {
                return ResponseEntity.notFound().build();
            } else {
                EnderecoUsuarioModel responseModel = toResponseModel(getIdEnderecoUsuario);
                responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarPorId(id)).withSelfRel());
                responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarTodos()).withRel("listar"));
                responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).atualizar(id, null)).withRel("atualizar"));
                responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).deletar(id)).withRel("deletar"));
                return ResponseEntity.ok(responseModel);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoUsuarioModel>> buscarTodos() {
        List<EnderecoUsuarioOdontoprev> enderecoUsuario = enderecoUsuarioService.getAllEnderecoUsuario();
        List<EnderecoUsuarioModel> responseModels = enderecoUsuario.stream()
                .map(this::toResponseModel)
                .collect(Collectors.toList());
        responseModels.forEach(model -> model.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarTodos()).withSelfRel()));
        return ResponseEntity.ok(responseModels);
    }

    @PutMapping("{id}")
    public ResponseEntity<EnderecoUsuarioModel> atualizar(@PathVariable("id") Long id,
                                                                  @RequestBody @Valid EnderecoUsuarioDTO enderecoUsuario) {
        EnderecoUsuarioOdontoprev updatedEnderecoUsuario = enderecoUsuarioService.updateEnderecoUsuario(id, enderecoUsuario);
        EnderecoUsuarioModel responseModel = toResponseModel(updatedEnderecoUsuario);
        responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarPorId(id)).withSelfRel());
        responseModel.add(linkTo(methodOn(EnderecoUsuarioController.class).buscarTodos()).withRel("listar"));
        return ResponseEntity.ok(responseModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        enderecoUsuarioService.deleteEnderecoUsuario(id);
        return new ResponseEntity<>("Endereço de usuário deletado com sucesso", HttpStatus.OK);
    }

    private EnderecoUsuarioModel toResponseModel(EnderecoUsuarioOdontoprev endereco) {
        return new EnderecoUsuarioModel(
                endereco.getUsuario().getUsuarioId(),
                endereco.getCepUsuario(),
                endereco.getCidadeUsuario(),
                endereco.getEstadoUsuario(),
                endereco.getLogradouroUsuario(),
                endereco.getBairroUsuario()
        );
    }
}
