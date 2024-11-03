package infnet.arquitetura_java_biblioteca.domain.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record RenovarEmprestimoDTO(@NotNull Date novaDataDevolucao) {
}
