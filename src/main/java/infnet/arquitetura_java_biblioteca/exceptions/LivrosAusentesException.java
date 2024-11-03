package infnet.arquitetura_java_biblioteca.exceptions;

import java.util.List;

public class LivrosAusentesException extends Exception {

    List<Long> livrosAusentes;

    public LivrosAusentesException(String message, List<Long> livros) {
        super(message);
        this.livrosAusentes = livros;
    }

    public List<Long> getLivrosAusentes() {
        return livrosAusentes;
    }
}
