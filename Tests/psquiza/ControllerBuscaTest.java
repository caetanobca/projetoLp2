package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.atividade.ControllerAtividade;
import psquiza.objetivo.ControllerObjetivo;
import psquiza.pesquisa.ControllerPesquisa;
import psquiza.pesquisador.ControllerPesquisador;
import psquiza.problema.ControllerProblema;

import static org.junit.jupiter.api.Assertions.*;

class ControllerBuscaTest {

    ControllerBusca buscaTeste;
    ControllerPesquisa pesquisaTeste;
    ControllerPesquisador pesquisadorTeste;
    ControllerAtividade atividadeTeste;
    ControllerObjetivo objetivoTeste;
    ControllerProblema problemaTeste;


    @BeforeEach
    void constroiBusca() {

        this.pesquisadorTeste = new ControllerPesquisador();
        this.atividadeTeste = new ControllerAtividade();
        this.objetivoTeste = new ControllerObjetivo();
        this.problemaTeste = new ControllerProblema();
        this.pesquisaTeste = new ControllerPesquisa(this.objetivoTeste.getObjetivos(), this.problemaTeste.getProblemas(),
                this.atividadeTeste.getAtividades(), this.pesquisadorTeste.getPesquisadores() );


        pesquisaTeste.cadastraPesquisa("Casos de homofobia na graduação de Ciências da Computação", "homofobia," +
                "computação," +
                "graduação");
        pesquisadorTeste.cadastraPesquisador("Djonga", "estudante", "Estudante de História, negro, anti-fascista, " +
                "mineiro e da " +
                "favela. Rapper que costuma lançar discos e músicas muito boas!", "djonga@rapper.com", "http" +
                "://djongaehfoda.com");
        atividadeTeste.cadastraAtividade("Relatos de alunos que sofreram homofobia na graduação", "BAIXO", "Risco " +
                "baixo");
        objetivoTeste.cadastraObjetivo("GERAL", "Diminuir casos de homofobia na graduação de Ciências da Computação",
                3, 3);
        problemaTeste.cadastraProblema("Homofobia na graduação", 3);

        this.buscaTeste = new ControllerBusca(problemaTeste, objetivoTeste, pesquisaTeste, atividadeTeste,
                pesquisadorTeste);

    }

    @Test
    void buscaTest() {
        String msg1 = "HOM1: Casos de homofobia na graduação de Ciências da Computação | HOM1: homofobia,computação," +
                "graduação | O1: Diminuir casos de homofobia na graduação de Ciências da Computação | A1: Relatos de " +
                "alunos que sofreram homofobia na graduação";
        assertEquals(msg1, buscaTeste.busca("homofobia"));

        String msg2 = "HOM1: Casos de homofobia na graduação de Ciências da Computação | O1: Diminuir casos de " +
                "homofobia na" +
                " graduação de Ciências da Computação";
        assertEquals(msg2, buscaTeste.busca("Ciências"));

        String msg3 = "djonga@rapper.com: Estudante de História, negro, anti-fascista, mineiro e da favela. Rapper " +
                "que costuma lançar discos e músicas muito boas!";
        assertEquals(msg3, buscaTeste.busca("Est"));

        pesquisaTeste.cadastraPesquisa("Casos de machismo nas graduações da área das exatas", "machismo,graduação," +
                "exatas");
        pesquisadorTeste.cadastraPesquisador("Luisa", "professor", "Doutora, professora da graduação de Engenharia de" +
                " Materiais, anti-fascista e feminista", "luisa@ufcg.com", "http://fotoperfil.com");
        atividadeTeste.cadastraAtividade("Identificação de machistas nas graduações da UFCG", "ALTO", "Risco alto");
        objetivoTeste.cadastraObjetivo("GERAL", "Erradicar casos de machismo na área das exatas da UFCG", 3, 3);
        problemaTeste.cadastraProblema("Machismo na graduação", 3);

        String msg4 = "MAC1: Casos de machismo nas graduações da área das exatas | MAC1: machismo,graduação,exatas | " +
                "O2: Erradicar casos de machismo na área das exatas da UFCG | A2: Identificação de machistas nas " +
                "graduações da UFCG";
        assertEquals(msg4, buscaTeste.busca("mach"));

        String msg5 = "MAC1: Casos de machismo nas graduações da área das exatas | MAC1: machismo,graduação,exatas | " +
                "HOM1: Casos de homofobia na graduação de Ciências da Computação | HOM1: homofobia,computação," +
                "graduação | luisa@ufcg.com: Doutora, professora da graduação de Engenharia de Materiais, " +
                "anti-fascista e feminista | P2: Machismo na graduação | P1: Homofobia na graduação | O1: Diminuir " +
                "casos de homofobia na graduação de Ciências da Computação | A2: Identificação de machistas nas " +
                "graduações da UFCG | A1: Relatos de alunos que sofreram homofobia na graduação";
        assertEquals(msg5, buscaTeste.busca("gradua"));

        String msg6 = "O2: Erradicar casos de machismo na área das exatas da UFCG | O1: Diminuir casos de homofobia " +
                "na graduação de Ciências da Computação";
        assertEquals(msg6, buscaTeste.busca("casos"));

        String msg7 = "MAC1: Casos de machismo nas graduações da área das exatas | HOM1: Casos de homofobia na " +
                "graduação de Ciências da Computação | O2: Erradicar casos de machismo na área das exatas da UFCG | " +
                "O1: Diminuir casos de homofobia na graduação de Ciências da Computação";
        assertEquals(msg7, buscaTeste.busca("asos"));

        String msg8 = "luisa@ufcg.com: Doutora, professora da graduação de Engenharia de Materiais, anti-fascista e " +
                "feminista | djonga@rapper.com: Estudante de História, negro, anti-fascista, mineiro e da favela. " +
                "Rapper que costuma lançar discos e músicas muito boas!";
        assertEquals(msg8, buscaTeste.busca("fasc"));

        String msg9 = "";
        assertEquals(msg9, buscaTeste.busca("Lula Livre"));

    }

    @Test
    void bsucaTestTermoVaziouOuNull() {
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca(""));
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca(" "));
        assertThrows(NullPointerException.class, () -> buscaTeste.busca(null));

    }

    @Test
    void buscaTestComNumero() {
        pesquisaTeste.cadastraPesquisa("Casos de machismo nas graduações da área das exatas", "machismo,graduação," +
                "exatas");
        pesquisadorTeste.cadastraPesquisador("Luisa", "professor", "Doutora, professora da graduação de Engenharia de" +
                " Materiais, anti-fascista e feminista", "luisa@ufcg.com", "http://fotoperfil.com");
        atividadeTeste.cadastraAtividade("Identificação de machistas nas graduações da UFCG", "ALTO", "Risco alto");
        objetivoTeste.cadastraObjetivo("GERAL", "Erradicar casos de machismo na área das exatas da UFCG", 3, 3);
        problemaTeste.cadastraProblema("Machismo na graduação", 3);

        String msg1 = "MAC1: Casos de machismo nas graduações da área das exatas";
        assertEquals(msg1, buscaTeste.busca("mach", 1));

        String msg2 = "P2: Machismo na graduação";
        assertEquals(msg2, buscaTeste.busca("gradua", 6));

        String msg3 = "HOM1: Casos de homofobia na graduação de Ciências da Computação";
        assertEquals(msg3, buscaTeste.busca("a", 3));

        String msg4 = "djonga@rapper.com: Estudante de História, negro, anti-fascista, mineiro e da favela. Rapper " +
                "que costuma lançar discos e músicas muito boas!";
        assertEquals(msg4, buscaTeste.busca("e", 5));
    }

    @Test
    void buscaTestComNumeroTermoVazioOuNulo() {

        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca("", 2));
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca(" ", 2));
        assertThrows(NullPointerException.class, () -> buscaTeste.busca(null, 2));
    }

    @Test
    void buscaTestComNumeroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca("mach", 0));
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca("mach", -13));

    }

    @Test
    void buscaTestComNumeroMaior() {
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca("mach", 13));
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.busca("mach", 10));
    }


    @Test
    void contaResultadosBuscaTest() {
        pesquisaTeste.cadastraPesquisa("Casos de machismo nas graduações da área das exatas", "machismo,graduação," +
                "exatas");
        pesquisadorTeste.cadastraPesquisador("Luisa", "professor", "Doutora, professora da graduação de Engenharia de" +
                " Materiais, anti-fascista e feminista", "luisa@ufcg.com", "http://fotoperfil.com");
        atividadeTeste.cadastraAtividade("Identificação de machistas nas graduações da UFCG", "ALTO", "Risco alto");
        objetivoTeste.cadastraObjetivo("GERAL", "Erradicar casos de machismo na área das exatas da UFCG", 3, 3);
        problemaTeste.cadastraProblema("Macihsmo na graduação", 3);

        assertEquals(2, buscaTeste.contaResultadosBusca("rea"));
        assertEquals(3, buscaTeste.contaResultadosBusca("ismo"));
        assertEquals(1, buscaTeste.contaResultadosBusca("outora"));
        assertEquals(14, buscaTeste.contaResultadosBusca("a"));

    }

    @Test
    void contaResultadosBuscaTestTermoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.contaResultadosBusca(""));
        assertThrows(IllegalArgumentException.class, () -> buscaTeste.contaResultadosBusca(" "));
        assertThrows(NullPointerException.class, () -> buscaTeste.contaResultadosBusca(null));

    }

    @Test
    void contaResultadosBuscaTestSemResultado(){
        assertThrows(IllegalArgumentException.class, ()-> buscaTeste.contaResultadosBusca("Lula Livre"));
    }




}