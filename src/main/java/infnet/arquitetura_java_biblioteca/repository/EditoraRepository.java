package infnet.arquitetura_java_biblioteca.repository;

import infnet.arquitetura_java_biblioteca.domain.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
