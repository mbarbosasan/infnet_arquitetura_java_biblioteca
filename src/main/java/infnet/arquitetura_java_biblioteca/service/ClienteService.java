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

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteSaved = this.clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Não foi possível encontrar o cliente, verifique se os valores estão corretos e tente novamente."));
        // Talvez seria mais fácil criar um mapper para realizar esse mapeamento dos atributos que foram enviados, mas como são poucos casos
        // que eu identifiquei de uma entidade com multiplos campos que podem ser enviados acabei realizando o mais simples sem a necessidade de adicionar uma nova dependência.
        clienteSaved.setNome(cliente.getNome() != null ? cliente.getNome() : clienteSaved.getNome());
        clienteSaved.setEmail(cliente.getEmail() != null ? cliente.getEmail() : clienteSaved.getEmail());
        clienteSaved.setTelefone(cliente.getTelefone() != null ? cliente.getTelefone() : clienteSaved.getTelefone());
        clienteSaved.setEndereco(cliente.getEndereco() != null ? cliente.getEndereco() : clienteSaved.getEndereco());
        clienteSaved.setDeletado(cliente.getDeletado() != null ? cliente.getDeletado() : clienteSaved.getDeletado());
        return this.clienteRepository.save(clienteSaved);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = this.clienteRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Não foi possível encontrar o cliente, verifique se os valores estão corretos e tente novamente"));
        cliente.setDeletado(true);
        this.clienteRepository.save(cliente);
    }
}
