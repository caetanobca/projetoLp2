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
     * E o identificador unico do problema, no formato P+1,P+2,P+3,... que sempre quando um novo problema e gerado
     * e gerado automaticamente.
     */
    private String id;

    /**
     * Constroi um problema por meio de sua respectiva descricao e viabilidade.
     * @param descricao e a descricao do problema
     * @param viabilidade e a viabilidade do problema
     * @param id e o id do problema, que e seu identificador unico
     */
    public Problema(String descricao,int viabilidade,String id){
        this.descricao = descricao;
        this.viabilidade = viabilidade;
        this.id = id;
    }

    /**
     * Representação de um problema como objeto, no formato "DESCRICAO - VIABILIDADE".
     * @return a string que representa o problema
     */
    @Override
    public String toString() {
        return this.descricao+" - "+this.viabilidade;
    }
}