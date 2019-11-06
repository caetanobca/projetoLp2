package psquiza;

import util.Validacao;

public class ControllerGeral {

    private ControllerPesquisa controllerPesquisa;
    private ControllerObjetivo controllerObjetivo;
    private ControllerProblema controllerProblema;
    private ControllerAtividade controllerAtividade;
    private ControllerPesquisador controllerPesquisador;
    private Validacao validador;

    public ControllerGeral(ControllerProblema controllerProblema, ControllerObjetivo controllerObjetivo,
                           ControllerPesquisa controllerPesquisa, ControllerAtividade controllerAtividade, ControllerPesquisador controllerPesquisador) {
        this.controllerObjetivo = controllerObjetivo;
        this.controllerPesquisa = controllerPesquisa;
        this.controllerProblema = controllerProblema;
        this.controllerAtividade = controllerAtividade;
        this.controllerPesquisador = controllerPesquisador;
        this.validador = new Validacao();

    }

    public boolean associaProblema(String idPesquisa, String idProblema) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");




        return controllerPesquisa.associaProblemaEmPesquisa(idPesquisa, controllerProblema.getProblema(idProblema));

    }

    public boolean desassociaProblema(String idPesquisa, String idProblema) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");

        return controllerPesquisa.desassociaProblemaEmPesquisa(idPesquisa, controllerProblema.getProblema(idProblema));

    }

    public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

        return controllerPesquisa.associaObjetivoEmPesquisa(idPesquisa, controllerObjetivo.getObjetivo(idObjetivo));

    }

    public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");

        return controllerPesquisa.desassociaObjetivoEmPesquisa(idPesquisa, controllerObjetivo.getObjetivo(idObjetivo));

    }



}



