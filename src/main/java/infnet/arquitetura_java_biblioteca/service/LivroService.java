package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorService autorService;


    public Livro criarLivro(Long idAutor, Livro livro) throws AutorNaoEncontradoException {
        Autor autor = this.autorService.buscarAutor(idAutor).orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        livro.setAutor(autor);
        return this.livroRepository.save(livro);
    }

    public Iterable<Livro> buscarLivros() {
        return this.livroRepository.findAll();
    }

    public Iterable<Livro> buscarLivrosPorAutor(Long id) {
        return this.livroRepository.findByAutorId(id);
    }
}
