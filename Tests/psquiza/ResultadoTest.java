package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultadoTest {

    private Resultado resultado1;
    private Resultado resultado2;

    @BeforeEach
    public void criaResultado() {
        resultado1 = new Resultado("Satisfatorio");
        resultado2 = new Resultado("Ruim");
    }

    @Test
    public void testaToString() {
        assertEquals(resultado1.toString(),"Satisfatorio");
        assertEquals(resultado2.toString(),"Ruim");
    }
}
