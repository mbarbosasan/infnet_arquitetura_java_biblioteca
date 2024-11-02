package infnet.arquitetura_java_biblioteca.repository;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import org.springframework.data.repository.CrudRepository;

public interface AutorRepository extends CrudRepository<Autor, Long> {
}
