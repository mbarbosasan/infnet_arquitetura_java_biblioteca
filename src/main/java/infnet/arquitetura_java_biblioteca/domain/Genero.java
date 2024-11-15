package infnet.arquitetura_java_biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity()
public class Generos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @ManyToMany(mappedBy = "generos")
    private List<ItemBiblioteca> itensBiblioteca;

    public Generos() {
    }

    public Generos(String nome, List<ItemBiblioteca> itensBiblioteca) {
        this.nome = nome;
        this.itensBiblioteca = itensBiblioteca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getNome() {
        return nome;
    }

    public void setNome(@NotNull String nome) {
        this.nome = nome;
    }

    public List<ItemBiblioteca> getItensBiblioteca() {
        return itensBiblioteca;
    }

    public void setItensBiblioteca(List<ItemBiblioteca> itensBiblioteca) {
        this.itensBiblioteca = itensBiblioteca;
    }
}
