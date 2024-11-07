package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ModificarEstoqueDTO(
        @NotEmpty(message = "Lista de itens não pode ser vazia") List<ItemBibliotecaDTO> itensBiblioteca) {
}
