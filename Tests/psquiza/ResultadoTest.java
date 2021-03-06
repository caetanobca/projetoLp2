package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.atividade.Resultado;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultadoTest {

    private Resultado resultado1;
    private Resultado resultado2;

    @BeforeEach
    public void criaResultado() {
        resultado1 = new Resultado("Satisfatorio",1);
        resultado2 = new Resultado("Ruim",2);
    }

    @Test
    public void testaToString() {
        assertEquals(resultado1.toString(),"Satisfatorio");
        assertEquals(resultado2.toString(),"Ruim");
    }
}
