package psquiza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import psquiza.atividade.Risco;

import static org.junit.jupiter.api.Assertions.*;

class RiscoTest {

    private Risco risco1;
    private Risco risco2;
    private Risco risco3;

    @BeforeEach
    public void criaRisco() {
        this.risco1 = new Risco("BAIXO","Degustadores de chocolate, podem sofrer hipertensao");
        this.risco2 = new Risco("MEDIO","Assistentes Sociais podem sofrer violencia verbal");
        this.risco3 = new Risco("ALTO","Fumantes podem ter depressao ao tentar parar de fumar");
    }

    @Test
    public void testaConstroiRisco() {
        Risco riscoBaixo = new Risco("bAiXo","Degustadores de chocolate, podem sofrer hipertensao");
        Risco riscoMedio = new Risco("medio","Assistentes Sociais podem sofrer violencia verbal");
        Risco ricoAlto = new Risco("ALTO","Fumantes podem ter depressao ao tentar parar de fumar");
    }

    @Test
    public void testaConstroiRiscoComExcecoes() {
        assertThrows(IllegalArgumentException.class,()-> new Risco("","Medicos podem sofrer depressao por stress hospitalar"));
        assertThrows(NullPointerException.class,()-> new Risco(null,"Professores podem sofrer agressoes fisicas e verbais"));
        assertThrows(IllegalArgumentException.class,()-> new Risco("MEDIO",""));
        assertThrows(NullPointerException.class,()-> new Risco("BAIXO",null));
        assertThrows(IllegalArgumentException.class,()-> new Risco("QUALQUER VALOR DIFERENTE DE ALTO MEDIO OU BAIXO","erro"));
    }

    @Test
    public void testaToString() {
        assertEquals(this.risco1.toString(),"BAIXO - Degustadores de chocolate, podem sofrer hipertensao");
        assertEquals(this.risco2.toString(),"MEDIO - Assistentes Sociais podem sofrer violencia verbal");
        assertEquals(this.risco3.toString(),"ALTO - Fumantes podem ter depressao ao tentar parar de fumar");
    }
}