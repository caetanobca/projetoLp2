package psquiza.atividade;

import psquiza.atividade.Atividade;

import java.util.Comparator;

/**
 * Compara duas atividade com base na duracao dos seus itens.
 */
public class ComparadorAtividadePorDuracao implements Comparator<Atividade> {

    @Override
    public int compare(Atividade a1, Atividade a2){
        int duracao1, duracao2;
        duracao1 = a1.getDuracao();
        duracao2 = a2.getDuracao();
        if (duracao1 < duracao2) {
            return 1;
        } else if (duracao1 > duracao2) {
            return -1;
        } else{
            return a1.compareTo(a2);
        }
    }
}
