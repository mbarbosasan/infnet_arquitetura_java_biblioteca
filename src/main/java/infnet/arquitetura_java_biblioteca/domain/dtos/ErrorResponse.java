package infnet.arquitetura_java_biblioteca.domain.dtos;

import java.util.List;

public record ErrorResponse(String message, List<?> details) {
}
