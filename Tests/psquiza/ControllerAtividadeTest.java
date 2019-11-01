package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAtividadeTest {

    private ControllerAtividade controladorDeAtividade;

    @BeforeEach
    public void criaControllerAtividadeTeste() {
        controladorDeAtividade = new ControllerAtividade();
    }

    @Test
    public void testaCadastraAtividade() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos","BAIXO",
                "Nenhum risco identficado"),"A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes","MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"),"A2");
    }

    @Test
    public void testaCadastraAtividadeComExcecoes() {
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.cadastraAtividade(null,"MEDIO",
                "Desenvolvimento de depressao"));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraAtividade("","MEDIO",
                "Desenvolvimento de depressao"));
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.cadastraAtividade("Degustacao de cerveja artesanak",
                null,"Algum degustador ficar alcoolizado"));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraAtividade("Degustacao de cerveja artesanak",
                "","Algum degustador ficar alcoolizado"));
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.cadastraAtividade("Pesquisa de campo sobre moda",
                "BAIXO",null));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraAtividade("Pesquisa de campo sobre moda",
                "BAIXO",""));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraAtividade("Qualquer outro valor para Nivel Risco",
                "MEDIANA","dara erro"));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraAtividade("Qualquer outro valor para Nivel Risco",
                "MODA","dara erro"));
    }

    @Test
    public void testaApagaAtividade() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca","BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraAtividade("Jogar Video Game","BAIXO",
                "Problemas na vista devido ao excesso do jogo");
        controladorDeAtividade.apagaAtividade("A1");
        controladorDeAtividade.apagaAtividade("A2");
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.exibeAtividade("A1"));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.exibeAtividade("A2"));
    }

    @Test
    public void testaApagaAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.apagaAtividade("A11"));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.apagaAtividade("A2"));
    }

    @Test
    public void testaApagaAtividadesComExcecoes() {
        assertThrows(NullPointerException.class,()->controladorDeAtividade.apagaAtividade(null));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.apagaAtividade(""));
    }

    @Test
    public void testaCadastraItens() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca","BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraItem("A1","Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1","Tira Gosto");
        assertEquals(controladorDeAtividade.exibeAtividade("A1"),"Degustacao de cachaca (BAIXO - Algum degustador ficar alcoolizado)" +
                " | PENDENTE - Copo de cachaca | PENDENTE - Tira Gosto");
    }

    @Test
    public void testaCadastraItemAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.cadastraItem("A11","Copo de cana"));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.cadastraItem("A1","Tira gosto"));
    }

    @Test
    public void testaCadastraItemComExcecoes() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca","BAIXO",
                "Algum degustador ficar alcoolizado");
        assertThrows(NullPointerException.class,()->controladorDeAtividade.cadastraItem(null,"Prancheta"));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.cadastraItem("","Prancheta"));
        assertThrows(NullPointerException.class,()->controladorDeAtividade.cadastraItem("A1",null));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.cadastraItem("A1",""));
    }

    @Test
    public void testaExibeAtividade() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca","BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraItem("A1","Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1","Tira Gosto");
        assertEquals(controladorDeAtividade.exibeAtividade("A1"),"Degustacao de cachaca (BAIXO - Algum degustador ficar alcoolizado)" +
                " | PENDENTE - Copo de cachaca | PENDENTE - Tira Gosto");
    }

    @Test
    public void testaExibeAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.exibeAtividade("A1"));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.exibeAtividade("A222"));
    }

    @Test
    public void testaExibeAtividadeComExcecoes() {
        assertThrows(NullPointerException.class,()->controladorDeAtividade.exibeAtividade(null));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.exibeAtividade(""));
    }

    @Test
    public void testaContaItensRealizados() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca","BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraItem("A1","Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1","Tira Gosto");
        assertTrue(controladorDeAtividade.contaItensRealizados("A1")==0);
    }

    @Test
    public void testaContaItensRealizadosAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.contaItensRealizados("A22"));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.contaItensRealizados("A3"));
    }

    @Test
    public void testaContaItensRealizadosComExcecoes() {
        assertThrows(NullPointerException.class,()->controladorDeAtividade.contaItensRealizados(null));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.contaItensRealizados(""));
    }

    @Test
    public void testaContaItensPendentes() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca","BAIXO",
                "Algum degustador ficar alcoolizado");
        assertTrue(controladorDeAtividade.contaItensPendentes("A1")==0);
        controladorDeAtividade.cadastraItem("A1","Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1","Tira Gosto");
        assertTrue(controladorDeAtividade.contaItensPendentes("A1")==2);
    }

    @Test
    public void testaContaItensPendetesAtividadeInexistentes() {
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.contaItensPendentes("A22"));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.contaItensPendentes("A3"));
    }

    @Test
    public void testaContaItensPendentesAtividadesComExcecoes() {
        assertThrows(NullPointerException.class,()->controladorDeAtividade.contaItensPendentes(null));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.contaItensPendentes(""));
    }
}