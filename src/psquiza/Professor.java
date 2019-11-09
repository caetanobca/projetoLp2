package psquiza;

/**
 * Representacao de um pesquisador que e professor da UFCG, o professor extende o pesquisador, possuindo assim
 * todos os atributos e metodos de um pesquisador, alem disso o professor pode ter uma especialidade cadastrada e
 * quando tem, uma formacao, unidade e data sao adicionados a ele.
 */
public class Professor implements Especialidade {

    /**
     * E a formacao do professor.
     */
    private String formacao;

    /**
     * E a data da formacao do professor.
     */
    private String data;

    /**
     * E a unidade que o professor trabalha.
     */
    private String unidade;

    /**
     * Constroi um pesquisador a partir de seu nome, funcao, biografia, email valido,
     * URL da foto valida e suas respectivas pesquisas. Caso professor seja especializado
     * a data,formacao e unidade sao acrescentados a construcao do professor.
     * Caso qualquer atributo esteja invalido, uma excecao sera lancada.
     * @param nome      o nome do pesquisador
     * @param funcao    a funcao do pesquisador (estudante, professor ou externo)
     * @param biografia a biografia do pesquisador
     * @param email     o email do pesquisador
     * @param fotoURL   a foto do pesquisador
     */
    public Professor(String formacao, String unidade, String data) {
        this.data = data;
        this.formacao = formacao;
        this.unidade = unidade;
    }

    public void edita(String atributo, String valor){
        if (atributo.equals("DATA")){
            this.setData(valor);
        }else if (atributo.equals("FORMACAO")){
            this.setFormacao(valor);
        }else if (atributo.equals("UNIDADE")){
            this.setUnidade(valor);
        }
    }

    /**
     * Metodo que altera a formacao do professor
     * @param formacao e a nova formacao
     */
    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    /**
     * Metodo que altera a data de formacao de um professor
     * @param data e a nova data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Metodo que altera a unidade de um professor.
     * @param unidade e a nova unidade
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Representacao de um professor com especializacao como objeto, sao acrescentados a representacao
     * de um pesquisador a formacao,unidade e data.
     * @return a exibicao de um professor como objeto
     */
    public String toString(){
        return " - "+this.formacao+" - "+this.unidade+" - "+this.data;
    }
}
