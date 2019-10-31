package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerPesquisadorTest {

    private ControllerPesquisador controllerPesquisador;

    @BeforeEach
    void testConstrutor() {
        this.controllerPesquisador = new ControllerPesquisador();
    }

    @Test
    void cadastraPesquisador() {

    }

    @Test
    void cadastraPesquisadorNomeNulleVazio() {
        assertThrows(IllegalArgumentException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorFuncaoNulleVazio() {
        assertThrows(IllegalArgumentException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorBiografiaNulleVazio() {
        assertThrows(IllegalArgumentException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorEmailNulleVazio() {
        assertThrows(IllegalArgumentException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorFNulleVazio() {
        assertThrows(IllegalArgumentException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorNulleVazio() {
        assertThrows(IllegalArgumentException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void alteraPesquisador() {
    }

    @Test
    void alteraPesquisadorNulleVazio() {
    }


    @Test
    void ativaPesquisador() {
    }

    @Test
    void ativaPesquisadorNulleVazio() {
    }


    @Test
    void desativaPesquisador() {
    }

    @Test
    void desativaPesquisadorNulleVazio() {
    }


    @Test
    void exibePesquisador() {
    }

    @Test
    void exibePesquisadorNulleVazio() {
    }


    @Test
    void pesquisadorEhAtivo() {
    }

    @Test
    void pesquisadorEhAtivoNulleVazio() {
    }

}