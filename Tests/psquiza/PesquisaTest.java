package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PesquisaTest {

    private Pesquisa teste1;
    private Pesquisa teste2;

    @BeforeEach
    void testConstroiPesquisaTest() {
        teste1 = new Pesquisa("Homofobia na graduacao de Ciencias da Computacao", "computacao,homofobia,graduacao",
                "COM1");

    }

    @Test
    void testConstroiPesquisaDescricaoVaziaOuNullTest() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("", "computacao,homofobia,graduacao"
                , "COM1"));
        assertThrows(NullPointerException.class, () -> teste2 = new Pesquisa(null, "computacao,homofobia,graduacao",
                "COM1"));
    }

    @Test
    void testConstroiPesquisaCampoInteresseVaziaOuNullTest() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da Computacao", "", "COM1"));
        assertThrows(NullPointerException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia da " +
                "Computacao", null, "COM1"));
    }

    @Test
    void testConstroiPesquisaCampoInteresseTamanhoInvalidoTest() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da Computacao", "aa", "COM1"));
        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da " +
                "Computacao", "computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "COM1"));
    }

    @Test
    void testConstroiPesquisaQtdInteresseTest() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da Computacao", "computacao,homofobia,graduacao,internet,computadores", "COM1"));
    }

    @Test
    void testConstroiPesquisaCampoInteresseUmVazioOuNullTest() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da Computacao", "computacao,,homofobia", "COM1"));

    }

    @Test
    void testConstroiPesquisaCampoInteresseUmTamanhoInvalidoTest() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da Computacao", "aa,computacao,homofobia", "COM1"));
        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("Homofobia na graduacao de Ciencia " +
                "da " +
                "Computacao", "computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "COM1"));
    }


    @Test
    void setDescricaoTest() {
        teste1.setDescricao("Homofobia nas graduacoes da area de Exatas");
        String msg = "COM1 - Homofobia nas graduacoes da area de Exatas - computacao,homofobia,graduacao";
        assertEquals(msg, teste1.toString());
    }


    @Test
    void setDescricaoVaziaOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste1.setDescricao(""));
        assertThrows(NullPointerException.class, () -> teste1.setDescricao(null));
    }

    @Test
    void setDescricaoPesquisaDesativadaTest() {
        teste1.desativa("Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste1.setDescricao("Homofobia nas graduacoes da area de " +
                "Exatas"));

    }

    @Test
    void setCampoDeInteresseTest() {
        teste1.setCampoDeInteresse("computacao,homofobia,graduacao,exatas");
        String msg = "COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao,exatas";
        assertEquals(msg, teste1.toString());

    }

    @Test
    void setCampoDeInteresseVazioOuNullTest() {

        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse(""));
        assertThrows(NullPointerException.class, () -> teste1.setCampoDeInteresse(null));

    }

    @Test
    void setCampoDeInteresseTamanhoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("aa"));
        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void setCampoDeInteresseMuitosInteressesTest() {

        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("computacao,homofobia," +
                "graduacao,exatas,internet"));
    }

    @Test
    void setCampoDeInteresseUmTamanhoInvalidoTest() {
        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("comptacao,homofobia,aa"));
        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("computacao, " +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void setCampoDeInteresseUmInteresseVazioTest() {
        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("computacao,,homofobia"));
    }

    @Test
    void setCampoDeInteressePesquisaDesativadaTest() {
        teste1.desativa("Falta de verba");
        assertThrows(IllegalArgumentException.class, () -> teste1.setCampoDeInteresse("computacao,homofobia"));
    }

    @Test
    void isAtivadaTest() {
        assertTrue(teste1.isAtivada());
        teste2 = new Pesquisa("Saude mental do jovem universitario brasileiro", "universidade,jovem,saude,mental",
                "UNI1");
        teste2.desativa("Falta de verba");
        assertFalse(teste2.isAtivada());
    }

    @Test
    void desativaTest() {
        teste1.desativa("Falta de verba");
        assertFalse(teste1.isAtivada());
    }

    @Test
    void desativaMotivoVazioOuNullTest() {
        assertThrows(IllegalArgumentException.class, () -> teste1.desativa(""));
        assertThrows(NullPointerException.class, () -> teste1.desativa(null));
    }

    @Test
    void ativaTest() {
        teste1.desativa("Falta de verba");
        teste1.ativa();
        assertTrue(teste1.isAtivada());
    }

    @Test
    void toStringTest() {
        String msg1 = "COM1 - Homofobia na graduacao de Ciencias da Computacao - computacao,homofobia,graduacao";
        String msg2 = "UNI1 - Saude mental do jovem universitario brasileiro - universidade,jovem,saude,mental";
        teste2 = new Pesquisa("Saude mental do jovem universitario brasileiro", "universidade,jovem,saude,mental",
                "UNI1");

        assertEquals(msg1, teste1.toString());
        assertEquals(msg2, teste2.toString());
    }

    @Test
    void equalsTest() {
        Pesquisa teste3;
        teste2 = new Pesquisa("Saude mental do jovem universitario brasileiro", "universidade,jovem,saude,mental",
                "UNI1");
        teste3 = new Pesquisa("Machismo na graduacao de Ciencias da Computacao", "computacao,machismo,graduacao",
                "COM1");

        assertTrue(teste1.equals(teste3));
        assertFalse(teste1.equals(teste2));
    }

    @Test
    void hashCodeTest() {
        Pesquisa teste3;
        teste2 = new Pesquisa("Saude mental do jovem universitario brasileiro", "universidade,jovem,saude,mental",
                "UNI1");
        teste3 = new Pesquisa("Machismo na graduacao de Ciencias da Computacao", "computacao,machismo,graduacao",
                "COM1");

        assertEquals(teste1.hashCode(),teste3.hashCode());
        assertNotEquals(teste1.hashCode(), teste2.hashCode());
    }
}