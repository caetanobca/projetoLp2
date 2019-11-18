package psquiza;

import util.Validacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma pesquisa, que tem descricao, campo de interesse, codigo, e uma variavel que indica
 * se esta ativa ou encerrada
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable {

    /**
     * Problema associado a essa pesquisa.
     */
    private Problema problemaAssociado;

    /**
     * Um texto livre com um resumo da pesquisa a ser realizada.
     */
    private String descricao;

    /**
     * Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados por vírgula e ter até 255
     * caracteres.
     */
    private String campoDeInteresse;

    /**
     * Codiogo gerado automaticamente pelos 3 primeiros caracteres do campo de interesse e um inteiro. O codigo e o
     * indentificador unico.
     */
    private String codigo;

    /**
     * Variavel do tipo boolean, que assume o valor true caso a pesquisa esteja ativa e false caso esteja encerrada.
     */
    private boolean ativada;

    /**
     * Um texto livre com um resumo do motivo da pesquisa ter sido encerrada.
     */
    private String motivoDesativacao;

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
     * Construtor da Classe Atividade. O Construtor não aceita parametros vazios, nulos ou não válidos, caso algum
     * valor seja, ele lançara um erro.
     *
     * @param descricao        Descricao da Pesquisa
     * @param campoDeInteresse Campos de interesse que estao associados a esta Pesquisa
     * @param codigo           Indentificador unico da pesquisa, gerado a partir do campo de interesse.
     */
    public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
        this.validador = new Validacao();

        this.validador.validaNulleVazio(descricao, "Descricao nao pode ser nula ou vazia.");
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaCampoDeInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");

        this.descricao = descricao;
        this.campoDeInteresse = campoDeInteresse;
        this.codigo = codigo;
        this.ativada = true;
        this.problemaAssociado = null;
        this.objetivos = new ArrayList<>();
        this.atividades = new ArrayList<>();

    }

    /**
     * Metodo responsavel por alterar o valor da descricao da pesquisa.
     *
     * @param descricao - novo valor da descricao
     */
    public void setDescricao(String descricao) {
        this.validador.validaNulleVazio(descricao, "Descricao nao pode ser nula ou vazia.");
        if (this.ativada == false) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        this.descricao = descricao;
    }

    /**
     * Metodo responsavel por alterar o valor do campo de interesse da pesquisa.
     *
     * @param campoDeInteresse - novo valor do campo de interesse
     */
    public void setCampoDeInteresse(String campoDeInteresse) {
        this.validador.validaNulleVazio(campoDeInteresse, "Formato do campo de interesse invalido.");
        this.validador.validaCampoDeInteresse(campoDeInteresse, "Formato do campo de interesse invalido.");

        if (this.ativada == false) {
            this.validador.lancaExcecao("Pesquisa desativada.");
        }
        this.campoDeInteresse = campoDeInteresse;
    }

    /**
     * Metodo responsavel por verificar se a pesquisa esta ativa ou encerrada
     *
     * @return true caso a pesquisa esteja ativa e false caso esteja encerrada
     */
    public boolean isAtivada() {
        return ativada;
    }

    /**
     * Metodo responsavel por desativar uma pesquisa e assim bloqueando qualquer alteracao na pesquisa.
     *
     * @param motivo - motivo pelo qual o usuario deseja desativar essa pesquisa
     */
    public void desativa(String motivo) {
        this.validador.validaNulleVazio(motivo, "Motivo nao pode ser nulo ou vazio.");

        this.ativada = false;
        this.motivoDesativacao = motivo;
    }

    /**
     * metodo responsavel por ativar a pesquisa e assim permitindo alteracoes no objeto.
     */
    public void ativa() {
        this.ativada = true;
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
     * Metodo de acesso ao Codigo de identificao da Pesquisa
     * @return Uma String com o Codigo de identificacao da Pesquisa.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Metodo responsvel por receber um Problema como parametro, verificar se a Pesquisa ja tem associacao com algum
     * problema, e caso nao tenha,  associa-lo a Pesquisa.
     *
     * @param problema Objeto Problema que sera associado a Pesquisa.
     * @return  variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaProblemaEmPesquisa(Problema problema) {
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
    public boolean desassociaProblemaEmPesquisa() {
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
    public boolean associaObjetivoEmPesquisa(Objetivo objetivo) {
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
    public boolean desassociaObjetivoEmPesquisa(Objetivo objetivo) {
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
    public boolean associaAtividadeEmPesquisa(Atividade atividade) {
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
    public boolean desassociaAtividadeEmPesquisa(Atividade atividade){
        if (!atividades.contains(atividade)){
            return false;
        } else {
            atividades.remove(atividade);
            atividade.desassocia();
            return true;
        }


    }


    /**
     * Metodo que cria uma representacao textual da pesquisa.
     *
     * @return uma representacao da pesquisa.
     */
    @Override
    public String toString() {
        return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
    }

    /**
     * Metodo que compara se duas pesquisa sao iguais, usando como criterio o codigo da pesquisa
     *
     * @param o - objeto que sera comparado
     * @return - true caso as pesquisas tem o mesmo codigo e false caso os codigos sejam diferentes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesquisa pesquisa = (Pesquisa) o;
        return codigo.equals(pesquisa.codigo);
    }


    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }


    /**
     * Metodo responsavel por pegar a descricao da Pesquisa.
     * @return descricao da Pesquisa.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Metodo responsavel por pegar os campos de interesse da Pesquisa.
     * @return campos de interesse da Pesquisa.
     */
    public String getCampoDeInteresse() {
        return campoDeInteresse;
    }

    @Override
    public int compareTo(Pesquisa o) {
        return o.getCodigo().compareTo(codigo);
    }
}