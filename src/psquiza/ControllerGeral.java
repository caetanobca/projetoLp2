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

    /**
     * Metodo responsavel por associar uma pesquisa a determinado pesquisador, para identificacao de qual pesquisa e qual
     * pesquisador serao relacionados, o idPesquisa e o emailPesquisador sao utilizados. Uma excecao e lancada caso o usuario
     * queira fornecer algum valor nulo ou vazio para os parametros.
     * @param idPesquisa e o identificador unico da pesquisa
     * @param emailPesquisador e o email e identificador unico do pesquisador
     */
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

    /**
     * Metodo responsavel por desassociar uma pesquisa de um determinado pesquisador, para identificacao de qual pesquisa e qual
     * pesquisador serao desassociados, o idPesquisa e o emailPesquisador sao utilizados. Uma excecao e lancada caso o usuario
     * queira fornecer algum valor nulo ou vazio para os parametros.
     * @param idPesquisa e o identificador unico da pesquisa
     * @param emailPesquisador e o email e identificador unico do pesquisador
     */
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



