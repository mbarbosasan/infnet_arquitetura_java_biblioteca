package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.HashMap;

public record ModificarEstoqueDTO(
        @NotEmpty(message = "Lista de itens não pode ser vazia") HashMap<Long, Integer> itensBiblioteca) {
}
