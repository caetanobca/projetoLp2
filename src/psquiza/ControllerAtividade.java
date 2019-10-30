package psquiza;

import util.Validacao;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe responssavel por gerenciar as Atividades cadastradas no sistema. As Atividades sao armazenadas em um mapa
 * usando como chave um codigo gerado pela concatanecao de A + um inteiro dado pela ordem do cadastro.
 */

public class ControllerAtividade {

    /**
     * Map que armazena Atividades. Tem como chaves uma String que e o codigo de cada Atividade, gerado pela
     * concatanecao de A + um inteiro dado pela ordem do cadastro.
     */
    private Map<String, Atividade> atividades;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * Quantidade de Atividades cadastradas.
     */
    private int qtdCadastrados;

    /**
     * Construtor do ControllerAtividade.
     */
    public ControllerAtividade() {
        this.atividades = new HashMap<>();
        this.validador = new Validacao();
        this.qtdCadastrados = 0;

    }

    /**
     * Metodo responsavel por cadastrar Atividades e inseri-las no Map responsavel por armazena-las. Esse metodo não
     * aceita parametros vazios, nulos ou não válidos, caso algum valor seja, ele lançara um erro.
     *
     * @param descricao      Descricao da Atividade a ser cadastrada.
     * @param nivelRisco     Nivel do Risco da Atividade a ser cadastrada
     * @param descricaoRisco Descricao do Risco da Atividade a ser cadastrada.
     * @return O codigo gerado pela concatanecao de A + um inteiro dado pela ordem do cadastro.
     */
    public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {

        validador.validaNulleVazio(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        String chaveNova = geraCodigoIdentificador();

        Atividade atividadeNova = new Atividade(chaveNova, descricao, nivelRisco, descricaoRisco);

        atividades.put(chaveNova, atividadeNova);
        qtdCadastrados++;

        return chaveNova;
    }

    /**
     * Metodo responsavel por gerar o codigo identificador da atividade, a partir da concatanecao de A + um inteiro
     * dado pela ordem do cadastro.
     *
     * @return String com o codigo gerado.
     */
    private String geraCodigoIdentificador() {
        int codigo = qtdCadastrados + 1;
        String codigoCompleto = "A" + Integer.toString(codigo);

        return codigoCompleto;

    }

    /**
     * Metodo responsavel por remover a Atividade do Map de atividades.
     *
     * @param codigoIdentificador Codigo identificador da Atividade a ser removida.
     */
    public void apagaAtividade(String codigoIdentificador) {
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");


        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {

            atividades.remove(codigoIdentificador);
        }

    }

    /**
     * Metodo responsavel por cadastrar um Item na Atividade.
     *
     * @param codigoIdentificador Codigo identificador da Atividade que se deseja cadastrar um Item.
     * @param item                Item que se deseja cadastrar.
     */
    public void cadastraItem(String codigoIdentificador, String item) {
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(item, "Item nao pode ser nulo ou vazio.");

        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {

            atividades.get(codigoIdentificador).cadastraItem(item);
        }

    }

    /**
     * Metodo responsavel por exibir uma determinada Atividade.
     *
     * @param codigoIdentificador Codigo identificador da Atividade a ser exibida.
     * @return String com a representação textual de uma Atividade.
     */
    public String exibeAtividade(String codigoIdentificador) {

        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");
        String msg = "";


        if (!atividades.containsKey(codigoIdentificador)) {

            validador.lancaExcecao("Atividade nao encontrada");
        } else {

            msg = atividades.get(codigoIdentificador).toString();
        }

        return msg;
    }

    /**
     * Metodo responsavel por retornar quantos Itens tem o status de "REALIZADO" na Atividade.
     *
     * @param codigoIdentificador Codigo identificador da Atividade que se deseja tal informação.
     * @return Inteiro com o numero de Itens com o status de "REALIZADO" na Atividade.
     */
    public int contaItensRealizados(String codigoIdentificador) {
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");

        int itensRealizados = 0;

        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {
            itensRealizados = atividades.get(codigoIdentificador).contaItensRealizados();
        }

        return itensRealizados;
    }

    /**
     * Metodo responsavel por retornar quantos Itens tem o status de "PENDENTE" na Atividade.
     *
     * @param codigoIdentificador Codigo identificador da Atividade que se deseja tal informação.
     * @return Inteiro com o numero de Itens com o status de "PENDENTE" na Atividade.
     */
    public int contaItensPendentes(String codigoIdentificador) {
        validador.validaNulleVazio(codigoIdentificador, "Campo codigo nao pode ser nulo ou vazio.");

        int itensPendentes = 0;

        if (!atividades.containsKey(codigoIdentificador)) {
            validador.lancaExcecao("Atividade nao encontrada");
        } else {
            itensPendentes = atividades.get(codigoIdentificador).contaItensPendentes();
        }

        return itensPendentes;
    }
}

