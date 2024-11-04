package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.dtos.AumentarEstoqueDTO;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.LivroNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.LivroSemEstoqueException;
import infnet.arquitetura_java_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Iterable<Livro> buscarLivrosPorId(List<Long> ids) {
        return this.livroRepository.findAllById(ids);
    }

    public void diminuirEstoqueLivro(Long id, Integer quantidade) {
        Livro livro = this.livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException("Livro não foi encontrado", id));
        if (livro.getQuantidade() < quantidade)
            throw new LivroSemEstoqueException("Não é possível iniciar o empréstimo pois a quantidade desejada é maior do que a disponível em estoque, quantidade disponível: " + livro.getQuantidade(), livro);
        livro.setQuantidade(livro.getQuantidade() - quantidade);
        this.livroRepository.save(livro);
    }

    public void aumentarEstoqueLivro(AumentarEstoqueDTO aumentarEstoqueDTO) {
        aumentarEstoqueDTO.livros().forEach((id, quantidade) -> {
            Livro livro = this.livroRepository.findById(id).orElseThrow(() -> new LivroNaoEncontradoException("Livro não encontrado", id));
            livro.setQuantidade(livro.getQuantidade() + quantidade);
            this.livroRepository.save(livro);
        });
    }

}
