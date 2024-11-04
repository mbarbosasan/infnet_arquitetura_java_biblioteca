package infnet.arquitetura_java_biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @NotNull
    private String editora;

    @NotNull
    private Date dataPublicacao;

    @NotNull
    private String genero;

    @NotNull
    private Long quantidade;

    public Livro(String titulo, Autor autor, String editora, Date dataPublicacao, String genero, Long quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.dataPublicacao = dataPublicacao;
        this.genero = genero;
        this.quantidade = quantidade;
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public @NotNull Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull Long quantidade) {
        this.quantidade = quantidade;
    }
}
