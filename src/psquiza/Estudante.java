package psquiza;

public class Estudante extends Pesquisador {

    private int semestre;
    private double IEA;
    private boolean especializado;

    /**
     * Constroi um pesquisador a partir de seu nome, funcao, biografia, email valido,
     * URL da foto valida e suas respectivas pesquisas.
     * Caso qualquer atributo esteja invalido, uma excecao sera lancada.
     *
     * @param nome      o nome do pesquisador
     * @param funcao    a funcao do pesquisador (estudante, professor ou externo)
     * @param biografia a biografia do pesquisador
     * @param email     o email do pesquisador
     * @param fotoURL   a foto do pesquisador
     */
    public Estudante(String nome, String funcao, String biografia, String email, String fotoURL) {
        super(nome, funcao, biografia, email, fotoURL);
        this.semestre = semestre;
        this.IEA = IEA;
        this.especializado = false;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setIEA(double IEA) {
        this.IEA = IEA;
    }

    public boolean isEspecializado() {
        return especializado;
    }

    public void setEspecializado(boolean especializado) {
        this.especializado = especializado;
    }

    public String exibeEstudanteEspecializado() {
        return super.toString()+" - "+this.semestre+" - "+this.IEA;
    }
}
