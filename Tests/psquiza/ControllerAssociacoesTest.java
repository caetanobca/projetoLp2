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
    }

    @Test
    void desassociaProblema() {
    }

    @Test
    void associaObjetivo() {
    }

    @Test
    void desassociaObjetivo() {
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