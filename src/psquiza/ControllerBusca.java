package psquiza;

import psquiza.atividade.ControllerAtividade;
import psquiza.objetivo.ControllerObjetivo;
import psquiza.pesquisa.ControllerPesquisa;
import psquiza.pesquisador.ControllerPesquisador;
import psquiza.problema.ControllerProblema;
import util.Validacao;

import java.util.ArrayList;
import java.util.List;

/**
* Entidade responsavel por gerenciar as funcionaliades de busca do sistema
*/
public class ControllerBusca {

private ControllerPesquisa controllerPesquisa;
private ControllerObjetivo controllerObjetivo;
private ControllerProblema controllerProblema;
private ControllerAtividade controllerAtividade;
private ControllerPesquisador controllerPesquisador;
private Validacao validador;

public ControllerBusca(ControllerProblema controllerProblema, ControllerObjetivo controllerObjetivo,
                             ControllerPesquisa controllerPesquisa, ControllerAtividade controllerAtividade,
                             ControllerPesquisador controllerPesquisador) {
        this.controllerObjetivo = controllerObjetivo;
        this.controllerPesquisa = controllerPesquisa;
        this.controllerProblema = controllerProblema;
        this.controllerAtividade = controllerAtividade;
        this.controllerPesquisador = controllerPesquisador;
        this.validador = new Validacao();
    }

    /**
     * Metodo responsavel por realizar uma busca no sistema a partir de um termo
     * @param termo - termo que sera usado na busca
     * @return Um string com todos os resultados encontrados
     */
    public String busca(String termo) {
            validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

            List<String> resultados = this.realizaBusca(termo);

            String resultString = "";

            for (int i = 0; i < resultados.size(); i++){
                resultString += resultados.get(i) + " | ";
            }

            if("".equals(resultString)){
                return resultString;
            }

            return resultString.substring(0, resultString.length()-3);
    }

    /**
     * Metodo responsavel por pegar um unico resultados da usca por um termo
     * @param termo - termo que sera buscado
     * @param numeroDoResultado - posicao do resultado que devera ser retornado
     * @return um unico resultado da busca
     */
    public String busca(String termo, int numeroDoResultado) {
        validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");
        validador.validaInteiro(numeroDoResultado, "Numero do resultado nao pode ser negativo");

        List<String> resultados = this.realizaBusca(termo);

        if (resultados.size() < numeroDoResultado) {
            this.validador.lancaExcecao("Entidade nao encontrada.");
        }

        return resultados.get(numeroDoResultado - 1);
    }

    /**
     * Metodo responsavel por contar quantos resultado e encontrado a partir de uma busca
     * @param termo termo que devera ser buscado
     * @return um inteiro que representa a quantidade de reultados encontrados
     */
    public int contaResultadosBusca(String termo) {
            validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

            List<String> resultados = this.realizaBusca(termo);

            if (resultados.size() <1){
                this.validador.lancaExcecao("Nenhum resultado encontrado");
            }

            return resultados.size();
    }

    /**
     * Metodo auxiliar que realiza a busca
     * @param termo termo que devera ser buscado
     * @return uma lista com todos os resultados da busca
     */
    private List<String> realizaBusca (String termo){
            validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

            List<String> resultados = new ArrayList<>();

            resultados.addAll(this.controllerPesquisa.busca(termo));
            resultados.addAll(this.controllerPesquisador.busca(termo));
            resultados.addAll(this.controllerProblema.busca(termo));
            resultados.addAll(this.controllerObjetivo.busca(termo));
            resultados.addAll(this.controllerAtividade.busca(termo));

            return resultados;
    }

}

