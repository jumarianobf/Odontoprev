package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.AtendimentoUsuarioOdontoprev;
import br.com.fiap.challenge.service.AtendimentoUsuarioService;
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
@RequestMapping("api/atendimento-usuario")
public class AtendimentoUsuarioController {

    @Autowired
    private AtendimentoUsuarioService atendimentoUsuarioService;

    @PostMapping
    public ResponseEntity<AtendimentoUsuarioOdontoprev> createAtendimentoUsuario(@Valid @RequestBody AtendimentoUsuarioOdontoprev atendimentoUsuario) {
        try {
            AtendimentoUsuarioOdontoprev savedAtendimento = atendimentoUsuarioService.createAtendimentoUsuario(atendimentoUsuario);
            return new ResponseEntity<>(savedAtendimento, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<AtendimentoUsuarioOdontoprev> getAtendimentoUsuarioById(
            @PathVariable("id") Long id,
            @Valid @RequestBody AtendimentoUsuarioOdontoprev atendimentoUsuario) {
        try {
            AtendimentoUsuarioOdontoprev getIdAtendimento = atendimentoUsuarioService.getById(id);
            if (getIdAtendimento == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(getIdAtendimento);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoUsuarioOdontoprev>> getAllAtendimentoUsuario() {
        List<AtendimentoUsuarioOdontoprev> atendimentoUsuario = atendimentoUsuarioService.getAllAtendimentoUsuario();
        return ResponseEntity.ok(atendimentoUsuario);
    }

    @PostMapping("{id}")
    public ResponseEntity<AtendimentoUsuarioOdontoprev> updateAtendimentoUsuario(@PathVariable("id") Long id, @RequestBody AtendimentoUsuarioOdontoprev atendimentoUsuario) {
        atendimentoUsuario.setAtendimentoId(Long.valueOf(String.valueOf(id)));
        AtendimentoUsuarioOdontoprev updatedAtendimento = atendimentoUsuarioService.updateAtendimentoUsuario(atendimentoUsuario);
        return ResponseEntity.ok(updatedAtendimento);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAtendimentoUsuario(@PathVariable("id") Long id) {
        atendimentoUsuarioService.deleteAtendimentoUsuario(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

}
