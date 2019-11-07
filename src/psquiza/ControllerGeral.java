package psquiza;

import util.Validacao;

import java.util.ArrayList;
import java.util.List;

public class ControllerGeral {

    private ControllerPesquisa controllerPesquisa;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisador controllerPesquisador;
    private Validacao validador;

    public ControllerGeral(ControllerProblema controllerProblema, ControllerObjetivo controllerObjetivo,
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
     * Metodo responsavel por associar um Problema a uma Pesquisa. Cada Pesquisa so pode ter um Problema associado,
     * porem Problema pode se associar com varias pesquisas. O metodo nao aceita campos vazios ou nulos, caso algum
     * seja, lancara um erro.
     *
     * @param idPesquisa Identificacao da Pesquisa que recebera um Problema associado.
     * @param idProblema Identificacao do Problema que sera associado a uma Pesquisa.
     * @return variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaProblema(String idPesquisa, String idProblema) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");


        return controllerPesquisa.associaProblemaEmPesquisa(idPesquisa, controllerProblema.getProblema(idProblema));

    }

    /**
     * Metodo responsavel por desassociar um Problema de uma Pesquisa especifica, permitindo que aquela Pesquisa
     * possa receber outro Problema, caso seja necessario. O metodo nao aceita campos vazios ou nulos, caso algum
     * seja, lancara um erro.
     *
     * @param idPesquisa Identificacao da Pesquisa que tera o Problema desassociado.
     * @param idProblema Identificacao do Problema que sera desassociado da Pesquisa.
     * @return variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaProblema(String idPesquisa, String idProblema) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");

        return controllerPesquisa.desassociaProblemaEmPesquisa(idPesquisa, controllerProblema.getProblema(idProblema));

    }

    /**
     * Metodo responsavel por associar um Objetivo a uma Pesquisa. Cada Pesquisa pode ter varios objetivos
     * associados, porem, um objetivo nao  pode se associar com varias pesquisas. O metodo nao aceita campos vazios
     * ou nulos, caso algum seja, lancara um erro.
     *
     * @param idPesquisa Identificacao da Pesquisa que recebera um Problema associado.
     * @param idProblema Identificacao do Problema que sera associado a uma Pesquisa.
     * @return variavel booleana, true caso a associacao tenha dado certo, false caso contrario.
     */
    public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

        return controllerPesquisa.associaObjetivoEmPesquisa(idPesquisa, controllerObjetivo.getObjetivo(idObjetivo));

    }

    /**
     * Metodo responsavel por desassociar um Objetivo especifico de uma Pesquisa. Quando ocorre a desassociacao o
     * Objetivo podera ser associado a qualquer outra Pesquisa. O metodo nao aceita campos vazios
     * ou nulos, caso algum seja, lancara um erro.
     *
     * @param idPesquisa Identificacao da Pesquisa que recebera um Problema associado.
     * @param idProblema Identificacao do Problema que sera associado a uma Pesquisa.
     * @return variavel booleana, true caso a desassociacao tenha dado certo, false caso contrario.
     */
    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

        return controllerPesquisa.desassociaObjetivoEmPesquisa(idPesquisa, controllerObjetivo.getObjetivo(idObjetivo));

    }


    public String busca(String termo) {
        validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

        List<String> resultados = this.realizaBusca(termo);

        String resultString = "";

        for (int i = 0; i < resultados.size(); i++){
            resultString += resultados.get(i) + " | ";
        }

        return resultString.substring(0, resultString.length()-3);
    }

    public String busca(String termo, int numeroDoResultado) {
        validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");
        validador.validaInteiro(numeroDoResultado,"Numero do resultado nao pode ser negativo");

        List<String> resultados = this.realizaBusca(termo);

        if (resultados.size() < numeroDoResultado){
            this.validador.lancaExcecao("Entidade nao encontrada.");
        }

        return resultados.get(numeroDoResultado - 1);
    }

    public int contaResultadosBusca(String termo) {
        validador.validaNulleVazio(termo, "Campo termo nao pode ser nulo ou vazio.");

        List<String> resultados = this.realizaBusca(termo);

        if (resultados.size() <1){
            this.validador.lancaExcecao("Nenhum resultado encontrado");
        }

        return resultados.size();
    }

    public List<String> realizaBusca (String termo){
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



