package infnet.arquitetura_java_biblioteca.exceptions;

import infnet.arquitetura_java_biblioteca.domain.dtos.ItemBibliotecaDTO;

public class ItemSemEstoque extends RuntimeException {

    ItemBibliotecaDTO itemBiblioteca;

    public ItemSemEstoque(String message, ItemBibliotecaDTO itemBiblioteca) {
        super(message);

    }

    public ItemBibliotecaDTO getItemBiblioteca() {
        return itemBiblioteca;
    }

    public void setItemBiblioteca(ItemBibliotecaDTO itemBiblioteca) {
        this.itemBiblioteca = itemBiblioteca;
    }
}
