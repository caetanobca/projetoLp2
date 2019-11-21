package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AtividadeTest {

    private Atividade atividade1;
    private Atividade atividade2;
    private Atividade atividade3;
    private Atividade atividade4;

    @BeforeEach
    public void criaAtividade() {
        this.atividade1 = new Atividade("A1", "Visita tecnica a uma empresa de calcados",
                "BAIXO", "Algum aluno pode se machucar ao operar, sem autorizacao previa, algum equipamento");
        this.atividade2 = new Atividade("A2", "Visita a postos de saude de comunidades carentes",
                "BAIXO", "Algum problema interno da comunidade causar um dano colateral aos alunos");
    }

    @Test
    public void testaConstroiAtividade() {
        Atividade atividadeLegal = new Atividade("A3", "Visita tecnica a um engenho de cachaca",
                "BAIXO", "Algum aluno ficar embreagado");
        Atividade atividadePsicologica = new Atividade("A4", "Elaboracao de rodas de conversa com o tema homofobia",
                "BAIXO", "Alguma opiniao adversa que possa acarretar desavencas");
    }

    @Test
    public void testaConstroiAtividadeComExcecoes() {
        assertThrows(NullPointerException.class, () -> new Atividade("A8", null,
                "Vistoriar instalacoes prediais antigas da cidade", "Alguma instalacao possuir locais que nao " +
                "suportem o peso"));
        assertThrows(IllegalArgumentException.class, () -> new Atividade("A4", "",
                "Vistoriar instalacoes prediais antigas da cidade", "Alguma instalacao possuir locais que nao " +
                "suportem o peso"));
        assertThrows(NullPointerException.class, () -> new Atividade("A4", "Vistoriar instalacoes prediais antigas da" +
                " cidade",
                null, "Alguma instalacao possuir locais que nao suportem o peso"));
        assertThrows(IllegalArgumentException.class, () -> new Atividade("A3", "Comer alimentos em prazos de validade" +
                " proximos ao limite",
                "", "Algum alimento ja estar estragado"));
        assertThrows(NullPointerException.class, () -> new Atividade("A7", "Visitar Hospitais e conversar com " +
                "pacientes",
                "BAIXO", null));
        assertThrows(IllegalArgumentException.class, () -> new Atividade("A7", "Visitar Hospitais e conversar com " +
                "pacientes",
                "BAIXO", ""));
    }

    @Test
    public void testaCadastraItem() {
        atividade1.cadastraItem("Monitoramento Facebook");
        atividade1.cadastraItem("Degustacao de cervejas artesanais");
        atividade1.cadastraItem("Degustacao de cachaca");
        assertEquals(atividade1.toString(), "Visita tecnica a uma empresa de calcados (BAIXO - Algum aluno pode se " +
                "machucar ao operar," +
                " sem autorizacao previa, algum equipamento) | PENDENTE - Monitoramento Facebook | PENDENTE - " +
                "Degustacao de cervejas artesanais " +
                "| PENDENTE - Degustacao de cachaca");
    }

    @Test
    public void testaCadastraItemComExcecao() {
        assertThrows(NullPointerException.class, () -> atividade1.cadastraItem(null));
        assertThrows(IllegalArgumentException.class, () -> atividade2.cadastraItem(""));
    }

    @Test
    public void testaItensPendentes() {
        assertEquals(atividade2.contaItensPendentes(), 0);
        atividade2.cadastraItem("Degustacao de cerveja artesanal");
        atividade2.cadastraItem("Monitoramento de Whatsapp");
        atividade2.cadastraItem("Monitoramento de Facebook");
        assertEquals(atividade2.contaItensPendentes(), 3);
    }

    @Test
    public void testaItensRealizados() {
        assertEquals(atividade1.contaItensRealizados(), 0);
        assertEquals(atividade2.contaItensRealizados(), 0);
    }

    @Test
    public void testaToString() {
        atividade1.cadastraItem("Monitoramento Facebook");
        atividade1.cadastraItem("Degustacao de cervejas artesanais");
        assertEquals(atividade1.toString(), "Visita tecnica a uma empresa de calcados (BAIXO - Algum aluno pode se " +
                "machucar ao operar," +
                " sem autorizacao previa, algum equipamento) | PENDENTE - Monitoramento Facebook | PENDENTE - " +
                "Degustacao de cervejas artesanais");
    }

    @Test
    public void testaEqualsAtividadesIguais() {
        assertEquals(atividade1, new Atividade("A1", "Monitoramento Tinder", "BAIXO",
                "Nenhum risco foi identificado"));
        assertEquals(atividade2, new Atividade("A2", "Visita a postos de saude de comunidades carentes",
                "BAIXO", "Algum problema interno da comunidade causar um dano colateral aos alunos"));
    }

    @Test
    public void testaEqualsAtividadesDiferentes() {
        assertNotEquals(atividade1, new Atividade("A4", "Visita tecnica a uma empresa de calcados",
                "BAIXO", "Algum aluno pode se machucar ao operar, sem autorizacao previa, algum equipamento"));
        assertNotEquals(atividade2, new Atividade("A7", "Monitoramento do Tinder",
                "BAIXO", "Nenhum risco foi identificado"));
    }

    @Test
    public void testaHashCodeIgual() {
        Atividade atividadeIdIgual = new Atividade("A1", "Visita a instalacao Hospitalar",
                "MEDIO", "Alguma contaminacao hospitalar ser adquirida");
        Atividade atividadeTudoIgual = new Atividade("A2", "Visita a postos de saude de comunidades carentes",
                "BAIXO", "Algum problema interno da comunidade causar um dano colateral aos alunos");
        assertTrue(atividade1.hashCode() == atividadeIdIgual.hashCode());
        assertTrue(atividadeTudoIgual.hashCode() == atividade2.hashCode());
    }

    @Test
    public void testaHashCodeDiferente() {
        Atividade atividadeSoIdDiferente = new Atividade("A4", "Visita a postos de saude de comunidades carentes",
                "BAIXO", "Algum problema interno da comunidade causar um dano colateral aos alunos");
        Atividade atividadeTudoDiferente = new Atividade("A10", "Degustacao de Frutos do Mar",
                "MEDIO", "Algum aluno ser alergico a frutos do mar e nao saber");
        assertFalse(atividade2.hashCode() == atividadeSoIdDiferente.hashCode());
        assertFalse(atividade1.hashCode() == atividadeTudoDiferente.hashCode());
    }

    @Test
    public void testaExecutaAtividadeComExcecoes() {
        Pesquisa pesquisa = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao", "COM1");

        pesquisa.associaAtividade(atividade1);
        atividade1.cadastraItem("Mapa da empresa");

        //Testanto valor de item nulo ou negativo

        assertThrows(IllegalArgumentException.class, () -> atividade1.executaAtividade(0, 10));
        assertThrows(IllegalArgumentException.class, () -> atividade1.executaAtividade(-1, 6));

        //Testando valor de duracao nulo ou negativo

        assertThrows(IllegalArgumentException.class, () -> atividade1.executaAtividade(1, 0));
        assertThrows(IllegalArgumentException.class, () -> atividade1.executaAtividade(1, -1));

        //Testando item ja executado

        atividade1.executaAtividade(1, 10);
        assertThrows(IllegalArgumentException.class, () -> atividade1.executaAtividade(1, 10));

        //Testando item nao encontrado

        assertThrows(IllegalArgumentException.class, () -> atividade1.executaAtividade(2, 10));

        //Testando atividade sem associacoes a pesquisa

        assertThrows(IllegalArgumentException.class, () -> atividade2.executaAtividade(1, 10));

    }

    @Test
    public void testaExecutaAtividade() {
        Pesquisa pesquisa = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao", "COM1");

        pesquisa.associaAtividade(atividade1);
        atividade1.cadastraItem("Mapa da empresa");
        atividade1.executaAtividade(1, 20);
        assertEquals(atividade1.contaItensRealizados(), 1);
    }

    @Test
    public void testaCadastraResultado() {

        assertEquals(atividade1.cadastraResultado("Satisfatorio"), 1);
        assertEquals(atividade1.cadastraResultado("Horrivel"), 2);
        assertEquals(atividade2.cadastraResultado("Regular"), 1);
    }

    @Test
    public void testaRemoveResultadoComExcecoes() {
        //Valor nulo ou negativo para resultado
        assertThrows(IllegalArgumentException.class, () -> atividade1.removeResultado(-1));
        assertThrows(IllegalArgumentException.class, () -> atividade2.removeResultado(0));

        //Resultado inexistente no sistema
        assertThrows(IllegalArgumentException.class, () -> atividade1.removeResultado(4));
    }

    @Test
    public void testaRemoveResultado() {
        atividade1.cadastraResultado("Satisfatorio");
        atividade1.cadastraResultado("Uma lastima");
        atividade1.cadastraResultado("Cenas lamentaveis");
        assertTrue(atividade1.removeResultado(2));
        assertTrue(atividade1.removeResultado(1));
        assertTrue(atividade1.removeResultado(3));
    }

    @Test
    public void testaListaResultados() {
        atividade1.cadastraResultado("Muita briga");
        atividade1.cadastraResultado("Discurssao");
        atividade1.cadastraResultado("Bomba");
        assertEquals(atividade1.listaResultados(), "Muita briga" +
                " | Discurssao | Bomba");
    }

    @Test
    public void testaGetDuracao() {
        Pesquisa pesquisa = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao", "COM1");

        pesquisa.associaAtividade(atividade1);
        atividade1.cadastraItem("Mapa da empresa");
        atividade1.executaAtividade(1, 20);
        assertEquals(atividade1.getDuracao(), 20);
    }

    @Test
    void testAdicionaPrecedente() {
        atividade1.adicionaPrecedente("A2");

        assertTrue(atividade1.getPrecedentes().contains("A2"));
        this.atividade3 = new Atividade("A3", "Fazer levantamentos de acontecimentos entre estudantes" +
                " de CC e Arq/Urb", "BAIXO", "Nenhum risco identficado");

        this.atividade4 = new Atividade("A4", "Descobrir motivos que causaram 1119 votos de diferenca", "ALTO",
                "Conversas com pessoas estressadas");

        atividade1.adicionaPrecedente("A3");
        assertTrue(atividade1.getPrecedentes().contains("A3"));
        atividade1.adicionaPrecedente("A4");
        assertTrue(atividade1.getPrecedentes().contains("A4"));
    }

    @Test
    void testGetPrecedentes() {

        this.atividade3 = new Atividade("A3", "Fazer levantamentos de acontecimentos entre estudantes" +
                " de CC e Arq/Urb", "BAIXO", "Nenhum risco identficado");

        this.atividade4 = new Atividade("A4", "Descobrir motivos que causaram 1119 votos de diferenca", "ALTO",
                "Conversas com pessoas estressadas");

        List<String> precedentesTeste = new ArrayList<>();

        atividade1.adicionaPrecedente("A2");
        precedentesTeste.add("A2");
        atividade1.adicionaPrecedente("A3");
        precedentesTeste.add("A3");
        atividade1.adicionaPrecedente("A4");
        precedentesTeste.add("A4");

        List<String> precedentes = atividade1.getPrecedentes();

        assertEquals(precedentes,precedentesTeste);

    }

    @Test
    void testeSetEgetSubsequente(){
        this.atividade3 = new Atividade("A3", "Fazer levantamentos de acontecimentos entre estudantes" +
                " de CC e Arq/Urb", "BAIXO", "Nenhum risco identficado");

        this.atividade4 = new Atividade("A4", "Descobrir motivos que causaram 1119 votos de diferenca", "ALTO",
                "Conversas com pessoas estressadas");

        atividade1.setSubsequente("A2");
        atividade3.setSubsequente("A4");

        assertEquals("A2", atividade1.getSubsequente());
        assertEquals("A4", atividade3.getSubsequente());

    }

    @Test
    void testeRemovePrecedente(){
        this.atividade3 = new Atividade("A3", "Fazer levantamentos de acontecimentos entre estudantes" +
                " de CC e Arq/Urb", "BAIXO", "Nenhum risco identficado");

        this.atividade4 = new Atividade("A4", "Descobrir motivos que causaram 1119 votos de diferenca", "ALTO",
                "Conversas com pessoas estressadas");

        atividade1.adicionaPrecedente("A2");
        assertTrue(atividade1.getPrecedentes().contains("A2"));
        atividade1.adicionaPrecedente("A3");
        assertTrue(atividade1.getPrecedentes().contains("A3"));
        atividade1.adicionaPrecedente("A4");
        assertTrue(atividade1.getPrecedentes().contains("A4"));

        atividade1.removePrecedente("A2");
        assertFalse(atividade1.getPrecedentes().contains("A2"));
        atividade1.removePrecedente("A3");
        assertFalse(atividade1.getPrecedentes().contains("A3"));
        atividade1.removePrecedente("A4");
        assertFalse(atividade1.getPrecedentes().contains("A4"));


    }


}