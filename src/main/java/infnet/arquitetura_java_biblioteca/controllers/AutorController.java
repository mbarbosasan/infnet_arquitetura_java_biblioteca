package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.service.AutorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autor", description = "Busca, atualização e cadastro de autores no sistema.")
@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping
    public ResponseEntity<?> criarAutor(@Valid @RequestBody Autor autor) {
        try {
            this.autorService.criarAutor(autor);
            return ResponseEntity.ok("Autor cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Já existe um autor com esse nome, escolha outro.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarAutores() {
        try {
            return ResponseEntity.ok(this.autorService.buscarAutores());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAutor(@PathVariable("id") Long id) {
        try {
            this.autorService.deletarAutor(id);
            return ResponseEntity.ok("Autor deletado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> adicionarLivro(@PathVariable("id") Long id, @RequestBody Livro livro) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAutor(@PathVariable("id") Long id, @RequestBody Autor autor) {
        try {
            this.autorService.atualizarAutor(id, autor);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Já existe um autor com esse nome, utilize outro nome e tente novamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
