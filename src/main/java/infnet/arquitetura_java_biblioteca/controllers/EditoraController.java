package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Editora;
import infnet.arquitetura_java_biblioteca.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<?> buscarEditoras() {
        try {
            return ResponseEntity.ok(this.editoraService.buscarEditoras());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
