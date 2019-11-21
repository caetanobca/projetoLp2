package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.pesquisador.ControllerPesquisador;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerPesquisadorTest {

    private ControllerPesquisador controllerPesquisador;

    @BeforeEach
    void setUp() {
        this.controllerPesquisador = new ControllerPesquisador();

        // Cadastrando alguns Pesquisadores
        this.controllerPesquisador.cadastraPesquisador("heisenberg", "professor",
                "Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da " +
                        "pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.",
                "breakingbad@2008", "https://iamthedanger");
        this.controllerPesquisador.cadastraPesquisador("Fernando", "estudante", "Ex-sofredor de EE, atualmente sofrendo com moderacao em CC.", "fernando.costa@ccc.ufcg.edu.br", "https://imgur.com.br");
        this.controllerPesquisador.cadastraPesquisador("Externo da Silva Souza", "externo", "Estou aqui para dar uma olhada na UFCG, talvez tirar umas fotos.", "externo@hotmail.com","https://google.com/imagens");
    }

    @Test
    void cadastraPesquisador() {
        this.controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto");
        assertEquals("Caetano (Estudante) - Sofrido estudante de cc - caetano.albuquerque@ccc.ufcg.edu.br - " +
                        "https://minhafoto",
                this.controllerPesquisador.exibePesquisador("caetano.albuquerque@ccc.ufcg.edu.br"));
    }

    @Test
    void cadastraPesquisadorNomeNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraPesquisador(null, "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorFuncaoNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano", "",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano", null,
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorBiografiaNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                null, "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorEmailNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "", "https://minhafoto"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", null, "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorEmailInvalido() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "aa", "https://minhafoto"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "1@", "https://minhafoto"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "@a", "https://minhafoto"));
    }

    @Test
    void cadastraPesquisadorFotoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "://minhafoto"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https/minhafoto"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "minhafoto://minhafoto"));
    }

    @Test
    void cadastraPesquisadorFotoNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano",
                "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", ""));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", null));
    }

    @Test
    void alteraPesquisadorNome() {
        this.controllerPesquisador.alteraPesquisador("breakingbad@2008", "NOME", "Walter");
        assertEquals("Walter (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. " +
                        "Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que " +
                        "ganhou um premio nobel." +
                        " - breakingbad@2008 - https://iamthedanger",
                this.controllerPesquisador.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void alteraPesquisadorFuncao() {
        this.controllerPesquisador.alteraPesquisador("breakingbad@2008", "FUNCAO", "externo");
        assertEquals("heisenberg (externo) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. " +
                        "Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que " +
                        "ganhou um premio nobel." +
                        " - breakingbad@2008 - https://iamthedanger",
                this.controllerPesquisador.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void alteraPesquisadorBiografia() {
        this.controllerPesquisador.alteraPesquisador("breakingbad@2008", "BIOGRAFIA", "Interresado nos efeitos da " +
                "metafetamina");
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina" +
                        " - breakingbad@2008 - https://iamthedanger",
                this.controllerPesquisador.exibePesquisador("breakingbad@2008"));

    }

    @Test
    void alteraPesquisadorEmail() {
        this.controllerPesquisador.alteraPesquisador("breakingbad@2008", "EMAIL", "breakingbad@2009");
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. " +
                        "Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que " +
                        "ganhou um premio nobel." +
                        " - breakingbad@2009 - https://iamthedanger",
                this.controllerPesquisador.exibePesquisador("breakingbad@2009"));
    }

    @Test
    void alteraPesquisadorFoto() {
        this.controllerPesquisador.alteraPesquisador("breakingbad@2008", "FOTO", "https://Cordyceps");
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. " +
                        "Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que " +
                        "ganhou um premio nobel." +
                        " - breakingbad@2008 - https://Cordyceps",
                this.controllerPesquisador.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void alteraPesquisadorEmailNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("",
                "NOME", "Walter"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.alteraPesquisador(null,
                "NOME", "Walter"));
    }

    @Test
    void alteraPesquisadoAtributoNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("breakingbad@2008",
                "", "Walter"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.alteraPesquisador("breakingbad@2008",
                null, "Walter"));
    }

    @Test
    void alteraPesquisadorNovoValorNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("breakingbad@2008",
                "NOME", ""));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.alteraPesquisador("breakingbad@2008",
                "NOME", null));
    }

    @Test
    void alteraPesquisadorNaoCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("nao@cadastrado",
                "NOME", "nao"));
    }

    @Test
    void alteraPesquisadorDesativo() {
        this.controllerPesquisador.desativaPesquisador("breakingbad@2008");
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("breakingbad@2008",
                "NOME", "Walter"));
    }

    @Test
    void alteraPesquisadorFuncaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("breakingbad@2008",
                "Telefone", "99999999"));
    }

    @Test
    void ativaPesquisador() {
        this.controllerPesquisador.desativaPesquisador("breakingbad@2008");
        assertFalse(this.controllerPesquisador.pesquisadorEhAtivo("breakingbad@2008"));
        this.controllerPesquisador.ativaPesquisador("breakingbad@2008");
        assertTrue(this.controllerPesquisador.pesquisadorEhAtivo("breakingbad@2008"));
    }

    @Test
    void ativaPesquisadorEmaiNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.ativaPesquisador(""));
        assertThrows(NullPointerException.class, () -> this.controllerPesquisador.ativaPesquisador(null));
    }

    @Test
    void ativaPesquisadorNaoCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.ativaPesquisador("nao" +
                "@cadastrado"));
    }

    @Test
    void ativaPesquisadorAtivo() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.ativaPesquisador("breakingbad" +
                "@2008"));
    }

    @Test
    void desativaPesquisador() {
        assertTrue(this.controllerPesquisador.pesquisadorEhAtivo("breakingbad@2008"));
        this.controllerPesquisador.desativaPesquisador("breakingbad@2008");
        assertFalse(this.controllerPesquisador.pesquisadorEhAtivo("breakingbad@2008"));
    }

    @Test
    void desativaPesquisadorEmailNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.desativaPesquisador(""));
        assertThrows(NullPointerException.class, () -> this.controllerPesquisador.desativaPesquisador(null));
    }

    @Test
    void desativaPesquisadorNaoCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.desativaPesquisador("nao" +
                "@cadastrado"));
    }

    @Test
    void desativaPesquisadorDesativado() {
        this.controllerPesquisador.desativaPesquisador("breakingbad@2008");
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.desativaPesquisador(
                "breakingbad@2008"));
    }


    @Test
    void exibePesquisador() {
        assertEquals("heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer." +
                        " Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que" +
                        " ganhou um premio nobel. - breakingbad@2008 - https://iamthedanger",
                this.controllerPesquisador.exibePesquisador("breakingbad@2008"));
    }

    @Test
    void exibePesquisadorEmailNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.exibePesquisador(""));
        assertThrows(NullPointerException.class, () -> this.controllerPesquisador.exibePesquisador(null));
    }

    @Test
    void exibePesquisadorNaoCadastrado() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.exibePesquisador("nao" +
                "@cadastrado"));
    }

    @Test
    void pesquisadorEhAtivo() {
        assertTrue(this.controllerPesquisador.pesquisadorEhAtivo("breakingbad@2008"));
        this.controllerPesquisador.desativaPesquisador("breakingbad@2008");
        assertFalse(this.controllerPesquisador.pesquisadorEhAtivo("breakingbad@2008"));
    }

    @Test
    void pesquisadorEhEmailAtivoNulleVazio() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.pesquisadorEhAtivo(""));
        assertThrows(NullPointerException.class, () -> this.controllerPesquisador.pesquisadorEhAtivo(null));
    }

    @Test
    void pesquisadorEhAtivoNaoCadstrado() {
        assertThrows(IllegalArgumentException.class, () -> this.controllerPesquisador.pesquisadorEhAtivo("nao" +
                "@cadastrado"));
    }

    @Test
    void buscaTest() {
        this.controllerPesquisador.cadastraPesquisador("Caetano", "Estudante",
                "Sofrido estudante de cc", "caetano.albuquerque@ccc.ufcg.edu.br", "https://minhafoto");
        this.controllerPesquisador.cadastraPesquisador("Djonga", "estudante", "Estudante de História, negro, " +
                "anti-fascista, mineiro e da favela. Rapper que costuma lançar discos e músicas muito boas!", "djonga" +
                "@rapper.com", "http://djongaehfoda.com");

        List<String> resultado = new ArrayList<>();

        resultado.add("fernando.costa@ccc.ufcg.edu.br: Ex-sofredor de EE, atualmente sofrendo com moderacao em CC.");
        resultado.add("externo@hotmail.com: Estou aqui para dar uma olhada na UFCG, talvez tirar umas fotos.");
        resultado.add("djonga@rapper.com: Estudante de História, negro, anti-fascista, mineiro e da favela. Rapper que costuma lançar discos e músicas muito boas!");
        resultado.add("caetano.albuquerque@ccc.ufcg.edu.br: Sofrido estudante de cc");
        resultado.add("breakingbad@2008: Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.");

        assertEquals(resultado, controllerPesquisador.busca("a"));

        List<String> resultado2 = new ArrayList<>();

        resultado2.add("djonga@rapper.com: Estudante de História, negro, anti-fascista, mineiro e da favela. Rapper que costuma lançar discos e músicas muito boas!");

        assertEquals(resultado2, controllerPesquisador.busca("anti"));

        List<String> resultado3 = new ArrayList<>();

        resultado3.add("caetano.albuquerque@ccc.ufcg.edu.br: Sofrido estudante de cc");

        assertEquals(resultado3, controllerPesquisador.busca("frido"));

        List<String> resultado4 = new ArrayList<>();

        resultado4.add("breakingbad@2008: Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel.");

        assertEquals(resultado4, controllerPesquisador.busca("nobel"));
    }

    @Test
    void buscaTestTermoVaziouOuNull(){
        assertThrows(IllegalArgumentException.class, ()-> controllerPesquisador.busca(""));
        assertThrows(NullPointerException.class, ()-> controllerPesquisador.busca(null));
    }


    @Test
    void cadastraEspecialidadeProfessor(){
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","28/05/2002");
    }


    @Test
    void cadastraEspecialidadeProfessorComInformacoesVaziasOuNulas(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("","Doutor", "UAEQ","28/05/2002"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor(null,"Doutor", "UAEQ","28/05/2002"));
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","", "UAEQ","28/05/2002"));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008",null, "UAEQ","28/05/2002"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "","28/05/2002"));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", null,"28/05/2002"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ",""));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ",null));
    }

    @Test
    void cadastraEspecialidadeProfessorInexistente(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("trollei@hotmail.com","Bacharel", "DSC", "02/02/2012"));
    }

    @Test
    void cadastraEspecialidadeProfessorComDataInvalida(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutora", "UAEQ","01012011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","0101/2011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","01/012011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","01/01/11"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","01/01/20011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","010/01/2011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","01/010/2011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","01/17/2011"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","45/01/2011"));
    }

    @Test
    void cadastraEspecialidadeProfessorEmAlunoOuExterno(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("externo@hotmail.com", "Doutor", "DSC", "28/05/2018"));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeProfessor("fernando.costa@ccc.ufcg.edu.br", "Doutor", "DSC", "12/05/2018"));
    }

    @Test
    void cadastraEspecialidadeAluno(){
        controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br", 2, 7);
    }

    @Test
    void cadastraEspecialidadeAlunoInexistente(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("trollei@hotmail.com",2, 10));
    }

    @Test
    void cadastraEspecialidadeAlunoComEmailVazioOuNulo(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("",1, 5));
        assertThrows(NullPointerException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno(null,1, 5));
    }

    @Test
    void cadastraEspecialidadeAlunoComSemestreInvalido(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",0, 5));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",-1, 5));
    }

    @Test
    void cadastraEspecialidadeAlunoComIeaInvalido(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",4, -3.5));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",3, 12));
    }

    @Test
    void cadastraEspecialidadeAlunoEmProfessorOuExterno(){
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("breakingbad@2008",2, 9.3));
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.cadastraEspecialidadeAluno("externo@hotmail.com",4, 2.3));
    }


    @Test
    void listaPesquisadores(){
        System.out.println(controllerPesquisador.listaPesquisadores("EXTERNO"));
        System.out.println(controllerPesquisador.listaPesquisadores("PROFESSORA"));
        System.out.println(controllerPesquisador.listaPesquisadores("ALUNA"));
    }

    @Test
    void alteraProfessor(){
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutor", "UAEQ","28/05/2002");

        controllerPesquisador.alteraPesquisador("breakingbad@2008", "FORMACAO", "PhD");
        assertEquals(controllerPesquisador.exibePesquisador("breakingbad@2008"), "heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel. - breakingbad@2008 - https://iamthedanger - PhD - UAEQ - 28/05/2002");

        controllerPesquisador.alteraPesquisador("breakingbad@2008","DATA", "02/02/2018");
        assertEquals(controllerPesquisador.exibePesquisador("breakingbad@2008"), "heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel. - breakingbad@2008 - https://iamthedanger - PhD - UAEQ - 02/02/2018");

        controllerPesquisador.alteraPesquisador("breakingbad@2008","UNIDADE", "DSC");
        assertEquals(controllerPesquisador.exibePesquisador("breakingbad@2008"), "heisenberg (professor) - Interresado nos efeitos da metafetamina e no estudo sobre o cancer. Pesquisador principal da pesquisa de radigrafia a fotons, peca fundamental na pesquisa que ganhou um premio nobel. - breakingbad@2008 - https://iamthedanger - PhD - DSC - 02/02/2018");
    }


    @Test
    void alteraProfessorEmailVazioOuNulo(){
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "UAEQ","28/05/2002");
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("", "FORMACAO", "PhD"));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.alteraPesquisador(null, "FORMACAO", "PhD"));
    }

    @Test
    void alteraProfessorAtributoVazioOuNulo(){
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "UAEQ","28/05/2002");
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("breakingbad@2008", "", "PhD"));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.alteraPesquisador("breakingbad@2008", null, "PhD"));
    }

    @Test
    void alteraProfessorAtributoInvalido(){
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "UAEQ","28/05/2002");
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("breakingbad@2008", "FORCA MENTAL", "PhD"));
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("breakingbad@2008", "43232", "PhD"));
    }

    @Test
    void alteraProfessorNovoValorVazioOuNulo(){
        controllerPesquisador.cadastraEspecialidadeProfessor("breakingbad@2008","Doutorado", "UAEQ","28/05/2002");
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("breakingbad@2008", "FORMACAO", ""));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.alteraPesquisador("breakingbad@2008", "FORMACAO", null));
    }

    @Test
    void alteraAluno(){
        controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",2, 7);
        controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "SEMESTRE", "3");
        assertEquals(controllerPesquisador.exibePesquisador("fernando.costa@ccc.ufcg.edu.br"), "Fernando (estudante) - Ex-sofredor de EE, atualmente sofrendo com moderacao em CC. - fernando.costa@ccc.ufcg.edu.br - https://imgur.com.br - 3o SEMESTRE - 7.0");
        controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "IEA", "10");
        assertEquals(controllerPesquisador.exibePesquisador("fernando.costa@ccc.ufcg.edu.br"), "Fernando (estudante) - Ex-sofredor de EE, atualmente sofrendo com moderacao em CC. - fernando.costa@ccc.ufcg.edu.br - https://imgur.com.br - 3o SEMESTRE - 10.0");
    }

    @Test
    void alteraAlunoEmailVaziOuNulo(){
        controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",2, 7);
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("", "SEMESTRE", "3"));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.alteraPesquisador(null, "SEMESTRE", "PhD"));
    }

    @Test
    void alteraAlunoAtributoVaziOuNulo(){
        controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",2, 7);
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "", "3"));
        assertThrows(NullPointerException.class, () ->  controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", null, "PhD"));
    }

    @Test
    void alteraAlunoAtributoInvalido(){
        controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",2, 7);
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "VONTADE DE ESTUDAR", "0"));
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "432", "PhD"));
    }

    @Test
    void alteraAlunoNovoValorVazioOuNulo(){
        controllerPesquisador.cadastraEspecialidadeAluno("fernando.costa@ccc.ufcg.edu.br",2, 7);
        assertThrows(IllegalArgumentException.class, () -> controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "VONTADE DE ESTUDAR", ""));
        assertThrows(IllegalArgumentException.class, () ->  controllerPesquisador.alteraPesquisador("fernando.costa@ccc.ufcg.edu.br", "432", null));
    }
}