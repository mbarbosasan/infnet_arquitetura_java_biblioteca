package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotNull;

public record ItemBibliotecaDTO(@NotNull String tipo, @NotNull Long id, @NotNull Integer quantidade) {
}
