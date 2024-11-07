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
