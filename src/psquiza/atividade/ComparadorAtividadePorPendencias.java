package psquiza.atividade;

import psquiza.atividade.Atividade;

import java.util.Comparator;

/**
 * Compara duas Atividades com base no numero de itens pendentes.
 */
public class ComparadorAtividadePorPendencias implements Comparator<Atividade> {

    @Override
    public int compare(Atividade a1, Atividade a2) {
        int pendentesA1 = a1.contaItensPendentes();
        int pendentesA2 = a2.contaItensPendentes();
        if (pendentesA1 < pendentesA2) {
            return -1;
        } else if (pendentesA1 > pendentesA2) {
            return 1;
        } else {
            return a1.compareTo(a2);
        }
    }
}

