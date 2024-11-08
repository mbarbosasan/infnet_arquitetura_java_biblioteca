package infnet.arquitetura_java_biblioteca.services;

import infnet.arquitetura_java_biblioteca.domain.Autor;
import infnet.arquitetura_java_biblioteca.repository.AutorRepository;
import infnet.arquitetura_java_biblioteca.service.AutorService;
import infnet.arquitetura_java_biblioteca.service.ItemBibliotecaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;
    @Mock
    private ItemBibliotecaService itemBibliotecaService;
    @InjectMocks
    private AutorService autorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarAutor() {
        // Arrange
        Autor autor = new Autor("Benjamin Franklin", null);
        when(autorRepository.save(autor)).thenReturn(autor);

        // Act
        Autor autorCadastrado = this.autorService.criarAutor(autor);

        // Assert
        assertEquals(autor, autorCadastrado);
    }
}
