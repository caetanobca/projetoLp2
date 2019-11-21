package psquiza.pesquisador;

import util.Validacao;

import java.io.Serializable;

/**
 * Representacao de um pesquisador que e um estudante da UFCG, o estudante implementa especialidade , portanto
 * possui seus  metodos.
 */
public class Estudante implements Especialidade, Serializable {

    /**
     * E o semestre em que o aluno esta cursando.
     */
    private int semestre;

    /**
     * E o indice de evasao do aluno.
     */
    private double IEA;

    /**
     * Objeto da classe Validacao que tem como funcao verificar se algum
     * valor de excecao e passado no sistema.
     */
    private Validacao validacao;
    /**
     * Constroi um estudante a partir do semestre em que ele esta em curso
     * e o indice de evasao do aluno(IEA).
     * @param  semestre e o semestre que o aluno esta cursando
     * @param IEA e o indice de evasao do aluno
     */
    public Estudante(int semestre, double IEA) {
        this.validacao = new Validacao();
        this.semestre = semestre;
        this.IEA = IEA;
    }

    /**
     * Metodo que altera a especialidade de um estudante, podendo alterar
     * seu semestre ou IEA, dependendo do valor passado. Uma excecao sera lancada
     * caso usuario queira forneceder um atributo ou valor vazio ou nulo.
     * @param atributo e o que o usuario deseja alterar, IEA ou semestre
     * @param valor e o novo valor do atributo do estudante
     */
    public void edita(String atributo, String valor){
        validacao.validaNulleVazio(atributo,"Campo atributo nao pode ser vazio ou nulo");
        validacao.validaNulleVazio(valor,"Campo valor nao pode ser vazio ou nulo");
        if (atributo.equals("SEMESTRE")){
            this.setSemestre(Integer.parseInt(valor));
        }else if (atributo.equals("IEA")){
            this.setIEA(Double.parseDouble(valor));
        }
    }

    /**
     * Metodo que altera o semestre que o aluno esta cursando.
     * @param semestre e o semestre que o aluno ira comecar a cursar
     */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * Metodo que altera o IEA do aluno.
     * @param IEA e o novo indice do aluno
     */
    public void setIEA(double IEA) {
        this.IEA = IEA;
    }

    /**
     * Representacao do estudante especializado como objeto, nos quais sao incluidos semestre e IEA
     * junto a representacao de um pesquisador.
     * @return a representacao de um estudante
     */
    public String toString() {
        return " - "+this.semestre+"o SEMESTRE - "+this.IEA;
    }
}
