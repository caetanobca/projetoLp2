package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.atividade.ControllerAtividade;
import psquiza.objetivo.ControllerObjetivo;
import psquiza.pesquisa.ControllerPesquisa;
import psquiza.pesquisador.ControllerPesquisador;
import psquiza.problema.ControllerProblema;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPersistenciaTest {

    private ControllerPersistencia controllerPersistencia;
    private ControllerPesquisa controllerPesquisa;
    private ControllerPesquisador controllerPesquisador;
    private ControllerAtividade controllerAtividade;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;

    @BeforeEach
    public void criaControllerPersistencia() {
        this.controllerPesquisador = new ControllerPesquisador();
        this.controllerAtividade = new ControllerAtividade();
        this.controllerObjetivo = new ControllerObjetivo();
        this.controllerProblema = new ControllerProblema();
        this.controllerPesquisa = new ControllerPesquisa(this.controllerObjetivo.getObjetivos(), this.controllerProblema.getProblemas(),
                this.controllerAtividade.getAtividades(), this.controllerPesquisador.getPesquisadores());

        controllerPesquisa.cadastraPesquisa("Casos de homofobia na graduação de Ciências da Computação", "homofobia," +
                "computação," +
                "graduação");
        controllerPesquisador.cadastraPesquisador("Djonga", "estudante", "Estudante de História, negro, anti-fascista, " +
                "mineiro e da " +
                "favela. Rapper que costuma lançar discos e músicas muito boas!", "djonga@rapper.com", "http" +
                "://djongaehfoda.com");
        controllerAtividade.cadastraAtividade("Relatos de alunos que sofreram homofobia na graduação", "BAIXO", "Risco " +
                "baixo");
        controllerObjetivo.cadastraObjetivo("GERAL", "Diminuir casos de homofobia na graduação de Ciências da Computação",
                3, 3);
        controllerProblema.cadastraProblema("Homofobia na graduação", 3);

        this.controllerPersistencia = new ControllerPersistencia(controllerProblema, controllerObjetivo, controllerPesquisa, controllerAtividade, controllerPesquisador);
    }

    @Test
    void salva() throws IOException, ClassNotFoundException {
        ControllerPesquisa pesquisaTeste = this.controllerPesquisa;
        ControllerPesquisador pesquisadorTeste = this.controllerPesquisador;
        ControllerAtividade atividadeTeste = this.controllerAtividade;
        ControllerObjetivo objetivoTeste = this.controllerObjetivo;
        ControllerProblema problemaTeste = this.controllerProblema;

        this.controllerPersistencia.salva();

        assertEquals(pesquisaTeste.getPesquisas(), this.controllerPersistencia.carregaPesquisa().getPesquisas());
        assertEquals(pesquisadorTeste.getPesquisadores(), this.controllerPersistencia.carregaPesquisador().getPesquisadores());
        assertEquals(atividadeTeste.getAtividades(), this.controllerPersistencia.carregaAtividade().getAtividades());
        assertEquals(objetivoTeste.getObjetivos(), this.controllerPersistencia.carregaObjetivo().getObjetivos());
        assertEquals(problemaTeste.getProblemas(), this.controllerPersistencia.carregaProblema().getProblemas());

    }

    @Test
    void carregaAtividade() throws IOException, ClassNotFoundException {
        ControllerAtividade atividadeTeste = this.controllerAtividade;

        this.controllerPersistencia.salva();

        assertEquals(atividadeTeste.getAtividades(), this.controllerPersistencia.carregaAtividade().getAtividades());
    }

    @Test
    void carregaPesquisa() throws IOException, ClassNotFoundException {
        ControllerPesquisa pesquisaTeste = this.controllerPesquisa;

        this.controllerPersistencia.salva();

        assertEquals(pesquisaTeste.getPesquisas(), this.controllerPersistencia.carregaPesquisa().getPesquisas());

    }

    @Test
    void carregaProblema() throws IOException, ClassNotFoundException {
        ControllerProblema problemaTeste = this.controllerProblema;

        this.controllerPersistencia.salva();

        assertEquals(problemaTeste.getProblemas(), this.controllerPersistencia.carregaProblema().getProblemas());
    }

    @Test
    void carregaPesquisador() throws IOException, ClassNotFoundException {
        ControllerPesquisador pesquisadorTeste = this.controllerPesquisador;

        this.controllerPersistencia.salva();

        assertEquals(pesquisadorTeste.getPesquisadores(), this.controllerPersistencia.carregaPesquisador().getPesquisadores());
    }

    @Test
    void carregaObjetivo() throws IOException, ClassNotFoundException {
        ControllerObjetivo objetivoTeste = this.controllerObjetivo;

        this.controllerPersistencia.salva();

        assertEquals(objetivoTeste.getObjetivos(), this.controllerPersistencia.carregaObjetivo().getObjetivos());
    }

}