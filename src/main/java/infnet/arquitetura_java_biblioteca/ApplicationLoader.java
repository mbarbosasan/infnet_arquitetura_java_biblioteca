package infnet.arquitetura_java_biblioteca;

import infnet.arquitetura_java_biblioteca.domain.*;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.domain.dtos.ItemBibliotecaDTO;
import infnet.arquitetura_java_biblioteca.exceptions.ItensAusentesException;
import infnet.arquitetura_java_biblioteca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class ApplicationLoader implements ApplicationRunner {

    @Autowired
    private AutorService autorService;
    @Autowired
    private ItemBibliotecaService itemBibliotecaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmprestimoService emprestimoService;
    @Autowired
    private EditoraService editoraService;

    @Override
    public void run(ApplicationArguments args) {
        Autor aldousHuxley = autorService.criarAutor(new Autor("Aldous Huxley", null));
        Autor douglasAdams = autorService.criarAutor(new Autor("Douglas Adams", null));
        Autor edgarAllanPoe = autorService.criarAutor(new Autor("Edgar Allan Poe", null));

        Livro livro = new Livro("Admirável Mundo Novo", "Ficção Científica", "Muito bom", "https://google.com.br", LocalDate.of(1932, 1, 1), 50, aldousHuxley, "123456");
        Livro livro2 = new Livro("1984", "Ficção Científica", "Muito bom", "https://google.com.br", LocalDate.of(1949, 1, 1), 50, aldousHuxley, "123456");
        Livro livro3 = new Livro("O mochileiro das galaxias", "Ficção Científica", "Muito bom", "https://google.com.br", LocalDate.of(1979, 1, 1), 50, douglasAdams, "123456");
        Livro livro4 = new Livro("O corvo", "Terror", "Muito bom", "https://google.com.br", LocalDate.of(1845, 1, 1), 50, edgarAllanPoe, "123456");

        itemBibliotecaService.cadastrarItemBiblioteca(livro, aldousHuxley.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(livro2, aldousHuxley.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(livro3, douglasAdams.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(livro4, edgarAllanPoe.getId());

        Editora useACabeca = editoraService.cadastrarEditora(new Editora("Use a Cabeça", null));
        Editora oRlly = editoraService.cadastrarEditora(new Editora("O' Rlly?", null));

        Revista revista = new Revista("Java: Guia do Aprendiz para programação no mundo real", "Tecnologia", "Muito bom", "https://google.com.br", LocalDate.of(2021, 1, 1), 50, useACabeca, "123456");
        Revista revista2 = new Revista("Trying things until it works", "Tecnologia", "Muito bom", "https://google.com.br", LocalDate.of(2021, 1, 1), 50, oRlly, "123456");

        itemBibliotecaService.cadastrarItemBiblioteca(revista, useACabeca.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(revista2, oRlly.getId());

        Cliente cliente = new Cliente("Moises Santos", "Rua das Flores, 123", "8599999999", "moises@mail.com", null);
        clienteService.criarCliente(cliente);

        List<ItemBibliotecaDTO> livros = new ArrayList<>();
        livros.add(new ItemBibliotecaDTO("LIVRO", livro.getId(), 1));
        livros.add(new ItemBibliotecaDTO("LIVRO", livro2.getId(), 1));
        livros.add(new ItemBibliotecaDTO("LIVRO", livro3.getId(), 1));
        livros.add(new ItemBibliotecaDTO("LIVRO", livro4.getId(), 1));

        CriarEmprestimoDTO criarEmprestimoDTO = new CriarEmprestimoDTO(livros, cliente.getId(), java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

        try {
            emprestimoService.criarEmprestimo(criarEmprestimoDTO);
        } catch (ItensAusentesException e) {
            e.printStackTrace();
        }


    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }
}
