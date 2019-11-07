package psquiza;

import util.Validacao;

import java.util.Objects;

/**
 * Representacao de um objetivo, que dentro de uma pesquisa aplicada, trabalha para a resolucao do problema,
 * definindo a finalidade da pesquisa. Os objetivos sao divididos em dois subgrupos(Geral e Especifico),
 * onde os especificos sao delimitados e comumente estruturados dentro de um objetivo geral, este que por sua vez, busca
 * de maneira mais abrangente e que responde diretamente ao problema da pesquisa. Um objetivo geral, pode vir a
 * nao resolver totalmente o problema apresentado, mas e considerado como um passo na direcao da solucao de tal
 * problema,
 * todos os objetivos devem ser claros,diretos e viaveis e sao caracterizados pelo seu tipo, descricao
 * aderencia e viabilidade.
 */
public class Objetivo {

    /**
     * E o tipo do objetivo que varia entre Geral ou Especifico.
     */
    private TipoObjetivo tipo;

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
     * Variavel que armazena se o objetivo é associado ou não em algum problema.
     */
    private boolean associado;

    /**
     * Atributo importado de uma classe externa, que possui metodos que auxiliam a nao permitir que valores que causem
     * erros sejam atribuidos as variaveis, sejam nulos,vazios ou quaisquer outro tipos de excessoes.
     */
    private Validacao validacao;

    /**
     * Constroi um objetivo, por meio de seu tipo,descricao,aderencia e viabilidade.
     *
     * @param tipo        e o tipo do objetivo
     * @param descricao   e a descricao do objetivo
     * @param aderencia   e a aderencia ao problema do objetivo
     * @param viabilidade e a viabilidade do objetivo
     * @param id          e o id do objetivo e seu identificador unico
     */
    public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String id) {
        this.validacao = new Validacao();
        validacao.validaNulleVazio(tipo, "Campo tipo nao pode ser nulo ou vazio.");
        validacao.validaNulleVazio(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidadeOuAderencia(aderencia, "Valor invalido de aderencia");
        validacao.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");
        validacao.validaTipoObjetivo(tipo, "Valor invalido de tipo");
        this.tipo = TipoObjetivo.valueOf(tipo);
        this.descricao = descricao;
        this.aderencia = aderencia;
        this.viabilidade = viabilidade;
        this.id = id;
        this.associado = false;

    }

    /**
     * Metodo de acesso a variavel associado, ou seja, o metodo informa se o Objetivo ja esta associado com alguma
     * Pesquisa.
     *
     * @return variavel booleana, true caso ja esteja associado a alguma pesquisa, false caso contrario.
     */
    public boolean getAssociado() {
        return associado;
    }

    /**
     * Metodo responsavel por alterar a variavel associado. Caso o Objetivo seja associado a alguma pesquisa, esse
     * metodo sera chamado e ira alterar a variavel associado para true. Caso seja desassociado, o metodo sera
     * chamado a fim de alterar a variavel associado para false
     *
     * @param associado um booleano com true ou false, true caso seja para associa-lo, ou false caso contrario.
     */
    public void setAssociado(boolean associado) {
        this.associado = associado;
    }

    /**
     * Metodo de acesso ao Id de Objetivo.
     * @return String com o Id do Objetivo.
     */
    public String getId() {
        return id;
    }

    /**
     * Apresenta a String no formato "TIPO - DESCRICAO - VALOR". Onde o valor e a soma
     * aritmetica da viabilidade com a aderencia do objetivo.
     *
     * @return a representacao de um objetivo como objeto
     */
    @Override
    public String toString() {
        return this.tipo + " - " + this.descricao + " - " + (this.viabilidade + this.aderencia);
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

    /**
     * Metodo responsavel por pegar a descricao do Objetivo.
     * @return descricao da Pesquisa.
     */
    public String getDescricao() {
        return descricao;
    }
}