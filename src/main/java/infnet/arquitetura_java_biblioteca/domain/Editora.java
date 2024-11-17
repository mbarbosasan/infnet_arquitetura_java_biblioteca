package infnet.arquitetura_java_biblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "editora")
    @JsonIgnore
    private List<Revista> revistas;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deletado = false;

    public Editora() {
    }

    public Editora(String nome, List<Revista> revistas) {
        this.nome = nome;
        this.revistas = revistas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Revista> getRevistas() {
        return revistas;
    }

    public void setRevistas(List<Revista> revistas) {
        this.revistas = revistas;
    }

    public Boolean getDeletado() {
        return deletado;
    }

    public void setDeletado(Boolean deletado) {
        this.deletado = deletado;
    }
}
