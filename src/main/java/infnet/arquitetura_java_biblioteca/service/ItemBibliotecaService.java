package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.ItemBiblioteca;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.Revista;
import infnet.arquitetura_java_biblioteca.domain.dtos.ItemBibliotecaDTO;
import infnet.arquitetura_java_biblioteca.domain.dtos.ModificarEstoqueDTO;
import infnet.arquitetura_java_biblioteca.exceptions.AutorNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.ItemNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.ItemSemEstoque;
import infnet.arquitetura_java_biblioteca.repository.ItemBibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
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

    public void aumentarEstoqueLivro(ModificarEstoqueDTO modificarEstoqueDTO) {
        modificarEstoqueDTO.itensBiblioteca().forEach((itemBibliotecaDTO) -> {
            ItemBiblioteca itemBiblioteca = this.itemBibliotecaRepository.findById(itemBibliotecaDTO.id()).orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado", itemBibliotecaDTO.id()));
            itemBiblioteca.setQuantidade(itemBiblioteca.getQuantidade() + itemBibliotecaDTO.quantidade());
            this.itemBibliotecaRepository.save(itemBiblioteca);
        });
    }

    public void subtrairEstoqueLivro(List<ItemBibliotecaDTO> itensBiblioteca) {
        itensBiblioteca.forEach((itemBibliotecaDTO) -> {
            ItemBiblioteca itemBiblioteca = this.itemBibliotecaRepository.findById(itemBibliotecaDTO.id()).orElseThrow(() -> new ItemNaoEncontradoException("Item não encontrado", itemBibliotecaDTO.id()));
            if (itemBiblioteca.getQuantidade() < itemBibliotecaDTO.quantidade()) {
                throw new ItemSemEstoque(MessageFormat.format("Não foi possível iniciar o empréstimo pois não há estoque suficiente para o item {0}, estando apenas com {1} unidades", itemBiblioteca.getTitulo(), itemBiblioteca.getQuantidade()), itemBibliotecaDTO);
            }
            itemBiblioteca.setQuantidade(itemBiblioteca.getQuantidade() - itemBibliotecaDTO.quantidade());
            this.itemBibliotecaRepository.save(itemBiblioteca);
        });
    }

    public List<ItemBiblioteca> buscarItensPorAutor(Long id) {
        return this.itemBibliotecaRepository.findAllById(id);
    }

}
