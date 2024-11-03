package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Cliente;
import infnet.arquitetura_java_biblioteca.domain.Emprestimo;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.exceptions.ClienteNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.exceptions.LivrosAusentesException;
import infnet.arquitetura_java_biblioteca.exceptions.LivrosNaoInformadosException;
import infnet.arquitetura_java_biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private LivroService livroService;
    @Autowired
    private ClienteService clienteService;

    public Emprestimo criarEmprestimo(CriarEmprestimoDTO criarEmprestimoDTO) throws LivrosAusentesException {
        if (criarEmprestimoDTO.livrosIds().isEmpty()) throw new LivrosNaoInformadosException("Nenhum livro informado.");
        List<Livro> listaLivros = (List<Livro>) this.livroService.buscarLivrosPorId(criarEmprestimoDTO.livrosIds());
        if (listaLivros.size() != criarEmprestimoDTO.livrosIds().size()) {
            List<Long> idsEncontrados = listaLivros.stream()
                    .map(Livro::getId)
                    .toList();
            List<Long> idsAusentes = criarEmprestimoDTO.livrosIds().stream()
                    .filter(id -> !idsEncontrados.contains(id))
                    .toList();
            throw new LivrosAusentesException("Nem todos os livros informados foram encontrados, verifique-os IDs enviados e tente novamente", idsAusentes);
        }
        Cliente cliente = this.clienteService.buscarCliente(criarEmprestimoDTO.usuarioId());
        if (cliente == null) throw new ClienteNaoEncontradoException("Cliente não encontrado.");

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivros(listaLivros);
        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setDataDevolucao(criarEmprestimoDTO.dataDevolucao());
        emprestimo.setCliente(cliente);
        return this.emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() {
        return this.emprestimoRepository.findAll();
    }


}
