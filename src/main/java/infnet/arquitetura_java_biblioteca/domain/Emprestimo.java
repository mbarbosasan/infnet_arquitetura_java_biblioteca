package infnet.arquitetura_java_biblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import infnet.arquitetura_java_biblioteca.domain.enums.EmprestimoStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date dataEmprestimo;

    @Column(nullable = false)
    @NotNull(message = "Data de devolução é obrigatória")
    private Date dataDevolucao;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'ABERTO'")
    @Enumerated(EnumType.STRING)
    private EmprestimoStatus status;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NULL")
    private Date dataDevolucaoEfetivada;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    @JsonIgnore
    private Cliente cliente;

    @NotNull(message = "Livros são obrigatórios")
    @ManyToMany
    @JoinTable(
            name = "emprestimo_livro",
            joinColumns = @JoinColumn(name = "emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private List<Livro> livros;

    public Emprestimo() {
    }


    public Emprestimo(Long id, Date dataEmprestimo, Date dataDevolucao, EmprestimoStatus status, Date dataDevolucaoEfetivada, Cliente cliente, List<Livro> livros) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
        this.dataDevolucaoEfetivada = dataDevolucaoEfetivada;
        this.cliente = cliente;
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public @NotNull Cliente getCliente() {
        return cliente;
    }

    public void setCliente(@NotNull Cliente cliente) {
        this.cliente = cliente;
    }

    public EmprestimoStatus getStatus() {
        return status;
    }

    public void setStatus(EmprestimoStatus status) {
        this.status = status;
    }

    public Date getDataDevolucaoEfetivada() {
        return dataDevolucaoEfetivada;
    }

    public void setDataDevolucaoEfetivada(Date dataDevolucaoEfetivada) {
        this.dataDevolucaoEfetivada = dataDevolucaoEfetivada;
    }
}
