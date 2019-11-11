package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAssociacoesTest {

    ControllerAssociacoes controllerAssociacoes;
    ControllerProblema controllerProblema;
    ControllerObjetivo controllerObjetivo;
    ControllerPesquisa controllerPesquisa;
    ControllerAtividade controllerAtividade;
    ControllerPesquisador controllerPesquisador;


    @BeforeEach
    void setUp() {
        this.controllerProblema = new ControllerProblema();
        this.controllerObjetivo = new ControllerObjetivo();
        this.controllerPesquisa = new ControllerPesquisa();
        this.controllerAtividade = new ControllerAtividade();
        this.controllerPesquisador = new ControllerPesquisador();
        this.controllerAssociacoes = new ControllerAssociacoes(this.controllerProblema, this.controllerObjetivo,
                this.controllerPesquisa, this.controllerAtividade, this.controllerPesquisador);
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
    void associaPesquisador() {
    }

    @Test
    void desassociaPesquisador() {
    }

    @Test
    void associaAtividade() {
    }

    @Test
    void desassociaAtividade() {
    }
}