package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProblemaTest {

    private Problema problema1, problema2;

    @BeforeEach
    void setUp() {
        //problema1 = new Problema("A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao", 3);
        //problema2 = new Problema("A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG", 4);
    }

    @Test
    void testToString() {
        assertEquals(problema1.toString(), "A falta de paciencia durante a criacao de testes no estudantes da graduacao de computacao - 3");
        assertEquals(problema2.toString(), "A problematica da falta do RU na evasao escolar no estudantes de baixa renda na UFCG - 4");
    }
}