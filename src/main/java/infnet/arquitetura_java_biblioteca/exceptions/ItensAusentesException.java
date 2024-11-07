package infnet.arquitetura_java_biblioteca.exceptions;

import infnet.arquitetura_java_biblioteca.domain.dtos.ItemBibliotecaDTO;

import java.util.List;

public class ItensAusentesException extends Exception {

    List<ItemBibliotecaDTO> itensAusentes;

    public ItensAusentesException(String message, List<ItemBibliotecaDTO> livros) {
        super(message);
        this.itensAusentes = livros;
    }

    public List<ItemBibliotecaDTO> getItensAusentes() {
        return itensAusentes;
    }
}
