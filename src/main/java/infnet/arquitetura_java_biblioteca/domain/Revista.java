package infnet.arquitetura_java_biblioteca.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("REVISTA")
public class Revista extends ItemBiblioteca {
    @ManyToOne
    @JoinColumn(name = "editora_id")
    @NotNull
    private Editora editora;

    @NotNull
    private String issn;

    public Revista() {
    }

    public Revista(String titulo, String generos, String descricao, String imagem_capa, LocalDate dataPublicacao, Integer quantidade, Editora editora, String issn) {
        super(titulo, generos, descricao, imagem_capa, dataPublicacao, quantidade);
        this.editora = editora;
        this.issn = issn;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }
}
