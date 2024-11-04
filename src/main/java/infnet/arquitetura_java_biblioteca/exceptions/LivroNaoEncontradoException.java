package infnet.arquitetura_java_biblioteca.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {

    Long idLivro;

    public LivroNaoEncontradoException(String message, Long idLivro) {
        super(message);
        this.idLivro = idLivro;
    }

    public Long getLivro() {
        return idLivro;
    }

    public void setLivro(Long idLivro) {
        this.idLivro = idLivro;
    }
}
