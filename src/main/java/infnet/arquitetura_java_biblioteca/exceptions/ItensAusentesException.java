package infnet.arquitetura_java_biblioteca.exceptions;

import java.util.List;

public class ItensAusentesException extends Exception {

    List<Long> itensAusentes;

    public ItensAusentesException(String message, List<Long> livros) {
        super(message);
        this.itensAusentes = livros;
    }

    public List<Long> getItensAusentes() {
        return itensAusentes;
    }
}
