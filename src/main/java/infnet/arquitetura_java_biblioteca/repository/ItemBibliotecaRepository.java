package infnet.arquitetura_java_biblioteca.repository;

import infnet.arquitetura_java_biblioteca.domain.ItemBiblioteca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemBibliotecaRepository extends JpaRepository<ItemBiblioteca, Long> {

    @Query("SELECT i FROM ItemBiblioteca i WHERE i.id = :id")
    List<ItemBiblioteca> findAllById(Long id);

}
