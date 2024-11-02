package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping("/criar/{idAutor}")
    public ResponseEntity<?> criarLivro(@PathVariable("idAutor") Long idAutor, @RequestBody Livro livro) {
        try {
            this.livroService.criarLivro(idAutor, livro);
            return ResponseEntity.ok("Livro cadastrado com sucesso.");
        } catch (AutorNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Livro>> buscarLivros() {
        return ResponseEntity.ok(this.livroService.buscarLivros());
    }

    @GetMapping("/buscarPorAutor/{id}")
    public ResponseEntity<Iterable<Livro>> buscarLivrosPorAutor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.livroService.buscarLivrosPorAutor(id));
    }
}
