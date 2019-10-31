package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item1;
    private Item item2;
    private Item item3;

    @BeforeEach
    public void criaItem() {
        this.item1 = new Item(1, "Monitoramento Twitter");
        this.item2 = new Item(2, "Monitoramento orkut");
        this.item3 = new Item(3, "Degustacao de parrila");
    }

    @Test
    public void testaConstroiItem() {
        assertEquals(item1, new Item(1, "Monitoramento Twitter"));
        assertEquals(item2, new Item(2, "Monitoramento orkut"));
        assertEquals(item3, new Item(3, "Degustacao de parrila"));
    }

    @Test
    public void testaConstroiItemComExcecoes() {
        assertThrows(NullPointerException.class,()-> new Item(5,null));
        assertThrows(IllegalArgumentException.class,()-> new Item(10,""));
    }

    @Test
    public void testaSetStatus() {
        this.item1.setStatus();
        this.item2.setStatus();
        this.item3.setStatus();
        assertEquals(this.item1.getStatus(),"REALIZADO");
        assertEquals(this.item2.getStatus(),"REALIZADO");
        assertEquals(this.item3.getStatus(),"REALIZADO");
    }

    @Test
    public void testaGetStatus() {
        this.item3.setStatus();
        assertEquals(this.item1.getStatus(),"PENDENTE");
        assertEquals(this.item2.getStatus(),"PENDENTE");
        assertEquals(this.item3.getStatus(),"REALIZADO");
    }

    @Test
    public void testaToString() {
        this.item1.setStatus();
        this.item2.setStatus();
        assertEquals(this.item1.toString(),"REALIZADO - Monitoramento Twitter");
        assertEquals(this.item2.toString(),"REALIZADO - Monitoramento orkut");
        assertEquals(this.item3.toString(),"PENDENTE - Degustacao de parrila");
    }

    @Test
    public void testaEqualsItensDiferentes() {
        Item itemIdIgual = new Item(3,"Degustacao de Macarronada");
        Item itemTudoDiferente = new Item(9,"Massagens relaxantes");
        assertFalse(itemIdIgual.equals(this.item3));
        assertFalse(itemTudoDiferente.equals(this.item2));
    }

    @Test
    public void testaEqualsItensIguais() {
        Item itemNomeIgual = new Item(10,"Monitoramento Twitter");
        Item itemTudoIgual = new Item(3,"Degustacao de parrila");
        assertTrue(itemNomeIgual.equals(this.item1));
        assertTrue(itemTudoIgual.equals(this.item3));
    }

    @Test
    public void testaHashCodeDiferente() {
        Item itemIdIgual = new Item(3,"Degustacao de Macarronada");
        Item itemTudoDiferente = new Item(9,"Massagens relaxantes");
        assertFalse(itemIdIgual.hashCode()==this.item3.hashCode());
        assertFalse(itemTudoDiferente.hashCode()==this.item2.hashCode());
    }

    @Test
    public void testaHashCodeIgual() {
        Item itemNomeIgual = new Item(10,"Monitoramento Twitter");
        Item itemTudoIgual = new Item(3,"Degustacao de parrila");
        assertTrue(itemNomeIgual.hashCode()==this.item1.hashCode());
        assertTrue(itemTudoIgual.hashCode()==this.item3.hashCode());
    }


}