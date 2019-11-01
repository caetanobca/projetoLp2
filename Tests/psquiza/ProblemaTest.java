package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemaTest {

    private Problema problema1, problema2, problemaTeste;

    @BeforeEach
    void setUp() {
        problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao",3,"P1");
        problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4, "P2");
    }


    @Test
    void constroiProblema(){
        problemaTeste = new Problema("Descricao teste", 2,"P1");
    }

    @Test
    void constroiProblemaDescricaoVaziaOuNula(){
        assertThrows(IllegalArgumentException.class, () -> {
            problemaTeste = new Problema("", 1,"P3");});
        assertThrows(NullPointerException.class, () -> {
            problemaTeste = new Problema(null, 1,"P3");});
    }

    @Test
    void constroiProblemaViabilidadeInvalida(){
        assertThrows(IllegalArgumentException.class, () -> {
            problemaTeste = new Problema("Descricao teste", -1,"P3");});
        assertThrows(IllegalArgumentException.class, () -> {
            problemaTeste = new Problema("Descricao teste", 6,"P3");});
        assertThrows(IllegalArgumentException.class, () -> {
            problemaTeste = new Problema("Descricao teste", 0,"P3");});
    }

    @Test
    void constroiProblemaIdVazioOuNUlo(){
        assertThrows(IllegalArgumentException.class, () -> {
            problemaTeste = new Problema("Descricao teste", -1,"");});
        assertThrows(IllegalArgumentException.class, () -> {
            problemaTeste = new Problema("Descricao teste", 6,null);});
    }

    @Test
    void testToString() {
        assertEquals(problema1.toString(), "A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao - 3");
        assertEquals(problema2.toString(), "A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG - 4");
    }

    @Test
    void testHashCode(){
        assertNotEquals(problema1.hashCode(),problema2.hashCode());
        problemaTeste = new Problema("Descricao teste", 2,"P1");
        assertEquals(problema1.hashCode(),problemaTeste.hashCode());
    }

    @Test
    void equalsProblemasComIdDiferentes(){
        assertFalse(problema1.equals(problema2));
        problemaTeste = new Problema("Descricao teste", 2,"P1");
        assertFalse(problemaTeste.equals(problema2));
    }

    @Test
    void equalsProblemasComIdIguais(){
        problemaTeste = new Problema("Descricao teste", 2,"P1");
        assertTrue(problema1.equals(problemaTeste));
    }

}