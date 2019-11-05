package psquiza;

import java.util.Comparator;

public class ComparadorPesquisaPorProblema implements Comparator<Pesquisa> {

    public int compare(Pesquisa p1, Pesquisa p2){

        String nomeProblema1 = p1.getProblemaAssociado().getId();
        String nomeProblema2 = p1.getProblemaAssociado().getId();

        return nomeProblema2.compareTo(nomeProblema1);

    }


}
