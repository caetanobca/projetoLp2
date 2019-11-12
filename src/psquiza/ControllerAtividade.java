package psquiza;

import util.Validacao;

import java.util.*;

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

    /**
     * Retorna uma Atividade com base no codigo identificador unico dela.
     *
     * @param id o codigo identificador unico
     * @return a Atividade com o codigo identificador procurado
     */
    public Atividade getAtividade(String id) {
        if(!this.atividades.containsKey(id)) {
            validador.lancaExcecao("Atividade nao encontrada");
        }
        return this.atividades.get(id);
    }

    /**
     * Executa um item de uma atividade. Cada execucao de item possui uma duracao.
     *
     * @param codigoAtividade codigo da Atividade a ser executada
     * @param item item da atividade a ser executado
     * @param duracao duracao da execucao do item na Atividade
     */
    public void executaAtividade(String codigoAtividade, int item, int duracao) {
        validador.validaNulleVazio(codigoAtividade,"Campo codigoAtividade nao pode ser nulo ou vazio.");
        validador.validaInteiro(item,"Item nao pode ser nulo ou negativo.");
        validador.validaInteiro(duracao,"Duracao nao pode ser nula ou negativa.");
        if (!atividades.containsKey(codigoAtividade)) {
            validador.lancaExcecao("Atividade nao encontrada");
        }
        atividades.get(codigoAtividade).executaAtividade(item,duracao);
    }

    /**
     * Cadastra um Resultado na Atividade. Cada resultado retorna um identificador unico inteiro com base
     * na ordem de insercao do resultado.
     *
     * @param codigoAtividade identificador da atividade, em String
     * @param resultado representacao em String do resultado da atividade
     * @return valor inteiro do indentificador inteiro de resultado
     */
    public int cadastraResultado(String codigoAtividade, String resultado){
        validador.validaNulleVazio(codigoAtividade,"Campo codigoAtividade nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(resultado,"Resultado nao pode ser nulo ou vazio.");
        if (!atividades.containsKey(codigoAtividade)) {
            validador.lancaExcecao("Atividade nao encontrada");
        }
        Resultado r = new Resultado(resultado);
        return atividades.get(codigoAtividade).cadastraResultado(r);
    }


    /**
     * Remove um resultado de uma atividade com base nos identificadores de Resultado e Atividade.
     * Retorna um booleano informando o sucesso ou nao da operacao.
     *
     * @param codigoAtividade codigo identificador da atividade, em String
     * @param numeroResultado codigo idenficiador do resultado, em inteiro
     * @return valor booleano informando o sucesso ou nao da atividade
     */
    public boolean removeResultado(String codigoAtividade, int numeroResultado){
        validador.validaNulleVazio(codigoAtividade,"Campo codigoAtividade nao pode ser nulo ou vazio.");
        validador.validaInteiro(numeroResultado,"numeroResultado nao pode ser nulo ou negativo.");
        if (!atividades.containsKey(codigoAtividade)) {
            validador.lancaExcecao("Atividade nao encontrada");
        }
        return atividades.get(codigoAtividade).removeResultado(numeroResultado);
    }

    /**
     * Retorna a lista de todos os resultados de uma certa Atividade.
     *
     * @param codigoAtividade codigo identificador da atividade em String
     * @return representacao em String dos resultados da Atividade
     */
    public String listaResultados(String codigoAtividade){
        validador.validaNulleVazio(codigoAtividade,"Campo codigoAtividade nao pode ser nulo ou vazio.");
        if (!atividades.containsKey(codigoAtividade)) {
            validador.lancaExcecao("Atividade nao encontrada");
        }
        return atividades.get(codigoAtividade).listaResultados();
    }

    /**
     * Retorna a duracacao da realizacao de todos os itens de uma Atividade.
     *
     * @return valor inteiro que representa a duracao da realizacao da atividade
     */
    public int getDuracao(String codigoAtividade){
        validador.validaNulleVazio(codigoAtividade,"Campo codigoAtividade nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(codigoAtividade)){
            validador.lancaExcecao("Atividade nao encontrada");
        }
        return atividades.get(codigoAtividade).getDuracao();
    }

    /**
     * Metedo responsavel por buscar um termo nas descricoes e nas descricoes dos risco das Atividades.
     * @param termo Texto que sera usado como referencia na busca.
     * @return uma lista com todos o resultados.
     */
    public List<String> busca(String termo) {
        validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

        List<String> results = new ArrayList<>();

        List<String> buscaDescricao = new ArrayList<>();

        for (String codigo: atividades.keySet()){
            if (this.atividades.get(codigo).getDescricao().contains(termo)){
                results.add(codigo + ": " + this.atividades.get(codigo).getDescricao());
            }
            if (this.atividades.get(codigo).getDescricaoDoRisco().contains(termo)){
                results.add(codigo + ": " + this.atividades.get(codigo).getDescricaoDoRisco());
            }
        }

        Collections.sort(results, new ComparadorBusca());

        return results;
    }
}

