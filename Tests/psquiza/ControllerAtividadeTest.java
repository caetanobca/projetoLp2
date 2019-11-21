package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.atividade.ControllerAtividade;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAtividadeTest {

    private ControllerAtividade controladorDeAtividade;

    @BeforeEach
    public void criaControllerAtividadeTeste() {
        controladorDeAtividade = new ControllerAtividade();
    }

    @Test
    public void testaCadastraAtividade() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
    }

    @Test
    public void testaCadastraAtividadeComExcecoes() {
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraAtividade(null, "MEDIO",
                "Desenvolvimento de depressao"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraAtividade("", "MEDIO",
                "Desenvolvimento de depressao"));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraAtividade("Degustacao de " +
                        "cerveja artesanak",
                null, "Algum degustador ficar alcoolizado"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraAtividade("Degustacao de " +
                        "cerveja artesanak",
                "", "Algum degustador ficar alcoolizado"));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraAtividade("Pesquisa de campo " +
                        "sobre moda",
                "BAIXO", null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraAtividade("Pesquisa de " +
                        "campo sobre moda",
                "BAIXO", ""));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraAtividade("Qualquer outro " +
                        "valor para Nivel Risco",
                "MEDIANA", "dara erro"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraAtividade("Qualquer outro " +
                        "valor para Nivel Risco",
                "MODA", "dara erro"));
    }

    @Test
    public void testaApagaAtividade() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca", "BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraAtividade("Jogar Video Game", "BAIXO",
                "Problemas na vista devido ao excesso do jogo");
        controladorDeAtividade.apagaAtividade("A1");
        controladorDeAtividade.apagaAtividade("A2");
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.exibeAtividade("A1"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.exibeAtividade("A2"));
    }

    @Test
    public void testaApagaAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.apagaAtividade("A11"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.apagaAtividade("A2"));
    }

    @Test
    public void testaApagaAtividadesComExcecoes() {
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.apagaAtividade(null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.apagaAtividade(""));
    }

    @Test
    public void testaCadastraItens() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca", "BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraItem("A1", "Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1", "Tira Gosto");
        assertEquals(controladorDeAtividade.exibeAtividade("A1"), "Degustacao de cachaca (BAIXO - Algum degustador " +
                "ficar alcoolizado)" +
                " | PENDENTE - Copo de cachaca | PENDENTE - Tira Gosto");
    }

    @Test
    public void testaCadastraItemAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraItem("A11", "Copo de cana"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraItem("A1", "Tira gosto"));
    }

    @Test
    public void testaCadastraItemComExcecoes() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca", "BAIXO",
                "Algum degustador ficar alcoolizado");
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraItem(null, "Prancheta"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraItem("", "Prancheta"));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraItem("A1", null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraItem("A1", ""));
    }

    @Test
    public void testaExibeAtividade() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca", "BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraItem("A1", "Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1", "Tira Gosto");
        assertEquals(controladorDeAtividade.exibeAtividade("A1"), "Degustacao de cachaca (BAIXO - Algum degustador " +
                "ficar alcoolizado)" +
                " | PENDENTE - Copo de cachaca | PENDENTE - Tira Gosto");
    }

    @Test
    public void testaExibeAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.exibeAtividade("A1"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.exibeAtividade("A222"));
    }

    @Test
    public void testaExibeAtividadeComExcecoes() {
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.exibeAtividade(null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.exibeAtividade(""));
    }

    @Test
    public void testaContaItensRealizados() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca", "BAIXO",
                "Algum degustador ficar alcoolizado");
        controladorDeAtividade.cadastraItem("A1", "Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1", "Tira Gosto");
        assertTrue(controladorDeAtividade.contaItensRealizados("A1") == 0);
    }

    @Test
    public void testaContaItensRealizadosAtividadeInexistente() {
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaItensRealizados("A22"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaItensRealizados("A3"));
    }

    @Test
    public void testaContaItensRealizadosComExcecoes() {
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.contaItensRealizados(null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaItensRealizados(""));
    }

    @Test
    public void testaContaItensPendentes() {
        controladorDeAtividade.cadastraAtividade("Degustacao de cachaca", "BAIXO",
                "Algum degustador ficar alcoolizado");
        assertTrue(controladorDeAtividade.contaItensPendentes("A1") == 0);
        controladorDeAtividade.cadastraItem("A1", "Copo de cachaca");
        controladorDeAtividade.cadastraItem("A1", "Tira Gosto");
        assertTrue(controladorDeAtividade.contaItensPendentes("A1") == 2);
    }

    @Test
    public void testaContaItensPendetesAtividadeInexistentes() {
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaItensPendentes("A22"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaItensPendentes("A3"));
    }

    @Test
    public void testaContaItensPendentesAtividadesComExcecoes() {
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.contaItensPendentes(null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaItensPendentes(""));
    }

    @Test
    void buscaTest() {
        controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado");
        controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa");
        controladorDeAtividade.cadastraAtividade("Entrevistas com vítimas de racismo", "BAIXO", "Risco Baixo");

        List<String> resultado = new ArrayList<>();

        resultado.add("A3: Entrevistas com vítimas de racismo");
        resultado.add("A3: Risco Baixo");
        resultado.add("A2: Percorrer comunidades carentes");
        resultado.add("A2: Algum evento na comunidade que interfira na pesquisa");
        resultado.add("A1: Fazer rodas de conversa sobre temas diversos");
        resultado.add("A1: Nenhum risco identficado");

        assertEquals(resultado, controladorDeAtividade.busca("a"));

        List<String> resultado2 = new ArrayList<>();

        resultado2.add("A3: Entrevistas com vítimas de racismo");

        assertEquals(resultado2, controladorDeAtividade.busca("racismo"));

        List<String> resultado3 = new ArrayList<>();

        resultado3.add("A2: Percorrer comunidades carentes");

        assertEquals(resultado3, controladorDeAtividade.busca("comunidades"));

        List<String> resultado4 = new ArrayList<>();

        resultado4.add("A1: Fazer rodas de conversa sobre temas diversos");

        assertEquals(resultado4, controladorDeAtividade.busca("temas"));

    }

    @Test
    void buscaTestTermoVaziouOuNull() {
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.busca(""));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.busca(null));
    }

    @Test
    public void testaExecutaAtividadeComExcecoes() {
        Pesquisa pesquisa = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao", "COM1");

        controladorDeAtividade.cadastraAtividade("Realizacao de rodas de conversa para debater homofobia", "BAIXO"
                , "Algum tipo de comportamento homofobico");

        pesquisa.associaAtividade(controladorDeAtividade.getAtividade("A1"));
        controladorDeAtividade.cadastraItem("A1", "Papel");
        controladorDeAtividade.executaAtividade("A1", 1, 10);

        assertThrows(NullPointerException.class, () -> controladorDeAtividade.executaAtividade(null, 1, 8));

        //Testando Item negativo
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.executaAtividade("A1", -1, 10));

        //Testando item com valor nulo
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.executaAtividade("A1", 0, 10));

        //Testando duracao negativa
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.executaAtividade("A1", 1, -1));

        //Testando duracao com valor nulo
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.executaAtividade("A1", 1, 0));

        //Testando atividade inexistente
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.executaAtividade("A5", 1, 15));
    }

    @Test
    public void testaExecutaAtividade() {
        Pesquisa pesquisa = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao", "COM1");

        controladorDeAtividade.cadastraAtividade("Realizacao de rodas de conversa para debater homofobia", "BAIXO"
                , "Algum tipo de comportamento homofobico");

        pesquisa.associaAtividade(controladorDeAtividade.getAtividade("A1"));
        controladorDeAtividade.cadastraItem("A1", "Papel");
        controladorDeAtividade.executaAtividade("A1", 1, 10);
        assertEquals(controladorDeAtividade.contaItensRealizados("A1"), 1);
    }

    @Test
    public void testaCadastraResultadoComExcecoesTest() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas", "BAIXO"
                , "Nenhum");
        //Cadastrando valores nulos para codigo e resultado
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraResultado("A1", null));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.cadastraResultado(null, "A1"));

        //Cadastrando valores vazios para codigo e resultado
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraResultado("A1", ""));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraResultado("", "A1"));

        //Cadastrando resultado em atividade inexistente

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.cadastraResultado("", "A10"));
    }

    @Test
    public void testaCadastraResultado() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas", "BAIXO"
                , "Nenhum");
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre politica", "ALTO",
                "Muitas brigas");
        assertEquals(controladorDeAtividade.cadastraResultado("A1", "Satisfatorio"), 1);
        assertEquals(controladorDeAtividade.cadastraResultado("A2", "Tiro,porrada e bomba"), 1);
        assertEquals(controladorDeAtividade.cadastraResultado("A1", "Muito bom"), 2);
    }

    @Test
    public void testaRemoveResultadoComExcecoes() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas", "BAIXO"
                , "Nenhum");
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre politica", "ALTO",
                "Muitas brigas");
        controladorDeAtividade.cadastraResultado("A1", "Satisfatorio");
        controladorDeAtividade.cadastraResultado("A2", "Tiro,porrada e bomba");
        controladorDeAtividade.cadastraResultado("A1", "Muito bom");

        //Removendo resultados com codigo atividade nulo ou vazio
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.removeResultado(null, 1));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.removeResultado("", 1));

        //Removendo resultados de atividades inexistentes
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.removeResultado("A7", 1));

        //Testando remocao com resultado que nao existe
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.removeResultado("A1", 4));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.removeResultado("A2", 22));
    }

    @Test
    public void testaRemoveResultado() {
        controladorDeAtividade.cadastraAtividade("Visita tecnica a coteminas", "BAIXO"
                , "Nenhum");
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre politica", "ALTO",
                "Muitas brigas");
        controladorDeAtividade.cadastraResultado("A1", "Satisfatorio");
        controladorDeAtividade.cadastraResultado("A2", "Tiro,porrada e bomba");
        controladorDeAtividade.cadastraResultado("A1", "Muito bom");

        //Testando remocao feita com sucesso
        assertTrue(controladorDeAtividade.removeResultado("A1", 1));
        assertTrue(controladorDeAtividade.removeResultado("A2", 1));
    }

    @Test
    public void testaListaResultadosComExcecoes() {
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.listaResultados(null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.listaResultados(""));
    }

    @Test
    public void testaListaResultados() {
        controladorDeAtividade.cadastraAtividade("Roda de conversa sobre religiao", "ALTO"
                , "Terceira Guerra Mundial");
        controladorDeAtividade.cadastraResultado("A1", "Muita briga");
        controladorDeAtividade.cadastraResultado("A1", "Discurssao");
        controladorDeAtividade.cadastraResultado("A1", "Bomba");
        assertEquals(controladorDeAtividade.listaResultados("A1"), "Muita briga" +
                " | Discurssao | Bomba");
    }

    @Test
    public void testaGetDuracaoComExcecoes() {
        //Codigo atividade nulo ou vazio
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.getDuracao(null));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.getDuracao(""));

        //Atividade inexistente no sistema
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.getDuracao("A10"));
    }

    @Test
    public void testaGetDuracao() {
        Pesquisa pesquisa = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao", "COM1");
        controladorDeAtividade.cadastraAtividade("Realizacao de rodas de conversa para debater homofobia", "BAIXO"
                , "Algum tipo de comportamento homofobico");

        pesquisa.associaAtividade(controladorDeAtividade.getAtividade("A1"));
        controladorDeAtividade.cadastraItem("A1", "Papel");
        controladorDeAtividade.executaAtividade("A1", 1, 10);
        assertEquals(controladorDeAtividade.getDuracao("A1"), 10);
    }

    @Test
    void testaDefineProximaAtividade() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        controladorDeAtividade.defineProximaAtividade("A2", "A3");
        controladorDeAtividade.defineProximaAtividade("A3", "A4");

        assertEquals(controladorDeAtividade.getAtividade("A1").getSubsequente(), "A2");
        assertEquals(controladorDeAtividade.getAtividade("A2").getSubsequente(), "A3");
        assertEquals(controladorDeAtividade.getAtividade("A3").getSubsequente(), "A4");

        assertTrue(controladorDeAtividade.getAtividade("A1").getPrecedentes().isEmpty());

        assertTrue(controladorDeAtividade.getAtividade("A2").getPrecedentes().contains("A1"));

        assertTrue(controladorDeAtividade.getAtividade("A3").getPrecedentes().contains("A1"));
        assertTrue(controladorDeAtividade.getAtividade("A3").getPrecedentes().contains("A2"));

        assertTrue(controladorDeAtividade.getAtividade("A4").getPrecedentes().contains("A1"));
        assertTrue(controladorDeAtividade.getAtividade("A4").getPrecedentes().contains("A2"));
        assertTrue(controladorDeAtividade.getAtividade("A4").getPrecedentes().contains("A3"));
    }

    @Test
    void testaDefineProximaAtividadeIdPrecedenteNullOuVazio() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("", "A2"));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.defineProximaAtividade(null, "A2"));
    }

    @Test
    void testaDefineProximaAtividadeIdSubsequenteNullOuVazio() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A1", ""));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.defineProximaAtividade("A1", null));
    }

    @Test
    void testaDefineProximaAtividadeNaoExiste() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A2", "A1"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A1", "A2"));
    }

    @Test
    void testaDefineProximaAtividadeJaPossuiSubsequente() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        controladorDeAtividade.defineProximaAtividade("A2", "A3");
        controladorDeAtividade.defineProximaAtividade("A3", "A4");


        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A1", "A3"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A3", "A2"));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A2", "A4"));
    }

    @Test
    void testaDefineProximaAtividadeCriacaoDeLoopsNegada() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A2", "A1"));

        controladorDeAtividade.defineProximaAtividade("A3", "A4");
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.defineProximaAtividade("A4", "A3"));
    }

    @Test
    void testContaProximos() {

        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        controladorDeAtividade.defineProximaAtividade("A2", "A3");
        controladorDeAtividade.defineProximaAtividade("A3", "A4");

        assertEquals(3, controladorDeAtividade.contaProximos("A1"));
        assertEquals(2, controladorDeAtividade.contaProximos("A2"));
        assertEquals(1, controladorDeAtividade.contaProximos("A3"));
        assertEquals(0, controladorDeAtividade.contaProximos("A4"));
    }

    @Test
    void testaContaProximosIdPrecedenteNullOuVazio() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaProximos(""));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.contaProximos(null));
    }

    @Test
    void testaContaProximosAtividadeInexistente() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.contaProximos("A3"));

    }

    @Test
    void testaTiraProximaAtividade() {

        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        controladorDeAtividade.defineProximaAtividade("A2", "A3");
        controladorDeAtividade.defineProximaAtividade("A3", "A4");

        controladorDeAtividade.tiraProximaAtividade("A1");

        assertEquals(controladorDeAtividade.getAtividade("A1").getSubsequente(), "");
        List<String> precedentes = new ArrayList<>();
        precedentes.add("A1");
        precedentes.add("A3");
        assertEquals(controladorDeAtividade.getAtividade("A4").getPrecedentes(), precedentes);

        controladorDeAtividade.tiraProximaAtividade("A2");

        assertEquals(controladorDeAtividade.getAtividade("A2").getSubsequente(), "");
        List<String> precedentes2 = new ArrayList<>();
        precedentes2.add("A1");
        assertEquals(controladorDeAtividade.getAtividade("A4").getPrecedentes(), precedentes2);

    }

    @Test
    void testaTiraProximaAtividadeNullOuVazio() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.tiraProximaAtividade(""));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.tiraProximaAtividade(null));
    }

    @Test
    void testaTiraProximaAtividadeInexistente() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.tiraProximaAtividade("A3"));

    }

    @Test
    void testaPegaProximo() {

        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        controladorDeAtividade.defineProximaAtividade("A2", "A3");
        controladorDeAtividade.defineProximaAtividade("A3", "A4");

        assertEquals("A2", controladorDeAtividade.pegaProximo("A1", 1));
        assertEquals("A3", controladorDeAtividade.pegaProximo("A1", 2));
        assertEquals("A4", controladorDeAtividade.pegaProximo("A1", 3));

        assertEquals("A3", controladorDeAtividade.pegaProximo("A2", 1));
        assertEquals("A4", controladorDeAtividade.pegaProximo("A2", 2));

        assertEquals("A4", controladorDeAtividade.pegaProximo("A3", 1));


    }

    @Test
    void testaPegaProximoNullOuVazio() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaProximo("", 1));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.pegaProximo(null, 1));
    }

    @Test
    void testaPegaProximoEnesimaAtividadeInvalida(){
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaProximo("A1", -1));
        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaProximo("A1", 0));
    }

    @Test
    void testaPegaProximoAtividadeInexistente() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaProximo("A3", 1));
       assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaProximo("A4", 1));

    }

    @Test
    void testaPegaMaiorRiscoAtividades(){

        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "BAIXO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer levantamentos de acontecimentos entre estudantes" +
                        " de CC e Arq/Urb", "BAIXO",
                "Nenhum risco identficado"), "A3");
        assertEquals(controladorDeAtividade.cadastraAtividade("Descobrir motivos que causaram 1119 votos de " +
                        "diferenca", "ALTO",
                "Conversas com pessoas estressadas"), "A4");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");
        controladorDeAtividade.defineProximaAtividade("A2", "A3");
        controladorDeAtividade.defineProximaAtividade("A3", "A4");

        assertEquals("A3", controladorDeAtividade.pegaMaiorRiscoAtividades("A1"));
        assertEquals("A3", controladorDeAtividade.pegaMaiorRiscoAtividades("A2"));
        assertEquals("A4", controladorDeAtividade.pegaMaiorRiscoAtividades("A3"));

    }

    @Test
    void testaPegaMaiorRiscoAtividadesNullOuVazio() {
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaMaiorRiscoAtividades(""));
        assertThrows(NullPointerException.class, () -> controladorDeAtividade.pegaMaiorRiscoAtividades(null));
    }

    @Test
    void testaPegaMaiorRiscoAtividadesAtividadeInexistente(){
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaMaiorRiscoAtividades("A3"));

    }

    @Test
    void testaPegaMaiorRiscoAtividadesSemProximaAtividade(){
        assertEquals(controladorDeAtividade.cadastraAtividade("Fazer rodas de conversa sobre temas diversos", "BAIXO",
                "Nenhum risco identficado"), "A1");
        assertEquals(controladorDeAtividade.cadastraAtividade("Percorrer comunidades carentes", "MEDIO",
                "Algum evento na comunidade que interfira na pesquisa"), "A2");

        controladorDeAtividade.defineProximaAtividade("A1", "A2");

        assertThrows(IllegalArgumentException.class, () -> controladorDeAtividade.pegaMaiorRiscoAtividades("A2"));

    }
}
