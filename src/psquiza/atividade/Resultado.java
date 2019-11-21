package psquiza.atividade;

import java.io.Serializable;

/**
 * Representacao de um resultado obtido por uma pesquisa, que e diretamente associado
 * a atividade.
 */
public class Resultado implements Serializable {


    /**
     * Descreve o resultado da atividade.
     */
    private String descricaoResultado;

    /**
     * Indetificador unico do resultado
     */
    private int id;

    /**
     * Constroi um resultado por meio de sua descricao.
     * @param descricaoResultado e a descricao do resultado
     */
    public Resultado(String descricaoResultado,int id) {
        this.id = id;
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
