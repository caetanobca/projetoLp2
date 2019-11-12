package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAssociacoesTest {

    private ControllerAssociacoes controllerAssociacoes;
    private ControllerPesquisa controllerPesquisa;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisador controllerPesquisador;


    @BeforeEach
    public void setUp() {
        this.controllerAtividade = new ControllerAtividade();
        this.controllerPesquisa = new ControllerPesquisa();
        this.controllerObjetivo = new ControllerObjetivo();
        this.controllerPesquisador = new ControllerPesquisador();
        this.controllerProblema = new ControllerProblema();
        this.controllerAssociacoes = new ControllerAssociacoes(controllerProblema,controllerObjetivo,controllerPesquisa,controllerAtividade,controllerPesquisador );
        //Criando algumas atividades

        controllerAtividade.cadastraAtividade("Visitar instalacoes de religioes de matrizes africanas","BAIXO"
                ,"Nenhum risco identificado");
        controllerAtividade.cadastraAtividade("Realizar roda de conversas sobre homofobia","MEDIO",
                "Algum desentendimento");

        //Criando algumas pesquisas

        controllerPesquisa.cadastraPesquisa("Pesquisa sobre religioes de matrizes africandas","religiao,africa");
        controllerPesquisa.cadastraPesquisa("Pesquisar sobre homofobia na UFCG","universidade,homofobia");
    }

    @Test
    void associaProblema() {
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");
        this.controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao " +
                "Orientada a Objeto.", "computacao, poo");

        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);

        assertTrue(controllerAssociacoes.associaProblema("COM2", "P1"));
        assertTrue(controllerAssociacoes.associaProblema("COM1", "P1"));
        assertFalse(controllerAssociacoes.associaProblema("COM2", "P1"));
    }

    @Test
    void associaProblemaIdPesquisaVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.associaProblema("", "P1"));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.associaProblema(null, "P1"));
    }

    @Test
    void associaProblemaIdProblemaVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.associaProblema("COM1", ""));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.associaProblema("COM1", null));
    }

    @Test
    void associaProblemaEmPesquisaNaoCadastrada(){
        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no " +
                "estudantes da graduacao de computacao", 3);

        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.associaProblema("UNI1", "P1"));
    }

    @Test
    void associaProblemaEmPesquisaDesativada(){
        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no " +
                "estudantes da graduacao de computacao", 3);

        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.associaProblema("COM1", "P1"));
    }

    @Test
    void associaProblemaEmPesquisaComProblema() {
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no " +
                "estudantes da graduacao de computacao", 3);
        this.controllerProblema.cadastraProblema("A EXTREMA falta de paciencia e criatividade durante a" +
                " criacao de testes no estudantes da graduacao de computacao", 2);

        controllerAssociacoes.associaProblema("COM1", "P1");

        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.associaProblema("COM1", "P2"));
    }

    @Test
    void desassociaProblema() {
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);
        controllerAssociacoes.associaProblema("COM1", "P1");

        assertTrue(this.controllerAssociacoes.desassociaProblema("COM1"));
        assertFalse(this.controllerAssociacoes.desassociaProblema("COM1"));
    }

    @Test
    void desassociaProblemaEmPesquisaNaoCadastrada(){
        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no " +
                "estudantes da graduacao de computacao", 3);

        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.desassociaProblema("UNI1"));
    }

    @Test
    void desassociaProblemaEmPesquisaDesativada(){
        this.controllerProblema.cadastraProblema("A falta de paciencia durante a criacao de testes no " +
                "estudantes da graduacao de computacao", 3);

        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.desassociaProblema("COM1"));
    }

    @Test
    void desassociaProblemaIdPesquisaVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.desassociaProblema(""));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.desassociaProblema(null));
    }

    @Test
    void associaObjetivo() {
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);
        this.controllerObjetivo.cadastraObjetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5);

        assertTrue(controllerAssociacoes.associaObjetivo("COM1", "O2"));
        assertTrue(controllerAssociacoes.associaObjetivo("COM1", "O1"));
        assertFalse(controllerAssociacoes.associaObjetivo("COM1", "O2"));
    }

    @Test
    void associaObjetivoEmPesquisaNaoCadastrada(){
        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);

        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.associaObjetivo("UNI1", "O1"));
    }
    @Test
    void associaObjetivoEmPesquisaDesativada(){
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);

        this.controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.associaObjetivo("COM1", "O1"));
    }

    @Test
    void associaObjetivosIdPesquisaVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.associaObjetivo("", "O1"));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.associaObjetivo(null, "O1"));
    }

    @Test
    void associaObjetivosIdObjetivoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.associaObjetivo("COM1", ""));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.associaObjetivo("COM1", null));
    }

    @Test
    void associaObjetivosJaAssociado() {
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");
        this.controllerPesquisa.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao " +
                "Orientada a Objeto.", "computacao, poo");


        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);

        controllerAssociacoes.associaObjetivo("COM1", "O1");
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.associaObjetivo("COM2", "O1"));
    }


    @Test
    void desassociaObjetivo() {
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);

        this.controllerAssociacoes.associaObjetivo("COM1", "O1");
        assertTrue(this.controllerObjetivo.getObjetivoAssociado("O1"));
        this.controllerAssociacoes.desassociaObjetivo("COM1","O1");
        assertFalse(this.controllerObjetivo.getObjetivoAssociado("O1"));

    }

    @Test
    void desassociaObjetivosIdPesquisaVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.desassociaObjetivo("", "O1"));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.desassociaObjetivo(null, "O1"));
    }

    @Test
    void desassociaObjetivosIdObjetivoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerAssociacoes.desassociaObjetivo("COM1", ""));
        assertThrows(NullPointerException.class, () -> this.controllerAssociacoes.desassociaObjetivo("COM1", null));
    }

    @Test
    void desassociaObjetivoEmPesquisaNaoCadastrada(){
        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);

        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.desassociaObjetivo("UNI1", "O1"));
    }
    @Test
    void desassociaObjetivoEmPesquisaDesativada(){
        this.controllerPesquisa.cadastraPesquisa("Homofobia na graduacao de Ciencias da Computacao",
                "computacao, homofobia,graduacao");

        this.controllerObjetivo.cadastraObjetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5);

        this.controllerPesquisa.encerraPesquisa("COM1", "Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> controllerAssociacoes.desassociaObjetivo("COM1", "O1"));
    }

    @Test
    void associaPesquisador() {
    }

    @Test
    void desassociaPesquisador() {
    }

    @Test
    void associaAtividadeComExcecoesTest() {
        assertThrows(NullPointerException.class,()-> controllerAssociacoes.associaAtividade(null,"A1"));
        assertThrows(NullPointerException.class,()-> controllerAssociacoes.associaAtividade("REL1",null));
        assertThrows(IllegalArgumentException.class,()-> controllerAssociacoes.associaAtividade("","A2"));
        assertThrows(IllegalArgumentException.class,()-> controllerAssociacoes.associaAtividade("REL1",""));
    }

    @Test
    void associaAtividade() {
        assertTrue(controllerAssociacoes.associaAtividade("REL1","A1"));
        assertTrue(controllerAssociacoes.associaAtividade("UNI1","A2"));
    }

    @Test
    void associaAtividadeJaAssociada() {
        controllerAssociacoes.associaAtividade("REL1","A1");
        assertFalse(controllerAssociacoes.associaAtividade("REL1","A1"));
    }

    @Test
    void desassociaAtividadeComExcecoesTest() {
        controllerAssociacoes.associaAtividade("REL1","A1");
        controllerAssociacoes.associaAtividade("UNI1","A2");
        assertThrows(NullPointerException.class,()-> controllerAssociacoes.desassociaAtividade(null,"A1"));
        assertThrows(NullPointerException.class,()-> controllerAssociacoes.desassociaAtividade("REL1",null));
        assertThrows(IllegalArgumentException.class,()-> controllerAssociacoes.desassociaAtividade("","A2"));
        assertThrows(IllegalArgumentException.class,()-> controllerAssociacoes.desassociaAtividade("REL1",""));
    }

    @Test
    void desassociaAtividade() {
        controllerAssociacoes.associaAtividade("REL1","A1");
        controllerAssociacoes.associaAtividade("UNI1","A2");
        assertTrue(controllerAssociacoes.desassociaAtividade("REL1","A1"));
        assertTrue(controllerAssociacoes.desassociaAtividade("UNI1","A2"));
    }

    @Test
    void desassociaAtividadeNaoAssociadaTest() {
        assertFalse(controllerAssociacoes.desassociaAtividade("REL1","A1"));
    }
}