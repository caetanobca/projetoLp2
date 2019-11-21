package psquiza.objetivo;

import psquiza.ComparadorBusca;
import util.Validacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Entidade responsavel por armazenar e controlar os objetivos cadastrados no sistema. Essa entidade vai possuir um
 * mapa com todos os problemas armazenados, um objeto da classe validacao, necessario no tratamento das excecoes e
 * e um contador que sera necessario para gerar o id do Objetivo.
 */
public class ControllerObjetivo implements Serializable {
    /**
     * E o mapa que armazena os objetivos no sistema.
     */
    private HashMap<String,Objetivo> objetivos;

    /**
     * E um objeto da classe validacao responsavel pelo tratamento das excecoes nos metodos da classe.
     */
    private Validacao validacao;

    /**
     * E um contador necessario para a geracao do id de um objetivo, inicializado com valor 1 e incrementado a cada
     * vez que um objetivo e cadastrado.
     */
    private int contaObjetivos;

    /**
     * Constroi a entidade responsavel por armazenar os objetivos por meio de um mapa que ira armazenar todos os
     * objetivos em um mapa.
     */
    public ControllerObjetivo() {
        this.objetivos = new HashMap<>();
        this.contaObjetivos = 1;
        this.validacao = new Validacao();
    }

    /**
     * Metodo responsavel pelo cadastro de um novo objetivo, para que o cadastro seja efetuado com sucesso serao
     * verificadas as excecoes para que tipo,descricao,aderencia e viabilidade nao sejam vazias ou nulas e se
     * o tipo sera diferente de "GERAL" ou "ESPECIFICO".Caso os dados estejam de acordo com as especificacoes
     * necessarias, um novo objeto do tipo objetivo e criado,junto a uma String que sera da forma O+1,O+2,O+3,...
     * que seja identificador unico e chave do objetivo no mapa de objetivos.
     * @param tipo e o tipo do objetivo
     * @param descricao e a descricao do objetivo
     * @param aderencia e o nivel de aderencia do objetivo, variando de 1 a 5
     * @param viabilidade e a viabilidade do objetivo, variando de 1 a 5
     * @return a string com o identificador unico e chave do mapa do objetivo
     */
    public String cadastraObjetivo(String tipo,String descricao,int aderencia,int viabilidade) {
        validacao.validaNulleVazio(tipo,"Campo tipo nao pode ser nulo ou vazio.");
        validacao.validaNulleVazio(descricao,"Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidadeOuAderencia(aderencia,"Valor invalido de aderencia");
        validacao.validaViabilidadeOuAderencia(viabilidade,"Valor invalido de viabilidade.");
        if((!tipo.equals("GERAL")) && (!tipo.equals("ESPECIFICO"))) {
            validacao.lancaExcecao("Valor invalido de tipo.");
        }
        String idObjetivo = "O"+this.contaObjetivos;
        Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade,idObjetivo);
        this.contaObjetivos++;
        this.objetivos.put(idObjetivo,objetivo);
        return idObjetivo;
    }

    /**
     * Metodo que deleta um objetivo do sistema caso surja necessidade ao usuario, este objetivo possui excecoes que
     * verificam se o identificador do objetivo e nulo,vazio ou nao existe no sistema.
     * @param codigo e o identificador unico do objetivo, que e a chave do mapa
     */
    public void apagarObjetivo(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!this.objetivos.containsKey(codigo)) {
            validacao.lancaExcecao("Objetivo nao encontrado");
        }
        this.objetivos.remove(codigo);
    }

    /**
     * Metodo que exibe determinado objetivo identificado pelo seu id, o metodo possui excecoes quando o id fornecido
     * e nulo,vazio,ou nao existe no sistema. Caso sistema nao detecte nenhuma excecao e apresentando em tela uma
     * mensagem  no formato "CODIGO - TIPO - DESCRICAO - VALOR".Onde o valor e a soma aritmetica da viabilidade com a
     * aderencia do objetivo.
     * @param codigo e o id do objetivo, que e utilizado como chave no mapa de objetivos
     * @return a representacao de um objetivo como objeto
     */
    public String exibeObjetivo(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!this.objetivos.containsKey(codigo)) {
            validacao.lancaExcecao("Objetivo nao encontrado");
        }
        String retorno = codigo+" - ";
        Objetivo objetivo = objetivos.get(codigo);
        retorno+=objetivo.toString();
        return retorno;
    }

    public Objetivo getObjetivo(String codigo){
        if(!this.objetivos.containsKey(codigo)) {
            validacao.lancaExcecao("Objetivo nao encontrado.");
        }

        return this.objetivos.get(codigo);
    }

    public boolean getObjetivoAssociado(String codigo){
        if(!this.objetivos.containsKey(codigo)) {
            validacao.lancaExcecao("Objetivo nao encontrado.");
        }

        return this.objetivos.get(codigo).getAssociado();
    }


    /**
     * Metedo responsavel por buscar um termo nas descricoes dos Objetivos.
     * @param termo Texto que sera usado como referencia na busca.
     * @return uma lista com todos o resultados.
     */
    public List<String> busca(String termo) {
        validacao.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

        List<String> results = new ArrayList<>();

        for (String codigo: objetivos.keySet()){
            if (this.objetivos.get(codigo).getDescricao().contains(termo)){
                results.add(codigo + ": " + this.objetivos.get(codigo).getDescricao());
            }
        }

        Collections.sort(results, new ComparadorBusca());
        return results;
    }

    public HashMap<String, Objetivo> getObjetivos() {
        return objetivos;
    }
}