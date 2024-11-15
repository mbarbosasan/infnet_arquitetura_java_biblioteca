package infnet.arquitetura_java_biblioteca.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("LIVRO")
public class Livro extends ItemBiblioteca {
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @NotNull
    private String isbn;

    public Livro() {
    }

    public Livro(String titulo, List<Genero> generos, String descricao, String imagem_capa, LocalDate dataPublicacao, Integer quantidade, Autor autor, String isbn) {
        super(titulo, generos, descricao, imagem_capa, dataPublicacao, quantidade);
        this.autor = autor;
        this.isbn = isbn;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
