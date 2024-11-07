package infnet.arquitetura_java_biblioteca.exceptions;

import java.util.HashMap;

public class ItemSemEstoque extends RuntimeException {

    HashMap<Long, Integer> itemBiblioteca;

    public ItemSemEstoque(String message, HashMap<Long, Integer> itemBiblioteca) {
        super(message);

    }

    public HashMap<Long, Integer> getItemBiblioteca() {
        return itemBiblioteca;
    }

    public void setItemBiblioteca(HashMap<Long, Integer> itemBiblioteca) {
        this.itemBiblioteca = itemBiblioteca;
    }
}
