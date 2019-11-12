package psquiza;

/**
 * Representacao de um resultado obtido por uma pesquisa, que e diretamente associado
 * a atividade.
 */
public class Resultado {

    /**
     * Descreve o resultado da atividade.
     */
    private String descricaoResultado;

    /**
     * Constroi um resultado por meio de sua descricao.
     * @param descricaoResultado e a descricao do resultado
     */
    public Resultado(String descricaoResultado) {
        this.descricaoResultado = descricaoResultado;
    }

    /**
     * Representacao de um resultado como objeto.
     * @return o objeto Resultado em formado string
     */
    public String toString() {
        return this.descricaoResultado;
    }
}
