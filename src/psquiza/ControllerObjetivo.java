package psquiza;

import util.Validacao;

import java.util.HashMap;

public class ControllerObjetivo {

    private HashMap<String,Objetivo> objetivos;
    private Validacao validacao = new Validacao();
    private int contaObjetivos = 0;

    public ControllerObjetivo() {
        this.objetivos = new HashMap<>();
    }

    public String cadastraObjetivo(String tipo,String descricao,int aderencia,int viabilidade) {
        validacao.validaNulleVazio(tipo,"Campo tipo nao pode ser nulo ou vazio.");
        validacao.validaNulleVazio(descricao,"Campo descricao nao pode ser nulo ou vazio.");
        validacao.validaViabilidadeOuAderencia(aderencia,"Valor invalido de aderencia");
        validacao.validaViabilidadeOuAderencia(viabilidade,"Valor invalido de viabilidade.");
        if((!tipo.equals("GERAL")) || (!tipo.equals("ESPECIFICO"))) {
            validacao.lancaExcecao("Valor invalido de tipo.");
        }
        Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade);
        this.contaObjetivos++;
        String idObjetivo = "O"+this.contaObjetivos;
        this.objetivos.put(idObjetivo,objetivo);
        return idObjetivo;
    }

    public void apagarObjetivo(String codigo) {
        validacao.validaNulleVazio(codigo,"Campo codigo nao pode ser nulo ou vazio.");
        if(!this.objetivos.containsKey(codigo)) {
            validacao.lancaExcecao("Objetivo nao encontrado");
        }
        this.objetivos.remove(codigo);
    }

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
}
