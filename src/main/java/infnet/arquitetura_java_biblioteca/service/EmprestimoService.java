package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Cliente;
import infnet.arquitetura_java_biblioteca.domain.Emprestimo;
import infnet.arquitetura_java_biblioteca.domain.ItemBiblioteca;
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
    private ClienteService clienteService;
    @Autowired
    private ItemBibliotecaService itemBibliotecaService;

    @Transactional()
    public Emprestimo criarEmprestimo(CriarEmprestimoDTO criarEmprestimoDTO) throws ItensAusentesException {
        if (criarEmprestimoDTO.itensBiblioteca().isEmpty()) throw new ItensNaoInformados("Nenhum livro informado.");
        List<ItemBiblioteca> itensBibliotecas = criarEmprestimoDTO.itensBiblioteca().keySet().stream()
                .map(this.itemBibliotecaService::buscarPorId)
                .toList();
        if (itensBibliotecas.size() != criarEmprestimoDTO.itensBiblioteca().size()) {
//            Identifica IDs ausentes na lista de itens buscados na service e retorna os que não foram encontrados
            List<Long> idsAusentes = criarEmprestimoDTO.itensBiblioteca().keySet().stream()
                    .filter(id -> itensBibliotecas.stream().noneMatch(itemBiblioteca -> itemBiblioteca.getId().equals(id)))
                    .toList();
            throw new ItensAusentesException("Itens não encontrados.", idsAusentes);
        }

        Cliente cliente = this.clienteService.buscarCliente(criarEmprestimoDTO.usuarioId()).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado."));

        this.itemBibliotecaService.subtrairEstoqueLivro(criarEmprestimoDTO.itensBiblioteca());

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setCliente(cliente);
        emprestimo.setItensBiblioteca(itensBibliotecas);
        emprestimo.setDataEmprestimo(new Date());
        emprestimo.setStatus(EmprestimoStatus.INICIADO);
        emprestimo.setDataDevolucao(criarEmprestimoDTO.dataDevolucao());
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
