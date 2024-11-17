package infnet.arquitetura_java_biblioteca.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import infnet.arquitetura_java_biblioteca.domain.enums.EmprestimoStatus;
import infnet.arquitetura_java_biblioteca.exceptions.EmprestimoAtrasadoException;
import infnet.arquitetura_java_biblioteca.exceptions.EmprestimoDataDevolucaoInvalidaException;
import infnet.arquitetura_java_biblioteca.exceptions.EmprestimoFinalizadoException;
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

    @NotNull(message = "Data de devolução é obrigatória")
    private Date dataDevolucao;

    @Column(columnDefinition = "varchar(255) default 'ABERTO'")
    @Enumerated(EnumType.STRING)
    private EmprestimoStatus status;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NULL")
    private Date dataDevolucaoEfetivada;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull
    @JsonIgnore
    private Cliente cliente;

    @NotNull(message = "Itens são obrigatórios")
    @ManyToMany
    @JoinTable(
            name = "emprestimo_livro",
            joinColumns = @JoinColumn(name = "emprestimo_id"),
            inverseJoinColumns = @JoinColumn(name = "item_biblioteca_id")
    )
    private List<ItemBiblioteca> itensBiblioteca;

    public Emprestimo() {
    }

    public Emprestimo(Date dataEmprestimo, Date dataDevolucao, EmprestimoStatus status, Date dataDevolucaoEfetivada, Cliente cliente, List<ItemBiblioteca> itensBiblioteca) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
        this.dataDevolucaoEfetivada = dataDevolucaoEfetivada;
        this.cliente = cliente;
        this.itensBiblioteca = itensBiblioteca;
    }

    public void validaRenovacaoEmprestimo(Date novaDataDevolucao) {
        if (this.getDataDevolucao().before(new Date()))
            throw new EmprestimoAtrasadoException("Não é possível renovar um emprestimo que já está atrasado.");
        if (novaDataDevolucao.before(new Date()) || novaDataDevolucao.before(this.getDataDevolucao()))
            throw new EmprestimoDataDevolucaoInvalidaException("A data de devolução informada é inválida, verifique se ela não é anterior a data atual ou a data de devolução atual.");
        if (this.getDataDevolucaoEfetivada() != null)
            throw new EmprestimoFinalizadoException("Não é possível renovar um emprestimo que já foi finalizado.");
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

    public @NotNull(message = "Livros são obrigatórios") List<ItemBiblioteca> getItensBiblioteca() {
        return itensBiblioteca;
    }

    public void setItensBiblioteca(@NotNull(message = "Itens são obrigatórios") List<ItemBiblioteca> itensBiblioteca) {
        this.itensBiblioteca = itensBiblioteca;
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
