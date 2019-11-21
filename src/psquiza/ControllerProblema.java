package psquiza;

import util.Validacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Entidade utilizada no sistema para ser a dona das informacoes sobre os problemas, ou seja nesta classe, serao
 * feitos os cadastros, exibicoes e remocoes de problemas do sistema.
 */
public class ControllerProblema implements Serializable {

    /**
     * Sao os problemas cadastrados no sistema.
     */
    private HashMap<String, Problema> problemas;

    /**
     * Atributo importado de uma classe externa, que possui metodos que auxiliam a nao permitir que valores que causem
     * erros sejam atribuidos as variaveis, sejam nulos,vazios ou quaisquer outro tipos de excessoes.
     */
    private Validacao validacao;

    /**
     * Contador utilizado para que a cada vez que um problema seja cadastrado, um id possa ser gerado da forma
     * "P+1,P+2,P+3..." e assim sucessivamente. E inicializaco com valor 1.
     */
    private int contaProblema;

    /**
     * Constroi a entidade responsavel por controlar os problemas, por meio de um mapa, que vai conter todos os
     * problemas do sistema cadastrados.
     */
    public ControllerProblema() {
        this.problemas = new HashMap<>();
        this.contaProblema = 1;
        this.validacao = new Validacao();
    }

    /**
     * Metodo responsavel por cadastrar um problema, nesse metodo existirao excecoes lancadas caso a descricao ou
     * viabilidade sejam valores nulos ou vazios. Um identificador unico do problema e gerado, que serve como chave
     * para o mapa que armazena os problemas.
     *
     * @param descricao   e a descricao do problema
     * @param viabilidade e a viabilidae do problema, variando de 1 a 5
     * @return uma string na forma P+1,P+2,P+3... que serve como chave para acesso ao problema e identificador unico
     * do mesmo
     */
    public String cadastraProblema(String descricao, int viabilidade) {
        validacao.validaNulleVazio(descricao, "Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidadeOuAderencia(viabilidade, "Valor invalido de viabilidade.");
        String idProblema = "P" + this.contaProblema;
        Problema problema = new Problema(descricao, viabilidade, idProblema);
        this.contaProblema++;
        this.problemas.put(idProblema, problema);
        return idProblema;
    }

    /**
     * Metodo que deleta um problema do sistema caso surja necessidade ao usuario, este metodo possui excecoes que
     * verificam se o identificador do problema e nulo,vazio ou nao existe no sistema.
     *
     * @param codigo e o identificador unico do problema, que e a chave do mapa
     */
    public void apagarProblema(String codigo) {
        validacao.validaNulleVazio(codigo, "Campo codigo nao pode ser nulo ou vazio.");
        if (!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("Problema nao encontrado");
        }
        this.problemas.remove(codigo);
    }

    /**
     * Metodo que exibe determinado problema identificado pelo seu id, o metodo possui excecoes quando o id fornecido
     * e nulo,vazio,ou nao existe no sistema. Caso sistema nao detecte nenhuma excecao e apresentando em tela uma
     * mensagem  no formato "CODIGO - DESCRICAO - VIABILIDADE".
     *
     * @param codigo e o id do problema, que e utilizado como chave no mapa de problemas
     * @return a representacao de um problema como objeto
     */
    public String exibeProblema(String codigo) {
        validacao.validaNulleVazio(codigo, "Campo codigo nao pode ser nulo ou vazio.");
        if (!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("Problema nao encontrado");
        }
        String retorno = codigo + " - ";
        Problema problema = this.problemas.get(codigo);
        retorno += problema.toString();
        return retorno;
    }

    public Problema getProblema(String codigo) {

        if (!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("Problema nao encontrado");
        }

        return problemas.get(codigo);

    }

    /**
     * Metedo responsavel por buscar um termo nas descricoes dos Problemas.
     * @param termo Texto que sera usado como referencia na busca.
     * @return uma lista com todos o resultados.
     */
    public List<String> busca(String termo) {
        validacao.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

        List<String> results = new ArrayList<>();

        for (String codigo: problemas.keySet()){
            if (this.problemas.get(codigo).getDescricao().contains(termo)){
                results.add(codigo + ": " + this.problemas.get(codigo).getDescricao());
            }
        }

        Collections.sort(results, new ComparadorBusca());
        return results;
    }

    public HashMap<String, Problema> getProblemas() {
        return problemas;
    }
}