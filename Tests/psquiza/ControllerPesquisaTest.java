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

    @BeforeEach
    void constroiControllerPesquisaTest() {
        controllerAtividade = new ControllerAtividade();
        controllerObjetivo = new ControllerObjetivo();
        controllerPesquisador = new ControllerPesquisador();
        controllerProblema = new ControllerProblema();
        controllerPesquisa = new ControllerPesquisa(this.controllerObjetivo.getObjetivos(), this.controllerProblema.getProblemas(),
                this.controllerAtividade.getAtividades(), this.controllerPesquisador.getPesquisadores());
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

        controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);
        controllerProblema.cadastraProblema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4);


        assertTrue(controllerPesquisa.associaProblema("COM2", "P1"));
        assertTrue(controllerPesquisa.associaProblema("COM1", "P1"));
        assertFalse(controllerPesquisa.associaProblema("COM2", "P1"));
    }

    @Test
    void associaProblemaEmPesquisaNaoCadastrada() {
        controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblema("UNI1", "P1"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblema("AAA1", "P1"));
    }

    @Test
    void associaProblemaEmPesquisaDesativada() {
        controllerProblema.cadastraProblema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4);

        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblema("COM1", "P1"));
    }

    @Test
    void associaProblemaEmPesquisaJaAssociada() {
        controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);
        controllerProblema.cadastraProblema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4);

        controllerPesquisa.associaProblema("COM1", "P1");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaProblema("COM1", "P2"));
    }

    @Test
    void desassociaProblemaEmPesquisa() {
        controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);

        controllerPesquisa.associaProblema("COM1", "P1");

        assertTrue(controllerPesquisa.desassociaProblema("COM1"));
        assertFalse(controllerPesquisa.desassociaProblema("COM1"));
    }

    void desassociaProblemaEmPesquisaNaoCadastrada(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaProblema("UNI1"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaProblema("AAA1"));

    }

    @Test
    void desassociaProblemaEmPesquisaDesativada() {
        controllerProblema.cadastraProblema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4);

        controllerPesquisa.associaProblema("COM1", "P2");
        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaProblema("COM1"));
    }

    @Test
    void associaObjetivoEmPesquisa() {
        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5);
        this.controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);


        assertTrue(this.controllerPesquisa.associaObjetivo("COM1", "O1"));

        assertFalse(this.controllerObjetivo.getObjetivo("O2").getAssociado());
        assertTrue(this.controllerPesquisa.associaObjetivo("COM1", "O2"));
        assertTrue(this.controllerObjetivo.getObjetivo("O2").getAssociado());

        assertFalse(this.controllerPesquisa.associaObjetivo("COM1", "O1"));
    }

    @Test
    void associaObjetivoEmPesquisaNaoCadastrada() {
        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5);

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivo("UNI1", "O1"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivo("AAA1", "O1"));
    }

    @Test
    void associaObjetivoEmPesquisaDesativada() {
        this.controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);

        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivo("COM1", "O1"));
    }

    @Test
    void associaObjetivoJaAssociado(){
        controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");


        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5);

        controllerPesquisa.associaObjetivo("COM2", "O1");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.associaObjetivo("COM1", "O1"));
    }

    @Test
    void desassociaObjetivoEmPesquisa() {
        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5);
        this.controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);

        this.controllerPesquisa.associaObjetivo("COM1", "O1");
        assertTrue(this.controllerObjetivo.getObjetivo("O1").getAssociado());
        assertTrue(this.controllerPesquisa.desassociaObjetivo("COM1", "O1"));
        assertFalse(this.controllerObjetivo.getObjetivo("O1").getAssociado());
        assertFalse(this.controllerPesquisa.desassociaObjetivo("COM1", "O2"));
    }

    @Test
    void desassociaObjetivoEmPesquisaNaoCadastrada() {
        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5);

        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaObjetivo("UNI1", "O1"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaObjetivo("AAA1", "O1"));
    }

    @Test
    void desassociaObjetivoEmPesquisaDesativada() {
        this.controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);

        controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.desassociaObjetivo("COM1", "O1"));
    }

    @Test
    void listaPesquisas(){
        controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");
        controllerPesquisa.cadastraPesquisa("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.", "eleicao");
        controllerPesquisa.cadastraPesquisa("Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.", "fermentacao, cerveja");
        controllerPesquisa.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil");

        controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);
        controllerProblema.cadastraProblema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4);
        controllerProblema.cadastraProblema("A extrema falta de paciencia durante a criacao de testes da junit nos estudantes da graduacao de computacao da ufcg", 3);

        controllerPesquisa.associaProblema("COM1", "P1");
        controllerPesquisa.associaProblema("COM2", "P2");
        controllerPesquisa.associaProblema("ELE1", "P3");
        controllerPesquisa.associaProblema("PSI1", "P2");

        controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao", 5, 5);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Gerenciar melhor as notas dos alunos", 3, 4);
        controllerObjetivo.cadastraObjetivo("GERAL", "gerenceiar melhor a distribuicao dos alunos nas aulas de lp2", 5, 5);
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Encontarar o melhor resultado possivel com as leveduras nao-Saccharomyces.", 4, 3);
        controllerObjetivo.cadastraObjetivo("GERAL", "Melhorar a extracao de caracteristicas significativas nas eleicoes brasileiras.", 3, 4);
        controllerObjetivo.cadastraObjetivo("GERAL", "Acabou minha criatividade", 5, 5);

        controllerPesquisa.associaObjetivo("COM1", "O1");
        controllerPesquisa.associaObjetivo("ELE1", "O2");
        controllerPesquisa.associaObjetivo("PSI1", "O3");
        controllerPesquisa.associaObjetivo("COM2", "O4");
        controllerPesquisa.associaObjetivo("COM2", "O5");
        controllerPesquisa.associaObjetivo("PSI1", "O6");
        controllerPesquisa.associaObjetivo("COM2", "O7");


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
        controllerAtividade.cadastraAtividade("rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertThrows(NullPointerException.class,()->  controllerPesquisa.associaAtividade(null,"A1"));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.associaAtividade("","A1"));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.associaAtividade("LEI1","A1"));
        controllerPesquisa.encerraPesquisa("COM1","Corte de verbas");
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.associaAtividade("COM1","A1"));

    }

    @Test
    void associaAtividadeTest() {
        controllerAtividade.cadastraAtividade("rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertTrue(controllerPesquisa.associaAtividade("COM1","A1"));
    }

    @Test
    void associaAtividadeJaAssociadaTest() {
        controllerAtividade.cadastraAtividade("rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        controllerPesquisa.associaAtividade("COM1","A1");
        assertFalse(controllerPesquisa.associaAtividade("COM1","A1" ));
    }

    @Test
    void desassociaAtividadeEmPesquisaComExcecoesTest() {
        controllerAtividade.cadastraAtividade("rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertThrows(NullPointerException.class,()->  controllerPesquisa.desassociaAtividade(null,"A1"));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.desassociaAtividade("","A1"));
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.desassociaAtividade("LEI1","A1"));
        controllerPesquisa.encerraPesquisa("COM1","Corte de verbas");
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.desassociaAtividade("COM1","A1"));
    }

    @Test
    void desassociaAtividadeTest() {
        controllerAtividade.cadastraAtividade("rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        controllerPesquisa.associaAtividade("COM1","A1");
        assertTrue(controllerPesquisa.desassociaAtividade("COM1","A1"));

    }

    @Test
    void desassociaAtividadeNaoAssociadaTest() {
        controllerAtividade.cadastraAtividade("rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertFalse(controllerPesquisa.desassociaAtividade("COM1","A1"));
    }

    @Test
    void gravaResumoTest() throws IOException {
        controllerPesquisa.cadastraPesquisa("Pesquisa teste para checar se o gravaResumo esta em dia", "Resumos");
        controllerAtividade.cadastraAtividade("Atividade teste para checar se o gravaResumo esta em dia","BAIXO","Nao tem muito erro ta ligado");
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Fazer o gravaResumoTest() ficar 100%.",2,5);

        controllerPesquisa.associaObjetivo("RES1", "O1");
        controllerPesquisa.associaAtividade("RES1", "A1");

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
        controllerPesquisa.associaAtividade("TES1", "A1");
        controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Fazer o gravaResultadoTest() ficar 100%.",2,5);
        controllerPesquisa.associaObjetivo("TES1", "O1");
        controllerPesquisador.cadastraPesquisador("Fernando", "estudante", "Codador paciente na aula de lp2", "fernando@ccc.ufcg.com","https://facebook.com/photos");
        controllerPesquisa.associaPesquisador("TES1", "fernando@ccc.ufcg.com");
        controllerPesquisa.gravaResultados("TES1");

        assertEquals("",JavaFileUtil.getFileContent("./TES1-Resultados.txt"));

    }

    @Test
    void gravaResultadoPesquisaVaziaOuNula(){
        assertThrows(IllegalArgumentException.class,()-> controllerPesquisa.gravaResultados(""));
        assertThrows(NullPointerException.class,()-> controllerPesquisa.gravaResultados(null));
    }

    @Test
    void gravaResultadosPesquisaNaoEncontrada() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.gravaResultados("KKK2"));
    }

    @Test
    void testaConfiguraEstrategiaComExcecoes() {
        //Testando valor nulo e vazio para estrategia
        assertThrows(NullPointerException.class, () -> controllerPesquisa.configuraEstrategia(null));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.configuraEstrategia(""));

        //Testando valores diferentes de : MAIS_ANTIGA,MENOS_PENDENCIAS,MAIOR_RISCO,MAIOR_DURACAO
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.configuraEstrategia("MAIOR_CHATICE"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisa.configuraEstrategia("MENOS_TEMPO"));

    }

//    @Test
//    void testaConfiguraEstrategia() {
//        //Testando MAIS_ANTIGA
//        teste.configuraEstrategia("MAIS_ANTIGA");
//        assertEquals(controllerPesquisa.getEstrategia(), "MAIS_ANTIGA");
//
//        //Testando MENOS_PENDENCIAS
//        teste.configuraEstrategia("MENOS_PENDENCIAS");
//        assertEquals(controllerPesquisa.getEstrategia(), "MENOS_PENDENCIAS");
//
//        //Testando MAIOR_RISCO
//        teste.configuraEstrategia("MAIOR_RISCO");
//        assertEquals(controllerPesquisa.getEstrategia(), "MAIOR_RISCO");
//
//        //Testando MAIOR_DURACAO
//        teste.configuraEstrategia("MAIOR_DURACAO");
//        assertEquals(controllerPesquisa.getEstrategia(), "MAIOR_DURACAO");
//    }

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