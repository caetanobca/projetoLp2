package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisaTest {

    ControllerPesquisa teste;

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
}