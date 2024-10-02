package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.EnderecoUsuarioDTO;
import br.com.fiap.challenge.entity.EnderecoUsuarioOdontoprev;
import br.com.fiap.challenge.service.EnderecoUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/endereco-usuario")
public class EnderecoUsuarioController {

    @Autowired
    private EnderecoUsuarioService enderecoUsuarioService;

    @PostMapping
    public ResponseEntity<EnderecoUsuarioOdontoprev> cadastrarEndereco (@RequestBody @Valid EnderecoUsuarioDTO enderecoUsuario) {
        try {
            EnderecoUsuarioOdontoprev savedEnderecoUsuario = enderecoUsuarioService.createEnderecoUsuario(enderecoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEnderecoUsuario);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<EnderecoUsuarioOdontoprev> buscarPorId (
            @PathVariable("id")Long id){
        try {
            EnderecoUsuarioOdontoprev getIdEnderecoUsuario = enderecoUsuarioService.getById(id);
            if(getIdEnderecoUsuario == null){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(getIdEnderecoUsuario);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EnderecoUsuarioOdontoprev>> buscarTodos() {
        List<EnderecoUsuarioOdontoprev> enderecoUsuario = enderecoUsuarioService.getAllEnderecoUsuario();
        return ResponseEntity.ok(enderecoUsuario);
    }

    @PostMapping("{id}")
    public ResponseEntity<EnderecoUsuarioOdontoprev> atualizar (
            @PathVariable("id") Long id,
            @RequestBody @Valid EnderecoUsuarioDTO enderecoUsuario){
        EnderecoUsuarioOdontoprev updatedEnderecoUsuario = enderecoUsuarioService.updateEnderecoUsuario(id, enderecoUsuario);
        return ResponseEntity.ok(updatedEnderecoUsuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar (@PathVariable("id") Long id) {
        enderecoUsuarioService.deleteEnderecoUsuario(id);
        return new ResponseEntity<>("Endereço de usuário deletado com sucesso", HttpStatus.OK);
    }
}
