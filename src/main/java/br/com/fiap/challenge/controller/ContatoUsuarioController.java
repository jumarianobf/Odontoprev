package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.controller.dto.ContatoUsuarioDTO;
import br.com.fiap.challenge.entity.ContatoUsuarioOdontoprev;
import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.service.ContatoUsuarioService;
import br.com.fiap.challenge.service.UsuarioService;
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
@RequestMapping("api/contato-usuario")
public class ContatoUsuarioController {

    @Autowired
    private ContatoUsuarioService contatoUsuarioService;


    @PostMapping("/cadastrar")
    public ResponseEntity<ContatoUsuarioOdontoprev> cadastrar( @RequestBody @Valid ContatoUsuarioDTO contatoUsuario) {
        try {
            ContatoUsuarioOdontoprev savedContatoUsuario = contatoUsuarioService.createContatoUsuario(contatoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedContatoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ContatoUsuarioOdontoprev> buscarPorId(
            @PathVariable("id") Long id) {
        try {
            ContatoUsuarioOdontoprev contato = contatoUsuarioService.getById(id);
            return ResponseEntity.ok(contato);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContatoUsuarioOdontoprev>> buscarTodos() {
        List<ContatoUsuarioOdontoprev> contatos = contatoUsuarioService.getAllContatoUsuario();
        return ResponseEntity.ok(contatos);
    }

    @PutMapping("{id}")
    public ResponseEntity<ContatoUsuarioOdontoprev> atualizar(
            @PathVariable("id") Long id,
            @RequestBody @Valid ContatoUsuarioDTO contatoUsuario) {
        ContatoUsuarioOdontoprev updatedContatoUsuario = contatoUsuarioService.updateContatoUsuario(id, contatoUsuario);
        return ResponseEntity.ok(updatedContatoUsuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        contatoUsuarioService.deleteContatoUsuario(id);
        return new ResponseEntity<>("Contato deletado com sucesso", HttpStatus.OK);
    }

}
