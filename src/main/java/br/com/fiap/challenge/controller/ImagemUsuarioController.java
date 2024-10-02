package br.com.fiap.challenge.controller;

import br.com.fiap.challenge.entity.ImagemUsuarioOdontoprev;
import br.com.fiap.challenge.service.ImagemUsuarioService;
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
@RequestMapping("api/imagem-usuario")
public class ImagemUsuarioController {

    @Autowired
    private ImagemUsuarioService imagemUsuarioService;

    @PostMapping
    public ResponseEntity<ImagemUsuarioOdontoprev> cadastrar (@RequestBody @Valid ImagemUsuarioOdontoprev imagemUsuario) {
        try {
            ImagemUsuarioOdontoprev savedImagemUsuario = imagemUsuarioService.createImagemUsuario(imagemUsuario);
            return new ResponseEntity<>(savedImagemUsuario, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ImagemUsuarioOdontoprev> buscarPorId (
            @PathVariable("id")Long id,
            @RequestBody @Valid ImagemUsuarioOdontoprev imagemUsuario){
        try {
            ImagemUsuarioOdontoprev getIdImagemUsuario = imagemUsuarioService.getById(id);
            if(getIdImagemUsuario == null){
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(getIdImagemUsuario);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ImagemUsuarioOdontoprev>> buscarTodos() {
        List<ImagemUsuarioOdontoprev> imagemUsuario = imagemUsuarioService.getAllImagemUsuario();
        return ResponseEntity.ok(imagemUsuario);
    }

    @PostMapping("{id}")
    public ResponseEntity<ImagemUsuarioOdontoprev> atualizar (@PathVariable("id") Long id, @RequestBody @Valid ImagemUsuarioOdontoprev imagemUsuario){
        imagemUsuario.setImagemUsuarioId(Long.valueOf(String.valueOf(id)));
        ImagemUsuarioOdontoprev updatedImagemUsuario = imagemUsuarioService.updateImagemUsuario(imagemUsuario);
        return ResponseEntity.ok(updatedImagemUsuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletar(@PathVariable("id") Long id) {
        imagemUsuarioService.deleteImagemUsuario(id);
        return new ResponseEntity<>("Imagem de usuário deletada com sucesso", HttpStatus.OK);
    }
}
