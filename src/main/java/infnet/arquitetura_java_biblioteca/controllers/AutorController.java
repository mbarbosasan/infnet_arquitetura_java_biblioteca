package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.service.AutorService;
import infnet.arquitetura_java_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<?> criarAutor(@Validated @RequestBody Autor autor) {
        try {
            return ResponseEntity.ok(this.autorService.criarAutor(autor));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Já existe um autor com esse nome, escolha outro.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> adicionarLivro(@PathVariable("id") Long id, @RequestBody Livro livro) {
        return ResponseEntity.unprocessableEntity().build();
    }
}
