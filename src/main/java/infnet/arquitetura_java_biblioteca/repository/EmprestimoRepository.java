package infnet.arquitetura_java_biblioteca.repository;

import infnet.arquitetura_java_biblioteca.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}
