package psquiza;

public class Professor extends Pesquisador {

    private String formacao;
    private String data;
    private String unidade;

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
    public Professor(String nome, String funcao, String biografia, String email, String fotoURL) {
        super(nome, funcao, biografia, email, fotoURL);
        this.data = data;
        this.formacao = formacao;
        this.unidade = unidade;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String exibeProfessorEspecializado(){
        return super.toString()+" - "+this.formacao+" - "+this.unidade+" - "+this.data;
    }
}
