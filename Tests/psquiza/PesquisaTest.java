package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PesquisaTest {

    private Pesquisa teste1;
    private Pesquisa teste2;

    @BeforeEach
    void testConstroiPesquisa() {
        teste1 = new Pesquisa("Homofobia na graduacao de Ciencia da Computacao", "computacao,homofobia,graduacao", "COM1");

    }

    @Test
    void testConstroiPesquisaDescricaoVaziaOuNull() {

        assertThrows(IllegalArgumentException.class, () -> teste2 = new Pesquisa("", "computacao,homofobia,graduacao", "COM1"));
        assertThrows(NullPointerException.class, () -> teste2 = new Pesquisa(null, "computacao,homofobia,graduacao", "COM1"));

    }

    @Test
    void setDescricao() {
    }

    @Test
    void setCampoDeInteresse() {
    }

    @Test
    void isAtivada() {
    }

    @Test
    void desativa() {
    }

    @Test
    void ativa() {
    }

  //  @Test
   // void toString() {
  //  }

    @Test
    void equals() {
    }

  //  @Test
  //  void hashCode() {
  //  }
}