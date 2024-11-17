package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Editora;
import infnet.arquitetura_java_biblioteca.exceptions.EditoraNaoEncontradaException;
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

    public void atualizarEditora(Long id, Editora editora) {
        editora.setId(id);
        this.editoraRepository.save(editora);
    }

    public Iterable<Editora> buscarEditoras() {
        return this.editoraRepository.findAll();
    }

    public void excluirEditora(Long id) {
        Editora editora = this.editoraRepository.findById(id).orElseThrow(() -> new EditoraNaoEncontradaException("Não foi possível encontrar essa editora, verifique se os dados estão corretos e tente novamente."));
        editora.setDeletado(true);
        editora.getRevistas().forEach((revista) -> revista.setDeletado(true));
        this.editoraRepository.save(editora);
    }
}
