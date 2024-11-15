package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Genero;
import infnet.arquitetura_java_biblioteca.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<?> criarGenero(@Valid @RequestBody Genero genero) {
        try {
            this.generoService.criarGenero(genero);
            return ResponseEntity.ok("Genero cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarGeneros() {
        try {
            return ResponseEntity.ok(this.generoService.buscarTodosGeneros());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarGeneroPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.generoService.buscarGeneroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarGenero(@PathVariable Long id, @Valid @RequestBody Genero genero) {
        try {
            Genero generoAtualizado = this.generoService.atualizarGenero(id, genero);
            return ResponseEntity.ok(generoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarGeneroPorId(@PathVariable Long id) {
        try {
            this.generoService.deletarGeneroPorId(id);
            return ResponseEntity.ok("Genero deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
