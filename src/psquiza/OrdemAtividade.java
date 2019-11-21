package psquiza;

import util.Validacao;

import java.util.HashMap;
import java.util.Map;

public class OrdemAtividade {

    /**
     * Objeto que tem funcoes que auxiliam na validacao de entradas.
    **/
    private Validacao validador;

    /**
     * Map que armazena Atividades. Tem como chaves uma String que e o codigo de cada Atividade, gerado pela
     * concatanecao de A + um inteiro dado pela ordem do cadastro.
     */
    private Map<String, Atividade> atividades;


    public OrdemAtividade(HashMap<String, Atividade> atividades){
        this.validador = new Validacao();
        this.atividades = atividades;
    }

    /**
     * Metodo responsavel por definir qual sera a proxima atividade a ser executada, criando assim
     * uma ordem de execucao, que nao necessariamente precisa ser seguida, mas que e util para melhor
     * aproveitamento. Para definir a ordem, o metodo recebe duas strings com ids de atividades, uma e
     * a precedente e outra a subsequente, apos verificar as excecoes(Valores nulos,vazios,inexistentes ou
     * atividade precedente ja possuir uma subsequente) o metodo armazena nas atividades que possuem os
     * respecitvos ids as informacoes de precedencia e subsequencia.
     * @param idPrecedente e o id da atividade precedente
     * @param idSubsquente e o id da atividade subsequente
     */
    public void defineProximaAtividade(String idPrecedente,String idSubsquente) {
        boolean contem = false;

        this.validador.validaNulleVazio(idPrecedente,"Atividade nao pode ser nulo ou vazio.");
        this.validador.validaNulleVazio(idSubsquente,"Atividade nao pode ser nulo ou vazio.");

        if(!atividades.containsKey(idSubsquente) || (!atividades.containsKey(idPrecedente))) {
            validador.lancaExcecao("Atividade nao encontrada.");
        }
        if(!atividades.get(idPrecedente).getSubsequente().equals("")) {
            validador.lancaExcecao("Atividade ja possui uma subsequente.");
        }
        for(String chave : this.atividades.keySet()) {
            if(atividades.get(chave).getPrecedentes().contains(idSubsquente)) {
                contem = true;
            }
        }
        if(contem==true) {
            validador.lancaExcecao("Criacao de loops negada.");
        }
        atividades.get(idPrecedente).setSubsequente(idSubsquente);
        boolean verifica = false;
        for(String chave : atividades.keySet()) {
            if(atividades.get(chave).getSubsequente().equals(idSubsquente)) {
                verifica = true;
            }
        }
        for(int i=0;i<atividades.get(idPrecedente).getPrecedentes().size();i++){
            atividades.get(idSubsquente).adicionaPrecedente(atividades.get(idPrecedente).getPrecedentes().get(i));
        }
        atividades.get(idSubsquente).adicionaPrecedente(idPrecedente);
    }

    /**
     * Metodo que tem como funcao retornar um valor inteiro que informa ao usuario quantas atividades
     * restam ser realizadas se a ordem for seguida. Uma excecao e lancada caso usuario informe um id
     * nulo,vazio ou inexistente no sistema.
     * @param idPrecedente e o id do qual se ira partir para saber quantas atividades restam ser executadas
     * @return o numero inteiro de atividades que faltam ser realizadas
     */
    public int contaProximos(String idPrecedente) {
        validador.validaNulleVazio(idPrecedente,"Atividade nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(idPrecedente)) {
            validador.lancaExcecao("Atividade nao encontrada.");
        }
        int contador = 0;
        String compare = atividades.get(idPrecedente).getSubsequente();
        while(true) {
            if(compare.equals("")) {
                break;
            }else {
                contador++;
                idPrecedente = compare;
                compare = atividades.get(compare).getSubsequente();
            }
        }

        return contador;

    }

    /**
     * Metodo responsavel por retirar uma atividade de determinada ordem de execucao, para o metodo
     * funcionar, ele recebe o id da atividade, e retira este id de todas as listas de precedencia e da
     * subsequencia em que ele estava. Uma excecao sera lancada, caso usuario informe um id nulo,vazio ou
     * inexistente.
     * @param idPrecedente e o id que se deseja remover da ordem
     */
    public void tiraProximaAtividade(String idPrecedente) {
        validador.validaNulleVazio(idPrecedente,"Atividade nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(idPrecedente)) {
            validador.lancaExcecao("Atividade nao encontrada.");
        }
        String retirar = atividades.get(idPrecedente).getSubsequente();
        for(String chave : atividades.keySet()) {
            Atividade atividade = atividades.get(chave);
            atividade.removePrecedente(retirar);
        }
        Atividade atividade = atividades.get(idPrecedente);
        atividade.setSubsequente("");
    }

    /**
     * Metodo responsavel por retornar ao usuario a atividade que sera executada apos x
     * atividades.
     * @param idAtividade e o id da atividade que esta sendo executada
     * @param enesimaAtividade e o valor de quantas atividades depois o usuario deseja saber
     * @return
     */
    public String pegaProximo(String idAtividade,int enesimaAtividade) {
        validador.validaNulleVazio(idAtividade,"Atividade nao pode ser nulo ou vazio.");
        if(enesimaAtividade<=0) {
            validador.lancaExcecao("EnesimaAtividade nao pode ser negativa ou zero.");
        }
        String retorno = "";
        int contador = 0;
        if(!atividades.containsKey(idAtividade)){
            validador.lancaExcecao("Atividade inexistente.");
        }
        String compare = atividades.get(idAtividade).getSubsequente();
        while(true) {
            if(contador==enesimaAtividade) {
                retorno = idAtividade;
                break;
            }else if(compare.equals("")) {
                validador.lancaExcecao("Atividade inexistente.");
            }else {
                contador++;
                idAtividade = compare;
                compare = atividades.get(idAtividade).getSubsequente();
            }

        }
        return retorno;
    }

    /**
     * Metodo que retorna a string com o id da atividade com maior risco de ser executada na ordem
     * de execucao.
     * @param idAtividade e o id da atividade que esta em uma ordem
     * @return o valor da ultima string subsequente da ordem
     */
    public String pegaMaiorRiscoAtividades(String idAtividade) {
        validador.validaNulleVazio(idAtividade,"Atividade nao pode ser nulo ou vazio.");
        if(!atividades.containsKey(idAtividade)) {
            validador.lancaExcecao("Atividade nao encontrada.");
        }
        if(atividades.get(idAtividade).getSubsequente().equals("")) {
            validador.lancaExcecao("Nao existe proxima atividade.");
        }
        idAtividade = atividades.get(idAtividade).getSubsequente();
        String compare = atividades.get(idAtividade).getNivelRisco();
        String retorno = idAtividade;
        while(true) {
            if(atividades.get(idAtividade).getSubsequente().equals("")) {
                break;
            }else {
                if(compare.equals("BAIXO")) {
                    compare = atividades.get(idAtividade).getNivelRisco();
                    retorno = idAtividade;
                }else if(compare.equals("MEDIO")) {
                    if((atividades.get(idAtividade).getNivelRisco().equals("MEDIO")) || (atividades.get(idAtividade).getNivelRisco().equals("ALTO"))) {
                        compare = atividades.get(idAtividade).getNivelRisco();
                        retorno = idAtividade;
                    }
                }else if(compare.equals("ALTO")) {
                    if(atividades.get(idAtividade).getNivelRisco().equals("ALTO")) {
                        compare = atividades.get(idAtividade).getNivelRisco();
                        retorno = idAtividade;
                    }
                }
                idAtividade = atividades.get(idAtividade).getSubsequente();
            }
        }
        return retorno;
    }

}
