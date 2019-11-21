package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.pesquisador.Pesquisador;

import static org.junit.jupiter.api.Assertions.*;

class PesquisadorTest {

    private Pesquisador pesquisador1;

    @BeforeEach
    void constroiPesquisador() {
        this.pesquisador1 = new Pesquisador("heisenberg", "professor",
                "Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da " +
                        "pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008","https://iamthedanger");
    }

    @Test
    void constroiPesquisadorNomeVazioOuNull() {
        assertThrows(IllegalArgumentException.class, ()-> new Pesquisador("", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->new Pesquisador(null, "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }
    @Test
    void constroiPesquisadorFuncaoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, ()-> new Pesquisador("Caetano", "",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->new Pesquisador("Caetano", null,
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }
    @Test
    void constroiPesquisadorBiografiaVaziOuNull() {
        assertThrows(IllegalArgumentException.class, ()-> new Pesquisador("Caetano", "Estudante",
                "", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->new Pesquisador("Caetano", "Estudante",
                null, "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }
    @Test
    void constroiPesquisadorEmailVazioOuNull() {
        assertThrows(IllegalArgumentException.class, ()-> new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "", "https://minhafoto"));
        assertThrows(NullPointerException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", null, "https://minhafoto"));
    }
    @Test
    void constroiPesquisadorFotoVazioOuNull() {
        assertThrows(IllegalArgumentException.class, ()-> new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", ""));
        assertThrows(NullPointerException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", null));
    }

    @Test
    void constroiPesquisadorEmailInvalido() {
        assertThrows(IllegalArgumentException.class, ()-> new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "aa", "https://minhafoto"));
        assertThrows(IllegalArgumentException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "1@", "https://minhafoto"));
        assertThrows(IllegalArgumentException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "@a", "https://minhafoto"));
    }

    @Test
    void constroiPesquisadorFotoInvalida() {
        assertThrows(IllegalArgumentException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "://minhafoto"));
        assertThrows(IllegalArgumentException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https/minhafoto"));
        assertThrows(IllegalArgumentException.class, ()->new Pesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "minhafoto://minhafoto"));
    }

    @Test
    void setNome() {
        this.pesquisador1.setNome("Walter");
        assertEquals("Walter (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                " Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou" +
                " um premio nobel. - breakingbad@2008 - https://iamthedanger", this.pesquisador1.toString());
    }

    @Test
    void setNomeNullOuVazio() {
        assertThrows(IllegalArgumentException.class, ()->this.pesquisador1.setNome(""));
        assertThrows(NullPointerException.class, ()->this.pesquisador1.setNome(null));
    }

    @Test
    void setFuncao() {
        this.pesquisador1.setFuncao("externo");
        assertEquals("heisenberg (externo) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                " Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou" +
                " um premio nobel. - breakingbad@2008 - https://iamthedanger", this.pesquisador1.toString());
    }

    @Test
    void setFuncaoNullOuVazio() {
        assertThrows(IllegalArgumentException.class, ()->this.pesquisador1.setFuncao(""));
        assertThrows(NullPointerException.class, ()->this.pesquisador1.setFuncao(null));
    }


    @Test
    void setBiografia() {
        this.pesquisador1.setBiografia("Interresado nos efeitos da metafetamina e no estudo sobre o cancer.");
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                " - breakingbad@2008 - https://iamthedanger", this.pesquisador1.toString());
    }

    @Test
    void setBiografiaNullOuVazio() {
        assertThrows(IllegalArgumentException.class, ()->this.pesquisador1.setBiografia(""));
        assertThrows(NullPointerException.class, ()->this.pesquisador1.setBiografia(null));
    }

    @Test
    void setEmail() {
        this.pesquisador1.setEmail("breakingbad@2009");
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                " Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou" +
                " um premio nobel. - breakingbad@2009 - https://iamthedanger", this.pesquisador1.toString());
    }

    @Test
    void setEmailNullOuVazio() {
        assertThrows(IllegalArgumentException.class, ()->this.pesquisador1.setEmail(""));
        assertThrows(NullPointerException.class, ()->this.pesquisador1.setEmail(null));
    }

    @Test
    void setEmailInvalido() {
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.setEmail("aa"));
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.setEmail("1@"));
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.setEmail("@a"));
    }

    @Test
    void setFoto() {
        this.pesquisador1.setFotoURL("https://iatd");
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                " Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou" +
                " um premio nobel. - breakingbad@2008 - https://iatd", this.pesquisador1.toString());
    }

    @Test
    void setFotoNullOuVazio() {
        assertThrows(IllegalArgumentException.class, ()->this.pesquisador1.setFotoURL(""));
        assertThrows(NullPointerException.class, ()->this.pesquisador1.setFotoURL(null));
    }

    @Test
    void setFotoInvalida() {
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.setFotoURL("://minhafoto"));
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.setFotoURL("https/minhafoto"));
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.setFotoURL("minhafoto://minhafoto"));
    }

    @Test
    void ativa() {
        this.pesquisador1.desativa();
        assertEquals(false, this.pesquisador1.isAtivo());
        this.pesquisador1.ativa();
        assertEquals(true, this.pesquisador1.isAtivo());
    }

    @Test
    void ativaPesquisadorAtivo() {
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.ativa());
    }

    @Test
    void desativa() {
        assertEquals(true, this.pesquisador1.isAtivo());
        this.pesquisador1.desativa();
        assertEquals(false, this.pesquisador1.isAtivo());
    }

    @Test
    void desativaPesquisadorDesativado() {
        this.pesquisador1.desativa();
        assertThrows(IllegalArgumentException.class, ()-> this.pesquisador1.desativa());
    }

    @Test
    void isAtivo() {
        assertEquals(true, this.pesquisador1.isAtivo());
        this.pesquisador1.desativa();
        assertEquals(false, this.pesquisador1.isAtivo());
        this.pesquisador1.ativa();
        assertEquals(true, this.pesquisador1.isAtivo());
    }

    @Test
    void toString1() {
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                " Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel." +
                " - breakingbad@2008 - https://iamthedanger", this.pesquisador1.toString());
    }

    @Test
    void equals1() {
        assertEquals(new Pesquisador("Walter", "externo", "Antigo professor",
                "breakingbad@2008", "https://foto"), this.pesquisador1);
    }

    @Test
    void hashCode1(){
        assertEquals(new Pesquisador("Walter", "externo", "Antigo professor",
                "breakingbad@2008", "https://foto").hashCode(), this.pesquisador1.hashCode());
    }
}