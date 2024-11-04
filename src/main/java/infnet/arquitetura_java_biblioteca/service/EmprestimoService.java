package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Cliente;
import infnet.arquitetura_java_biblioteca.domain.Emprestimo;
import infnet.arquitetura_java_biblioteca.domain.Livro;
import infnet.arquitetura_java_biblioteca.domain.dtos.CriarEmprestimoDTO;
import infnet.arquitetura_java_biblioteca.domain.enums.EmprestimoStatus;
import infnet.arquitetura_java_biblioteca.exceptions.*;
import infnet.arquitetura_java_biblioteca.repository.EmprestimoRepository;
import jakarta.transaction.Transactional;
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

    @Transactional()
    public Emprestimo criarEmprestimo(CriarEmprestimoDTO criarEmprestimoDTO) throws LivrosAusentesException {
        if (criarEmprestimoDTO.livros().isEmpty()) throw new LivrosNaoInformadosException("Nenhum livro informado.");

        List<Livro> listaLivros = (List<Livro>) this.livroService.buscarLivrosPorId(
                criarEmprestimoDTO.livros().keySet().stream().toList()
        );
        if (listaLivros.size() != criarEmprestimoDTO.livros().size()) {
            List<Long> idsEncontrados = listaLivros.stream()
                    .map(Livro::getId)
                    .toList();
            List<Long> idsAusentes = criarEmprestimoDTO.livros().keySet().stream()
                    .filter(id -> !idsEncontrados.contains(id))
                    .toList();
            throw new LivrosAusentesException("Nem todos os livros informados foram encontrados, verifique-os IDs enviados e tente novamente", idsAusentes);
        }

        Cliente cliente = this.clienteService.buscarCliente(criarEmprestimoDTO.usuarioId());
        if (cliente == null) throw new ClienteNaoEncontradoException("Cliente não encontrado.");

        criarEmprestimoDTO.livros().forEach((id, quantidade) -> this.livroService.diminuirEstoqueLivro(id, quantidade));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivros(listaLivros);
        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setDataDevolucao(criarEmprestimoDTO.dataDevolucao());
        emprestimo.setCliente(cliente);
        emprestimo.setStatus(EmprestimoStatus.INICIADO);
        return this.emprestimoRepository.save(emprestimo);
    }

    public Emprestimo finalizarEmprestimo(Long id) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNaoEncontradoException("Emprestimo não encontrado."));
        emprestimo.setStatus(
                emprestimo.getDataDevolucao().before(new Date())
                        ? EmprestimoStatus.FINALIZADO_COM_ATRASO
                        : EmprestimoStatus.FINALIZADO
        );
        emprestimo.setDataDevolucaoEfetivada(new Date());
        return this.emprestimoRepository.save(emprestimo);
    }

    public Emprestimo renovarEmprestimo(Long id, Date novaDataDevolucao) {
        Emprestimo emprestimo = this.emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNaoEncontradoException("Emprestimo não encontrado."));
        if (emprestimo.getDataDevolucao().before(new Date()))
            throw new EmprestimoAtrasadoException("Não é possível renovar um emprestimo que já está atrasado.");
        if (novaDataDevolucao.before(new Date()) || novaDataDevolucao.before(emprestimo.getDataDevolucao()))
            throw new EmprestimoDataDevolucaoInvalidaException("A data de devolução informada é inválida, verifique se ela não é anterior a data atual ou a data de devolução atual.");
        if (emprestimo.getDataDevolucaoEfetivada() != null)
            throw new EmprestimoFinalizadoException("Não é possível renovar um emprestimo que já foi finalizado.");
        emprestimo.setDataDevolucao(novaDataDevolucao);
        emprestimo.setStatus(EmprestimoStatus.RENOVADO);
        return this.emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarEmprestimos() {
        return this.emprestimoRepository.findAll();
    }


}
