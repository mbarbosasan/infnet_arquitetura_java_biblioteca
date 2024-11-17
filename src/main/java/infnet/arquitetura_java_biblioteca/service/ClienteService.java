package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Cliente;
import infnet.arquitetura_java_biblioteca.exceptions.ClienteNaoEncontradoException;
import infnet.arquitetura_java_biblioteca.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarCliente(Long id) {
        return this.clienteRepository.findById(id);
    }

    public Iterable<Cliente> buscarClientes() { return this.clienteRepository.findAll(); }

    public Cliente atualizarCliente(Cliente cliente) { return this.clienteRepository.save(cliente); }

    public void deletarCliente(Long id) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Não foi possível encontrar o cliente, verifique se os valores estão corretos e tente novamente"));
        cliente.setDeletado(true);
        this.clienteRepository.save(cliente);
    }
}
