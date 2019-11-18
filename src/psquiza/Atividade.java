package psquiza;

import util.Validacao;

import java.util.*;

/**
 * Classe que representa uma Atividade a ser realizada afim de obter resultados. Cada atividade planejada apresenta
 * uma descricao do que deve ser feito, uma duracao planejada, resultados esperados e um risco associado.
 */
public class Atividade {

    /**
     * Codigo identificador da Atividade, cada atividade eh identificada por uma String com o codigo A + valor
     * comecando a partir de 1, gerado automaticamente pelo sistema.
     */
    private String codigoIdentificador;

    /**
     * Descricao do que deve ser feito para a conclusao da Atividade.
     */
    private String descricao;

    /**
     * Objeto da classe Risco, que representa o Risco daquela Atividade.
     */
    private Risco nivelRisco;

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
     */
    private Validacao validador;

    /**
     * ArrayList com a lista de Itens.
     */
    private List<Item> itens;

    /**
     * Identifica se a atividade esta associada ou nao em alguma pesquisa.
     */
    private boolean associada;

    /**
     * ArrayList com a lista de resultados.
     */
    private HashMap<Integer,Resultado> resultados;

    /**
     * Contador de resultados cadastrados no sistema.
     */
    private int contaResultado;

    /**
     * Lista que armazena os Id's das atividades que por ordem de definicao
     * provavelmente serao executadas antes desta atividade.
     */
    private List<String> precedentes;

    /**
     * Atividade que por ordem de definicao, provavelmente sera executada
     * depois desta atividade.Cada atividade pode possuir apenas uma atividade
     * subsequente. A subsequente e inicializada por padrao com uma String vazia.
     */
    private String subsequente;

    /**
     * Construtor da Classe Atividade. O Construtor não aceita parametros vazios, nulos ou não válidos, caso algum
     * valor seja, ele lançara um erro.
     *
     * @param codigoIdentificador Codigo identificador da Atividade.
     * @param descricao           Descricao da Atividade
     * @param nivelRisco          Nivel do Risco a ser associado a essa Atividade
     * @param descricaoRisco      Descricao do Risco a ser associado a essa Atividade.
     */
    public Atividade(String codigoIdentificador, String descricao,
                     String nivelRisco, String descricaoRisco) {

        this.validador = new Validacao();
        validador.validaNulleVazio(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
        validador.validaNivelRisco(nivelRisco, "Valor invalido do nivel do risco.");

        this.codigoIdentificador = codigoIdentificador;
        this.descricao = descricao;
        this.nivelRisco = new Risco(nivelRisco, descricaoRisco);
        this.itens = new ArrayList<Item>();
        this.associada = false;
        this.resultados = new HashMap<>();
        this.contaResultado = 0;
        this.precedentes = new ArrayList<>();
        this.subsequente = "";
    }

    /**
     * Método responsavel por cadastrar um item e adiciona-los no conjunto de itens da Atividade.
     *
     * @param item Descricao do item a ser cadastrado.
     */
    public void cadastraItem(String item) {
        validador.validaNulleVazio(item, "Item nao pode ser nulo ou vazio.");
        int codigoIdentificador = itens.size() + 1;
        Item itemNovo = new Item(codigoIdentificador, item);
        if(!itens.contains(itemNovo)){
            itens.add(itemNovo);
        }
    }

    /**
     * Metodo responsavel por contar quantos itens tem o status "PENDENTE" no conjunto de itens.
     *
     * @return Numero inteiro referente ao numero de itens com o status "PENDENTE" no conjunto de itens.
     */
    public int contaItensPendentes() {
        int contador = 0;

        for (Item item : itens) {
            if ("PENDENTE".equals(item.getStatus())) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Metodo responsavel por contar quantos itens tem o status "PENDENTE" no conjunto de itens.
     *
     * @return Numero inteiro referente ao numero de itens com o status "PENDENTE" no conjunto de itens.
     */
    public int contaItensRealizados() {
        int contador = 0;

        for (Item item : itens) {
            if ("REALIZADO".equals(item.getStatus())) {
                contador++;
            }
        }

        return contador;
    }

    /**
     * Metodo responsavel por contar quantos itens tem o status "RESOLVIDO" no conjunto de itens.
     *
     * @return Numero inteiro referente ao numero de itens com o status "RESOLVIDO" no conjunto de itens.
     */
    private String exibeItens() {
        String itensExibidos = "";
        if (!itens.isEmpty()) {
            for (Item item : itens) {
                itensExibidos += " | " + item.toString();
            }
        }
        return itensExibidos;
    }

    /**
     * Modifica o parametro associada para true.
     */
    public void associa() {
        this.associada = true;
    }

    /**
     * Modifica o parametro associada para false.
     */
    public void desassocia(){
        this.associada = false;
    }

    /**
     * Executa um item da atividade. Deve-se informar o codigo do item
     * e a duracao da execucao da atividade.
     *
     * @param codigoItem o codigo do item a ser executado
     * @param duracao a duracao da execucao do item
     */
    public void executaAtividade(int codigoItem, int duracao) {
        validador.validaInteiro(codigoItem,"CodigoItem nao pode ser nulo ou negativo");
        validador.validaInteiro(duracao,"Duracao nao pode ser nula ou negativa.");
        if (associada) {
            if(codigoItem>itens.size()){
                validador.lancaExcecao("Item nao encontrado.");
            }
            if (itens.get(codigoItem-1).getStatus().equals("PENDENTE")) {
                itens.get(codigoItem-1).setStatus();
                itens.get(codigoItem-1).setDuracao(duracao);
            } else {
                validador.lancaExcecao("Item ja executado.");
            }
        } else{
            validador.lancaExcecao("Atividade sem associacoes com pesquisas.");
        }
    }

    /**
     * Retorna a duracacao da realizacao de todos os itens da atividade.
     *
     * @return valor inteiro que representa a duracao da realizacao da atividade
     */
    public int getDuracao(){
        int duracao = 0;

        for (Item item: itens){
            duracao += item.getDuracao();
        }

        return duracao;
    }

    /**
     * Cadastra um resultado para a atividade.
     *
     * @param descricaoResultado representacao em String do resultado da Ativivdade
     * @return valor inteiro que representa o indice do resultado.
     */
    public int cadastraResultado(String descricaoResultado) {
        int codigoIdentificador = this.contaResultado + 1;
        Resultado r = new Resultado(descricaoResultado,codigoIdentificador);
        resultados.put(codigoIdentificador,r);
        this.contaResultado++;
        return codigoIdentificador;
    }

    /**
     * Remove um resultado de uma atividade.
     *
     * @param numeroResultado o indice do resultado a ser removido.
     * @return valor booleano que indica o sucesso ou nao da operacao
     */
    public boolean removeResultado(int numeroResultado){
        validador.validaInteiro(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
        if(!resultados.containsKey(numeroResultado)) {
            validador.lancaExcecao("Resultado nao encontrado.");
        }
        resultados.remove(numeroResultado);
        return true;
    }

    /**
     * Retorna a lista de todos os resultados da Atividade.
     *
     * @return representacao em String dos resultados da Atividade
     */
    public String listaResultados(){
        String resultadosExibidos = "";
        for(int chave : resultados.keySet()) {
            resultadosExibidos+=resultados.get(chave).toString()+" | ";
        }
        return resultadosExibidos.substring(0,resultadosExibidos.length()-3);
    }

    /**
     * Retorna um valor booleano informando se a atividade esta associada a uma pesquisa ou nao.
     *
     * @return valor booleano informando se a atividade esta associada a uma pesquisa ou nao
     */
    public boolean isAssociada() {
        return associada;
    }

    /**
     * Metodo responsavel por gerar uma representação textual da Atvividade.
     *
     * @return Uma String com a descricao da Atividade, seu Risco e os Itens pertencentes a mesma.
     */

    /**
     * Metodo responsavel por pegar a descricao da Atividade.
     * @return a descricao da Atividade.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Metodo responsavel por pegar a descricao do do risco da Atividade.
     * @return descricao do risco.
     */
    public String  getDescricaoDoRisco() {
        return this.nivelRisco.getDescricao();
    }

    public String getNivelRisco() {
        return nivelRisco.getNivelRisco();
    }


    /**
     * Metodo que tem como funcao adicionar a atividade o id das atividades
     * que foram definidas para serem executadas anteriormente.
     * @param idAtividade e o codigo da atividade que sera adicionado a precedencia
     */
    public void adicionaPrecedente(String idAtividade) {
        this.precedentes.add(idAtividade);
    }

    /**
     * Metodo responsavel por alterar a ordem de sequencia das atividades, pois
     * altera o subsequente.
     * @param subsequente e a atividade que sera executada posteriormente
     */
    public void setSubsequente(String subsequente) {
        this.subsequente = subsequente;
    }

    /**
     * Metodo que retona o Id da atividade subsequente.
     * @return o Id da atividade subsequente
     */
    public String getSubsequente() {
        return subsequente;
    }

    /**
     * Metodo que retorna a lista com as atividades precedentes.
     * @return a lista com os Id's das atividades precedentes
     */
    public List<String> getPrecedentes() {
        return precedentes;
    }

    /**
     * Metodo responsavel por verificar se uma atividade e precedente desta atividade. Para
     * realizar a verificacao, o Id da atividade que se deseja verificar e passado, caso este
     * id esteja contido na lista de precedentes o valor true e retornado, caso contrario
     * o valor false e retornado.
     * @param idPrecedente e o id que deseja se verificar se esta contido na precedencia
     * @return um valor booleano que dara a resposta se o valor de id informado esta contido
     * na lista de precedencias
     */
    public boolean contemPrecedente(String idPrecedente) {
        boolean retorno = false;
        for(int i=0;i<precedentes.size();i++) {
            if(idPrecedente.equals(precedentes.get(i))) {
                retorno = true;
            }
        }
        return retorno;
    }

    /**
     * Metodo responsavel por remover da lista de precedencia algum valor desejado pelo usuario.
     * Para realizar a remocao, o id que deseja ser removido pelo usuario e passado como parametro.
     * @param idRemover e o id que o usuario deseja remover da lista de precedencia
     */
    public void removePrecedente(String idRemover) {
        if(precedentes.contains(idRemover)) {
            precedentes.remove(idRemover);
        }
    }

    @Override
    public String toString() {
        return descricao + " (" + nivelRisco.toString() + ")" + exibeItens();
    }

    /**
     * Método utilizado para comparar dois objetos Atividade, utilizando como comparador o codigo identificador de cada objeto
     * Atividade.
     *
     * @param o Objeto a ser comparado.
     * @return Variavel booleana, false caso seja diferente, true caso seja igual.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividade atividade = (Atividade) o;
        return Objects.equals(codigoIdentificador,
                atividade.codigoIdentificador);
    }

    /**
     * Método que retorna um inteiro único baseado no codigo identificador da Atividade.
     * @return inteiro único baseado no codigo identificador da Atividade.
    g */
    @Override
    public int hashCode() {
        return Objects.hash(codigoIdentificador);
    }

}
