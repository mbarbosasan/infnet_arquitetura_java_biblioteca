package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.ItemBiblioteca;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.Revista;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.ItemNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.ItemSemEstoque;
import infnet.arquitetura_java_biblioteca.repository.ItemBibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

@Service
public class ItemBibliotecaService {

    @Autowired
    private ItemBibliotecaRepository itemBibliotecaRepository;
    @Autowired
    private AutorService autorService;

    public ItemBiblioteca buscarPorId(Long id) {
        return this.itemBibliotecaRepository.findById(id).orElse(null);
    }

    public ItemBiblioteca cadastrarItemBiblioteca(Livro livro, Long idAutor) {
        Autor autor = this.autorService.buscarAutor(idAutor).orElseThrow(() -> new AutorNaoEncontradoException("Autor não encontrado"));
        livro.setAutor(autor);
        return this.itemBibliotecaRepository.save(livro);
    }

    public ItemBiblioteca cadastrarItemBiblioteca(Revista revista, Long idAutor) {
        return this.itemBibliotecaRepository.save(revista);
    }

    public void atualizarLivro(Long id, Livro livro) {
        // Talvez seria mais fácil criar um mapper para realizar esse mapeamento dos atributos que foram enviados, mas como são poucos casos
        // que eu identifiquei de uma entidade com multiplos campos que podem ser enviados acabei realizando o mais simples sem a necessidade de adicionar uma nova dependência.
        Livro livroSaved = (Livro) this.itemBibliotecaRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado", id));
        livroSaved.setQuantidade(livro.getQuantidade() != null ? livro.getQuantidade() : livroSaved.getQuantidade());
        livroSaved.setDataPublicacao(livro.getDataPublicacao() != null ? livro.getDataPublicacao() : livroSaved.getDataPublicacao());
        livroSaved.setTitulo(livro.getTitulo() != null ? livro.getTitulo() : livroSaved.getTitulo());
        livroSaved.setImagem_capa(livro.getImagem_capa() != null ? livro.getImagem_capa() : livroSaved.getImagem_capa());
        livroSaved.setGeneros(livro.getGeneros() != null ? livro.getGeneros() : livroSaved.getGeneros());
        livroSaved.setDescricao(livro.getDescricao() != null ? livro.getDescricao() : livroSaved.getDescricao());
        livroSaved.setAutor(livro.getAutor() != null ? livro.getAutor() : livroSaved.getAutor());
        livroSaved.setIsbn(livro.getIsbn() != null ? livro.getIsbn() : livroSaved.getIsbn());
        this.itemBibliotecaRepository.save(livroSaved);
    }

    public void atualizarRevista(Long id, Revista revista) {
        // Talvez seria mais fácil criar um mapper para realizar esse mapeamento dos atributos que foram enviados, mas como são poucos casos
        // que eu identifiquei de uma entidade com multiplos campos que podem ser enviados acabei realizando o mais simples sem a necessidade de adicionar uma nova dependência.
        Revista revistaSaved = (Revista) this.itemBibliotecaRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado", id));
        revistaSaved.setQuantidade(revista.getQuantidade() != null ? revista.getQuantidade() : revistaSaved.getQuantidade());
        revistaSaved.setDataPublicacao(revista.getDataPublicacao() != null ? revista.getDataPublicacao() : revistaSaved.getDataPublicacao());
        revistaSaved.setTitulo(revista.getTitulo() != null ? revista.getTitulo() : revistaSaved.getTitulo());
        revistaSaved.setImagem_capa(revista.getImagem_capa() != null ? revista.getImagem_capa() : revistaSaved.getImagem_capa());
        revistaSaved.setGeneros(revista.getGeneros() != null ? revista.getGeneros() : revistaSaved.getGeneros());
        revistaSaved.setDescricao(revista.getDescricao() != null ? revista.getDescricao() : revistaSaved.getDescricao());
        revistaSaved.setEditora(revista.getEditora() != null ? revista.getEditora() : revistaSaved.getEditora());
        revistaSaved.setIssn(revista.getIssn() != null ? revista.getIssn() : revistaSaved.getIssn());
        this.itemBibliotecaRepository.save(revistaSaved);
    }

    public List<ItemBiblioteca> buscarItensBiblioteca() {
        return this.itemBibliotecaRepository.findAll();
    }

    public void aumentarEstoqueLivro(HashMap<Long, Integer> itensBibliotecas) {
        itensBibliotecas.forEach((id, quantidade) -> {
            ItemBiblioteca itemBiblioteca = this.itemBibliotecaRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado", id));
            itemBiblioteca.setQuantidade(itemBiblioteca.getQuantidade() + quantidade);
            this.itemBibliotecaRepository.save(itemBiblioteca);
        });
    }

    public void subtrairEstoqueLivro(HashMap<Long, Integer> itensBiblioteca) {
        itensBiblioteca.forEach((id, quantidade) -> {
            ItemBiblioteca itemBiblioteca = this.itemBibliotecaRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado", id));
            if (itemBiblioteca.getQuantidade() < quantidade || itemBiblioteca.getQuantidade() == 0) {
                throw new ItemSemEstoque(MessageFormat.format("Item {0} sem estoque", itemBiblioteca.getTitulo()), itensBiblioteca);
            }
            itemBiblioteca.setQuantidade(itemBiblioteca.getQuantidade() - quantidade);
            this.itemBibliotecaRepository.save(itemBiblioteca);
        });
    }

    public List<ItemBiblioteca> buscarItensPorAutor(Long id) {
        return this.itemBibliotecaRepository.findAllById(id);
    }

}
