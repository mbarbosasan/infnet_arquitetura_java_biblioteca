package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Editora;
import infnet.arquitetura_java_biblioteca.exceptions.EditoraNaoEncontradaException;
import infnet.arquitetura_java_biblioteca.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/editora")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @PostMapping
    public ResponseEntity<?> cadastrarEditora(@Valid @RequestBody Editora editora) {
        try {
            this.editoraService.cadastrarEditora(editora);
            return ResponseEntity.ok("Editora cadastrada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarEditora(@PathVariable Long id, @Valid @RequestBody Editora editora) {
        try {
            this.editoraService.atualizarEditora(id, editora);
            return ResponseEntity.ok("Editora atualizada com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Já existe uma editora com esse nome, escolha outro e tente novamente.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarEditoras() {
        try {
            return ResponseEntity.ok(this.editoraService.buscarEditoras());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirEditora(@PathVariable Long id) {
        try {
            this.editoraService.excluirEditora(id);
            return ResponseEntity.ok().build();
        } catch (EditoraNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
