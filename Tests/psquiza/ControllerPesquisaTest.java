package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.JavaFileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisaTest {

    private ControllerPesquisa controllerPesquisa;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisador controllerPesquisador;
    private ControllerAssociacoes controllerAssociacoes;

    @BeforeEach
    void constroiControllerPesquisaTest() {
        controllerPesquisa = new ControllerPesquisa();
        controllerAtividade = new ControllerAtividade();
        controllerObjetivo = new ControllerObjetivo();
        controllerPesquisador = new ControllerPesquisador();
        controllerAssociacoes = new ControllerAssociacoes(controllerProblema,controllerObjetivo,controllerPesquisa,controllerAtividade,controllerPesquisador);
        controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao");
    }

    @Test
    void cadastraPesquisaTest() {

        assertEquals("COM2", controllerPesquisa.cadastraPesquisa("Racismo na graduacao de Ciencias da Computacao", "computacao," +
                "racismo,graduacao"));
        assertEquals("SAU1", controllerPesquisa.cadastraPesquisa("Saude Mental dos estudantes da area de exatas", "saude mental, " +
                "estudantes,exatas"));

    }

    @Test
    void cadastraPesquisaDescricaoVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("", "computacao," +
                "homofobia,graduacao"));

        assertThrows(NullPointerException.class, () -> controllerPesquisa.cadastraPesquisa(null, "computacao," +
                "homofobia,graduacao"));
    }

    @Test
    void cadastraPesquisaCampoDeInteresseVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da Computacao", ""));


        assertThrows(NullPointerException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da " +
                "Computacao", null));
    }

    @Test
    void cadastraPesquisaTamanhoCampoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de " +
                "Ciencias" + " da Computacao", "computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " +
                "Computacao", "aa"));
    }

    @Test
    void cadastraPesquisaMaisCamposTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da Computacao", "computacao,homofobia,graduacao,universidade,internet"));
    }

    @Test
    void cadastraPesquisaTamanhoUmCampoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de " +
                "Ciencias" + " da Computacao", "computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " +
                "Computacao", "computacao,aa,homofobia"));
    }

    @Test
    void cadastraPesquisaUmCampoVazioTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " + "Computacao", "computacao,,homofobia,graduacao"));

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " + "Computacao", ",,computacao,homofobia,graduacao"));

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de " +
                "Ciencias" + " da" + " Computacao", " computacao, graduacao, , homofobia"));


    }

    @Test
    void alteraPesquisaDescricaoTest() {
        controllerPesquisa.alteraPesquisa("COM1", "DESCRICAO", "Homofobia na graduacao e na pos de Ciencias da Computacao");
        String msg = "COM1 - Homofobia na graduacao e na pos de Ciencias da Computacao - computacao,homofobia," +
                "graduacao";
        assertEquals(msg, controllerPesquisa.exibePesquisa("COM1"));

    }

    @Test
    void alteraPesquisaCampoTest() {
        controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "computacao,homofobia,graduacao,exatas");
        String msg = "COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao,exatas";
        assertEquals(msg, controllerPesquisa.exibePesquisa("COM1"));

    }

    @Test
    void alteraPesquisaCodigoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("", "CAMPO", "computacao,homofobia," +
                "graduacao,exatas"));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.alteraPesquisa(null, "CAMPO", "computacao,homofobia"));
    }

    @Test
    void alteraPesquisaCodigoInexistenteTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("GRA1", "CAMPO", "computacao," +
                "homofobia," +
                "graduacao,exatas"));

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM2", "CAMPO", "computacao," +
                "homofobia," +
                "graduacao,exatas"));
    }

    @Test
    void alteraPesquisaConteudoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "", "computacao,homofobia," +
                "graduacao,exatas"));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.alteraPesquisa("COM1", null, "computacao,homofobia"));

    }

    @Test
    void alteraPesquisaConteudoNovoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", ""));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", null));

    }

    @Test
    void alteraPesquisaDesativada() {
        controllerPesquisa.encerraPesquisa("COM1", "Falta de Verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "computacao," +
                "homofobia," +
                "graduacao,exatas"));
    }

    @Test
    void alteraPesquisaConteudoNaoPodeAlterarTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CODIGO", "HOM1"));
    }

    @Test
    void alteraPesquisaDescricaoNovaVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "DESCRICAO", ""));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "DESCRICAO", null));

    }

    @Test
    void alteraPesquisaCampoNovoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", ""));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", null));

    }

    @Test
    void alteraPesquisaCampoNovoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "co"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "computacao," +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "computacao,," +
                "homofobia"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.alteraPesquisa("COM1", "CAMPO", "computacao," +
                "homofobia,universidade,graduacao,internet"));
    }

    @Test
    void encerraPesquisa() {
        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertFalse(controllerPesquisa.pesquisaEhAtiva("COM1"));
    }

    @Test
    void encerraPesquisaCodigoVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.encerraPesquisa("", "Falta de verba"));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.encerraPesquisa(null, "Falta de verba"));

    }

    @Test
    void encerraPesquisaNaoEnncontrada() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.encerraPesquisa("UNI1", "Falta de verba"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.encerraPesquisa("AAA1", "Falta de verba"));

    }

    @Test
    void encerraPesquisaMotivoVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.encerraPesquisa("COM1", ""));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.encerraPesquisa("COM1", null));

    }

    @Test
    void encerraPesquisaDesativada() {
        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.encerraPesquisa("COM1", "Falta de verba"));
    }

    @Test
    void ativaPesquisa() {
        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertFalse(controllerPesquisa.pesquisaEhAtiva("COM1"));
        controllerPesquisa.ativaPesquisa("COM1");
        assertTrue(controllerPesquisa.pesquisaEhAtiva("COM1"));
    }

    @Test
    void ativaPesquisaCodigoVazioOuNull() {

        assertThrows(NullPointerException.class, () -> controllerPesquisa.ativaPesquisa(null));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.ativaPesquisa(""));

    }

    @Test
    void ativaPesquisaNaoEncontrada() {

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.ativaPesquisa("UNI1"));

    }

    @Test
    void ativaPesquisaJaAtiva() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.ativaPesquisa("COM1"));
    }


    @Test
    void exibePesquisa() {
        controllerPesquisa.cadastraPesquisa("Racismo na graduacao de Ciencias da Computacao", "computacao," +
                "racismo,graduacao");
        controllerPesquisa.cadastraPesquisa("Saude Mental dos estudantes da area de exatas", "saude mental, " +
                "estudantes,exatas");

        String msg1 = "COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao";
        String msg2 = "COM2 - Racismo na graduacao de Ciencias da Computacao - computacao,racismo,graduacao";
        String msg3 = "SAU1 - Saude Mental dos estudantes da area de exatas - saude mental, estudantes,exatas";

        assertEquals(msg1, controllerPesquisa.exibePesquisa("COM1"));
        assertEquals(msg2, controllerPesquisa.exibePesquisa("COM2"));
        assertEquals(msg3, controllerPesquisa.exibePesquisa("SAU1"));
    }

    @Test
    void exibePesquisaNaoCadastrada() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.exibePesquisa("UNI1"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.exibePesquisa("COM2"));
    }

    @Test
    void pesquisaEhAtiva() {
        assertTrue(controllerPesquisa.pesquisaEhAtiva("COM1"));
        controllerPesquisa.encerraPesquisa("COM1", "Falta de Verba");
        assertFalse(controllerPesquisa.pesquisaEhAtiva("COM1"));

    }

    @Test
    void pesquisaEhAtivaCodigoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.pesquisaEhAtiva(""));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.pesquisaEhAtiva(null));

    }

    @Test
    void pesquisaEhAtivaPesquisaNaoEncontrada() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.pesquisaEhAtiva("COM2"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.pesquisaEhAtiva("UNI2"));

    }

    @Test
    void buscaTest() {

        controllerPesquisa.cadastraPesquisa("Racismo na graduacao de Ciencias da Computacao", "computacao," +
                "racismo,graduacao");
        controllerPesquisa.cadastraPesquisa("Saude Mental dos estudantes da area de exatas", "saude mental, " +
                "estudantes,exatas");

        List<String> resultado = new ArrayList<>();

        resultado.add("SAU1: Saude Mental dos estudantes da area de exatas");
        resultado.add("SAU1: saude mental, estudantes,exatas");
        resultado.add("COM2: Racismo na graduacao de Ciencias da Computacao");
        resultado.add("COM2: computacao,racismo,graduacao");
        resultado.add("COM1: Homofobia na graduacao de Ciencias da Computacao");
        resultado.add("COM1: computacao,homofobia,graduacao");


        assertEquals(resultado, controllerPesquisa.busca("a"));


        List<String> resultado2 = new ArrayList<>();

        resultado2.add("COM2: Racismo na graduacao de Ciencias da Computacao");
        resultado2.add("COM2: computacao,racismo,graduacao");
        resultado2.add("COM1: Homofobia na graduacao de Ciencias da Computacao");
        resultado2.add("COM1: computacao,homofobia,graduacao");


        assertEquals(resultado2, controllerPesquisa.busca("graduacao"));


        List<String> resultado3 = new ArrayList<>();

        resultado3.add("SAU1: Saude Mental dos estudantes da area de exatas");


        assertEquals(resultado3, controllerPesquisa.busca("Mental"));


        List<String> resultado4 = new ArrayList<>();

        resultado4.add("COM2: Racismo na graduacao de Ciencias da Computacao");


        assertEquals(resultado4, controllerPesquisa.busca("Racismo"));

        List<String> resultado5 = new ArrayList<>();

        resultado5.add("COM1: Homofobia na graduacao de Ciencias da Computacao");


        assertEquals(resultado5, controllerPesquisa.busca("Homofobia"));
    }

    @Test
    void buscaTestTermoVaziouOuNull(){
        assertThrows(IllegalArgumentException.class, ()-> controllerPesquisa.busca(""));
        assertThrows(NullPointerException.class, ()-> controllerPesquisa.busca(null));
    }

    @Test
    void associaProblemaEmPesquisa(){
        controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");


        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3, "P1");
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");


        assertTrue(controllerPesquisa.associaProblemaEmPesquisa("COM2", problema1));
        assertTrue(controllerPesquisa.associaProblemaEmPesquisa("COM1", problema1));
        assertFalse(controllerPesquisa.associaProblemaEmPesquisa("COM2", problema1));
    }

    @Test
    void associaProblemaEmPesquisaNaoCadastrada() {
        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3, "P1");

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblemaEmPesquisa("UNI1", problema1));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblemaEmPesquisa("AAA1", problema1));
    }

    @Test
    void associaProblemaEmPesquisaDesativada() {
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");

        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblemaEmPesquisa("COM1", problema2));
    }

    @Test
    void associaProblemaEmPesquisaJaAssociada() {
        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3, "P1");
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");

        controllerPesquisa.associaProblemaEmPesquisa("COM1", problema1);
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblemaEmPesquisa("COM1", problema2));
    }

    @Test
    void desassociaProblemaEmPesquisa() {
        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3, "P1");

        controllerPesquisa.associaProblemaEmPesquisa("COM1", problema1);

        assertTrue(controllerPesquisa.desassociaProblemaEmPesquisa("COM1"));
        assertFalse(controllerPesquisa.desassociaProblemaEmPesquisa("COM1"));
    }

    void desassociaProblemaEmPesquisaNaoCadastrada(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaProblemaEmPesquisa("UNI1"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaProblemaEmPesquisa("AAA1"));

    }

    @Test
    void desassociaProblemaEmPesquisaDesativada() {
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");

        controllerPesquisa.associaProblemaEmPesquisa("COM1", problema2);
        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaProblemaEmPesquisa("COM1"));
    }

    @Test
    void associaObjetivoEmPesquisa() {
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5, "O1");
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        assertTrue(this.controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo1));

        assertFalse(objetivo2.getAssociado());
        assertTrue(this.controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo2));
        assertTrue(objetivo2.getAssociado());

        assertFalse(this.controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo1));
    }

    @Test
    void associaObjetivoEmPesquisaNaoCadastrada() {
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5, "O1");

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivoEmPesquisa("UNI1", objetivo1));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivoEmPesquisa("AAA1", objetivo1));
    }

    @Test
    void associaObjetivoEmPesquisaDesativada() {
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo2));
    }

    @Test
    void associaObjetivoJaAssociado(){
        controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");


        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5, "O1");

        controllerPesquisa.associaObjetivoEmPesquisa("COM2", objetivo1);
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo1));
    }

    @Test
    void desassociaObjetivoEmPesquisa() {
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5, "O1");
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        this.controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo1);
        assertTrue(objetivo1.getAssociado());
        assertTrue(this.controllerPesquisa.desassociaObjetivoEmPesquisa("COM1", objetivo1));
        assertFalse(objetivo1.getAssociado());
        assertFalse(this.controllerPesquisa.desassociaObjetivoEmPesquisa("COM1", objetivo2));
    }

    @Test
    void desassociaObjetivoEmPesquisaNaoCadastrada() {
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5, "O1");

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaObjetivoEmPesquisa("UNI1", objetivo1));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaObjetivoEmPesquisa("AAA1", objetivo1));
    }

    @Test
    void desassociaObjetivoEmPesquisaDesativada() {
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaObjetivoEmPesquisa("COM1", objetivo2));
    }

    @Test
    void listaPesquisas(){
        controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");
        controllerPesquisa.cadastraPesquisa("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.", "eleicao");
        controllerPesquisa.cadastraPesquisa("Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.", "fermentacao, cerveja");
        controllerPesquisa.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil");


        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3, "P1");
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");
        Problema problema3 = new Problema("A extrema falta de paciencia durante a criacao de testes da junit nos estudantes da graduacao de computacao da ufcg", 3, "P3");

        controllerPesquisa.associaProblemaEmPesquisa("COM1", problema1);
        controllerPesquisa.associaProblemaEmPesquisa("COM2", problema2);
        controllerPesquisa.associaProblemaEmPesquisa("ELE1", problema3);
        controllerPesquisa.associaProblemaEmPesquisa("PSI1", problema2);

        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5, "O1");
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");
        Objetivo objetivo3 = new Objetivo("ESPECIFICO", "Gerenciar melhor as notas dos alunos", 3, 4, "O3");
        Objetivo objetivo4 = new Objetivo("GERAL", "gerenceiar melhor a distribuicao dos alunos nas aulas de lp2", 5, 5, "O4");
        Objetivo objetivo5 = new Objetivo("ESPECIFICO", "Encontarar o melhor resultado possivel com as leveduras nao-Saccharomyces.", 4, 3, "O5");
        Objetivo objetivo6 = new Objetivo("GERAL", "Melhorar a extracao de caracteristicas significativas nas eleicoes brasileiras.", 3, 4, "O6");
        Objetivo objetivo7 = new Objetivo("GERAL", "Acabou minha criatividade", 5, 5, "O7");

        controllerPesquisa.associaObjetivoEmPesquisa("COM1", objetivo1);
        controllerPesquisa.associaObjetivoEmPesquisa("ELE1", objetivo2);
        controllerPesquisa.associaObjetivoEmPesquisa("PSI1", objetivo3);
        controllerPesquisa.associaObjetivoEmPesquisa("COM2", objetivo4);
        controllerPesquisa.associaObjetivoEmPesquisa("COM2", objetivo5);
        controllerPesquisa.associaObjetivoEmPesquisa("PSI1", objetivo6);
        controllerPesquisa.associaObjetivoEmPesquisa("COM2", objetivo7);


        assertEquals("ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao " +
                "| COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo " +
                "| PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil " +
                "| COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao " +
                "| FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja"
                , controllerPesquisa.listaPesquisas("PROBLEMA"));


        assertEquals("COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo " +
                        "| PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil " +
                        "| COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao " +
                        "| ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao " +
                        "| FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja"
                , controllerPesquisa.listaPesquisas("OBJETIVOS"));
        assertEquals("PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil " +
                "| FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja " +
                "| ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao " +
                "| COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo " +
                "| COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao"
                , controllerPesquisa.listaPesquisas("PESQUISA"));
    }

    @Test
    void listaPesquisasOrdemVaziaOuNull() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.listaPesquisas(""));
        assertThrows(NullPointerException.class, () -> controllerPesquisa.listaPesquisas(null));
    }

    @Test
    void associaAtividadeEmPesquisaComExcecoesTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertThrows(NullPointerException.class,()->  controllerPesquisa.associaAtividadeEmPesquisa(null,atividade));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.associaAtividadeEmPesquisa("",atividade));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.associaAtividadeEmPesquisa("LEI1",atividade));
        controllerPesquisa.encerraPesquisa("COM1","Corte de verbas");
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.associaAtividadeEmPesquisa("COM1",atividade));

    }

    @Test
    void associaAtividadeTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertTrue(controllerPesquisa.associaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void associaAtividadeJaAssociadaTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        controllerPesquisa.associaAtividadeEmPesquisa("COM1",atividade);
        assertFalse(controllerPesquisa.associaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void desassociaAtividadeEmPesquisaComExcecoesTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertThrows(NullPointerException.class,()->  controllerPesquisa.desassociaAtividadeEmPesquisa(null,atividade));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.desassociaAtividadeEmPesquisa("",atividade));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.desassociaAtividadeEmPesquisa("LEI1",atividade));
        controllerPesquisa.encerraPesquisa("COM1","Corte de verbas");
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.desassociaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void desassociaAtividadeTest() {

        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        controllerPesquisa.associaAtividadeEmPesquisa("COM1",atividade);
        assertTrue(controllerPesquisa.desassociaAtividadeEmPesquisa("COM1",atividade));

    }

    @Test
    void desassociaAtividadeNaoAssociadaTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertFalse(controllerPesquisa.desassociaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void gravaResumoTest() throws IOException {
        controllerPesquisa.cadastraPesquisa("Pesquisa teste para checar se o gravaResumo esta em dia", "Resumos");
        controllerAtividade.cadastraAtividade("Atividade teste para checar se o gravaResumo esta em dia","BAIXO","Nao tem muito erro ta ligado");
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Fazer o gravaResumoTest() ficar 100%.",2,5);
        controllerAssociacoes.associaObjetivo("RES1", "O1");
        controllerAssociacoes.associaAtividade("RES1", "A1");
        controllerPesquisa.gravaResumo("RES1");
        assertEquals("\"- Pesquisa: RES1 - Pesquisa teste para checar se o gravaResumo esta em dia - Resumos\n" +
                "     - Objetivos:\n" +
                "        - O1 - ESPECIFICO - Fazer o gravaResumoTest() ficar 100%. - 7\n" +
                "    - Atividades:\n" +
                "        - Atividade teste para checar se o gravaResumo esta em dia (BAIXO - Nao tem muito erro ta ligado)\"\n",JavaFileUtil.getFileContent("./_RES1.txt"));
    }

    @Test
    void gravaResumoPesquisaVaziaOuNula(){
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.gravaResumo(""));
        assertThrows(NullPointerException.class,()-> controllerPesquisa.gravaResumo(null));
    }

    @Test
    void gravaResumoPesquisaNaoEncontrada(){
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.gravaResumo("WTF1"));
    }

    @Test
    void gravaResultadoTest() throws IOException {
        controllerPesquisa.cadastraPesquisa("Pesquisa teste para checar se o gravaResultado esta em dia", "Teste de Resultados");
        controllerAtividade.cadastraAtividade("Atividade teste para checar se o gravaResultado esta em dia","BAIXO","Erro baixo envolvido");
        controllerAssociacoes.associaAtividade("TES1", "A1");
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Fazer o gravaResultadoTest() ficar 100%.",2,5);
        controllerAssociacoes.associaObjetivo("TES1", "O1");
        controllerPesquisador.cadastraPesquisador("Fernando", "estudante", "Codador paciente na aula de lp2", "fernando@ccc.ufcg.com","https://facebook.com/photos");
        controllerAssociacoes.associaPesquisador("TES1", "fernando@ccc.ufcg.com");
        controllerPesquisa.gravaResultados("TES1");
    }

    @Test
    void gravaResultadoPesquisaVaziaOuNula(){
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.gravaResultados(""));
        assertThrows(NullPointerException.class,()-> controllerPesquisa.gravaResultados(null));
    }

    @Test
    void gravaResultadosPesquisaNaoEncontrada(){
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.gravaResultados("KKK2"));

    @Test
    void testaConfiguraEstrategiaComExcecoes() {
        //Testando valor nulo e vazio para estrategia
        assertThrows(NullPointerException.class, () -> controllerPesquisa.configuraEstrategia(null));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.configuraEstrategia(""));

        //Testando valores diferentes de : MAIS_ANTIGA,MENOS_PENDENCIAS,MAIOR_RISCO,MAIOR_DURACAO
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.configuraEstrategia("MAIOR_CHATICE"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.configuraEstrategia("MENOS_TEMPO"));

    }

    @Test
    void testaConfiguraEstrategia() {
        //Testando MAIS_ANTIGA
        teste.configuraEstrategia("MAIS_ANTIGA");
        assertEquals(controllerPesquisa.getEstrategia(), "MAIS_ANTIGA");

        //Testando MENOS_PENDENCIAS
        teste.configuraEstrategia("MENOS_PENDENCIAS");
        assertEquals(controllerPesquisa.getEstrategia(), "MENOS_PENDENCIAS");

        //Testando MAIOR_RISCO
        teste.configuraEstrategia("MAIOR_RISCO");
        assertEquals(controllerPesquisa.getEstrategia(), "MAIOR_RISCO");

        //Testando MAIOR_DURACAO
        teste.configuraEstrategia("MAIOR_DURACAO");
        assertEquals(controllerPesquisa.getEstrategia(), "MAIOR_DURACAO");
    }

    @Test
    void testaProximaAtividadeComExcecoes() {
        //Testando valor nulo e vazio para codigo da Pesquisa
        assertThrows(NullPointerException.class, () -> controllerPesquisa.proximaAtividade(null));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.proximaAtividade(""));

        //Testando pesquisa inexistente no sistema
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.proximaAtividade("COM1"));

        //Testando pesquisa desativada
        controllerPesquisa.cadastraPesquisa("Desenvolvimento de estudos a partir de interacoes com pacientes do SAE", "psicologia,saude sexual,soropositivos");
        controllerPesquisa.encerraPesquisa("PSI1", "Falta de alunos para dar continuidade a pesquisa");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.proximaAtividade("PSI1"));
    }

}