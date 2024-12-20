package infnet.arquitetura_java_biblioteca.controllers;

import infnet.arquitetura_java_biblioteca.domain.Emprestimo;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.domain.dtos.ErrorResponse;
import infnet.arquitetura_java_biblioteca.domain.dtos.RenovarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.exceptions.*;
import infnet.arquitetura_java_biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Empréstimo", description = "Criação, renovação e busca de empréstimos no sistema.")
@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<?> criarEmprestimo(@Valid @RequestBody CriarEmprestimoDTO criarEmprestimoDTO) {
        try {
            return ResponseEntity.ok(this.emprestimoService.criarEmprestimo(criarEmprestimoDTO));
        } catch (ItensAusentesException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), e.getItensAusentes()));
        } catch (ItensNaoInformados | ClienteNaoEncontradoException | ItemNaoEncontradoException |
                 ItemSemEstoque e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/finalizar/{id}")
    public ResponseEntity<?> finalizarEmprestimo(@PathVariable("id") Long id) {
        try {
            this.emprestimoService.finalizarEmprestimo(id);
            return ResponseEntity.ok("Emprestimo finalizado com sucesso!");
        } catch (EmprestimoNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/renovar/{id}")
    public ResponseEntity<?> renovarEmprestimo(@PathVariable("id") Long id, @Valid @RequestBody RenovarEmprestimoDTO renovarEmprestimoDTO) {
        try {
            this.emprestimoService.renovarEmprestimo(id, renovarEmprestimoDTO.novaDataDevolucao());
            return ResponseEntity.ok().body("Emprestimo renovado com sucesso!");
        } catch (EmprestimoNaoEncontradoException | EmprestimoFinalizadoException |
                 EmprestimoDataDevolucaoInvalidaException | EmprestimoAtrasadoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping
    ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        return ResponseEntity.ok(this.emprestimoService.listarEmprestimos());
    }
}
