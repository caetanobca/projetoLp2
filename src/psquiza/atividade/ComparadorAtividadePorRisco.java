package psquiza.atividade;

import java.util.Comparator;

/**
 * Compara duas Atividades com base no Risco associado a ela.
 */
public class ComparadorAtividadePorRisco implements Comparator<Atividade> {

    @Override
    public int compare(Atividade a1, Atividade a2) {
        String risco1Token = a1.getNivelRisco();
        String risco2Token = a2.getNivelRisco();

        if(risco1Token.equals(risco2Token)){
            return a1.compareTo(a2);
        }
        int risco1 = 0, risco2 = 0;
        switch (risco1Token){
            case("BAIXO"):
                risco1 = 1;
                break;
            case("MEDIO"):
                risco1 = 2;
                break;
            case("ALTO"):
                risco1 = 3;
                break;
        }

        switch (risco2Token){
            case("BAIXO"):
                risco2 = 1;
                break;
            case("MEDIO"):
                risco2 = 2;
                break;
            case("ALTO"):
                risco2 = 3;
                break;
        }

        if (risco1 > risco2) {
            return -1;
        } else if (risco1 < risco2) {
            return 1;
        }
        return 0;
    }
}
