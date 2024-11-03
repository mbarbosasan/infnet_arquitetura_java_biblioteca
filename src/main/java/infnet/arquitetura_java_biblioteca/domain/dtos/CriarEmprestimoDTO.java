package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record CriarEmprestimoDTO(@NotNull List<Long> livrosIds, @NotNull Long usuarioId, @NotNull Date dataDevolucao) {
}