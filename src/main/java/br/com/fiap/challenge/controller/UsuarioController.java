package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.UsuarioOdontoprev;
import br.com.fiap.challenge.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(maxAge = 3600)
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioOdontoprev> createUsuario (@RequestBody @Valid UsuarioOdontoprev usuario) {
        try {
            UsuarioOdontoprev savedUsuario = usuarioService.createUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioOdontoprev> getUsuarioById (@PathVariable("id")Long id){
        try {
            UsuarioOdontoprev getIdUsuario = usuarioService.getById(id);
            if(getIdUsuario == null){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(getIdUsuario);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioOdontoprev>> getAllUsuario() throws ChangeSetPersister.NotFoundException {
        List<UsuarioOdontoprev> usuario = usuarioService.getAllUsuario();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("{id}")
    public ResponseEntity<UsuarioOdontoprev> updateUsuario (
            @PathVariable("id") Long id,
            @RequestBody @Valid UsuarioOdontoprev usuario){
        usuario.setUsuarioId(Long.valueOf(String.valueOf(id)));
        UsuarioOdontoprev updatedUsuario = usuarioService.updateUsuario(usuario);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") Long id) {
        usuarioService.deleteUsuario(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }
}
