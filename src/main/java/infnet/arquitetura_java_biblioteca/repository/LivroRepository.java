package infnet.arquitetura_java_biblioteca.repository;

import infnet.arquitetura_java_biblioteca.domain.Livro;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LivroRepository extends CrudRepository<Livro, Long> {
    List<Livro> findByAutorId(Long id);
}
