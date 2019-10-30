package psquiza;

import util.Validacao;

import java.util.HashMap;

/**
 * Entidade utilizada no sistema para ser a dona das informacoes sobre os problemas, ou seja nesta classe, serao
 * feitos os cadastros, exibicoes e remocoes de problemas do sistema.
 */
public class ControllerProblema {
    /**
     * Sao os problemas cadastrados no sistema.
     */
    private HashMap<String,Problema> problemas;

    /**
     * Atributo importado de uma classe externa, que possui metodos que auxiliam a nao permitir que valores que causem
     * erros sejam atribuidos as variaveis, sejam nulos,vazios ou quaisquer outro tipos de excessoes.
     */
    private Validacao validacao = new Validacao();

    /**
     * Contador utilizado para que a cada vez que um problema seja cadastrado, um id possa ser gerado da forma
     * "P+1,P+2,P+3..." e assim sucessivamente.
     */
    private int contaProblema = 0;

    public ControllerProblema(){
        this.problemas = new HashMap<>();
    }

    public String cadastraProblema(String descricao,int viabilidade) {
        validacao.validaNulleVazio(descricao,"Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidadeOuAderencia(viabilidade,"Valor invalido de viabilidade.");
        Problema problema = new Problema(descricao,viabilidade);
        this.contaProblema++;
        String idProblema = "P"+this.contaProblema;
        this.problemas.put(idProblema,problema);
        return idProblema;
    }

    public void apagarProblema(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("Problema nao encontrado");
        }
        this.problemas.remove(codigo);
    }

    public String exibeProblema(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("Problema nao encontrado");
        }
        String retorno = codigo+" - ";
        Problema problema = this.problemas.get(codigo);
        retorno += problema.toString();
        return retorno;
    }


}
