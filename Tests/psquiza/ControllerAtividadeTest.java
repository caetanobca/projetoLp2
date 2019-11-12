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

    @Test
    public void testaExecutaAtividadeComExcecoes() {
        ControllerPesquisa controllerPesquisa = new ControllerPesquisa();
        controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao");
        controladorDeAtividade.cadastraAtividade("Realizacao de rodas de conversa para debater homofobia","BAIXO"
                ,"Algum tipo de comportamento homofobico");
        controllerPesquisa.associaAtividadeEmPesquisa("COM1",controladorDeAtividade.getAtividade("A1"));
        controladorDeAtividade.cadastraItem("A1","Papel");
        controladorDeAtividade.executaAtividade("A1",1,10);

        assertThrows(NullPointerException.class,()-> controladorDeAtividade.executaAtividade(null,1,8));

        //Testando Item negativo
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.executaAtividade("A1",-1,10));

        //Testando item com valor nulo
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.executaAtividade("A1",0,10));

        //Testando duracao negativa
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.executaAtividade("A1",1,-1));

        //Testando duracao com valor nulo
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.executaAtividade("A1",1,0));

        //Testando atividade inexistente
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.executaAtividade("A5",1,15));
    }

    @Test
    public void testaExecutaAtividade() {
        ControllerPesquisa controllerPesquisa = new ControllerPesquisa();
        controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao");
        controladorDeAtividade.cadastraAtividade("Realizacao de rodas de conversa para debater homofobia","BAIXO"
        ,"Algum tipo de comportamento homofobico");
        controllerPesquisa.associaAtividadeEmPesquisa("COM1",controladorDeAtividade.getAtividade("A1"));
        controladorDeAtividade.cadastraItem("A1","Papel");
        controladorDeAtividade.executaAtividade("A1",1,10);
        assertEquals(controladorDeAtividade.contaItensRealizados("A1"),1);
    }

    @Test
    public void testaCadastraResultadoComExcecoesTest() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas","BAIXO"
                ,"Nenhum");
        //Cadastrando valores nulos para codigo e resultado
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.cadastraResultado("A1",null));
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.cadastraResultado(null,"A1"));

        //Cadastrando valores vazios para codigo e resultado
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraResultado("A1",""));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraResultado("","A1"));

        //Cadastrando resultado em atividade inexistente

        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.cadastraResultado("","A10"));
    }

    @Test
    public void testaCadastraResultado() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas","BAIXO"
                ,"Nenhum");
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre politica","ALTO",
                "Muitas brigas");
        assertEquals(controladorDeAtividade.cadastraResultado("A1","Satisfatorio"),1);
        assertEquals(controladorDeAtividade.cadastraResultado("A2","Tiro,porrada e bomba"),1);
        assertEquals(controladorDeAtividade.cadastraResultado("A1","Muito bom"),2);
    }

    @Test
    public void testaRemoveResultadoComExcecoes() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas","BAIXO"
                ,"Nenhum");
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre politica","ALTO",
                "Muitas brigas");
        controladorDeAtividade.cadastraResultado("A1","Satisfatorio");
        controladorDeAtividade.cadastraResultado("A2","Tiro,porrada e bomba");
        controladorDeAtividade.cadastraResultado("A1","Muito bom");

        //Removendo resultados com codigo atividade nulo ou vazio
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.removeResultado(null,1));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.removeResultado("",1));

        //Removendo resultados de atividades inexistentes
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.removeResultado("A7",1));

        //Testando remocao com resultado que nao existe
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.removeResultado("A1",4));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.removeResultado("A2",22));
    }

    @Test
    public void testaRemoveResultado() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas","BAIXO"
                ,"Nenhum");
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre politica","ALTO",
                "Muitas brigas");
        controladorDeAtividade.cadastraResultado("A1","Satisfatorio");
        controladorDeAtividade.cadastraResultado("A2","Tiro,porrada e bomba");
        controladorDeAtividade.cadastraResultado("A1","Muito bom");

        //Testando remocao feita com sucesso
        assertTrue(controladorDeAtividade.removeResultado("A1",1));
        assertTrue(controladorDeAtividade.removeResultado("A2",1));
    }

    @Test
    public void testaListaResultadosComExcecoes() {
        assertThrows(NullPointerException.class,()-> controladorDeAtividade.listaResultados(null));
        assertThrows(IllegalArgumentException.class,()-> controladorDeAtividade.listaResultados(""));
    }

    @Test
    public void testaListaResultados() {
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre religiao","ALTO"
        ,"Terceira Guerra Mundial");
        controladorDeAtividade.cadastraResultado("A1","Muita briga");
        controladorDeAtividade.cadastraResultado("A1","Discurssao");
        controladorDeAtividade.cadastraResultado("A1","Bomba");
        assertEquals(controladorDeAtividade.listaResultados("A1"),"Muita briga" +
                " | Discurssao | Bomba");
    }

    @Test
    public void testaGetDuracaoComExcecoes() {
        //Codigo atividade nulo ou vazio
        assertThrows(NullPointerException.class,()->controladorDeAtividade.getDuracao(null));
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.getDuracao(""));

        //Atividade inexistente no sistema
        assertThrows(IllegalArgumentException.class,()->controladorDeAtividade.getDuracao("A10"));
    }

    @Test
    public void testaGetDuracao() {
        ControllerPesquisa controllerPesquisa = new ControllerPesquisa();
        controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao");
        controladorDeAtividade.cadastraAtividade("Realizacao de rodas de conversa para debater homofobia","BAIXO"
                ,"Algum tipo de comportamento homofobico");
        controllerPesquisa.associaAtividadeEmPesquisa("COM1",controladorDeAtividade.getAtividade("A1"));
        controladorDeAtividade.cadastraItem("A1","Papel");
        controladorDeAtividade.executaAtividade("A1",1,10);
        assertEquals(controladorDeAtividade.getDuracao("A1"),10);
    }

}