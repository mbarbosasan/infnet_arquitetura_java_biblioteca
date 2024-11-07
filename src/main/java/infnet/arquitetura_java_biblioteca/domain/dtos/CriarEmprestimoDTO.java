package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.HashMap;

public record CriarEmprestimoDTO(@NotNull HashMap<Long, Integer> itensBiblioteca, @NotNull Long usuarioId,
                                 @NotNull Date dataDevolucao) {
}