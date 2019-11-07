package psquiza;

import java.util.Comparator;

/**
 * Comparator que utiliza o Problema associado a fim de comparar duas Pesquisas. A Pesquisa com o Problema associado
 * de maior Id vem primeiro. Para Pesquisas sem Problema associado deve-se ordernar da Pesquisa de maior Id para a Pesquisa
 * com menor Id.
 */
public class ComparadorPesquisaPorProblema implements Comparator<Pesquisa> {

    /**
     * Metodo responsavel por receber duas pesquisas e compara-las de acordo com criterio descrito acima.
     * @param p1 Objeto do tipo Pesquisa
     * @param p2 Objeto do tipo Pesquisa a ser comparado com outro Objeto do mesmo tipo.
     * @return Um numero inteiro referente a quem vem primeiro na ordem de comparação.
     */
    public int compare(Pesquisa p1, Pesquisa p2) {

        String nomeProblema1 = p1.getProblemaAssociado().getId();
        String nomeProblema2 = p1.getProblemaAssociado().getId();

        return nomeProblema2.compareTo(nomeProblema1);

    }


}
