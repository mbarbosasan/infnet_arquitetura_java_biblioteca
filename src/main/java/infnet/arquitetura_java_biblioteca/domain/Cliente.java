package infnet.arquitetura_java_biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String nome;
    @NotNull
    @Column(nullable = false)
    private String endereco;
    @NotNull
    @Column(nullable = false)
    private String telefone;
    @NotNull
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<Emprestimo> emprestimos;

    public Cliente() {
    }

    public Cliente(String nome, String endereco, String telefone, String email, List<Emprestimo> emprestimos) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.emprestimos = emprestimos;
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

    public @NotNull String getEndereco() {
        return endereco;
    }

    public void setEndereco(@NotNull String endereco) {
        this.endereco = endereco;
    }

    public @NotNull String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotNull String telefone) {
        this.telefone = telefone;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public void setEmail(@NotNull String email) {
        this.email = email;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

}
