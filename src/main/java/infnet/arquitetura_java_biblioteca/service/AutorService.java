package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    @Lazy
    private ItemBibliotecaService itemBibliotecaService;

    public Autor criarAutor(Autor autor) {
        if (autor.getLivros() != null) {
            autor.getLivros().forEach(livro -> livro.setAutor(autor));
        }
        return this.autorRepository.save(autor);
    }

    public void deletarAutor(Long id) {
        Autor autor = this.autorRepository.findById(id).orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado."));
        autor.setDeletado(true);
        if (autor.getLivros() != null) {
            autor.getLivros().forEach(livro -> livro.setDeletado(true));
        }
        this.autorRepository.save(autor);
    }

    public Optional<Autor> buscarAutor(Long id) {
        return this.autorRepository.findById(id);
    }
}
