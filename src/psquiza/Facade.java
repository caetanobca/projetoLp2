package psquiza;

public class Facade {
    private ControllerPesquisa controllerPesquisa;

    public Facade(){
        this.controllerPesquisa = new ControllerPesquisa();
    }

    public String cadastraPesquisa(String descricao, String campoDeInteresse){
        return this.controllerPesquisa.cadastraPesquisa(descricao, campoDeInteresse);
    }

    public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo){
        this.controllerPesquisa.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
    }

    public void encerraPesquisa(String codigo, String motivo){
        this.controllerPesquisa.encerraPesquisa(codigo, motivo);
    }

    public void ativaPesquisa(String codigo){
        this.controllerPesquisa.ativaPesquisa(codigo);
    }

    public String exibePesquisa(String codigo){
        return this.controllerPesquisa.exibePesquisa(codigo);
    }

    public boolean pesquisaEhAtiva(String codigo){
        return this.controllerPesquisa.pesquisaEhAtiva(codigo);
    }
}
