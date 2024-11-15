package infnet.arquitetura_java_biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class ItemBiblioteca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo titulo é obrigatório")
    private String titulo;

    @NotNull(message = "O campo generos é obrigatório")
    @ManyToMany
    @JoinTable(
            name = "item_genero",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generos;

    @NotNull(message = "O campo descrição é obrigatório")
    @Column(columnDefinition = "VARCHAR(1000)")
    private String descricao;

    @NotNull(message = "O campo imagem_capa é obrigatório")
    private String imagem_capa;

    @NotNull(message = "O campo dataPublicacao é obrigatório")
    private LocalDate dataPublicacao;

    @Min(value = 0, message = "A quantidade deve ser maior ou igual a 0")
    private Integer quantidade;

    private Boolean deletado = false;

    public ItemBiblioteca() {
    }

    public ItemBiblioteca(String titulo, List<Genero> generos, String descricao, String imagem_capa, LocalDate dataPublicacao, Integer quantidade) {
        this.titulo = titulo;
        this.generos = generos;
        this.descricao = descricao;
        this.imagem_capa = imagem_capa;
        this.dataPublicacao = dataPublicacao;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "O campo titulo é obrigatório") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull(message = "O campo titulo é obrigatório") String titulo) {
        this.titulo = titulo;
    }

    public @NotNull(message = "O campo generos é obrigatório") List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(@NotNull(message = "O campo generos é obrigatório") List<Genero> generos) {
        this.generos = generos;
    }

    public @NotNull(message = "O campo descrição é obrigatório") String getDescricao() {
        return descricao;
    }

    public void setDescricao(@NotNull(message = "O campo descrição é obrigatório") String descricao) {
        this.descricao = descricao;
    }

    public @NotNull(message = "O campo imagem_capa é obrigatório") String getImagem_capa() {
        return imagem_capa;
    }

    public void setImagem_capa(@NotNull(message = "O campo imagem_capa é obrigatório") String imagem_capa) {
        this.imagem_capa = imagem_capa;
    }

    public @NotNull(message = "O campo dataPublicacao é obrigatório") LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(@NotNull(message = "O campo dataPublicacao é obrigatório") LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public @Min(value = 0, message = "A quantidade deve ser maior ou igual a 0") Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@Min(value = 0, message = "A quantidade deve ser maior ou igual a 0") Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getDeletado() {
        return deletado;
    }

    public void setDeletado(Boolean deletado) {
        this.deletado = deletado;
    }
}
