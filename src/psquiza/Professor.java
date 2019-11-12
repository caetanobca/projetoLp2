package psquiza;

import util.Validacao;

/**
 * Representacao de um pesquisador que e professor da UFCG, o professor implementa especialidade, possuindo
 * metodos da interface.
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
     * Objeto da classe validacao, que tem como objetivo verificar se o usuario
     * passou ao sistema algum valor invalido.
     */
    private Validacao validacao;

    /**
     * Constroi um professor por meio de sua formacao,unidade e data de formacao. Uma excecao
     * sera lancada caso usuario queira passar ao sistema algum desses 3 valores como nulo
     * ou vazio.
     * @param formacao e o titulo do professor
     * @param unidade e a unidade que o professor trabalha
     * @param data e a data de formacao do professor
     */
    public Professor(String formacao, String unidade, String data) {
        validacao.validaNulleVazio(formacao,"Campo formacao nao pode ser nulo ou vazio");
        validacao.validaNulleVazio(unidade,"Campo unidade nao pode ser nulo ou vazio");
        validacao.validaNulleVazio(data,"Campo data nao pode ser nulo ou vazio");
        this.data = data;
        this.formacao = formacao;
        this.unidade = unidade;
    }

    /**
     * Metodo que de acordo com a necessidade do usuario edita os atributos de um professor,atribuindo
     * um novo valor ao atributo desejado.Uma excecao e lancada caso usuario tente passar um valor
     * nulo ou vazio para o sistema.
     * @param atributo e o atributo que o usuario deseja alterar
     * @param valor e o novo valor
     */
    public void edita(String atributo, String valor){
        validacao.validaNulleVazio(atributo,"Campo atibuto nao pode ser nulo ou vazio");
        validacao.validaNulleVazio(valor,"Campo valor nao pode ser nulo ou vazio");
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
