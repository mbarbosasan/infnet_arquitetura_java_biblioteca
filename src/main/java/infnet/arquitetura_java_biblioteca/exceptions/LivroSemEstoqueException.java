package infnet.arquitetura_java_biblioteca.exceptions;

import infnet.arquitetura_java_biblioteca.domain.Livro;

public class LivroSemEstoqueException extends RuntimeException {

    Livro livro;

    public LivroSemEstoqueException(String message, Livro livro) {
        super(message);

    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
}
