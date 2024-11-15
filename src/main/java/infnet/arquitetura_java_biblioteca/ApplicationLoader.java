package infnet.arquitetura_java_biblioteca;

import infnet.arquitetura_java_biblioteca.domain.*;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.exceptions.ItensAusentesException;
import infnet.arquitetura_java_biblioteca.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
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
    @Autowired
    private GeneroService generoService;

    @Override
    public void run(ApplicationArguments args) {
        Autor aldousHuxley = autorService.criarAutor(new Autor("Aldous Huxley", null));
        Autor georgeOrwell = autorService.criarAutor(new Autor("George Orwell", null));
        Autor douglasAdams = autorService.criarAutor(new Autor("Douglas Adams", null));
        Autor edgarAllanPoe = autorService.criarAutor(new Autor("Edgar Allan Poe", null));

        Genero ficcaoCientifica = generoService.criarGenero(new Genero("Ficção Científica", null));
        Genero terror = generoService.criarGenero(new Genero("Terror", null));
        Genero tecnologia = generoService.criarGenero(new Genero("Tecnologia", null));

        List<Genero> admiravelMundoNovoGeneros = List.of(ficcaoCientifica);

        List.of(ficcaoCientifica);


        Livro livro = new Livro("Admirável Mundo Novo", List.of(ficcaoCientifica), "Admirável Mundo Novo é um romance escrito por Aldous Huxley e publicado em 1932. A história se passa em Londres no ano 2540, o romance antecipa desenvolvimentos em tecnologia reprodutiva, hipnopedia, manipulação psicológica e condicionamento clássico, que se combinam para mudar profundamente a sociedade.", "https://martinsfontespaulista.vteximg.com.br/arquivos/ids/160764-511-511/725912_ampliada.jpg?v=637260563818600000", LocalDate.of(1932, 1, 1), 50, aldousHuxley, "123456");
        Livro livro2 = new Livro("1984", List.of(ficcaoCientifica), "Mil novecentos e oitenta e quatro é um romance distópico do escritor inglês George Orwell. Foi publicado em 8 de junho de 1949 pela Secker & Warburg como o nono e último livro de Orwell concluído em vida.", "https://m.media-amazon.com/images/I/511vWdI8zKL._AC_UF1000,1000_QL80_.jpg", LocalDate.of(1949, 1, 1), 50, georgeOrwell, "123456");
        Livro livro3 = new Livro("O Guia do Mochileiro das Galáxias", List.of(ficcaoCientifica), "Segundos antes de a Terra ser destruída para dar lugar a uma via expressa interespacial, Arthur Dent é salvo por Ford Prefect, um E.T. que fazia pesquisa de campo para a nova edição de O Guia do Mochileiro das Galáxias. Pegando carona numa nave alienígena, os dois dão início a uma alucinante viagem pelo tempo e pelo espaço.", "https://m.media-amazon.com/images/I/71K0ACNXURL._SY466_.jpg", LocalDate.of(1979, 1, 1), 50, douglasAdams, "123456");
        Livro livro4 = new Livro("O corvo e outros contos extraordinários", List.of(terror), "O Corvo e contos extraordinários é uma seleção dos textos mais expressivos do autor considerado o mestre do mistério e procura mostrar o gênio criativo de um dos mais conhecidos escritores americanos. Além dos contos, como 'A queda da Casa de Usher', 'O poço e o pêndulo' e 'O gato preto', este volume também traz a tradução do poema 'O Corvo' feita por Fernando Pessoa.", "https://m.media-amazon.com/images/I/71L9UogcMcL._SY466_.jpg", LocalDate.of(1845, 1, 1), 50, edgarAllanPoe, "123456");

        itemBibliotecaService.cadastrarItemBiblioteca(livro, aldousHuxley.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(livro2, aldousHuxley.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(livro3, douglasAdams.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(livro4, edgarAllanPoe.getId());

        Editora useACabeca = editoraService.cadastrarEditora(new Editora("Use a Cabeça", null));
        Editora oRlly = editoraService.cadastrarEditora(new Editora("O' Rlly?", null));

        Revista revista = new Revista("Java: Guia do Aprendiz para programação no mundo real", List.of(tecnologia), "O “Use a Cabeça Java” é uma experiência completa de aprendizado em Java e programação orientada a objetos. Com este livro, você aprenderá a linguagem Java de um jeito único, que ultrapassa os manuais de instruções, ajudando-o a se tornar um programador excelente.", "https://m.media-amazon.com/images/I/610D1O8WWOL._SY342_.jpg", LocalDate.of(2021, 1, 1), 50, useACabeca, "123456");
        Revista revista2 = new Revista("Trying stuff until it works", List.of(tecnologia), "Trying stuff until it works", "https://m.media-amazon.com/images/I/61jelQK-thL._SY466_.jpg", LocalDate.of(2021, 1, 1), 50, oRlly, "123456");

        itemBibliotecaService.cadastrarItemBiblioteca(revista, useACabeca.getId());
        itemBibliotecaService.cadastrarItemBiblioteca(revista2, oRlly.getId());

        Cliente cliente = new Cliente("Moises Santos", "Rua das Flores, 123", "8599999999", "moises@mail.com", null);
        clienteService.criarCliente(cliente);

        HashMap<Long, Integer> livros = new HashMap<>();
        livros.put(livro.getId(), 1);
        livros.put(livro2.getId(), 1);
        livros.put(livro3.getId(), 1);
        livros.put(livro4.getId(), 1);

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
