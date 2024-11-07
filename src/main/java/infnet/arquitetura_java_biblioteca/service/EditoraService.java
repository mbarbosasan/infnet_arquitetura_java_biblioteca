package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Editora;
import infnet.arquitetura_java_biblioteca.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {
    @Autowired
    private EditoraRepository editoraRepository;

    public Editora cadastrarEditora(Editora editora) {
        return this.editoraRepository.save(editora);
    }

    public void buscarEditora(Long id) {
        this.editoraRepository.findById(id);
    }
}
