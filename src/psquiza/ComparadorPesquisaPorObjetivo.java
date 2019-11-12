package psquiza;

import java.util.Comparator;

/**
 * Comparator que utiliza a quantidade de Objetivos para comparar duas Pesquisas. A Pesquisa com maior quantidade de
 * Objetivos vem primeiro, caso tenha duas Pesquisas com a mesma quantidade, lista primeiro a Pesquisa com Objetivo
 * com maior Id. Para as pesquisas sem objetivos, lista-se da pesquisa de maior ID para a de menor ID.
 */
public class ComparadorPesquisaPorObjetivo implements Comparator<Pesquisa> {

    /**
     * Metodo responsavel por receber duas pesquisas e compara-las de acordo com criterio descrito acima.
     * @param p1 Objeto do tipo Pesquisa
     * @param p2 Objeto do tipo Pesquisa a ser comparado com outro Objeto do mesmo tipo.
     * @return Um numero inteiro referente a quem vem primeiro na ordem de comparação.
     */
    @Override
    public int compare(Pesquisa p1, Pesquisa p2) {
        int retorno = 0;
        int qtdP1 = p1.getObjetivosAssociados().size();
        int qtdP2 = p2.getObjetivosAssociados().size();

        if (qtdP1 > qtdP2) {
            retorno = -1;
        } else if (qtdP1 < qtdP2) {
            retorno = 1;
        } else {
            for (int i = 0; i < p1.getObjetivosAssociados().size(); i++) {
                if (p1.getObjetivosAssociados().get(i).getId().compareTo(p2.getObjetivosAssociados().get(i).getId()) != 0) {
                    retorno =
                            p1.getObjetivosAssociados().get(i).getId().compareTo(p2.getObjetivosAssociados().get(i).getId());
                    break;

                }

            }
        }

        return retorno;
    }
}
