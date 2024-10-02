package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.PrevisaoUsuarioOdontoprev;
import br.com.fiap.challenge.service.PrevisaoUsuarioService;
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
@RequestMapping("api/previsao-usuario")
public class PrevisaoUsuarioController {

    @Autowired
    private PrevisaoUsuarioService previsaoUsuarioService;

    @PostMapping
    public ResponseEntity<PrevisaoUsuarioOdontoprev> cadastrarPrevisaoUsuario(@RequestBody @Valid PrevisaoUsuarioOdontoprev previsaoUsuarioOdontoprev) {
        try {
            PrevisaoUsuarioOdontoprev previsaoUsuarioOdontoprevSalva = previsaoUsuarioService.createPrevisaoUsuario(previsaoUsuarioOdontoprev);
            return new ResponseEntity<>(previsaoUsuarioOdontoprevSalva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<PrevisaoUsuarioOdontoprev> getPrevisaoUsuarioById(@PathVariable("id") Long id, @RequestBody @Valid PrevisaoUsuarioOdontoprev previsaoUsuarioOdontoprev) {
        try {
            PrevisaoUsuarioOdontoprev previsaoUsuario = previsaoUsuarioService.getById(id);
            if (previsaoUsuarioOdontoprev == null) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(previsaoUsuarioOdontoprev);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PrevisaoUsuarioOdontoprev>> getAllPrevisaoUsuario() {
        List<PrevisaoUsuarioOdontoprev> previsaoUsuarioOdontoprev = previsaoUsuarioService.getAllPrevisaoUsuario();
        return ResponseEntity.ok(previsaoUsuarioOdontoprev);
    }

    @PostMapping("{id}")
    public ResponseEntity<PrevisaoUsuarioOdontoprev> updatePrevisaoUsuario(
            @PathVariable("id") Long id,
            @RequestBody PrevisaoUsuarioOdontoprev previsaoUsuarioOdontoprev) {
        previsaoUsuarioOdontoprev.setPrevisaoUsuarioId(Long.valueOf(String.valueOf(id)));
        PrevisaoUsuarioOdontoprev previsaoUsuarioOdontoprevAtualizada = previsaoUsuarioService.updatePrevisaoUsuario(previsaoUsuarioOdontoprev);
        return ResponseEntity.ok(previsaoUsuarioOdontoprevAtualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePrevisaoUsuario(@PathVariable("id") Long id) {
        previsaoUsuarioService.deletePrevisaoUsuario(id);
        return new ResponseEntity<>("PrevisaoUsuario deletado com sucesso", HttpStatus.OK);
    }
}
