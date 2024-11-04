package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.HashMap;

public record AumentarEstoqueDTO(
        @NotEmpty(message = "Lista de livros não pode ser vazia") HashMap<Long, Integer> livros) {
}
