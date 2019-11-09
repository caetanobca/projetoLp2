package psquiza;

/**
 * Representacao de um pesquisador que e um estudante da UFCG, o estudante extende o pesquisador, portanto
 * possui seus atributos e metodos, alem disso quando cadastrada a especialidade passa a possuir semestre e IEA.
 */
public class Estudante implements Especialidade {

    /**
     * E o semestre em que o aluno esta cursando.
     */
    private int semestre;

    /**
     * E o indice de evasao do aluno.
     */
    private double IEA;

    /**
     * Constroi um pesquisador a partir de seu nome, funcao, biografia, email valido,
     * URL da foto valida e suas respectivas pesquisas. Quando e cadastrada uma especialidade
     * em um aluno, um semestre e um IEA tambem sao construidos no mesmo.
     * Caso qualquer atributo esteja invalido, uma excecao sera lancada.
     * @param nome      o nome do pesquisador
     * @param funcao    a funcao do pesquisador (estudante, professor ou externo)
     * @param biografia a biografia do pesquisador
     * @param email     o email do pesquisador
     * @param fotoURL   a foto do pesquisador
     */
    public Estudante(int semestre, double IEA) {
        this.semestre = semestre;
        this.IEA = IEA;
    }

    public void edita(String atributo, String valor){
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
