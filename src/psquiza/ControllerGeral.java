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


        Problema problema = controllerProblema.getProblema(idProblema);

        return controllerPesquisa.associaProblemaEmPesquisa(idPesquisa, problema);

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

    public boolean associaPesquisador(String idPesquisa,String emailPesquisador) {
        boolean retorno;
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if(!controllerPesquisa.getPesquisas().containsKey(idPesquisa)) {
            validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if(controllerPesquisa.getPesquisas().get(idPesquisa).isAtivada()==false) {
            validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!controllerPesquisador.getPesquisadores().containsKey(emailPesquisador)) {
            validador.lancaExcecao("Pesquisador nao encontrado.");
        }
        if(controllerPesquisador.getPesquisadores().get(emailPesquisador).isAtivo()==false) {
            validador.lancaExcecao("Pesquisador desativado");
        }
        if(controllerPesquisador.getPesquisadores().get(emailPesquisador).getPesquisas().contains(idPesquisa)) {
            retorno =  false;
        }else {
            Pesquisa pesquisa = controllerPesquisa.getPesquisas().get(idPesquisa);
            controllerPesquisador.associaPesquisador(idPesquisa, emailPesquisador);
            retorno = true;
        }
        return retorno;
    }

    public boolean desassociaPesquisador(String idPesquisa,String emailPesquisador) {
        boolean retorno;
        validador.validaNulleVazio(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
        validador.validaNulleVazio(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
        if(!controllerPesquisa.getPesquisas().containsKey(idPesquisa)) {
            validador.lancaExcecao("Pesquisa nao encontrada.");
        }
        if(controllerPesquisa.getPesquisas().get(idPesquisa).isAtivada()==false) {
            validador.lancaExcecao("Pesquisa desativada.");
        }
        if(!controllerPesquisador.getPesquisadores().containsKey(emailPesquisador)) {
            validador.lancaExcecao("Pesquisador nao encontrado.");
        }
        if(controllerPesquisador.getPesquisadores().get(emailPesquisador).isAtivo()==false) {
            validador.lancaExcecao("Pesquisador desativado");
        }
        if(controllerPesquisador.getPesquisadores().get(emailPesquisador).getPesquisas().contains(idPesquisa)) {
            Pesquisa pesquisa = controllerPesquisa.getPesquisas().get(idPesquisa);
            controllerPesquisador.desassociaPesquisador(idPesquisa, emailPesquisador);
            retorno =  true;
        }else {
            retorno = false;
        }
        return retorno;
    }




}



