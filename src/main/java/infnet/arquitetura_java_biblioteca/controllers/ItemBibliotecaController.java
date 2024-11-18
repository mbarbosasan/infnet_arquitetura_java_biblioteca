package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.ItemBiblioteca;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.Revista;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.ItemNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.service.ItemBibliotecaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "Itens da Biblioteca", description = "Busca, atualização e cadastro de itens da biblioteca podendo ser livro ou revistas.")
@RestController
@RequestMapping("/item-biblioteca")
public class ItemBibliotecaController {
    @Autowired
    private ItemBibliotecaService itemBibliotecaService;

    @PostMapping("/criar/livro/{idAutor}")
    public ResponseEntity<?> cadastrarLivro(@PathVariable("idAutor") Long idAutor, @Valid @RequestBody Livro livro) {
        try {
            this.itemBibliotecaService.cadastrarItemBiblioteca(livro, idAutor);
            return ResponseEntity.ok().build();
        } catch (AutorNaoEncontradoException e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/editar/livro/{id}")
    public ResponseEntity<?> editarLivro(@PathVariable("id") Long id, @RequestBody Livro livro) {
        try {
            this.itemBibliotecaService.atualizarLivro(id, livro);
            return ResponseEntity.ok().build();
        } catch (ItemNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body("Houve um erro ao tentar atualizar esse item, verifique se o tipo do item está correto e tente novamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/editar/revista/{id}")
    public ResponseEntity<?> editarRevista(@PathVariable("id") Long id, @RequestBody Revista revista) {
        try {
            this.itemBibliotecaService.atualizarRevista(id, revista);
            return ResponseEntity.ok().build();
        } catch (ItemNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body("Houve um erro ao tentar atualizar esse item, verifique se o tipo do item está correto e tente novamente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/criar/revista/{idEditora}")
    public ResponseEntity<?> cadastrarRevista(@PathVariable("idEditora") Long idEditora, @Valid @RequestBody Revista revista) {
        try {
            this.itemBibliotecaService.cadastrarItemBiblioteca(revista, idEditora);
            return ResponseEntity.ok().build();
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
