package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Emprestimo;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.domain.dtos.ErrorResponse;
import infnet.arquitetura_java_biblioteca.exceptions.ClienteNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.LivrosAusentesException;
import infnet.arquitetura_java_biblioteca.exceptions.LivrosNaoInformadosException;
import infnet.arquitetura_java_biblioteca.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<?> criarEmprestimo(@Validated @RequestBody CriarEmprestimoDTO criarEmprestimoDTO) {
        try {
            return ResponseEntity.ok(this.emprestimoService.criarEmprestimo(criarEmprestimoDTO));
        } catch (LivrosAusentesException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), e.getLivrosAusentes()));
        } catch (LivrosNaoInformadosException | ClienteNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        return ResponseEntity.ok(this.emprestimoService.listarEmprestimos());
    }
}
