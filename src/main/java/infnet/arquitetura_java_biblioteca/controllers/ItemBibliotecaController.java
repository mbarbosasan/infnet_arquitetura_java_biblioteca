package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.ItemBiblioteca;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.Revista;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.ItemNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.service.ItemBibliotecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/item-biblioteca")
public class ItemBibliotecaController {
    @Autowired
    private ItemBibliotecaService itemBibliotecaService;

    @PostMapping("/criar/livro/{idAutor}")
    public ResponseEntity<?> cadastrarLivro(@PathVariable("idAutor") Long idAutor, @Valid @RequestBody Livro livro) {
        try {
            this.itemBibliotecaService.cadastrarItemBiblioteca(livro, idAutor);
            return ResponseEntity.ok("Livro cadastrado com sucesso.");
        } catch (AutorNaoEncontradoException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/criar/revista/{idEditora}")
    public ResponseEntity<?> cadastrarRevista(@PathVariable("idEditora") Long idEditora, @Valid @RequestBody Revista revista) {
        try {
            this.itemBibliotecaService.cadastrarItemBiblioteca(revista, idEditora);
            return ResponseEntity.ok("Revista cadastrada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


    @GetMapping
    public ResponseEntity<Iterable<ItemBiblioteca>> buscarLivros() {
        return ResponseEntity.ok(this.itemBibliotecaService.buscarItensBiblioteca());
    }

    @GetMapping("/buscar/autor/{id}")
    public ResponseEntity<List<ItemBiblioteca>> buscarLivrosPorAutor(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.itemBibliotecaService.buscarItensPorAutor(id));
    }

    @PostMapping("/aumentar-estoque")
    public ResponseEntity<?> aumentarEstoqueLivro(@Valid @RequestBody HashMap<Long, Integer> itensBiblioteca) {
        try {
            this.itemBibliotecaService.aumentarEstoqueLivro(itensBiblioteca);
            return ResponseEntity.ok("Estoque aumentado com sucesso.");
        } catch (ItemNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
