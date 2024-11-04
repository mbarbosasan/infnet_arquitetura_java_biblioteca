package infnet.arquitetura_java_biblioteca;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.domain.Cliente;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.exceptions.LivrosAusentesException;
import infnet.arquitetura_java_biblioteca.service.AutorService;
import infnet.arquitetura_java_biblioteca.service.ClienteService;
import infnet.arquitetura_java_biblioteca.service.EmprestimoService;
import infnet.arquitetura_java_biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;


@Component
public class ApplicationLoader implements ApplicationRunner {

    @Autowired
    private AutorService autorService;
    @Autowired
    private LivroService livroService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmprestimoService emprestimoService;

    @Override
    public void run(ApplicationArguments args) {
        Autor aldousHuxley = autorService.criarAutor(new Autor("Aldous Huxley", null));
        Autor douglasAdams = autorService.criarAutor(new Autor("Douglas Adams", null));
        Autor edgarAllanPoe = autorService.criarAutor(new Autor("Edgar Allan Poe", null));

        Livro livro = new Livro("Admirável Mundo Novo", aldousHuxley, "Editora Abril", convertToDate(LocalDate.of(1932, 1, 1)), "Ficção Cientifica", true);
        Livro livro2 = new Livro("1984", aldousHuxley, "Editora Abril", convertToDate(LocalDate.of(1949, 1, 1)), "Ficção Cientifica", true);
        Livro livro3 = new Livro("O mochileiro das galaxias", douglasAdams, "Editora Abril", convertToDate(LocalDate.of(1979, 1, 1)), "Ficção Cientifica", true);
        Livro livro4 = new Livro("O corvo", edgarAllanPoe, "Editora Abril", convertToDate(LocalDate.of(1845, 1, 1)), "Terror", true);

        livroService.criarLivro(aldousHuxley.getId(), livro);
        livroService.criarLivro(aldousHuxley.getId(), livro2);
        livroService.criarLivro(douglasAdams.getId(), livro3);
        livroService.criarLivro(edgarAllanPoe.getId(), livro4);

        Cliente cliente = new Cliente("Moises Santos", "Rua das Flores, 123", "8599999999", "moises@mail.com", null);
        clienteService.criarCliente(cliente);

        List<Long> livros = List.of(livro.getId(), livro2.getId(), livro3.getId(), livro4.getId());
        CriarEmprestimoDTO criarEmprestimoDTO = new CriarEmprestimoDTO(livros, cliente.getId(), java.sql.Date.valueOf(LocalDate.now().plusDays(7)));

        try {
            emprestimoService.criarEmprestimo(criarEmprestimoDTO);
        } catch (LivrosAusentesException e) {
            e.printStackTrace();
        }


    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
    }
}
