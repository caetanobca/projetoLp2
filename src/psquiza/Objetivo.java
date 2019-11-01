package psquiza;

import util.Validacao;

import java.util.Objects;

/**
 * Representacao de um objetivo, que dentro de uma pesquisa aplicada, trabalha para a resolucao do problema,
 * definindo a finalidade da pesquisa. Os objetivos sao divididos em dois subgrupos(Geral e Especifico),
 * onde os especificos sao delimitados e comumente estruturados dentro de um objetivo geral, este que por sua vez, busca
 * de maneira mais abrangente e que responde diretamente ao problema da pesquisa. Um objetivo geral, pode vir a
 * nao resolver totalmente o problema apresentado, mas e considerado como um passo na direcao da solucao de tal problema,
 * todos os objetivos devem ser claros,diretos e viaveis e sao caracterizados pelo seu tipo, descricao
 * aderencia e viabilidade.
 */
public class Objetivo {

    /**
     * E o tipo do objetivo que varia entre Geral ou Especifico.
     */
    private String tipo;

    /**
     * E a descricao do objetivo, que define a meta a ser alcancada.
     */
    private String descricao;

    /**
     * E a aderencia que o objetivo traz a solucao do problema.
     */
    private int aderencia;

    /**
     * E a viabilidade do objetivo, podendo variar de 1 a 5, onde 1 diz que o objetivo pode nao ser muito viavel
     * e 5 possui viabilidade maxima.
     */
    private int viabilidade;

    /**
     * E o identificador unico do objetivo, no formato O+1,O+2,O+3,... que sempre quando um novo objetivo e gerado
     * e gerado automaticamente.
     */
    private String id;

    /**
     * Atributo importado de uma classe externa, que possui metodos que auxiliam a nao permitir que valores que causem
     * erros sejam atribuidos as variaveis, sejam nulos,vazios ou quaisquer outro tipos de excessoes.
     */
    private Validacao validacao = new Validacao();

    /**
     * Constroi um objetivo, por meio de seu tipo,descricao,aderencia e viabilidade.
     * @param tipo e o tipo do objetivo
     * @param descricao e a descricao do objetivo
     * @param aderencia e a aderencia ao problema do objetivo
     * @param viabilidade e a viabilidade do objetivo
     * @param id e o id do objetivo e seu identificador unico
     */
    public Objetivo(String tipo,String descricao,int aderencia,int viabilidade,String id){
        validacao.validaNulleVazio(tipo,"Campo tipo nao pode ser nulo ou vazio.");
        validacao.validaNulleVazio(descricao,"Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidadeOuAderencia(aderencia,"Valor invalido de aderencia");
        validacao.validaViabilidadeOuAderencia(viabilidade,"Valor invalido de viabilidade.");
        if((!tipo.equals("GERAL")) && (!tipo.equals("ESPECIFICO"))) {
            validacao.lancaExcecao("Valor invalido de tipo.");
        }
        this.tipo = tipo;
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
        this.id = id;
    }

    /**
     * Apresenta a String no formato "TIPO - DESCRICAO - VALOR". Onde o valor e a soma
     * aritmetica da viabilidade com a aderencia do objetivo.
     * @return a representacao de um objetivo como objeto
     */
    @Override
    public String toString() {
        return this.tipo+" - "+this.descricao+" - "+(this.viabilidade+this.aderencia);
    }

    /**
     * Verifica se o objetivo atual eh igual a outro com base em seus id's. Cada objetivo
     * eh identificado unicamente por seu id.
     *
     * @param o Objeto a ser comparado
     * @return Variavel booleana, false caso seja diferente, true caso seja igual
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Objetivo objetivo = (Objetivo) o;
        return id.equals(objetivo.id);
    }

    /**
     * Retorna inteiro unico baseado no id do objetivo.
     *
     * @return inteiro unico baseado no id que identifica o objetivo
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}