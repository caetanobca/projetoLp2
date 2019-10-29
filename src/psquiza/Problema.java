package psquiza;

/**
 * Representacao de um problema que pode vir a ser objeto de pesquisa para um pesquisador. Para que
 * o problema se torne interessante, e seja objeto de pesquisa, ele deve abordar aspectos que apresentam
 * fatos observaveis,deve ser possivel ver e caracterizar o problema, ser definido especifico,porem ligado a uma
 * area maior,ser passivel de tratamento experimental, despertar curiosidade do pesquisador, e ser relevante
 * para a comunidade.
 */
public class Problema {
    /**
     * E a descricao do problema, que busca descrever o tema que um problema sera abordado.
     */
    private String descricao;

    /**
     * E a viabilidade do problema, que e variavel de um nivel de 1 a 5, onde 1 quer dizer que o problema
     * nao possui muita viabilidade, e 5 viabilidade maxima.
     */
    private int viabilidade;

    /**
     * Constroi um problema por meio de sua respectiva descricao e viabilidade.
     * @param descricao e a descricao do problema
     * @param viabilidade e a viabilidade do problema
     */
    public Problema(String descricao,int viabilidade){
        this.descricao = descricao;
        this.viabilidade = viabilidade;
    }

    @Override
    public String toString() {
        return this.descricao+" - "+this.viabilidade;
    }
}
