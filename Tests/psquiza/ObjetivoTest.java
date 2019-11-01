package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ObjetivoTest {

    private Objetivo objetivo1, objetivo2, objetivoTeste;

    @BeforeEach
    void setUp() {
        objetivo1 = new Objetivo("GERAL", "Aumentar o interesse dos alunos em realizar testes nas aulas de programacao",5,5, "O1");
        objetivo2 = new Objetivo("ESPECIFICO", "Criar bonfiicacao aos melhores testadores de cada turma", 4, 5, "O2");
    }

    @Test
    void constroiObjetivo(){
        objetivoTeste = new Objetivo("ESPECIFICO", "Ter certeza de que os testes sao os mais especificos o possivel",2,3,"O3");
    }


    @Test
    void constroiObjetivoTipoNuloOuVazio(){
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("", "Checar se os tipos sao nulos os vazios",2,5,"O3"); });
        assertThrows(NullPointerException.class, () -> {
            objetivoTeste = new Objetivo(null, "Checar se os tipos sao nulos os vazios",5,1,"O4"); });
    }

    @Test
    void constroiObjetivoTipoInvalido(){
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("TREVOSO DAS TREVAS", "Checar se os tipos de objetivos sao validos",2,4,"O10"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("23", "Checar se os tipos de objetivos sao validos",2,4,"O12"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("gERaL", "Checar se os tipos de objetivos sao validos",2,4,"O4"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("eSpECIFIco", "Checar se os tipos de objetivos sao validos",2,4,"O3"); });
    }

    @Test
    void constroiObjetivoDescricaoVaziaOuNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "",3,3,"O3"); });
        assertThrows(NullPointerException.class, () -> {
            objetivoTeste = new Objetivo("ESPECIFICA", null,3,3,"O3"); });
    }

    @Test
    void constroiObjetivoAderenciaInvalida(){
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "Alguem deve ter grandes objetivos.",0,1,"O3"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "Alguem deve ter grandes objetivos.",6,1,"O3"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "Alguem deve ter grandes objetivos.",-2,1,"O3"); });
    }

    @Test
    void constroiObjetivoViabilidadeInvalida(){
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "Alguem deve ter grandes objetivos.",4,-1,"O3"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "Alguem deve ter grandes objetivos.",3,6,"O3"); });
        assertThrows(IllegalArgumentException.class, () -> {
            objetivoTeste = new Objetivo("GERAL", "Alguem deve ter grandes objetivos.",2,0,"O3"); });
    }

    @Test
    void testToString() {
        assertEquals(objetivo1.toString(), "GERAL - Aumentar o interesse dos alunos em realizar testes nas aulas de programacao - 10");
        assertEquals(objetivo2.toString(), "ESPECIFICO - Criar bonfiicacao aos melhores testadores de cada turma - 9");
    }

    @Test
    void testHashCode(){
        assertNotEquals(objetivo1.hashCode(),objetivo2.hashCode());
        objetivoTeste = new Objetivo("GERAL", "Nao falhar em nenhuma disciplina.",4,3,"O1");
        assertEquals(objetivo1.hashCode(),objetivoTeste.hashCode());
    }

    @Test
    void equalsComObjetivosComIdDiferentes(){
        assertFalse(objetivo1.equals(objetivo2));
    }

    @Test
    void equalsComObjetivoscomIdIguais(){
        objetivoTeste = new Objetivo("GERAL", "Nao falhar em nenhuma disciplina.",4,3,"O1");
        assertTrue(objetivo1.equals(objetivoTeste));
    }
}