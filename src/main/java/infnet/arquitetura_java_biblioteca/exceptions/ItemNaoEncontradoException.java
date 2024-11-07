package infnet.arquitetura_java_biblioteca.exceptions;

public class ItemNaoEncontradoException extends RuntimeException {

    Long idItemBiblioteca;

    public ItemNaoEncontradoException(String message, Long idItemBiblioteca) {
        super(message);
        this.idItemBiblioteca = idItemBiblioteca;
    }

    public Long getLivro() {
        return idItemBiblioteca;
    }

    public void setLivro(Long idItemBiblioteca) {
        this.idItemBiblioteca = idItemBiblioteca;
    }
}
