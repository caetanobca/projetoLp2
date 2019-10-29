package psquiza;

import util.Validacao;

import java.util.HashMap;

public class ControllerProblema {

    private HashMap<String,Problema> problemas;
    private Validacao validacao = new Validacao();
    private int contaProblema = 0;

    public ControllerProblema(){
        this.problemas = new HashMap<String,Problema>();
    }

    public String cadastraProblema(String descricao,int viabilidade) {
        validacao.validaNulleVazio(descricao,"Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidade(viabilidade,"Valor invalido de viabilidade.");
        Problema problema = new Problema(descricao,viabilidade);
        this.contaProblema++;
        String idProblema = "P"+this.contaProblema;
        problemas.put(idProblema,problema);
        return idProblema;
    }

    public void apagarProblema(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("psquiza.Problema nao encontrado");
        }
        problemas.remove(codigo);
    }

    public String exibeProblema(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!problemas.containsKey(codigo)) {
            validacao.lancaExcecao("psquiza.Problema nao encontrado");
        }
        String retorno = codigo+" - ";
        Problema problema = problemas.get(codigo);
        retorno += problema.toString();
        return retorno;
    }


}
