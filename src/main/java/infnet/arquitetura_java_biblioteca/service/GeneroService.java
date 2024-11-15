package infnet.arquitetura_java_biblioteca.service;

import infnet.arquitetura_java_biblioteca.domain.Genero;
import infnet.arquitetura_java_biblioteca.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public Genero criarGenero(Genero genero) {
        return this.generoRepository.save(genero);
    }

    public Genero buscarGeneroPorId(Long id) {
        return this.generoRepository.findById(id).orElse(null);
    }

    public Genero atualizarGenero(Long id, Genero genero) {
        Genero generoAtualizado = this.generoRepository.findById(id).orElse(null);
        if (generoAtualizado != null) {
            generoAtualizado.setNome(genero.getNome());
            return this.generoRepository.save(generoAtualizado);
        }
        return null;
    }

    public void deletarGeneroPorId(Long id) {
        this.generoRepository.deleteById(id);
    }

    public Iterable<Genero> buscarTodosGeneros() {
        return this.generoRepository.findAll();
    }
}
