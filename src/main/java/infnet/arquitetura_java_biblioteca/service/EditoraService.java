package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Editora;
import infnet.arquitetura_java_biblioteca.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    public Editora cadastrarEditora(Editora editora) {
        return this.editoraRepository.save(editora);
    }

    public Optional<Editora> buscarEditora(Long id) {
        return this.editoraRepository.findById(id);
    }

    public Iterable<Editora> buscarEditoras() {
        return this.editoraRepository.findAll();
    }
}
