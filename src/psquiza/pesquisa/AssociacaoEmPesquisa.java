package psquiza.pesquisa;

import psquiza.pesquisador.Pesquisador;
import psquiza.problema.Problema;
import psquiza.atividade.Atividade;
import psquiza.objetivo.Objetivo;
import util.Validacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AssociacaoEmPesquisa implements Serializable {

    /**
     * Problema associado a essa pesquisa.
     */
    private Problema problemaAssociado;

    /**
     * ArrayList com todos os objetivos da pesquisa.
     */
    private List<Objetivo> objetivos;

    /**
     * ArrayList com todas as atividades da Pesquisa.
     */
    private List<Atividade> atividades;


    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * Mapa que usa como chave a posicao de cadastro do pesquisador e guarda todos os
     * pesquisadores associados a pesquisa
     */
    private List<Pesquisador> pesquisadores;

    public AssociacaoEmPesquisa(){
        this.validador = new Validacao();

        this.objetivos = new ArrayList<>();
        this.atividades = new ArrayList<>();
        this.pesquisadores = new ArrayList<>();

    }

    /**
     * Metodo de acesso ao Problema associado a Pesquisa.
     *
     * @return Um Objeto do tipo Problema
     */
    public Problema getProblemaAssociado() {
        return problemaAssociado;
    }

    /**
     * Metodo de acesso ao ArrayList de Objetivos associados a Pesquisa.
     *
     * @return ArrayList de Objetivo.
     */
    public List<Objetivo> getObjetivosAssociados() {
        return objetivos;
    }



    /**
     * Retorna as Atividades associadas a essa pesquisa.
     * @return as Atividades associadas a essa pesquisa
     */
    public List<Atividade> getAtividades() {
        return atividades;
    }



    /**
     * Metodo responsvel por receber um Problema como parametro, verificar se a Pesquisa ja tem associacao com algum
     * problema, e caso nao tenha,  associa-lo a Pesquisa.
     *
     * @param problema Objeto Problema que sera associado a Pesquisa.
     * @return  variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaProblema(Problema problema) {
        boolean associou;


        if (this.problemaAssociado != null && !"".equals(this.problemaAssociado)) {

            if (this.problemaAssociado.equals(problema)) {
                associou = false;
            } else {
                validador.lancaExcecao("Pesquisa ja associada a um problema.");
                associou = false;
            }

        } else {
            this.problemaAssociado = problema;
            associou = true;

        }
        return associou;
    }

    /**
     * Metodo responsavel por desassociar um Problema da Pesquisa em que ele estava associado, verificando se o Problema
     * passado por parametro e o mesmo que ja esta associado, caso seja, a desassociacao será realizada.
     * @return variavel booleana, true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaProblema() {
        boolean desassociou;
        if (this.problemaAssociado == null || "".equals(this.problemaAssociado)){

            desassociou = false;
        } else {
            this.problemaAssociado = null;
            desassociou = true;
        }

        return desassociou;
    }

    /**
     * Metodo responsvel por receber um Objetivo como parametro, verificar se o Objetivo ja tem associacao com alguma
     * Pesquisa, e caso nao tenha,  associa-lo a Pesquisa.
     *
     * @param objetivo Objeto Objetivo que sera associado a Pesquisa.
     * @return  variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaObjetivo(Objetivo objetivo) {
        boolean associou = false;

        if (!objetivos.contains(objetivo)) {
            if (objetivo.getAssociado() == false) {
                objetivos.add(objetivo);
                objetivo.setAssociado(true);
                associou = true;
            } else {
                validador.lancaExcecao("Objetivo ja associado a uma pesquisa.");
            }
        }

        return associou;
    }

    /**
     * Metodo responsavel por desassociar um Objetivo da Pesquisa em que ele estava associado, verificando se o Problema
     * passado por parametro esta no ArrayList de Objetivos associados, caso esteja, a desassociacao sera realizada.
     * @param objetivo Objeto Problema que sera desassociado a Pesquisa.
     * @return variavel booleana, true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaObjetivo(Objetivo objetivo) {
        boolean desassociou = false;

        if (objetivos.contains(objetivo)) {
            objetivos.remove(objetivo);
            objetivo.setAssociado(false);
            desassociou = true;
        }

        return desassociou;
    }

    /**
     * Associa uma Atividade da Pesquisa. Uma Atividade nao pode ser associada se
     *      * nao estiver associada em Pesquisa.
     *
     * @param atividade a Atividade a ser associada
     * @return valor booleano que representa o sucesso ou nao da associacao
     */
    public boolean associaAtividade(Atividade atividade) {
        if(atividades.contains(atividade)) {
            return false;
        } else{
            atividades.add(atividade);
            atividade.associa();
            return true;
        }
    }

    /**
     * Desassocia uma Atividade da Pesquisa. Uma Atividade nao pode ser desassociada se
     * nao estiver associada em Pesquisa.
     *
     * @param atividade a Atividade a ser dessasociada
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean desassociaAtividade(Atividade atividade){
        if (!atividades.contains(atividade)){
            return false;
        } else {
            atividades.remove(atividade);
            atividade.desassocia();
            return true;
        }


    }

    /**
     * Associa um Pesquisador a uma Pesquisa.
     * @param pesquisador Pesquisador a ser associado
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean associaPesquisador(Pesquisador pesquisador) {
        if (this.pesquisadores.contains(pesquisador)){
            return false;
        }
        this.pesquisadores.add(pesquisador);

        return true;
    }

    /**
     * Desassocia um Pesquisador de uma Pesquisa.
     * @param pesquisador Pesquisador a ser desassociado
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean desassociaPesquisador(Pesquisador pesquisador) {
        if (this.pesquisadores.contains(pesquisador)){
            this.pesquisadores.remove(pesquisador);
            return true;
        }
        return false;
    }

    /**
     * Retorna os pesquisadores associados a essa pesquisa
     * @return Pesquisadores associados a essa pesquisa
     */
    public List<Pesquisador> getPesquisadores(){
        return pesquisadores;
    }

}
