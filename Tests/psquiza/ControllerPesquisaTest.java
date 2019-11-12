package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisaTest {

    ControllerPesquisa teste;
    ControllerAtividade atividade;

    @BeforeEach
    void constroiControllerPesquisaTest() {
        teste = new ControllerPesquisa();
        teste.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao," +
                "homofobia,graduacao");
    }

    @Test
    void cadastraPesquisaTest() {

        assertEquals("COM2", teste.cadastraPesquisa("Racismo na graduacao de Ciencias da Computacao", "computacao," +
                "racismo,graduacao"));
        assertEquals("SAU1", teste.cadastraPesquisa("Saude Mental dos estudantes da area de exatas", "saude mental, " +
                "estudantes,exatas"));

    }

    @Test
    void cadastraPesquisaDescricaoVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("", "computacao," +
                "homofobia,graduacao"));

        assertThrows(NullPointerException.class, () -> teste.cadastraPesquisa(null, "computacao," +
                "homofobia,graduacao"));
    }

    @Test
    void cadastraPesquisaCampoDeInteresseVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da Computacao", ""));


        assertThrows(NullPointerException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias da " +
                "Computacao", null));
    }

    @Test
    void cadastraPesquisaTamanhoCampoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de " +
                "Ciencias" + " da Computacao", "computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " +
                "Computacao", "aa"));
    }

    @Test
    void cadastraPesquisaMaisCamposTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da Computacao", "computacao,homofobia,graduacao,universidade,internet"));
    }

    @Test
    void cadastraPesquisaTamanhoUmCampoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de " +
                "Ciencias" + " da Computacao", "computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " +
                "Computacao", "computacao,aa,homofobia"));
    }

    @Test
    void cadastraPesquisaUmCampoVazioTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " + "Computacao", "computacao,,homofobia,graduacao"));

        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de Ciencias" +
                " da " + "Computacao", ",,computacao,homofobia,graduacao"));

        assertThrows(IllegalArgumentException.class, () -> teste.cadastraPesquisa("Homofobia na graduacao de " +
                "Ciencias" + " da" + " Computacao", " computacao, graduacao, , homofobia"));


    }

    @Test
    void alteraPesquisaDescricaoTest() {
        teste.alteraPesquisa("COM1", "DESCRICAO", "Homofobia na graduacao e na pos de Ciencias da Computacao");
        String msg = "COM1 - Homofobia na graduacao e na pos de Ciencias da Computacao - computacao,homofobia," +
                "graduacao";
        assertEquals(msg, teste.exibePesquisa("COM1"));

    }

    @Test
    void alteraPesquisaCampoTest() {
        teste.alteraPesquisa("COM1", "CAMPO", "computacao,homofobia,graduacao,exatas");
        String msg = "COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao,exatas";
        assertEquals(msg, teste.exibePesquisa("COM1"));

    }

    @Test
    void alteraPesquisaCodigoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("", "CAMPO", "computacao,homofobia," +
                "graduacao,exatas"));
        assertThrows(NullPointerException.class, () -> teste.alteraPesquisa(null, "CAMPO", "computacao,homofobia"));
    }

    @Test
    void alteraPesquisaCodigoInexistenteTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("GRA1", "CAMPO", "computacao," +
                "homofobia," +
                "graduacao,exatas"));

        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM2", "CAMPO", "computacao," +
                "homofobia," +
                "graduacao,exatas"));
    }

    @Test
    void alteraPesquisaConteudoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "", "computacao,homofobia," +
                "graduacao,exatas"));
        assertThrows(NullPointerException.class, () -> teste.alteraPesquisa("COM1", null, "computacao,homofobia"));

    }

    @Test
    void alteraPesquisaConteudoNovoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", ""));
        assertThrows(NullPointerException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", null));

    }

    @Test
    void alteraPesquisaDesativada() {
        teste.encerraPesquisa("COM1", "Falta de Verba");
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", "computacao," +
                "homofobia," +
                "graduacao,exatas"));
    }

    @Test
    void alteraPesquisaConteudoNaoPodeAlterarTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CODIGO", "HOM1"));
    }

    @Test
    void alteraPesquisaDescricaoNovaVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "DESCRICAO", ""));
        assertThrows(NullPointerException.class, () -> teste.alteraPesquisa("COM1", "DESCRICAO", null));

    }

    @Test
    void alteraPesquisaCampoNovoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", ""));
        assertThrows(NullPointerException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", null));

    }

    @Test
    void alteraPesquisaCampoNovoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", "co"));
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", "computacao," +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", "computacao,," +
                "homofobia"));
        assertThrows(IllegalArgumentException.class, () -> teste.alteraPesquisa("COM1", "CAMPO", "computacao," +
                "homofobia,universidade,graduacao,internet"));
    }

    @Test
    void encerraPesquisa() {
        teste.encerraPesquisa("COM1", "Falta de verba");
        assertFalse(teste.pesquisaEhAtiva("COM1"));
    }

    @Test
    void encerraPesquisaCodigoVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> teste.encerraPesquisa("", "Falta de verba"));
        assertThrows(NullPointerException.class, () -> teste.encerraPesquisa(null, "Falta de verba"));

    }

    @Test
    void encerraPesquisaNaoEnncontrada() {
        assertThrows(IllegalArgumentException.class, () -> teste.encerraPesquisa("UNI1", "Falta de verba"));
        assertThrows(IllegalArgumentException.class, () -> teste.encerraPesquisa("AAA1", "Falta de verba"));

    }

    @Test
    void encerraPesquisaMotivoVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> teste.encerraPesquisa("COM1", ""));
        assertThrows(NullPointerException.class, () -> teste.encerraPesquisa("COM1", null));

    }

    @Test
    void encerraPesquisaDesativada() {
        teste.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste.encerraPesquisa("COM1", "Falta de verba"));
    }

    @Test
    void ativaPesquisa() {
        teste.encerraPesquisa("COM1", "Falta de verba");
        assertFalse(teste.pesquisaEhAtiva("COM1"));
        teste.ativaPesquisa("COM1");
        assertTrue(teste.pesquisaEhAtiva("COM1"));
    }

    @Test
    void ativaPesquisaCodigoVazioOuNull() {

        assertThrows(NullPointerException.class, () -> teste.ativaPesquisa(null));
        assertThrows(IllegalArgumentException.class, () -> teste.ativaPesquisa(""));

    }

    @Test
    void ativaPesquisaNaoEncontrada() {

        assertThrows(IllegalArgumentException.class, () -> teste.ativaPesquisa("UNI1"));

    }

    @Test
    void ativaPesquisaJaAtiva() {
        assertThrows(IllegalArgumentException.class, () -> teste.ativaPesquisa("COM1"));
    }


    @Test
    void exibePesquisa() {
        teste.cadastraPesquisa("Racismo na graduacao de Ciencias da Computacao", "computacao," +
                "racismo,graduacao");
        teste.cadastraPesquisa("Saude Mental dos estudantes da area de exatas", "saude mental, " +
                "estudantes,exatas");

        String msg1 = "COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao";
        String msg2 = "COM2 - Racismo na graduacao de Ciencias da Computacao - computacao,racismo,graduacao";
        String msg3 = "SAU1 - Saude Mental dos estudantes da area de exatas - saude mental, estudantes,exatas";

        assertEquals(msg1,teste.exibePesquisa("COM1"));
        assertEquals(msg2,teste.exibePesquisa("COM2"));
        assertEquals(msg3,teste.exibePesquisa("SAU1"));
    }

    @Test
    void exibePesquisaNaoCadastrada(){
        assertThrows(IllegalArgumentException.class, ()->teste.exibePesquisa("UNI1"));
        assertThrows(IllegalArgumentException.class, ()->teste.exibePesquisa("COM2"));
    }

    @Test
    void pesquisaEhAtiva() {
        assertTrue(teste.pesquisaEhAtiva("COM1"));
        teste.encerraPesquisa("COM1", "Falta de Verba");
        assertFalse(teste.pesquisaEhAtiva("COM1"));

    }

    @Test
    void pesquisaEhAtivaCodigoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, ()->teste.pesquisaEhAtiva(""));
        assertThrows(NullPointerException.class, ()->teste.pesquisaEhAtiva(null));

    }

    @Test
    void pesquisaEhAtivaPesquisaNaoEncontrada() {
        assertThrows(IllegalArgumentException.class, ()->teste.pesquisaEhAtiva("COM2"));
        assertThrows(IllegalArgumentException.class, ()->teste.pesquisaEhAtiva("UNI2"));

    }

    @Test
    void associaProblemaEmPesquisa(){
        teste.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");
//        teste.cadastraPesquisa("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.", "eleicao");
//        teste.cadastraPesquisa("Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.", "fermentacao, cerveja");
//        teste.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil");

        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao",3,"P1");
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");


        assertTrue(teste.associaProblemaEmPesquisa("COM2", problema1));
        assertTrue(teste.associaProblemaEmPesquisa("COM1", problema1));
        assertFalse(teste.associaProblemaEmPesquisa("COM2", problema1));
    }

    @Test
    void associaProblemaEmPesquisaNaoCadastrada(){
        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao",3,"P1");

        assertThrows(IllegalArgumentException.class, () -> teste.associaProblemaEmPesquisa("UNI1", problema1));
        assertThrows(IllegalArgumentException.class, () -> teste.associaProblemaEmPesquisa("AAA1", problema1));
    }

    @Test
    void associaProblemaEmPesquisaDesativada(){
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");

        teste.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste.associaProblemaEmPesquisa("COM1", problema2));
    }

    @Test
    void associaProblemaEmPesquisaJaAssociada(){
        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao",3,"P1");
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");

        teste.associaProblemaEmPesquisa("COM1", problema1);
        assertThrows(IllegalArgumentException.class, () -> teste.associaProblemaEmPesquisa("COM1", problema2));
    }

    @Test
    void desassociaProblemaEmPesquisa(){
        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao",3,"P1");

        teste.associaProblemaEmPesquisa("COM1", problema1);

        assertTrue(teste.desassociaProblemaEmPesquisa("COM1"));
        assertFalse(teste.desassociaProblemaEmPesquisa("COM1"));
    }

    @Test
    void desassociaProblemaEmPesquisaNaoCadastrada(){
        assertThrows(IllegalArgumentException.class, () -> teste.desassociaProblemaEmPesquisa("UNI1"));
        assertThrows(IllegalArgumentException.class, () -> teste.desassociaProblemaEmPesquisa("AAA1"));
    }

    @Test
    void desassociaProblemaEmPesquisaDesativada(){
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");

        teste.associaProblemaEmPesquisa("COM1", problema2);
        teste.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste.desassociaProblemaEmPesquisa("COM1"));
    }

    @Test
    void associaObjetivoEmPesquisa(){
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        assertTrue(this.teste.associaObjetivoEmPesquisa("COM1", objetivo1));

        assertFalse(objetivo2.getAssociado());
        assertTrue(this.teste.associaObjetivoEmPesquisa("COM1", objetivo2));
        assertTrue(objetivo2.getAssociado());

        assertFalse(this.teste.associaObjetivoEmPesquisa("COM1", objetivo1));
    }

    @Test
    void associaObjetivoEmPesquisaNaoCadastrada(){
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");

        assertThrows(IllegalArgumentException.class, () -> teste.associaObjetivoEmPesquisa("UNI1", objetivo1));
        assertThrows(IllegalArgumentException.class, () -> teste.associaObjetivoEmPesquisa("AAA1", objetivo1));
    }
    @Test
    void associaObjetivoEmPesquisaDesativada(){
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        teste.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste.associaObjetivoEmPesquisa("COM1", objetivo2));
    }

    @Test
    void associaObjetivoJaAssociado(){
        teste.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");

        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");

        teste.associaObjetivoEmPesquisa("COM2", objetivo1);
        assertThrows(IllegalArgumentException.class, () -> teste.associaObjetivoEmPesquisa("COM1", objetivo1));
    }

    @Test
    void desassociaObjetivoEmPesquisa(){
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        this.teste.associaObjetivoEmPesquisa("COM1", objetivo1);
        assertTrue(objetivo1.getAssociado());
        assertTrue(this.teste.desassociaObjetivoEmPesquisa("COM1", objetivo1));
        assertFalse(objetivo1.getAssociado());
        assertFalse(this.teste.desassociaObjetivoEmPesquisa("COM1", objetivo2));
    }

    @Test
    void desassociaObjetivoEmPesquisaNaoCadastrada(){
        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");

        assertThrows(IllegalArgumentException.class, () -> teste.desassociaObjetivoEmPesquisa("UNI1", objetivo1));
        assertThrows(IllegalArgumentException.class, () -> teste.desassociaObjetivoEmPesquisa("AAA1", objetivo1));
    }
    @Test
    void desassociaObjetivoEmPesquisaDesativada(){
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");

        teste.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste.desassociaObjetivoEmPesquisa("COM1", objetivo2));
    }

    @Test
    void listaPesquisas(){
        teste.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto." , "computacao, poo");
        teste.cadastraPesquisa("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.", "eleicao");
        teste.cadastraPesquisa("Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.", "fermentacao, cerveja");
        teste.cadastraPesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil");

        Problema problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao",3,"P1");
        Problema problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");
        Problema problema3 = new Problema("A extrema falta de paciencia durante a criacao de testes da junit nos estudantes da graduacao de computacao da ufcg",3,"P3");

        teste.associaProblemaEmPesquisa("COM1", problema1);
        teste.associaProblemaEmPesquisa("COM2", problema2);
        teste.associaProblemaEmPesquisa("ELE1", problema3);
        teste.associaProblemaEmPesquisa("PSI1", problema2);

        Objetivo objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");
        Objetivo objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");
        Objetivo objetivo3 = new Objetivo("ESPECIFICO", "Gerenciar melhor as notas dos alunos", 3, 4, "O3");
        Objetivo objetivo4 = new Objetivo("GERAL", "gerenceiar melhor a distribuicao dos alunos nas aulas de lp2", 5, 5, "O4");
        Objetivo objetivo5 = new Objetivo("ESPECIFICO", "Encontarar o melhor resultado possivel com as leveduras nao-Saccharomyces.", 4, 3, "O5");
        Objetivo objetivo6 = new Objetivo("GERAL", "Melhorar a extracao de caracteristicas significativas nas eleicoes brasileiras.", 3, 4, "O6");
        Objetivo objetivo7 = new Objetivo("GERAL", "Acabou minha criatividade", 5, 5, "O7");

        teste.associaObjetivoEmPesquisa("COM1", objetivo1);
        teste.associaObjetivoEmPesquisa("ELE1", objetivo2);
        teste.associaObjetivoEmPesquisa("PSI1", objetivo3);
        teste.associaObjetivoEmPesquisa("COM2", objetivo4);
        teste.associaObjetivoEmPesquisa("COM2", objetivo5);
        teste.associaObjetivoEmPesquisa("PSI1", objetivo6);
        teste.associaObjetivoEmPesquisa("COM2", objetivo7);


        assertEquals("ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao " +
                "| COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo " +
                "| PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil " +
                "| COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao " +
                "| FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja"
                ,teste.listaPesquisas("PROBLEMA"));

        assertEquals("COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo " +
                        "| PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil " +
                        "| COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao " +
                        "| ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao " +
                        "| FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja"
                , teste.listaPesquisas("OBJETIVOS"));
        assertEquals("PSI1 - Alienacao Parental e o Sistema de Justica Brasileiro. - psicologia, sistema juridico, alienacao parental, brasil " +
                "| FER1 - Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. - fermentacao, cerveja " +
                "| ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao " +
                "| COM2 - Autoavaliacao na Disciplina de Programacao Orientada a Objeto. - computacao, poo " +
                "| COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao"
                , teste.listaPesquisas("PESQUISA"));

    }

    @Test
    void listaPesquisasOrdemVaziaOuNull(){
        assertThrows(IllegalArgumentException.class, ()->teste.listaPesquisas(""));
        assertThrows(NullPointerException.class, ()->teste.listaPesquisas(null));
      
    @Test  
    void associaAtividadeEmPesquisaComExcecoesTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertThrows(NullPointerException.class,()->  teste.associaAtividadeEmPesquisa(null,atividade));
        assertThrows(IllegalArgumentException.class,()-> teste.associaAtividadeEmPesquisa("",atividade));
        assertThrows(IllegalArgumentException.class,()-> teste.associaAtividadeEmPesquisa("LEI1",atividade));
        teste.encerraPesquisa("COM1","Corte de verbas");
        assertThrows(IllegalArgumentException.class,()-> teste.associaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void associaAtividadeTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertTrue(teste.associaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void associaAtividadeJaAssociadaTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        teste.associaAtividadeEmPesquisa("COM1",atividade);
        assertFalse(teste.associaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void desassociaAtividadeEmPesquisaComExcecoesTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertThrows(NullPointerException.class,()->  teste.desassociaAtividadeEmPesquisa(null,atividade));
        assertThrows(IllegalArgumentException.class,()-> teste.desassociaAtividadeEmPesquisa("",atividade));
        assertThrows(IllegalArgumentException.class,()-> teste.desassociaAtividadeEmPesquisa("LEI1",atividade));
        teste.encerraPesquisa("COM1","Corte de verbas");
        assertThrows(IllegalArgumentException.class,()-> teste.desassociaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void desassociaAtividadeTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        teste.associaAtividadeEmPesquisa("COM1",atividade);
        assertTrue(teste.desassociaAtividadeEmPesquisa("COM1",atividade));
    }

    @Test
    void desassociaAtividadeNaoAssociadaTest() {
        Atividade atividade = new Atividade("A1","rodas de conversa sobre homofobia",
                "BAIXO","Alguma manifestacao homofobica");
        assertFalse(teste.desassociaAtividadeEmPesquisa("COM1",atividade));
    }
}