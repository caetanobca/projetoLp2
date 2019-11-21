package psquiza;

import psquiza.atividade.Atividade;
import psquiza.objetivo.Objetivo;
import psquiza.pesquisa.AssociacaoEmPesquisa;
import psquiza.pesquisa.RelatorioPesquisa;
import psquiza.pesquisador.Pesquisador;
import psquiza.problema.Problema;
import util.Validacao;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma pesquisa, que tem descricao, campo de interesse, codigo, e uma variavel que indica
 * se esta ativa ou encerrada
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable {


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
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * Composição com uma classe que executa e armazena todas as Associações de Pesquisa com outras entidades.
     */
    private AssociacaoEmPesquisa associacao;

    /**
     * Objeto que tem funcoes que permite gerar um relatorio da pesquisa e dos resultados da pesquisa
     */
    private RelatorioPesquisa relatorioPesquisa;


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
        this.associacao = new AssociacaoEmPesquisa();
        this.relatorioPesquisa = new RelatorioPesquisa();


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

    /**
     * Retorna a informacao se a Atividada ha ou nao Itens
     * pendentes.
     *
     * @return valor booleano informando se ha itens pendentes ou nao
     */
    public boolean hasPendencias() {

        for (Atividade atividade : associacao.getAtividades()) {
            if (atividade != null) {
                if (atividade.contaItensPendentes() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }


    /**
     * Metodo responsavel por pegar a descricao da Pesquisa.
     *
     * @return descricao da Pesquisa.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Metodo responsavel por pegar os campos de interesse da Pesquisa.
     *
     * @return campos de interesse da Pesquisa.
     */
    public String getCampoDeInteresse() {
        return campoDeInteresse;
    }

    /**
     * Metodo de acesso ao Codigo de identificao da Pesquisa
     *
     * @return Uma String com o Codigo de identificacao da Pesquisa.
     */
    public String getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Pesquisa o) {
        return o.getCodigo().compareTo(codigo);
    }


    public void gravaResumo() throws IOException {
        this.relatorioPesquisa.gravaResumo(this.toString(), this.codigo, this.associacao);
    }


    public void gravaResultado() throws IOException {
        this.relatorioPesquisa.gravaResultado(this.toString(), this.codigo, this.associacao);
    }

    /**
     * Metodo de acesso ao Problema associado a Pesquisa.
     *
     * @return Um Objeto do tipo Problema
     */
    public Problema getProblemaAssociado() {
        return associacao.getProblemaAssociado();
    }

    /**
     * Metodo de acesso ao ArrayList de Objetivos associados a Pesquisa.
     *
     * @return ArrayList de Objetivo.
     */
    public List<Objetivo> getObjetivosAssociados() {
        return associacao.getObjetivosAssociados();
    }


    /**
     * Associa uma Atividade da Pesquisa. Uma Atividade nao pode ser associada se
     * * nao estiver associada em Pesquisa.
     *
     * @param atividade a Atividade a ser associada
     * @return valor booleano que representa o sucesso ou nao da associacao
     */
    public boolean associaAtividade(Atividade atividade) {
        return associacao.associaAtividade(atividade);
    }

    /**
     * Desassocia uma Atividade da Pesquisa. Uma Atividade nao pode ser desassociada se
     * nao estiver associada em Pesquisa.
     *
     * @param atividade a Atividade a ser dessasociada
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean desassociaAtividade(Atividade atividade) {
        return associacao.desassociaAtividade(atividade);
    }

    /**
     * Associa um Pesquisador a uma Pesquisa.
     *
     * @param pesquisador Pesquisador a ser associado
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean associaPesquisador(Pesquisador pesquisador) {
        return associacao.associaPesquisador(pesquisador);
    }

    /**
     * Desassocia um Pesquisador de uma Pesquisa.
     *
     * @param pesquisador Pesquisador a ser desassociado
     * @return valor booleano que representa o sucesso ou nao da operacao
     */
    public boolean desassociaPesquisador(Pesquisador pesquisador) {
        return associacao.desassociaPesquisador(pesquisador);
    }

    /**
     * Metodo responsvel por receber um Problema como parametro, verificar se a Pesquisa ja tem associacao com algum
     * problema, e caso nao tenha,  associa-lo a Pesquisa.
     *
     * @param problema Objeto Problema que sera associado a Pesquisa.
     * @return variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaProblema(Problema problema) {
        return associacao.associaProblema(problema);
    }

    /**
     * Metodo responsavel por desassociar um Problema da Pesquisa em que ele estava associado, verificando se o Problema
     * passado por parametro e o mesmo que ja esta associado, caso seja, a desassociacao será realizada.
     *
     * @return variavel booleana, true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaProblema() {
        return associacao.desassociaProblema();
    }

    /**
     * Metodo responsvel por receber um Objetivo como parametro, verificar se o Objetivo ja tem associacao com alguma
     * Pesquisa, e caso nao tenha,  associa-lo a Pesquisa.
     *
     * @param objetivo Objeto Objetivo que sera associado a Pesquisa.
     * @return variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaObjetivo(Objetivo objetivo) {
        return associacao.associaObjetivo(objetivo);
    }

    /**
     * Metodo responsavel por desassociar um Objetivo da Pesquisa em que ele estava associado, verificando se o Problema
     * passado por parametro esta no ArrayList de Objetivos associados, caso esteja, a desassociacao sera realizada.
     *
     * @param objetivo Objeto Problema que sera desassociado a Pesquisa.
     * @return variavel booleana, true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaObjetivo(Objetivo objetivo) {
        return associacao.desassociaObjetivo(objetivo);
    }

    /**
     * Retorna as Atividades associadas a essa pesquisa.
     *
     * @return as Atividades associadas a essa pesquisa
     */
    public List<Atividade> getAtividades() {
        return associacao.getAtividades();
    }
}